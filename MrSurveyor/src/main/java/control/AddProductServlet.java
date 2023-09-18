package control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.google.gson.Gson;

import model.bean.Category;
import model.bean.Manager;
import model.bean.Product;
import model.bean.Subcategory;
import model.dao.CatalogDAO;
import model.dao.CatalogDAOImp;

@WebServlet("/AddProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
maxFileSize = 1024 * 1024 * 10,
maxRequestSize = 1024 * 1024 * 50)
public class AddProductServlet extends HttpServlet {
	
	private static Logger logger = Logger.getLogger("MyLog");
	
	private static final String UPLOAD_DIRECTORY = File.separator+"images"+File.separator+"prod"+File.separator;
	
	private static final long serialVersionUID = 1L;
	
	private static String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for(String s : items) {
			if(s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=")+2, s.length()-1);
			}
		}
		return "";
	}
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		CatalogDAO catalogDAO = new CatalogDAOImp(ds);
		
		String parameter = request.getParameter("param");
		if(parameter != null && parameter.equals("getCategories")) {
			
			Collection<Category> categories = null;
			try {
				categories = catalogDAO.retrieveAllCategories();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("add_product_form.jsp").forward(request, response);
			
		} else if(parameter != null && parameter.equals("getSubcategories")) {
			
			String categoryName = request.getParameter("categoryName");
			
			Collection<Subcategory> subcategories = null;
			
			try {
				subcategories = catalogDAO.retrieveSubcategoriesByCategory(categoryName);
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			String jsonSubcategories = new Gson().toJson(subcategories);

		    response.setContentType("application/json");

		    response.getWriter().write(jsonSubcategories);
			
		} else {
			// Ho tutti i dati e posso aggiungere il prodotto
			String contentType = request.getHeader("Content-Type");
			
			String productName = request.getParameter("product_name");
			String productPrice = request.getParameter("product_price");
			String productQuantity = request.getParameter("product_quantity");
			String productDescription = request.getParameter("product_description");
			String productCategory = request.getParameter("product_category");
			String[] productSubcategories = request.getParameterValues("product_subcategory");
			
			int[] productSubcategoriesValues = null;
			if(productSubcategories != null) {
				productSubcategoriesValues = new int[productSubcategories.length];
				
				for(int i = 0; i < productSubcategoriesValues.length; i++)
					try {
						productSubcategoriesValues[i] = Integer.parseInt(productSubcategories[i]);
					} catch(NumberFormatException e) {
						e.printStackTrace();
					}
				
			}
				
			
			if(contentType == null || (!contentType.startsWith("multipart/form-data") && !contentType.startsWith("multipart/mixed"))) {
				response.sendRedirect(getServletContext().getContextPath()+"/add_product_form.jsp");
				
			} else {
				
				double price = 0;
				int quantity = 0;
				String imagePath = "";
				
				if(productPrice != null && productQuantity != null)
					try {
						price = Double.parseDouble(productPrice);
						quantity = Integer.parseInt(productQuantity);
					} catch(NumberFormatException e) {
						e.printStackTrace();
					}
				
				String appPath = request.getServletContext().getRealPath("/");
				String uploadPath = appPath + UPLOAD_DIRECTORY;
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdir();
				}

				for (Part part : request.getParts()) {
					String fileName = extractFileName(part);
					if (fileName != null && !fileName.trim().equals("")) {
						part.write(uploadPath + File.separator + fileName);
						logger.info(uploadPath + File.separator + fileName);
						imagePath = fileName;
					}
				}
				
				Product product = new Product();
				
				product.setName(productName);
				product.setImagePath(imagePath);
				product.setPrice(price);
				product.setQuantity(quantity);
				product.setDescription(productDescription);
				
				Manager catalogManager = (Manager) request.getSession().getAttribute("manager");
				product.setCatalogManager(catalogManager);
				
				try {
					int id = catalogDAO.saveProduct(product);
					catalogDAO.addCategory(id, productCategory);
					catalogDAO.addSubcategories(id, productSubcategoriesValues);
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
				response.sendRedirect(getServletContext().getContextPath()+"/homepage_manager.jsp");
			}
		}
	}
}
