package model;

import java.util.Date;

public class CreditCard {
	
	public CreditCard() {
		
	}
	
	public CreditCard(int cardNumber, Date expirationDate, int cvc, String holderName) {
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.cvc = cvc;
		this.holderName = holderName;
	}
	
	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	private int cardNumber, cvc;
	private Date expirationDate;
	private String holderName;
}
