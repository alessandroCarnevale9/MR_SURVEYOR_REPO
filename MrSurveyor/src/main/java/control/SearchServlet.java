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
import utils.Utlis;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchParam = request.getParameter("search");
		String isManualSubmit = request.getParameter("isManualSubmit");
		
		if(searchParam != null && !searchParam.trim().equals("")) {
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			CatalogDAO catalogDAO = new CatalogDAOImp(ds);
			
			Collection<Product> products = null;
			String resultHtml = "";
			
			try {
				products = catalogDAO.searchProducts(searchParam);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			if(isManualSubmit != null) {
				request.setAttribute("searchedProducts", products);
				request.getRequestDispatcher("/show_products_list.jsp").forward(request, response);
			}
			else {
				resultHtml = Utlis.generateResultHtml(products);
				
				response.setContentType("text/html");
			    response.getWriter().write(resultHtml);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
