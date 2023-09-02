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
import model.bean.CartProduct;
import model.bean.Product;
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
		
		int productID = 0;
		int quantity = 0;
		
		if(userCart == null) {
			userCart = new Cart();
			endUserSession.setAttribute("userCart", userCart);
		}
		
		String removeID = request.getParameter("removeID");
		String setQuantityID = request.getParameter("quantityID");
		String cartQuantity = request.getParameter("cart_quantity");
		
		if(cartQuantity != null && setQuantityID != null) {
			try {
				productID = Integer.parseInt(setQuantityID);
				quantity = Integer.parseInt(cartQuantity);
				
				Product product = catalogDAO.retrieveProductById(productID);
				
				CartProduct cartProduct = new CartProduct(product.getQuantity());
				
				cartProduct.setId(product.getId());
				cartProduct.setImagePath(product.getImagePath());
				cartProduct.setName(product.getName());
				cartProduct.setPrice(product.getPrice());
				
				userCart.setQuantity(cartProduct, quantity);
			}
			catch(NumberFormatException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			catch (IllegalArgumentException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/cart_view.jsp").forward(request, response);
				return;
			}
		}
		else if(removeID != null) {
			try {
				productID = Integer.parseInt(removeID);
				
				Product product = catalogDAO.retrieveProductById(productID);
				
				CartProduct cartProduct = new CartProduct(product.getQuantity());
				
				cartProduct.setId(product.getId());
				cartProduct.setImagePath(product.getImagePath());
				cartProduct.setName(product.getName());
				cartProduct.setPrice(product.getPrice());
				
				
				userCart.removeProduct(cartProduct);
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			String paramID = request.getParameter("productID");
			String paramQuantity = request.getParameter("quantity");
			
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
				Product product = catalogDAO.retrieveProductById(productID);
				
				CartProduct cartProduct = new CartProduct(product.getQuantity());
				
				cartProduct.setId(product.getId());
				cartProduct.setImagePath(product.getImagePath());
				cartProduct.setName(product.getName());
				cartProduct.setPrice(product.getPrice());
				
				userCart.addProduct(cartProduct, quantity);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (IllegalArgumentException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/cart_view.jsp").forward(request, response);
				return;
			}
		}
		
		endUserSession.removeAttribute("userCart");
		endUserSession.setAttribute("userCart", userCart);
		
		response.sendRedirect(getServletContext().getContextPath()+"/cart_view.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
