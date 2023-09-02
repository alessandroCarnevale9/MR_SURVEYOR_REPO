package model.bean;

public class CartProduct extends Product {

	private static final long serialVersionUID = 1L;
	
	public CartProduct(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	
	public int getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	
	@Override
	public String toString() {
		return super.toString()+"[maxQuantity="+maxQuantity+"]";
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if(!super.equals(otherObject))
			return false;
		
		CartProduct other = (CartProduct)otherObject;
		
		return Integer.compare(maxQuantity, other.maxQuantity) == 0;
	}
	
	@Override
	public CartProduct clone() {
		return (CartProduct)super.clone();
	}

	private int maxQuantity;
}
