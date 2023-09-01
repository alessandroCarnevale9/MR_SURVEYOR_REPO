package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.bean.Cart;
import model.dao.CatalogDAO;
import model.dao.CatalogDAOImp;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		CatalogDAO catalogDAO = new CatalogDAOImp(ds);
		
		HttpSession endUserSession = request.getSession();
		
		Cart userCart = (Cart)endUserSession.getAttribute("userCart");
		
		if(userCart == null) {
			userCart = new Cart();
			endUserSession.setAttribute("userCart", userCart);
		}
		
		String paramID = request.getParameter("productID");
		String paramQuantity = request.getParameter("quantity");
		
		int productID = 0;
		int quantity = 0;
		try {
			if(paramID != null)
				productID = Integer.parseInt(paramID);
			
			if(paramQuantity != null)
				quantity = Integer.parseInt(paramQuantity);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			userCart.addProduct(catalogDAO.retrieveProductById(productID), quantity);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		endUserSession.removeAttribute("userCart");
		endUserSession.setAttribute("userCart", userCart);
		
		request.getRequestDispatcher("/cart_view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
