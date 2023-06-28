package model.bean;

import java.util.Collection;
import java.util.LinkedList;

public class RegisteredEndUser extends EndUser {
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public RegisteredEndUser() {
		super();
		cards = new LinkedList<>();
	}
	
	public RegisteredEndUser(long id, String name, String surname, String email, String password, Collection<CreditCard> cards) {
		super(id, name, surname, email, password);
		this.cards = cards;
	}
	
	public Collection<CreditCard> getCreditCards() {
		return cards;
	}
	
	public void addCreditCard(CreditCard card) {
		cards.add(card);
	}
	
	public void removeCreditCard(CreditCard card) {
		if(!cards.isEmpty() && (cards.contains(card)))
			cards.remove(card);
	}
	
	public boolean equals(Object otherObject) {
		if(!super.equals(otherObject))
			return false;
		
		RegisteredEndUser other = (RegisteredEndUser)otherObject;
		
		return address.equals(other.address) && cards.equals(other.cards);
	}
	
	public RegisteredEndUser clone() {
		RegisteredEndUser cloned = (RegisteredEndUser)super.clone();
		
		cloned.address = address.clone();
		
		Collection<CreditCard> clonedCards = new LinkedList<>();
		for(CreditCard c : cards)
			clonedCards.add(c.clone());
		
		cloned.cards = clonedCards;
		
		return cloned;
	}
	
	private Address address;
	private Collection<CreditCard> cards;
}
