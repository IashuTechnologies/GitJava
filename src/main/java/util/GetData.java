package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.ProductBean;
import bean.ResponseBean;

public class GetData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         getData();
	}
	
	
	public static void getData(){
		
		  println("Begin calling LamdaJava handleRequest >>>>");
		    ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        Statement stmt = null;
	        
	        GyUtil intanceGyUtil = new GyUtil();
			try{  
				
				//conn = intanceGyUtil.getDBConnection();
				
				
				Class.forName("com.mysql.jdbc.Driver");  
				conn=DriverManager.getConnection("jdbc:mysql://database-2.cbxzomcivnrq.eu-west-2.rds.amazonaws.com:3306/testrdsdb","admin","admin1234");
				
				println("Got connection "+conn);
				//double taskid =  Double.parseDouble(input.get("taskid"));
				//String taskid = input.get("taskid");
				 println("title code 1");
				 //pstmt = conn.prepareStatement(APIConstant.getDetailsbySKU_test); 
				 
//				 pstmt = conn.( "SELECT * FROM tasks where task_id =1");
//				 println("title code 2");
//			    //pstmt.setString(1, taskid); // set input parameter
//			    println("title code 3");
//			      rs = pstmt.executeQuery();
			      
			      
			      
			      stmt = conn.createStatement();
			      println("title code 21");
		            // Create a query to use.
		            String query = "SELECT * FROM tasks where task_id =1";
		 
		            // Execute the query and get the result set, which contains
		            // all the results returned from the database.
		            
		            rs = stmt.executeQuery(query);
			      
			      
			      
			   // println("title code 4 > "+rs.getString("title"));
//			  
//			  if(rs!=null) {
//				  println("title code 9");
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
		// return bean;
		
	}
	
	 /**
	   * common class to print
	   * @param str
	   */
	  public static void println(String str){
		  System.out.println(str);
	  }

}
