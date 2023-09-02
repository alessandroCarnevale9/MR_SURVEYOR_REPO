package model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Product implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
	public Product() {
		categories = new LinkedList<>();
		subcategories = new LinkedList<>();
		catalogManager = new Manager(); // Manager che gestisce il prodotto
	}
	
	public Product(long id, String imagePath, String name, String description, double price, int quantity, Collection<Category> categories, Collection<Subcategory> subcategories, Manager cataloManager) {
		
		this.id = id;
		this.imagePath = imagePath;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.categories = categories;
		this.subcategories = subcategories;
		this.catalogManager = cataloManager;
	}
	
	public void addCategory(Category category) {
		if(category != null)
			categories.add(category);
	}
	
	public void addSubcategory(Subcategory subcategory) {
		if(subcategory != null)
			subcategories.add(subcategory);
	}
	
	public void removeCategory(Category category) {
		if(!categories.isEmpty() && categories.contains(category))
			categories.remove(category);
	}
	
	public void removeSubcategory(Subcategory subcategory) {
		if(!subcategories.isEmpty() && subcategories.contains(subcategory))
			subcategories.remove(subcategory);
	}
	
	public ArrayList<Category> getCategories() {
		return new ArrayList<Category>(categories);
	}
	
	public ArrayList<Subcategory> getSubcategories() {
		return new ArrayList<Subcategory>(subcategories);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Manager getCatalogManager() {
		return catalogManager;
	}

	public void setCatalogManager(Manager catalogManager) {
		this.catalogManager = catalogManager;
	}

	public String toString() {
		return getClass().getName()+"[id="+id+",imagePath="+imagePath+
				",name="+name+",price="+price+",quantity="+quantity+
				",description="+description+"]";
	}
	
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		
		if(getClass() != otherObject.getClass())
			return false;
		
		Product other = (Product)otherObject;
		
		return Long.compare(id, other.id) == 0 && imagePath.equals(other.imagePath) &&
				name.equals(other.name);
	}
	
	public Product clone() {
		
		try {
			Product cloned = (Product)super.clone();
			
			cloned.catalogManager = catalogManager.clone();
			
			Collection<Category> categoriesCloned = new LinkedList<>();
			
			for(Category c : categories)
				categoriesCloned.add(c.clone());
			
			cloned.categories = categoriesCloned;
			
			Collection<Subcategory> subcategoriesCloned = new LinkedList<>();
			
			for(Subcategory s : subcategories)
				subcategoriesCloned.add(s.clone());
			
			cloned.subcategories = subcategoriesCloned;
			
			return cloned;
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private long id;
	private String imagePath, name, description;
	private double price;
	private int quantity;
	
	private Collection<Category> categories;
	private Collection<Subcategory> subcategories;
	private Manager catalogManager;
}
