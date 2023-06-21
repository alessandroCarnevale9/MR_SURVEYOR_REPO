package model;

import java.util.Date;

public class Order implements Cloneable {
	
	public Order() {
		this.card = new CreditCard();
	}
	
	public Order(long id, Date orderDate, double totalPrice, Date shipmentDate, String trackingNumber, String courierName, State state, CreditCard card) {
		this.id = id;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.shipmentDate = shipmentDate;
		this.trackingNumber = trackingNumber;
		this.courierName = courierName;
		this.state = state;
		this.card = card.clone();
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
				state.equals(other.state) && card.equals(other.card);
	}
	
	public Order clone() {
		
		try {
			return (Order)super.clone();
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private long id;
	private Date orderDate, shipmentDate;
	private double totalPrice;
	private String trackingNumber, courierName;
	private State state;
	private CreditCard card;
	
	public enum State {
		TO_SEND,
		SENT
	}
}
