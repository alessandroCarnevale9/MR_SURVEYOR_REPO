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

import model.bean.Address;
import model.bean.RegisteredEndUser;
import model.dao.EndUserDAO;
import model.dao.EndUserDAOImp;

@WebServlet("/ManageAddressServlet")
public class ManageAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect(getServletContext().getContextPath()+"/authentication_enduser.jsp");
			return;
		}
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		EndUserDAO endUserDAO = new EndUserDAOImp(ds);
		
		String region = request.getParameter("region");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String houseNumber = request.getParameter("houseNumber");
		String cap = request.getParameter("cap");
		
		int houseNumberValue = 0;
		try {
			houseNumberValue = Integer.parseInt(houseNumber);
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		String page = "";
		
		Address address = new Address(region, province, city, street, houseNumberValue, cap);
		
		if(!address.isValidAddress()) {
			request.setAttribute("error", "Indirizzo non valido");
			page = "/address_form.jsp";
		}
		else {
			
			long enduserId = 0;
			if(session.getAttribute("userID") != null)
				enduserId = (long)session.getAttribute("userID");
			else {
				response.sendRedirect(getServletContext().getContextPath()+"/authentication_enduser.jsp");
				return;
			}
			
			try {
				endUserDAO.setAddress((int)enduserId, address);
				RegisteredEndUser endUser = (RegisteredEndUser)session.getAttribute("registeredEndUser");
				endUser.setAddress(address);
				session.removeAttribute("registeredEndUser");
				session.setAttribute("registeredEndUser", endUser);
				page = "/address_view.jsp";
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}
}
