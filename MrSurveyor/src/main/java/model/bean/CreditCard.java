package model.bean;

import java.util.Date;

public class CreditCard implements Cloneable {
	
	public CreditCard() {
		
	}
		
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
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

	public boolean isValidCreditCard() throws IllegalArgumentException {
	    Date today = new Date();

	    if (expirationDate.before(today)) {
	        throw new IllegalArgumentException("La carta di credito è scaduta.");
	    }

	    String trimmedCardNumber = cardNumber.trim();
	    if (trimmedCardNumber.length() != 16) {
	        throw new IllegalArgumentException("Il numero della carta di credito deve contenere esattamente 16 cifre.");
	    }

	    String trimmedCvc = cvc.trim();
	    if (trimmedCvc.length() != 3) {
	        throw new IllegalArgumentException("Il CVC deve contenere esattamente 3 cifre.");
	    }

	    return true;
	}

	
	public String toString() {
		return getClass().getName()+"[cardNumber="+cardNumber+",cvc="+cvc+
				",exipirationDate="+expirationDate+",holderName="+holderName+"]";
	}
	
    public CreditCard clone() {
        try {
            CreditCard clonedCard = (CreditCard) super.clone();

            if (this.expirationDate != null) {
                clonedCard.expirationDate = (Date) this.expirationDate.clone();
            }

            return clonedCard;
        } catch (CloneNotSupportedException e) {
     
            e.printStackTrace();
            return null;
        }
    }
	
	private String cardNumber, cvc;
	private Date expirationDate;
	private String holderName;
}
