package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.EndUserDAO;
import dao.EndUserDAOImp;
import model.Address;
import model.RegisteredEndUser;


@WebServlet("/LoginEndUserServlet")
public class LoginEndUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		EndUserDAO endUserDAO = new EndUserDAOImp(ds);
	
		String endUserName = request.getParameter("name");
		String endUserSurname = request.getParameter("surname");
		String endUserEmail = request.getParameter("email");
		String endUserPassword = request.getParameter("password");
		String endUserConfirmPassword = request.getParameter("confirm_password");
		
		if(endUserName == null && endUserSurname == null && endUserEmail == null
				&& endUserPassword == null && endUserConfirmPassword == null) {
			
			request.setAttribute("error", "Compila tutti i campi");
			
			response.sendRedirect(getServletContext().getContextPath()+"/registration_enduser.jsp");
		}
		
		if(endUserPassword != null && endUserConfirmPassword != null && 
				!endUserPassword.equals(endUserConfirmPassword)) {
			
			request.setAttribute("error", "Password e Conferma password contengono dati differenti");
			
			response.sendRedirect(getServletContext().getContextPath()+"/registration_enduser.jsp");
		}	
		
		RegisteredEndUser registeredEndUser = new RegisteredEndUser();
		
		registeredEndUser.setName(endUserName);
		registeredEndUser.setSurname(endUserSurname);
		registeredEndUser.setEmail(endUserEmail);
		registeredEndUser.setPassword(endUserPassword);
		registeredEndUser.setAddress(new Address()); // empty address
		
		try {
			if(!endUserDAO.exists(registeredEndUser)) { // se lo user non esiste già
				
				endUserDAO.addEndUser(registeredEndUser);
			
				response.sendRedirect(getServletContext().getContextPath()+"/homepage_enduser.jsp");
			}
			else {
				throw new IllegalArgumentException("Utente già esistente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
