package model.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

public class Cart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Cart() {
		cartProducts = new LinkedList<>();
		cartHolder = new EndUser();
	}
	
	public Cart(Collection<Product> products, EndUser cartHolder) {
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
	
	// Se il prodotto è già presente incremento di 1 la quantità
	public void addProduct(Product product) {
		if(cartProducts.contains(product)) {
			int tmp = product.getQuantity();
			tmp += 1;
			product.setQuantity(tmp);
		}
		else
			cartProducts.add(product);
	}
	
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
	
	private Collection<Product> cartProducts;
	private EndUser cartHolder;
}
