package model;

import java.util.Date;

public class Order {
	
	public Order() {
		
	}
	
	public Order(long id, Date orderDate, double totalPrice, Date shipmentDate, String trackingNumber, String courierName, State state) {
		this.id = id;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.shipmentDate = shipmentDate;
		this.trackingNumber = trackingNumber;
		this.courierName = courierName;
		this.state = state;
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


	/*
	 * ...
	 */

	private long id;
	private Date orderDate, shipmentDate;
	private double totalPrice;
	private String trackingNumber, courierName;
	private State state;
	
	public enum State {
		TO_SEND,
		SENT
	}
}
