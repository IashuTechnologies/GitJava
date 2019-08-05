package Client;

import java.util.ArrayList;

public class Codebautify {
 private String status;
 ArrayList < Object > products = new ArrayList < Object > ();


 // Getter Methods 

 public ArrayList<Object> getProducts() {
	return products;
}

public void setProducts(ArrayList<Object> products) {
	this.products = products;
}

public String getStatus() {
  return status;
 }

 // Setter Methods 

 public void setStatus(String status) {
  this.status = status;
 }
}