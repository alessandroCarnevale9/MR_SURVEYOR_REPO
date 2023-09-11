package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.google.gson.Gson;

import model.bean.Cart;
import model.bean.CartProduct;
import model.bean.Product;
import model.dao.CartDAO;
import model.dao.CartDAOImp;
import model.dao.CatalogDAO;
import model.dao.CatalogDAOImp;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		CatalogDAO catalogDAO = new CatalogDAOImp(ds);
		CartDAO cartDAO = new CartDAOImp(ds);
		
		HttpSession endUserSession = request.getSession();

		Cart userCart = (Cart)endUserSession.getAttribute("userCart");
		
		if(userCart == null) {
			
			Cookie[] cookies = request.getCookies();
			Gson gson = new Gson();
			
			boolean exists = false;
			if(cookies != null) {
				for(Cookie c : cookies) {
					if(c.getName().equals("0")) {
						userCart = gson.fromJson(c.getValue(), Cart.class);
						if(userCart != null)
							exists = true;
					}
				}
			}
			
			if(!exists)
				userCart = new Cart();
				
			Cookie cookie = new Cookie("0", gson.toJson(userCart));
			cookie.setMaxAge(7*24*60*60);
			response.addCookie(cookie);
				
			
			endUserSession.setAttribute("userCart", userCart);
		}
		
		
		if(userCart != null) {
			int productID = 0;
			int quantity = 0;
			
			String removeID = request.getParameter("removeID");
			String setQuantityID = request.getParameter("quantityID");
			String cartQuantity = request.getParameter("cart_quantity");
			
			if(cartQuantity != null && setQuantityID != null) {
				try {
					productID = Integer.parseInt(setQuantityID);
					quantity = Integer.parseInt(cartQuantity);
					
					if(productID > 0 && quantity > 0) {
						Product product = catalogDAO.retrieveProductById(productID);
						
						CartProduct cartProduct = new CartProduct(product.getQuantity());
						
						cartProduct.setId(product.getId());
						cartProduct.setImagePath(product.getImagePath());
						cartProduct.setName(product.getName());
						cartProduct.setPrice(product.getPrice());
						
						userCart.setQuantity(cartProduct, quantity);
					}
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
					
					if(productID > 0) {
						Product product = catalogDAO.retrieveProductById(productID);
						
						CartProduct cartProduct = new CartProduct(product.getQuantity());
						
						cartProduct.setId(product.getId());
						cartProduct.setImagePath(product.getImagePath());
						cartProduct.setName(product.getName());
						cartProduct.setPrice(product.getPrice());
						
						
						userCart.removeProduct(cartProduct);
					}
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
				
				if(productID > 0 && quantity > 0) {
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
			}
			
			endUserSession.removeAttribute("userCart");
			endUserSession.setAttribute("userCart", userCart);
			
			
			long valueEnduserId = 0;
			if(endUserSession.getAttribute("userID") != null) {
				valueEnduserId = (long)endUserSession.getAttribute("userID");
				
				try {	
					cartDAO.deleteProducts((int)valueEnduserId);
					cartDAO.addProducts((Cart)endUserSession.getAttribute("userCart"), (int)valueEnduserId);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				
				Gson gson = new Gson();
				Cookie cookie = new Cookie("0", gson.toJson(endUserSession.getAttribute("userCart")));
				cookie.setMaxAge(7*24*60*60);
				response.addCookie(cookie);
			}

		}
				
		response.sendRedirect(response.encodeURL(getServletContext().getContextPath()+"/cart_view.jsp"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
