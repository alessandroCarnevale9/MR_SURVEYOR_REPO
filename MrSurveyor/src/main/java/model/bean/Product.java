package model.bean;

import java.util.Collection;
import java.util.LinkedList;

public class Product implements Cloneable {
	
	public Product() {
		rootCategory = new Category();
		subcategories = new LinkedList<>();
		catalogManager = new Manager(); // Manager che gestisce il prodotto
	}
	
	public Product(long id, String imagePath, String name, String description, double price, int quantity, Category rootCategory, Collection<Subcategory> subcategories, Manager cataloManager) {
		
		this.id = id;
		this.imagePath = imagePath;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.rootCategory = rootCategory;
		this.subcategories = subcategories;
		this.catalogManager = cataloManager;
	}
	
	public Category getProductRootCategory() {
		return rootCategory;
	}
	
	public void setProductRootCategory(Category rootCategory) {
		this.rootCategory = rootCategory;
	}
	
	// aggiungo una sottocategoria soltanto se essa è coerente con la categoria padre
	public void addSubcategory(Subcategory subcategory) {
		if(subcategory.getRootCategory().equals(rootCategory))
			subcategories.add(subcategory);
	}
	
	public void removeSubcategory(Subcategory subcategory) {
		if(!subcategories.isEmpty() && subcategories.contains(subcategory))
			subcategories.remove(subcategory);
	}
	
	public Collection<Subcategory> getSubcategories() {
		return subcategories;
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
				",name="+name+",price="+price+",quantity="+quantity+",rootCategory"+
				rootCategory+",description="+description+"]";
	}
	
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		
		if(getClass() != otherObject.getClass())
			return false;
		
		Product other = (Product)otherObject;
		
		return Long.compare(id, other.id) == 0 && imagePath.equals(other.imagePath) &&
				name.equals(other.name) && description.equals(other.description) && rootCategory.equals(other.rootCategory) &&
				Integer.compare(quantity, other.quantity) == 0 && subcategories.equals(other.subcategories);
	}
	
	public Product clone() {
		
		try {
			Product cloned = (Product)super.clone();
			
			cloned.rootCategory = rootCategory.clone();
			cloned.catalogManager = catalogManager.clone();
			
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
	
	private Category rootCategory;
	private Collection<Subcategory> subcategories;
	private Manager catalogManager;
}
