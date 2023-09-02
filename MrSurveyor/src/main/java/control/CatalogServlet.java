package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.bean.Category;
import model.bean.Product;
import model.bean.Subcategory;
import model.dao.CatalogDAO;
import model.dao.CatalogDAOImp;

@WebServlet("/CatalogServlet")
public class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		CatalogDAO catalogDAO = new CatalogDAOImp(ds);
		
		HttpSession session = request.getSession();
		
		String page = "/categories_enduser.jsp";
		
		String category = request.getParameter("category");
		String subcategory = request.getParameter("subcategory");
		String detailProductID = request.getParameter("detailProductID");
		
		if(request.getParameter("homepage") != null) {
			page = "/homepage.jsp";
			
			Collection<Collection<Product>> productsCollection = new LinkedList<Collection<Product>>();
			
			try {
				
				Collection<Product> collection1 = catalogDAO.retrieveProductsByCategory("Termocamere FLIR");
				Collection<Product> collection2 = catalogDAO.retrieveProductsByCategory("Misuratori laser Leica DISTO");
				Collection<Product> collection3 = catalogDAO.retrieveProductsByCategory("Strumenti Topografici");
				
				for(Product p : collection1) {
					p.addCategory(new Category("Termocamere FLIR"));
					p.addSubcategory(new Subcategory(catalogDAO.retrieveSubcategoryByCategoryName(p.getId(), "Termocamere FLIR")));
				}
				
				for(Product p : collection2) {
					p.addCategory(new Category("Misuratori laser Leica DISTO"));
					p.addSubcategory(new Subcategory(catalogDAO.retrieveSubcategoryByCategoryName(p.getId(), "Misuratori laser Leica DISTO")));
				}
				
				for(Product p : collection3) {
					p.addCategory(new Category("Strumenti Topografici"));
					p.addSubcategory(new Subcategory(catalogDAO.retrieveSubcategoryByCategoryName(p.getId(), "Strumenti Topografici")));
				}
				
				productsCollection.add(collection1);
				productsCollection.add(collection2);
				productsCollection.add(collection3);

				
				request.setAttribute("homepage_products", productsCollection);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(category != null) {
			
			page = "/products_enduser.jsp";
			
			Collection<Product> products = null;
			
			try {
				
				products = catalogDAO.retrieveProductsByCategory(category);
				for(Product p : products) {
					p.addSubcategory(new Subcategory(catalogDAO.retrieveSubcategoryByCategoryName(p.getId(), category)));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(products != null && !products.isEmpty())
				request.setAttribute("products", products);
			else
				request.setAttribute("error", "Nessun prodotto da mostrare per la categoria "+category);
		}
		
		else if(subcategory != null && !subcategory.trim().equals("")) {
			
			category = (String)session.getAttribute("category");
			
			page = "/products_enduser.jsp";
			
			Collection<Product> products = null;
			
			try {
				
				products = catalogDAO.retrieveProductsBySubcategory(subcategory, category);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(products != null && !products.isEmpty())
				request.setAttribute("products", products);
			else
				request.setAttribute("error", "Nessun prodotto da mostrare per la sottocategoria "+subcategory);
		}
		
		else if(detailProductID != null) {
			
			try {
				
				int productID = Integer.parseInt(detailProductID);
				Product product = catalogDAO.retrieveProductById(productID);
				Subcategory subcategoryProduct = catalogDAO.retrieveFirstSubcategory(productID);
				
				if(product != null && subcategoryProduct.getName() != null) {
					product.addSubcategory(subcategoryProduct);
					request.setAttribute("showProduct", product);
				}
				else if(subcategoryProduct.getName() == null) {
					category = (String)session.getAttribute("category");
					product.addCategory(new Category(category));
					request.setAttribute("showProduct", product);
				}
				else
					request.setAttribute("error", "Nessun prodotto corrispondente all'id "+productID);
				
			} catch(NumberFormatException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			page = "/show_details_product.jsp";
		}
		
		Collection<Category> categories = null;
		
		try {
			categories = catalogDAO.retrieveAllCategories();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(categories != null && !categories.isEmpty())
			request.setAttribute("categories", categories);
		else
			request.setAttribute("error", "Nessuna categoria da mostrare");
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
