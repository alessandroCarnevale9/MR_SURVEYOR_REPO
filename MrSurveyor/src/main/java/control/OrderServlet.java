package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.bean.Address;
import model.bean.Cart;
import model.bean.Manager;
import model.bean.Order;
import model.bean.RegisteredEndUser;
import model.dao.CartDAO;
import model.dao.CartDAOImp;
import model.dao.ManagerDAO;
import model.dao.ManagerDAOImp;
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
		Manager manager = (Manager)session.getAttribute("manager");
		String cmd = request.getParameter("cmd");
		
		
		/*
		 * if(!cmd.equals("showAssigned") && enduser == null) {
		 * response.sendRedirect(getServletContext().getContextPath()+
		 * "/order_details.jsp"); return; }
		 * 
		 * if(cmd.equals("showAssigned") && manager == null) {
		 * response.sendRedirect(getServletContext().getContextPath()+
		 * "/authentication_manager.jsp"); return; }
		 */
		/*
		 * if(!cmd.equals("showProducts") && enduser != null &&
		 * !enduser.getAddress().isValidAddress()) {
		 * response.sendRedirect(getServletContext().getContextPath()+
		 * "/ManageAddressServlet?checkout"); return; }
		 * 
		 * if(cmd.equals("showAssigned") && (manager == null ||
		 * !manager.getRole().equals(Role.ORDER_MANAGER))) {
		 * response.sendRedirect(getServletContext().getContextPath()+
		 * "/authentication_manager.jsp"); return; }
		 */
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		OrderDAO orderDAO = new OrderDAOImp(ds);
		CartDAO cartDAO = new CartDAOImp(ds);
		ManagerDAO managerDAO = new ManagerDAOImp(ds);
		
		switch(cmd) {
		
			case "addOrder": {
				
				if(enduser == null) {
					response.sendRedirect(getServletContext().getContextPath()+"/order_details.jsp");
					return;
				}
				
				try {
					
					Address address = enduser.getAddress();
					String orderAddress = address.getStreet()+" "+address.getHouseNumber()+"\n"
							+ address.getCap()+"\n"+address.getRegion()+", "+address.getProvince()+"\n"+
							address.getCity();
					order.setOrderAddress(orderAddress);
					
					order.setId(orderDAO.addEndUserOrder(order, enduser)); // salvo l'ordine
					
					orderDAO.addOrderProducts(order); // salva i prodotti in order_product
					cartDAO.deleteProducts((int)enduser.getId()); // svuoto il carrello
					session.removeAttribute("userCart");
					session.setAttribute("userCart", new Cart());
					
					// assegna l'ordine a un gestore dell'ordine
					List<String> orderManagers = managerDAO.getOrderManagerNames();
					
					Random random = new Random();
			        int randomIndex = random.nextInt(orderManagers.size());
					
			        orderDAO.assingOrder(orderManagers.get(randomIndex), (int)order.getId());
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				
				response.sendRedirect(getServletContext().getContextPath()+"/OrderServlet?cmd=showProducts");
			}
			break;
			
			case "showProducts": {
				
				if(enduser == null) {
					response.sendRedirect(getServletContext().getContextPath()+"/order_details.jsp");
					return;
				}
				
				
				Collection<Order> ordersEndUser = new LinkedList<Order>();
				// mostra i prodotti in order_product
				try {
					ordersEndUser = orderDAO.getOrders((int)enduser.getId());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				request.setAttribute("orders", ordersEndUser);
				request.getRequestDispatcher("orders_view_enduser.jsp").forward(request, response);
			}
			break;
			
			case "showAssigned": {
				
				if(manager == null) {
					response.sendRedirect(getServletContext().getContextPath()+"/authentication_manager.jsp");
					return;
				}
				
				Collection<Order> ordersToManage = new LinkedList<Order>();
				
				try {
					ordersToManage = orderDAO.getOrdersToManage(manager.getUsername());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				request.setAttribute("toManage", ordersToManage);
				request.getRequestDispatcher("homepage_manager.jsp").forward(request, response);
			}
			break;
			
			default:
				response.sendRedirect(getServletContext().getContextPath()+"/homepage.jsp");
		}
	}
}
