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

import model.bean.Product;
import model.dao.CatalogDAO;
import model.dao.CatalogDAOImp;

@WebServlet("/CatalogManagerServlet")
public class CatalogManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		CatalogDAO catalogDAO = new CatalogDAOImp(ds);
		
		String cmd = request.getParameter("cmd");
		
		switch(cmd) {
		
			case "showProducts": {
				
				Collection<Product> products = null;
				
				String ordCriteria = request.getParameter("ord");
				
				try {
					products = catalogDAO.retrieveAllProducts(ordCriteria);
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
				request.setAttribute("selected", ordCriteria);
				request.setAttribute("catalogProducts", products);
				request.getRequestDispatcher("homepage_manager.jsp").forward(request, response);
			}
			break;
			
			case "removeProduct": {
				
				String productID = request.getParameter("id");
				
				if(productID != null && !productID.trim().equals("")) {
					int id = 0;
					try {
						id = Integer.parseInt(productID);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					
					try {
						catalogDAO.removeProduct(id);
					} catch(SQLException e) {
						e.printStackTrace();
					}
				}
				
				response.sendRedirect(getServletContext().getContextPath()+"/homepage_manager.jsp");
			}
			break;
			
			default:
				response.sendRedirect(getServletContext().getContextPath()+"/homepage.jsp");
		}
	}
}
