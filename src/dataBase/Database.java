package dataBase;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class Database {
	private String base_server = "gofrie.mysql.ukraine.com.ua";
	private String base_name = "gofrie_vlad";
	private String base_user = "gofrie_vlad";
	private String base_pass = "e9937rdk";
	
	private String dbName = "//" + base_server + "/" + base_name + "?user=" + base_user + "&password=" + base_pass;
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
	
	public String getColType(String table, String column) {
		String result = "";
		try {
			PreparedStatement ps =  connection.prepareStatement("SELECT data_type FROM INFORMATION_SCHEMA.COLUMNS "
					+ " WHERE table_schema = '" + base_name + "' AND  table_name = (?) and column_name = (?) ");
			ps.setString(1, table);
			ps.setString(2, column);
			
			ResultSet rs = ps.executeQuery();

	       	while (rs.next()) {
	       		result = rs.getString("data_type");
	       	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<String[]> getTableData (String table, String [] colums) {
		List<String[]> res = new ArrayList<String[]>();
		
		/*System.out.println("table " + table);

		for ( int i = 0; i < colums.length; ++i ) {
			System.out.println(getColType(table, colums[i]));
		}
		*/
		
		try {
			Statement st = connection.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM `" + table + "` ");
			
			
       	 while (rs.next()) {
       		String [] temp = new String[colums.length];
       		
       		for (int i = 0; i < colums.length; ++i) {
       			temp[i] =  rs.getString(colums[i]);
       		}
       		
       		res.add(temp);

       		//for (String col: colums) {
       			/*String type = getColType(table, col);
       			
       			if (type.equals("int")) {
       				res.add(Integer.parseInt(rs.getString("id"));
       			}*/
       			
       		//}
       		//res.put(Integer.parseInt(rs.getString("id")), rs.getString("name"));
       	 }
       	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public String[] getColTypes (String table, String [] cols) {
		String [] res = new String[cols.length];
		
		for (int i = 0; i < cols.length; ++i) {
			res[i] = getColType(table, cols[i]);
		}
		
		return res;
	}
	
	public List<String> getTables() {
		List<String> tables = new ArrayList<String>();
		
		try {
			PreparedStatement ps = 
					connection.prepareStatement("SELECT `TABLE_NAME` FROM INFORMATION_SCHEMA.TABLES WHERE `TABLE_TYPE` = 'BASE TABLE'");
       	 ResultSet rs = ps.executeQuery();
       	 
       	 while (rs.next())
       		tables.add(rs.getString("TABLE_NAME"));
       	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tables;
	}
	
	public List<String> getColums(String table) {
		List<String> colums = new ArrayList<String>();
		
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
												"WHERE TABLE_SCHEMA = '"+base_name+"' " +
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
