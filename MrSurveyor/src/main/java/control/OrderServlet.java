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
import model.bean.Order;
import model.bean.RegisteredEndUser;
import model.dao.CartDAO;
import model.dao.CartDAOImp;
import model.dao.OrderDAO;
import model.dao.OrderDAOImp;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		RegisteredEndUser enduser = (RegisteredEndUser)session.getAttribute("registeredEndUser");
		Order order = (Order)session.getAttribute("order");
		
		if(enduser == null || order == null) {
			response.sendRedirect(getServletContext().getContextPath()+"/order_details.jsp");
			return;
		}
		
		if(!enduser.getAddress().isValidAddress()) {
			response.sendRedirect(getServletContext().getContextPath()+"/ManageAddressServlet?checkout");
			return;
		}
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		OrderDAO orderDAO = new OrderDAOImp(ds);
		CartDAO cartDAO = new CartDAOImp(ds);
		
		String cmd = request.getParameter("cmd");
		switch(cmd) {
		
			case "addOrder": {
				try {
					order.setId(orderDAO.addEndUserOrder(order, enduser)); // salvo l'ordine
					orderDAO.addOrderProducts(order); // salva i prodotti in order_product
					cartDAO.deleteProducts((int)enduser.getId()); // svuoto il carrello
					session.removeAttribute("userCart");
					session.setAttribute("userCart", new Cart());
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				
				request.getRequestDispatcher("orders_view_enduser.jsp").forward(request, response);
			}
			break;
			
			case "showProducts": {
				// mostra i prodotti in order_product
			}
			break;
			
			default:
				response.sendRedirect(getServletContext().getContextPath()+"/homepage.jsp");
		}
	}
}
