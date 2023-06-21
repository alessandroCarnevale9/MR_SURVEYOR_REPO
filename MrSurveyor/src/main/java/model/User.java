package model;

public abstract class User implements Cloneable {
	
	public abstract String toString();
	public abstract boolean equals(Object otherObject);
	
	public User clone() {
		try {
			return (User)super.clone();
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
