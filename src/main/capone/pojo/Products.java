package main.capone.pojo;

import java.io.Serializable;
import java.util.List;

public class Products implements Serializable{
private int productCount;
private List<Product> products;



public List<Product> getProducts() {
	return products;
}

public void setProducts(List<Product> products) {
	this.products = products;
}

public int getProductCount() {
	return productCount;
}

public void setProductCount(int productCount) {
	this.productCount = productCount;
}


}
