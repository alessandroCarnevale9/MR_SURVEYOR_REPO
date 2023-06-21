package model;

import java.util.Collection;
import java.util.LinkedList;

public class RegisteredEndUser extends EndUser {
	
	public RegisteredEndUser() {
		super();
		addresses = new LinkedList<>();
		cards = new LinkedList<>();
	}
	
	public RegisteredEndUser(long id, String name, String surname, String email, String password, Collection<Address> addresses, Collection<CreditCard> cards) {
		super(id, name, surname, email, password);
		this.addresses = addresses;
		this.cards = cards;
	}
	
	public Collection<Address> getAddresses() {
		return addresses;
	}
	
	public void addAddress(Address address) {
		if(!addresses.contains(address))
			addresses.add(address);
	}
	
	public void removeAddress(Address address) {
		if(!addresses.isEmpty() && addresses.contains(address))
				addresses.remove(address);
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
		
		return addresses.equals(other.addresses) && cards.equals(other.cards);
	}
	
	private Collection<Address> addresses;
	private Collection<CreditCard> cards;
}
