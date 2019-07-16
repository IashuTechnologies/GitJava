package handler;
import bean.ProductBean;
import bean.ResponseBean;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import constants.APIConstant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import util.GyUtil;


/**
 * 
 * @author nchetry
 * This class takes AWS API handler . involvoked
 *  through API gateway  
 *  
 *  Unit Testing is done through manual testing as part of minimum viable solution.
 *   No automated testcases(eg. Junit) is not provided in this class 
 *
 */
public class LambdaJavaAPI implements RequestHandler<Map<String, String>,ResponseBean> {

 /**
  * @param Map
  * @param Context
  * Fetch the data from API Gateway template ,
  * and get the Data from RDS-mysql
  * and response back 
  * @return ResponseBean
  */
  @Override
  public ResponseBean handleRequest(Map<String, String> input, Context s) {
	    ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        GyUtil intanceGyUtil = new GyUtil();
		try{  
			
			conn = intanceGyUtil.getDBConnection();
			println("Got connection "+conn);
			double skucode =  Double.parseDouble(input.get("skucode"));
			 pstmt = conn.prepareStatement(APIConstant.getDetailsbySKU); 
		     pstmt.setDouble(1, skucode); // set input parameter
		      rs = pstmt.executeQuery();
		      
		    println("SKU code > "+skucode);
		      
		      while (rs.next()) {
		    	  String ProductDescription = rs.getString("ProductDescription");
		          String TireSize = rs.getString("TireSize");
		          double Rim_Size = rs.getDouble("Rim_Size");
		          double Speed = rs.getDouble("Speed");
		          String Index = rs.getString("Index");
		          String Section_Width = rs.getString("Section_Width");
		          String Customer_Name = rs.getString("Customer_Name");
		          String Region = rs.getString("Region");
		          String sales_zone = rs.getString("Sales Zone");
		          String province = rs.getString("Province");
		          String quantity = rs.getString("Quantity");
		          String country = "China";
		          ProductBean beanProd = new  ProductBean();
		          beanProd.setProduct_description(ProductDescription);
		          beanProd.setTiresize(TireSize);
		          beanProd.setRim_size(Rim_Size);
		          beanProd.setIndex(Index);
		          beanProd.setSection_width(Section_Width);
		          beanProd.setCustomer_name(Customer_Name);
		          beanProd.setRegion(Region);
		          beanProd.setSales_zone(sales_zone);
		          beanProd.setProvince(province);
		          beanProd.setQuantity(quantity);
		          beanProd.setCountry(country);
		          
		          productList.add(beanProd);
		         
		      }
		      if(productList!=null){
		    	 println("Size of product list "+productList.size());
		      }
			
		}catch(Exception e){ 
			System.out.println(e.toString());
			}
		finally{
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	 ResponseBean bean = new ResponseBean();
	 bean.setStatus("OK");
	 bean.setProducts(productList);
	
	 return bean;

        
}
  /**
   * common class to print
   * @param str
   */
  public void println(String str){
	  System.out.println(str);
  }
}


