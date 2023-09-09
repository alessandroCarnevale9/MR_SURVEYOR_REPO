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

@WebServlet("/SwitchRoleServlet")
public class SwitchRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			Manager manager = (Manager)session.getAttribute("manager");
			
			if(manager != null) {
				
				if(request.getParameter("catalog_manager") != null)
					manager.setRole(Role.CATALOG_MANAGER);
				
				else if(request.getParameter("order_manager") != null)
					manager.setRole(Role.ORDER_MANAGER);
				
				DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
				ManagerDAO managerDAO = new ManagerDAOImp(ds);
				
				try {
					if(!managerDAO.checkManager(manager)) {
						request.setAttribute("error", "Utente non autorizzato");
						request.getRequestDispatcher("/authentication_manager.jsp").forward(request, response);
					} else {
						response.sendRedirect(getServletContext().getContextPath()+"/homepage_manager.jsp");
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
