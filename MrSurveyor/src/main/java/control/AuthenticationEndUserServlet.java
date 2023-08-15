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
import model.dao.EndUserDAO;
import model.dao.EndUserDAOImp;


@WebServlet("/AuthenticationEndUserServlet")
public class AuthenticationEndUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		EndUserDAO endUserDAO = new EndUserDAOImp(ds);
		
		String userEmail = (String)request.getParameter("email");
		String userPassword = (String)request.getParameter("password");
		
		if(userEmail == null || userPassword == null || userEmail.trim().equals("")
				|| userPassword.trim().equals("")) {
			
			response.sendRedirect(getServletContext().getContextPath()+"/authentication_enduser.jsp");
		}
		else {
			
			try {
				
				if(!endUserDAO.checkEndUser(userEmail, userPassword)) {
					request.setAttribute("error", "Email o password non correte");
					
					request.getRequestDispatcher("/authentication_enduser.jsp")
					.forward(request, response);
				}
				else {
					
					HttpSession oldSession = request.getSession(false);
					if(oldSession != null)
						oldSession.invalidate(); // invalida la sessione se esiste
					
					HttpSession currentSession = request.getSession(); // crea una nuova sessione
					
					/*
					 * Mi serve mettere l'email e la password nella sessione ?
					 */
					currentSession.setAttribute("userEmail", userEmail);
					currentSession.setAttribute("userPassword", userPassword);
					currentSession.setAttribute("userCart", new Cart());
					
					currentSession.setMaxInactiveInterval(5*60); // 5 minuti di inattività massima, dopo cancella la sessione
				
					response.sendRedirect(getServletContext().getContextPath()+"/homepage_enduser.jsp");
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
