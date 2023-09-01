package model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Cart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Cart() {
		cartProducts = new ArrayList<Product>();
		cartHolder = new EndUser();
	}
	
	public Cart(ArrayList<Product> products, EndUser cartHolder) {
		this.cartProducts = products;
		this.cartHolder = cartHolder;
	}
	
	public EndUser getCartHolder() {
		return this.cartHolder;
	}
	
	public void setCartHolder(EndUser cartHolder) {
		this.cartHolder = cartHolder;
	}
	
	public Collection<Product> getProducts() {
		return cartProducts;
	}
	
	
	public void addProduct(Product product, int quantity) {
		
		if(product != null && quantity > 0) {
			if(!cartProducts.contains(product)) {
				product.setQuantity(quantity);
				cartProducts.add(product);
			}
			else {
				
				int currentQuantity = cartProducts.get(cartProducts.indexOf(product)).getQuantity();
				currentQuantity += quantity;
				cartProducts.get(cartProducts.indexOf(product)).setQuantity(currentQuantity);
			}
		}
	}
	
	/* 
	public void addProduct(Product product) {
		if(cartProducts.contains(product)) {
			int tmp = product.getQuantity();
			tmp += 1;
			product.setQuantity(tmp);
		}
		else
			cartProducts.add(product);
	}
	*/
	
	public void removeProduct(Product product) {
		if(!cartProducts.isEmpty() && cartProducts.contains(product)) {
			if(product.getQuantity() > 1) {
				int tmp = product.getQuantity();
				tmp -= 1;
				product.setQuantity(tmp);
			}
			else
				cartProducts.remove(product);
		}	
	}
	
	public void reomoveAllProducts() {
		cartProducts.removeAll(cartProducts);
	}
	
	private ArrayList<Product> cartProducts;
	private EndUser cartHolder;
}
