/**
 * 
 */
package handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import util.GyUtil;
import bean.ProductBean;
import bean.ResponseBean;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import constants.APIConstant;

/**
 * @author nchetry
 *
 */
public class LamdaJavaAPIDemo implements RequestHandler<Map<String, String>,ResponseBean> {
	

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
		  println("Begin calling LamdaJava handleRequest >>>>");
		    ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        Statement stmt = null;
	        
	        GyUtil intanceGyUtil = new GyUtil();
			try{  
				
				conn = intanceGyUtil.getDBConnection();
				println("Got connection "+conn);
				//double taskid =  Double.parseDouble(input.get("taskid"));
				String taskid = input.get("taskid");
				println("title code 1");
				pstmt = conn.prepareStatement(APIConstant.getDetailsbySKU_test); 
				// pstmt = conn.( "SELECT * FROM tasks where task_id =1");
				println("title code 2");
			    pstmt.setString(1, taskid); // set input parameter
			    println("title code 3");
			    rs = pstmt.executeQuery();
			    
			    while (rs.next()) {
			    	 println("title code 5");
			    	int task_id =rs.getInt("task_id");
			    	String title = rs.getString("title");
			    	ProductBean beanProd = new  ProductBean();
			    	beanProd.setId(task_id);
			    	beanProd.setProduct_description(title);
			    	 println("title code 6");
			    	 productList.add(beanProd);
			    	
			    }
			    
			    if(productList!=null && productList.size() >=1)
			    {
			    	println("title code 6.1 >>>");
			    }
			    println("title code 7");
			  
			      
//			      while (rs.next()) {
//			    	  String ProductDescription = rs.getString("ProductDescription");
//			          String TireSize = rs.getString("TireSize");
//			          double Rim_Size = rs.getDouble("Rim_Size");
//			          double Speed = rs.getDouble("Speed");
//			          String Index = rs.getString("Index");
//			          String Section_Width = rs.getString("Section_Width");
//			          String Customer_Name = rs.getString("Customer_Name");
//			          String Region = rs.getString("Region");
//			          String sales_zone = rs.getString("Sales Zone");
//			          String province = rs.getString("Province");
//			          String quantity = rs.getString("Quantity");
//			          String country = "China";
//			          ProductBean beanProd = new  ProductBean();
//			          beanProd.setProduct_description(ProductDescription);
//			          beanProd.setTiresize(TireSize);
//			          beanProd.setRim_size(Rim_Size);
//			          beanProd.setIndex(Index);
//			          beanProd.setSection_width(Section_Width);
//			          beanProd.setCustomer_name(Customer_Name);
//			          beanProd.setRegion(Region);
//			          beanProd.setSales_zone(sales_zone);
//			          beanProd.setProvince(province);
//			          beanProd.setQuantity(quantity);
//			          beanProd.setCountry(country);
//			          
//			          productList.add(beanProd);
//			         
//			      }
//			      if(productList!=null){
//			    	 println("Size of product list "+productList.size());
//			      }
				
			}catch(Exception e){ 
				 println("title code 10");
				System.out.println(e.toString());
				}
			finally{
				try {
					 println("title code 11");
					if(rs!=null && pstmt!=null & conn!=null ){
						 println("title code 12");
					rs.close();
					pstmt.close();
					conn.close();
					}
				} catch (SQLException e) {
					 println("title code 13");
					e.printStackTrace();
					println("title code 14");
				}
			}
			println("title code 15");
		 ResponseBean bean = new ResponseBean();
		 bean.setStatus("OK");
		 bean.setProducts(productList);
		 println("title code 16");
		 println("End calling LamdaJava handleRequest >>>>");
		 println("title code 17");
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





