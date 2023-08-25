package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.bean.Category;
import model.bean.Product;
import model.dao.CatalogDAO;
import model.dao.CatalogDAOImp;

@WebServlet("/CatalogServlet")
public class CatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		CatalogDAO catalogDAO = new CatalogDAOImp(ds);
		
		String page = "/categories_enduser.jsp";
		
		if(request.getParameter("category") != null) {
			
			page = "/products_enduser.jsp";
			
			Collection<Product> products = null;
			
			try {
				products = catalogDAO.retrieveProductsByCategory(request.getParameter("category"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(products != null && !products.isEmpty())
				request.setAttribute("products", products);
			else
				request.setAttribute("error", "Nessun prodotto da mostrare per la categoria "+request.getParameter("category"));
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
