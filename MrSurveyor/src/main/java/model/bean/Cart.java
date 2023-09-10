package model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Cart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Cart() {
		cartProducts = new ArrayList<CartProduct>();
		cartHolder = new EndUser();
	}
	
	public Cart(ArrayList<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
		
	public EndUser getCartHolder() {
		return this.cartHolder;
	}
	
	public void setCartHolder(EndUser cartHolder) {
		this.cartHolder = cartHolder;
	}
	
	public Collection<CartProduct> getProducts() {
		return cartProducts;
	}
	
	
	public void addProduct(CartProduct product, int quantity) throws IllegalArgumentException {
		
		if(product != null) {
			if(!cartProducts.contains(product)) {
				
				if(quantity > 0 && quantity <= product.getMaxQuantity()) {
					product.setQuantity(quantity);
					cartProducts.add(product);
				}
				else
					throw new IllegalArgumentException("Quantità di prodotto non disponibile ("+quantity+")");
			}
			else {
				
				int currentQuantity = cartProducts.get(cartProducts.indexOf(product)).getQuantity();
				currentQuantity += quantity;
				
				if(currentQuantity > 0 && currentQuantity <= product.getMaxQuantity())
					cartProducts.get(cartProducts.indexOf(product)).setQuantity(currentQuantity);
				else
					throw new IllegalArgumentException("Quantità di prodotto non disponibile ("+currentQuantity+")");
			}
		}
	}
	
	public void removeProduct(CartProduct product) {
		if(!cartProducts.isEmpty() && cartProducts.contains(product)) {
			
			int currentQuantity = cartProducts.get(cartProducts.indexOf(product)).getQuantity();
			currentQuantity -= 1;
			
			if(currentQuantity == 0)
				cartProducts.remove(cartProducts.indexOf(product));
			else
				cartProducts.get(cartProducts.indexOf(product)).setQuantity(currentQuantity);
		}	
	}
	
	public void setQuantity(CartProduct product, int quantity) throws IllegalArgumentException {
		if(!cartProducts.isEmpty() && cartProducts.contains(product)) {
			if(quantity > 0 && quantity <= product.getMaxQuantity()) {
				cartProducts.get(cartProducts.indexOf(product)).setQuantity(quantity);
			}
			else {
				throw new IllegalArgumentException("Quantità di prodotto non disponibile ("+quantity+")");
			}
		}
	}
	
	public boolean isEmpty() {
		return cartProducts.isEmpty();
	}
	
	private ArrayList<CartProduct> cartProducts;
	private EndUser cartHolder;
}
