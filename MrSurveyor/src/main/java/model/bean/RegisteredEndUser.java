package model.bean;

public class RegisteredEndUser extends EndUser {
	
	
	private static final long serialVersionUID = 1L;
	
	public RegisteredEndUser() {
		super();
	}
	
	public RegisteredEndUser(long id, String name, String surname, String email, String password, CreditCard card) {
		super(id, name, surname, email, password);
		this.card = card;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}

	public boolean equals(Object otherObject) {
		if(!super.equals(otherObject))
			return false;
		
		RegisteredEndUser other = (RegisteredEndUser)otherObject;
		
		return address.equals(other.address) && card.equals(other.card);
	}
	
	
	private Address address;
	private CreditCard card;
}
