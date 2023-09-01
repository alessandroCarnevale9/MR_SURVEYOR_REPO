package model.bean;

import java.io.Serializable;

public class Manager implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	public Manager() {
		
	}
	
	public Manager(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String toString() {
		return getClass().getName()+"[username="+username+",password="+password+
				",role="+role+"]";
	}
	
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		if(getClass() != otherObject.getClass())
			return false;
		
		Manager other = (Manager)otherObject;
		
		return username.equals(other.username) && password.equals(other.password) &&
				role.equals(other.role);
	}
	
	public Manager clone() {
		try {
			return (Manager)super.clone();
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String username, password;
	private Role role;
	
	public enum Role {
		ORDER_MANAGER,
		CATALOG_MANAGER
	}
}
