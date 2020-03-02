package constants;
/**
 * 
 * @author nchetry
 * Constant class for API Gateway
 *
 */
public class APIConstant {
	
	
	
	public static final String getDetailsbySKU = 
		///based on max load date-
			"SELECT * FROM Inventory_Visualization_Data where SAP_Code=? OR TyreSIZE= ?";
	
	
	public static final String configPath= "/resources/config.properties";
	public static final String dbUrl = "db.url";
	public static final String dbUser = "db.user";
	public static final String dbPassword ="db.password";
	public static final String configFullPath = "resources/config.properties";
	
	public static final String getDetailsbySKU_test = "SELECT * FROM tasks where task_id = ?";
	

}
