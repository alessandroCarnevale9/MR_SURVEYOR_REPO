package model;

public class Category implements Cloneable {
	
	public Category() {
		
	}
	
	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return getClass().getName()+"[name="+name+",description="+description+"]";
	}
	
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		
		if(getClass() != otherObject.getClass())
			return false;
		
		Category other = (Category)otherObject;
		
		return name.equals(other.name) && description.equals(other.description);
	}
	
	public Category clone() {
		try {
			return (Category)super.clone();
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String name, description;
}
