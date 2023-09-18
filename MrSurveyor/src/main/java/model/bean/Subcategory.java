package model.bean;

import java.io.Serializable;

public class Subcategory implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Subcategory() {
		rootCategory = new Category();
	}
	
	public Subcategory(String name) {
		this.name = name;
	}
	
	public Subcategory(int id, Category rootCategory, String name, String description) {
		this.id = id;
		this.rootCategory = rootCategory;
		this.name = name;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getRootCategory() {
		return rootCategory;
	}

	public void setRootCategory(Category rootCategory) {
		this.rootCategory = rootCategory;
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
		return getClass().getName()+"[id="+id+",rootCategory="+rootCategory.toString()+
				",name="+name+",description"+description+"]";
	}
	
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		
		if(getClass() != otherObject.getClass())
			return false;
		
		Subcategory other = (Subcategory)otherObject;
		
		return id == other.id && rootCategory.equals(other.rootCategory) && name.equals(other.name) &&
				description.equals(other.description);
	}
	
	public Subcategory clone() {
		
		try {
			return (Subcategory)super.clone();
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private int id;
	private Category rootCategory;
	private String name, description;
}
