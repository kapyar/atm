package dataBase;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class Database {
	private String dbName = "//sql4.freemysqlhosting.net/sql420969?user=sql420969&password=nE5*pG9*";
	private Connection connection;
	
	public Database () {
		init();
	}
	
	private void init () {
		try {
		 Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql:" + dbName);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Map<Integer, String> getCountries() {
		Map<Integer, String> hashmap = new HashMap<Integer, String>();
         try {
        	 Statement st = connection.createStatement();
        	 ResultSet rs = st.executeQuery("SELECT * FROM `Countries`");
        	 
        	 while (rs.next())
        		 hashmap.put(Integer.parseInt(rs.getString("id")), rs.getString("name"));
        	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
         
         return hashmap;
	}
	
	public HashSet<String> getTables() {
		HashSet<String> tables = new HashSet<String>();
		
		try {
       	 Statement st = connection.createStatement();
       	 ResultSet rs = st.executeQuery("SELECT `TABLE_NAME` FROM INFORMATION_SCHEMA.TABLES WHERE `TABLE_TYPE` = 'BASE TABLE'");
       	 
       	 while (rs.next())
       		tables.add(rs.getString("TABLE_NAME"));
       	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tables;
	}
	
	
	
	
	
	public HashSet<String> getColums(String table) {
		HashSet<String> colums = new HashSet<String>();
		
		try {
       	 Statement st = connection.createStatement();
       	 ResultSet rs = st.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE `TABLE_NAME`='"+table+"' ");
       	 
       	 while (rs.next())
       		colums.add(rs.getString("COLUMN_NAME"));
       	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return colums;
	}
	
	public String getColumComments(String table, String column) {
		//HashSet<String> colums = new HashSet<String>();
		String res = "";
		
		try {
			PreparedStatement ps = 
					connection.prepareStatement("SELECT `COLUMN_COMMENT` " +
												"FROM information_schema.COLUMNS " +
												"WHERE TABLE_SCHEMA = 'sql420969' " +
												"AND TABLE_NAME = (?) AND COLUMN_NAME = (?) ");
			ps.setString(1, table);
			ps.setString(2, column);
			ResultSet rs = ps.executeQuery();
			
	       	/*while (rs.next())
	       		colums.add(rs.getString("COLUMN_COMMENT"));*/
	       	rs.next();
	       	res = rs.getString("COLUMN_COMMENT");
       	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}


	
}
