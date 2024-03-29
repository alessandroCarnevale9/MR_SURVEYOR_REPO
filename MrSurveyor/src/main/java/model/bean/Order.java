package model.bean;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class Order {
	
	public Order() {
		this.card = new CreditCard(); // carta con la quale è stato pagato l'ordine
		this.orderProducts = new LinkedList<>(); // prodotti dell'ordine
		this.orderManager = new Manager(); // Manager che gestisce l'ordine
		this.endUser = new RegisteredEndUser(); // Utente che effettua l'ordine
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getCourierName() {
		return courierName;
	}

	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public CreditCard getCreditCard() {
		return this.card.clone();
	}
	
	public void setCreditCard(CreditCard card) {
		this.card = card;
	}
	
	public CreditCard getCard() {
		return card;
	}

	public Manager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(Manager orderManager) {
		this.orderManager = orderManager;
	}

	public RegisteredEndUser getEndUser() {
		return endUser;
	}

	public void setEndUser(RegisteredEndUser endUser) {
		this.endUser = endUser;
	}
	
	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public Collection<Product> getOrderProducts() {
		return orderProducts;
	}

	public void addOrderProduct(Product orderProduct) {
		orderProducts.add(orderProduct);
	}
	
	public void removeOrderProduct(Product orderProduct) {
		if(!orderProducts.isEmpty() && orderProducts.contains(orderProduct)) {
			if(orderProduct.getQuantity() > 1) {
				int tmp = orderProduct.getQuantity();
				tmp -= 1;
				orderProduct.setQuantity(tmp);
			}
			else
				orderProducts.remove(orderProduct);
		}
	}
	
	public void removeAllOrderProducts() {
		orderProducts.removeAll(orderProducts);
	}
	
	public String toString() {
		return getClass().getName()+"[id="+id+",orderDate="+orderDate+
				",shipmentDate="+shipmentDate+",totalPrice="+totalPrice+
				",trackingNumber="+trackingNumber+",courierName="+courierName+
				",state="+state+",card="+card.toString()+"]";
	}
	
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		
		if(getClass() != otherObject.getClass())
			return false;
		
		Order other = (Order)otherObject;
		
		return Long.compare(id, other.id) == 0 && 
				orderDate.equals(other.orderDate) &&
				shipmentDate.equals(other.shipmentDate) &&
				Double.compare(totalPrice, other.totalPrice) == 0 &&
				trackingNumber.equals(other.trackingNumber) &&
				courierName.equals(other.courierName) &&
				state.equals(other.state) && card.equals(other.card) &&
				orderProducts.equals(other.orderProducts) && orderManager.equals(other.orderManager);
	}
	
	private long id;
	private Date orderDate, shipmentDate;
	private double totalPrice;
	private String trackingNumber, courierName, orderAddress;
	private State state;
	private CreditCard card;
	private Collection<Product> orderProducts;
	private Manager orderManager;
	private RegisteredEndUser endUser;
	
	public enum State {
		TO_SEND,
		SENT
	}
}
