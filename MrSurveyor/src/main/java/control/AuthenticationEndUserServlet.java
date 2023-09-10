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
import model.bean.RegisteredEndUser;
import model.dao.CartDAO;
import model.dao.CartDAOImp;
import model.dao.EndUserDAO;
import model.dao.EndUserDAOImp;


@WebServlet("/AuthenticationEndUserServlet")
public class AuthenticationEndUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		CartDAO cartDAO = new CartDAOImp(ds);
		
		Gson gson = new Gson();
		Cookie[] cookies = request.getCookies();
		String productsJson = null;
		
		if(request.getParameter("invalidate") != null) {
			
			HttpSession oldSession = request.getSession(false);
			if(oldSession != null) {
				
				long userID = (long)oldSession.getAttribute("userID");
				try {
					cartDAO.deleteProducts((int)userID); // SVUOTO IL CARRELLO
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if(cookie.getName().equals(oldSession.getAttribute("userID"))) {
							cookie.setMaxAge(0);
							break;
						}
					}
				}
				
				// AGGIORNO IL COOKIE PER LO USER CON IL NUOVO CONTENUTO DEL CARRELLO
				
				Cookie cartCookie = new Cookie(String.valueOf(oldSession.getAttribute("userID")), gson.toJson(oldSession.getAttribute("userCart")));
				cartCookie.setMaxAge(7*24*60*60);
				response.addCookie(cartCookie);
				
				// persistenza carrello...				
				try {
					cartDAO.addProducts((Cart)oldSession.getAttribute("userCart"), (int)userID);
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				oldSession.invalidate();
				response.sendRedirect(response.encodeURL(getServletContext().getContextPath()+"/authentication_enduser.jsp"));
			}
		}
		else {
			
			EndUserDAO endUserDAO = new EndUserDAOImp(ds);
			
			String userEmail = (String)request.getParameter("email");
			String userPassword = (String)request.getParameter("password");
			
			if(userEmail == null || userPassword == null || userEmail.trim().equals("")
					|| userPassword.trim().equals("")) {
				
				response.sendRedirect(response.encodeURL(getServletContext().getContextPath()+"/authentication_enduser.jsp"));
			}
			else {
				try {
					
					if(!endUserDAO.checkEndUser(userEmail, userPassword)) {
						request.setAttribute("error", "Email o password non correte");
						
						request.getRequestDispatcher("/authentication_enduser.jsp")
						.forward(request, response);
					}
					else {
						
						RegisteredEndUser endUser = endUserDAO.getRegisteredEndUser(userEmail);

						long userID = endUser.getId();
						
						HttpSession oldSession = request.getSession(false);
						if(oldSession != null)
							oldSession.invalidate(); // invalida la sessione se esiste
						
						Cart userCart = null;
						
						// PROVA A PRENDERE IL CARRELLO DAL COOKIE SUL CLIENT...
						if(cookies != null) {
							for(Cookie cookie : cookies) {
								if(cookie.getName().equals(String.valueOf(userID))) {
									productsJson = cookie.getValue();
									break;
								}
							}
						}
						
						userCart = gson.fromJson(productsJson, Cart.class);
						
						// SE È NULL VUOL DIRE CHE SUL CLIENT NON VI ERANO COOKIE CONTENENTI IL CARRELLO...
						if(userCart == null) {
							
							// PROVA A VEDERE SE NEL DB CI SONO CART PRODUCTS SALVATI
							// SE VE NE SONO LI SCRIVO NEL COOKIE PER LO USER
							// ALTRIMENTI COSTRUISCO UN CARRELLO VUOTO E SCRIVO QUELLO
							
							userCart = new Cart();
							
							Cart retrievedCart = cartDAO.retrieveCartProducts((int)userID);
							
							if(retrievedCart != null)
								userCart = retrievedCart;
							
							productsJson = gson.toJson(userCart);
							Cookie cartCookie = new Cookie(String.valueOf(userID), productsJson);
							cartCookie.setMaxAge(7*24*60*60);
							response.addCookie(cartCookie);
						}
						
						HttpSession currentSession = request.getSession(); // crea una nuova sessione
						
						currentSession.setAttribute("userID", userID);
						currentSession.setAttribute("userEmail", userEmail);
						currentSession.setAttribute("registeredEndUser", endUser);
						currentSession.setAttribute("userCart", userCart);
						
						currentSession.setMaxInactiveInterval(5*60); // 5 minuti di inattività massima, dopo cancella la sessione
					
						response.sendRedirect(response.encodeURL(getServletContext().getContextPath()+"/homepage_enduser.jsp"));
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
