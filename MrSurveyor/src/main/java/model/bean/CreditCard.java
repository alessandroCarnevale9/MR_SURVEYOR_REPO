package model.bean;

import java.util.Date;

public class CreditCard implements Cloneable {
	
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
	
	public String toString() {
		return getClass().getName()+"[cardNumber="+cardNumber+",cvc="+cvc+
				",exipirationDate="+expirationDate+",holderName="+holderName+"]";
	}
	
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		
		if(getClass() != otherObject.getClass())
			return false;
		
		CreditCard other = (CreditCard)otherObject;
		
		return Integer.compare(cardNumber,other.cardNumber) == 0 && 
				Integer.compare(cvc,other.cvc) == 0 &&
				expirationDate.equals(other.expirationDate) && holderName.equals(other.holderName);
	}
	
	public CreditCard clone() {
		
		try {
			return (CreditCard)super.clone();
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private int cardNumber, cvc;
	private Date expirationDate;
	private String holderName;
}
