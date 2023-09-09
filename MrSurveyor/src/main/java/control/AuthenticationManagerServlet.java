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

import model.bean.Manager;
import model.bean.Manager.Role;
import model.dao.ManagerDAO;
import model.dao.ManagerDAOImp;

@WebServlet("/AuthenticationManagerServlet")
public class AuthenticationManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("invalidate") != null) {
			
			HttpSession oldSession = request.getSession(false);
			if(oldSession != null) {
				oldSession.invalidate();
				response.sendRedirect(getServletContext().getContextPath()+"/authentication_manager.jsp");
			}
		} else {
			DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
			ManagerDAO managerDAO = new ManagerDAOImp(ds);
			
			String managerUsername = request.getParameter("username");
			String managerPassword = request.getParameter("password");
			String managerRole = request.getParameter("role");
			
			
			if(managerUsername == null || managerPassword == null || managerRole == null) {
				response.sendRedirect(getServletContext().getContextPath()+"/authentication_manager.jsp");
			}
			else {
				
				Manager manager = new Manager();
				
				manager.setUsername(managerUsername);
				manager.setPassword(managerPassword);
				
				if(managerRole.equalsIgnoreCase("catalog_manager"))
					manager.setRole(Role.CATALOG_MANAGER);
				
				else if(managerRole.equalsIgnoreCase("order_manager"))
					manager.setRole(Role.ORDER_MANAGER);
				
				try {
					if(!managerDAO.checkManager(manager)) {
						request.setAttribute("error", "Utente non autorizzato");
						request.getRequestDispatcher("/authentication_manager.jsp").forward(request, response);
					}
					else {
						HttpSession oldSession = request.getSession(false);
	                    if(oldSession != null)
	                        oldSession.invalidate(); // invalida la sessione se esiste

	                    HttpSession currentSession = request.getSession(); // crea una nuova sessione
	                    
	                    currentSession.setAttribute("manager", manager);
	                    currentSession.setAttribute("assignedRoles", managerDAO.getAssignedRoles(manager));
	                    
	                    currentSession.setMaxInactiveInterval(5*60);
	                    
	                    response.sendRedirect(getServletContext().getContextPath()+"/homepage_manager.jsp");
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
