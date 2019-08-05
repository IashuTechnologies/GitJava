package bean;

import java.util.ArrayList;

/*
 * Bean class to construct the required JSOn response
 */
public class ResponseBean {
 private String status;
 ArrayList < ProductBean > products = new ArrayList < ProductBean > ();
 public ArrayList<ProductBean> getProducts() {
	return products;
}

public void setProducts(ArrayList<ProductBean> products) {
	this.products = products;
}

public String getStatus() {
  return status;
 }
 public void setStatus(String status) {
  this.status = status;
 }
}