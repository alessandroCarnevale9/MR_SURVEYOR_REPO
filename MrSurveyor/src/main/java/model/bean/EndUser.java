package model.bean;

public class EndUser implements Cloneable {
	
	public EndUser() {
	
	}
	
	public EndUser(long id, String name, String surname, String email, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return getClass().getName()+"[id="+id+",name="+name+",surname="+surname+
				",email="+email+",password="+password+"]";
	}
	
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		if(getClass() != otherObject.getClass())
			return false;
		
		EndUser other = (EndUser)otherObject;
		
		return Long.compare(id, other.id) == 0 &&
				name.equals(other.name) && surname.equals(other.surname) &&
				email.equals(other.email) && password.equals(other.password);
	}
	
	public EndUser clone() {
		try {
			return (EndUser)super.clone();
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private long id;
	private String name, surname, email, password;
}
