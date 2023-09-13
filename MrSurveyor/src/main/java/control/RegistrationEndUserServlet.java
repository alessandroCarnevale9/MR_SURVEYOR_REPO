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

import model.bean.Address;
import model.bean.Cart;
import model.bean.RegisteredEndUser;
import model.dao.EndUserDAO;
import model.dao.EndUserDAOImp;
import utils.Utlis;
import utils.Utlis.InputType;

@WebServlet("/RegistrationEndUserServlet")
public class RegistrationEndUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		EndUserDAO endUserDAO = new EndUserDAOImp(ds);
		
		String endUserName = request.getParameter("name");
		String endUserSurname = request.getParameter("surname");
		String endUserEmail = request.getParameter("email");
		String endUserPassword = request.getParameter("password");
		String endUserConfirmPassword = request.getParameter("confirm_password");
		
		if (endUserName == null || endUserName.trim().equals("") || endUserSurname == null
				|| endUserSurname.trim().equals("") || endUserEmail == null || endUserEmail.trim().equals("")
				|| endUserPassword == null || endUserPassword.trim().equals("") || endUserConfirmPassword == null
				|| endUserConfirmPassword.trim().equals("")) {
			
			request.setAttribute("error", "Compila tutti i campi");
		}
		
		else if(!Utlis.validate(endUserName, InputType.NAME)) {
			request.setAttribute("error", "Inserito nome non valido");
		}
		
		else if(!Utlis.validate(endUserSurname, InputType.NAME)) {
			request.setAttribute("error", "Inserito cognome non valido");
		}
		
		else if(!Utlis.validate(endUserEmail, InputType.EMAIL)) {
			request.setAttribute("error", "L"+"\'"+"email inserita non " + "\u00E8 " + "valida");
		}
		
		else if(!Utlis.validate(endUserPassword, InputType.PASSWORD)) {
			request.setAttribute("error", "La password inserita non rispetta i criteri di sicurezza");
		}

		else if (!endUserPassword.equals(endUserConfirmPassword)) {
			request.setAttribute("error", "Password e Conferma password contengono dati differenti");

		} else {
			
			RegisteredEndUser registeredEndUser = new RegisteredEndUser();

			registeredEndUser.setName(endUserName);
			registeredEndUser.setSurname(endUserSurname);
			registeredEndUser.setEmail(endUserEmail);
			registeredEndUser.setPassword(endUserPassword);
			registeredEndUser.setAddress(new Address()); // empty address

			try {
				if (!endUserDAO.exists(registeredEndUser)) { // se lo user non esiste
					
					
					endUserDAO.addEndUser(registeredEndUser);
					
					long enduserID = endUserDAO.getRegisteredEndUser(endUserEmail).getId();
					
					
					HttpSession oldSession = request.getSession(false);
					if(oldSession != null)
						oldSession.invalidate();
					
					
					Cart cartCookie = new Cart();
					
					Cookie[] cookies = request.getCookies();
					Gson gson = new Gson();
					
					if(cookies != null) {
						for(Cookie c : cookies) {
							if(c.getName().equals("0")) {
								cartCookie = gson.fromJson(c.getValue(), Cart.class);
							}
						}
					}
					
					
					HttpSession newSession = request.getSession();
					newSession.setMaxInactiveInterval(5 * 60);
					newSession.setAttribute("userEmail", endUserEmail);
					newSession.setAttribute("userID", enduserID);
					
					registeredEndUser.setId(enduserID);
					
					newSession.setAttribute("registeredEndUser", registeredEndUser);
					
					newSession.setAttribute("userCart", cartCookie);
					
					response.sendRedirect(getServletContext().getContextPath()+"/homepage_enduser.jsp");
					return;

				} else {
					request.setAttribute("error", "Questo indirizzo email "+"\u00E8 "+"gi"+"\u00E0 "+"associato a un account esistente");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(endUserName != null && !endUserName.trim().equals(""))
			request.setAttribute("prevName", endUserName);
		
		if(endUserSurname != null && !endUserSurname.trim().equals(""))
			request.setAttribute("prevSurname", endUserSurname);
		
		if(endUserEmail != null && !endUserEmail.trim().equals(""))
			request.setAttribute("prevEmail", endUserEmail);
		
		if(endUserPassword != null && !endUserPassword.trim().equals(""))
			request.setAttribute("prevPasswd", endUserPassword);
		
		if(endUserConfirmPassword != null && !endUserConfirmPassword.trim().equals(""))
			request.setAttribute("prevConfirmPasswd", endUserConfirmPassword);
		
		request.getRequestDispatcher("/registration_enduser.jsp").forward(request, response);
	}
}
