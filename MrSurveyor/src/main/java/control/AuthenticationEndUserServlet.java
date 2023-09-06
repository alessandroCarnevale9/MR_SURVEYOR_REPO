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
import model.dao.EndUserDAO;
import model.dao.EndUserDAOImp;


@WebServlet("/AuthenticationEndUserServlet")
public class AuthenticationEndUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Gson gson = new Gson();
		Cookie[] cookies = request.getCookies();
		String productsJson = null;
		
		if(request.getParameter("invalidate") != null) {
			
			HttpSession oldSession = request.getSession(false);
			if(oldSession != null) {
				
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if(cookie.getName().equals(oldSession.getAttribute("userID"))) {
							cookie.setMaxAge(0);
							break;
						}
					}
				}
				
				Cookie cartCookie = new Cookie(String.valueOf(oldSession.getAttribute("userID")), gson.toJson(oldSession.getAttribute("userCart")));
				cartCookie.setMaxAge(7*24*60*60);
				response.addCookie(cartCookie);
				
				oldSession.invalidate();
				response.sendRedirect(response.encodeURL(getServletContext().getContextPath()+"/authentication_enduser.jsp"));
			}
		}
		else {
			DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
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
						
						int userID = endUserDAO.getEndUserID(userEmail);
						
						HttpSession oldSession = request.getSession(false);
						if(oldSession != null)
							oldSession.invalidate(); // invalida la sessione se esiste
						
						Cart userCart = null;
						
						if(cookies != null) {
							for(Cookie cookie : cookies) {
								if(cookie.getName().equals(String.valueOf(userID))) {
									productsJson = cookie.getValue();
									break;
								}
							}
						}
						
						userCart = gson.fromJson(productsJson, Cart.class);
						
						if(userCart == null) {
							userCart = new Cart();
							productsJson = gson.toJson(userCart);
							Cookie cartCookie = new Cookie(String.valueOf(userID), productsJson);
							cartCookie.setMaxAge(7*24*60*60);
							response.addCookie(cartCookie);
						}
						
						HttpSession currentSession = request.getSession(); // crea una nuova sessione
						
						currentSession.setAttribute("userID", userID);
						currentSession.setAttribute("userEmail", userEmail);
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
