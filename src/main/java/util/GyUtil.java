package util;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import constants.APIConstant;

/**
 * Common DB connection utility
 * @author nchetry
 *
 */
public class GyUtil {
	
	/**
	 * get connection
	 * @return Connection
	 */
	public  Connection getDBConnection(){
		println("Begin getConnectionForLamda 123 10th Sep 2019 ");
	
		
		Connection con = null;
		GyUtil instanceUtil = new GyUtil();
		String dburl = instanceUtil.loadConfig().getProperty(APIConstant.dbUrl);
		String dbUser = instanceUtil.loadConfig().getProperty(APIConstant.dbUser);
		String password = instanceUtil.loadConfig().getProperty(APIConstant.dbPassword);
		
		
		
		println("dbUrl & user 0th Sep 2019 "+dburl +":"+dbUser);
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(dburl,dbUser,password);
			
			if(con!=null){
				println("Got connection 0th Sep 2019 "+con);
			}else {
				println("Connection failed 0th Sep 2019 ");
			}
		}catch(Exception e){ 
			println("Connection  "+e.toString());
			}
		println("End getConnectionForLamda  ");
		return con;  
	}

	/*
	 * load config 
	 * retun Properties
	 */
	public  Properties loadConfig(){
		println("Begin loadConfig ");
		Properties prop = null;
		 prop = new Properties();
		try {
			
			prop.load(new FileInputStream(APIConstant.configFullPath));

	        } catch (Exception ex) {
	            ex.printStackTrace();
	            println("Exception "+ex.toString());
	            
	        }
		println("End loadConfig ");
		return prop;
		
	}
	
	/**
	 * common method to print
	 * @param str
	 */
	public  void println(String str){
		System.out.println(str);
	}
	
	public static void main(String args[]){
		GyUtil isnatnce = new GyUtil();
		isnatnce.getDBConnection();
	}
	
	
	


}
