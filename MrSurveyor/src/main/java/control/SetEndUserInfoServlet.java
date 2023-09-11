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

import model.bean.RegisteredEndUser;
import model.dao.EndUserDAO;
import model.dao.EndUserDAOImp;

@WebServlet("/SetEndUserInfoServlet")
public class SetEndUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		EndUserDAO endUserDAO = new EndUserDAOImp(ds);
		
		HttpSession session = request.getSession();
		RegisteredEndUser currentEndUser = (RegisteredEndUser)session.getAttribute("registeredEndUser");
		
		String newName = request.getParameter("name");
		String newSurname = request.getParameter("surname");
		String newPassword = request.getParameter("password");
		String newConfirmPassword = request.getParameter("confirm_password");
		
		if(currentEndUser == null || newName == null || newName.trim().equals("") ||
				newSurname == null || newSurname.trim().equals("") || newPassword == null || newPassword.trim().equals("") ||
				newConfirmPassword == null || newConfirmPassword.trim().equals("")) {
			
			request.setAttribute("error", "Compila tutti i campi");
		
		} else if(!newPassword.equals(newConfirmPassword)) {
			request.setAttribute("error", "Le password non corrispondono");
		}
		
		else {
			currentEndUser.setName(newName);
			currentEndUser.setSurname(newSurname);
			currentEndUser.setPassword(newPassword);
			
			session.removeAttribute("registeredEndUser");
			session.setAttribute("registeredEndUser", currentEndUser);
			
			try {
				endUserDAO.setEndUser(currentEndUser);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("success", "Modifiche avvenute con successo");
		}
		
		request.getRequestDispatcher("/end_user_account_details.jsp").forward(request, response);
	}
}
