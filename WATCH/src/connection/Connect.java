package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;



public class Connect {

	private final String EMAIL = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "watch";
	private final String HOST = "localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	public  ResultSet rs;
	private ResultSetMetaData rsm;
	
	private Connection con;
	private Statement st;
	
	//Singleton -> satu class hanya bisa dibuat mnjadi satu objek
	
	private static Connect connect;
	private Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con = DriverManager.getConnection(CONNECTION, EMAIL, PASSWORD);
		    st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connect getInstance() {
		//Object connect masih kosong -> return objek bru
		if(connect == null) return new Connect();
		
		//Object connect udah dibuat
		return connect;
		
	}
	
	public void execUpdate(String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet execQuery(String query) {
		try {
			rs = st.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	
	
	
}
