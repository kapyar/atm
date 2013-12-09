package dataBase;
import java.security.Timestamp;
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

import javax.swing.JOptionPane;


public class Database {
	private String base_server = "162.211.226.101:3306";
	private String base_name = "gofrie_vlad";
	private String base_user = "atm";
	private String base_pass = "mndfra19";
	
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
	
	public List<Object[]> getTableData (String table, String [] colums, String [] types) {
		List<Object[]> res = new ArrayList<Object[]>();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM `" + table + "` ");
			
	       	 while (rs.next()) {
	       		Object [] temp = new Object[colums.length];
	       		
	       		for (int i = 0; i < colums.length-1; ++i) {
	       			String val = rs.getString(colums[i]);
	       			String type = types[i];
	       			
	       			if (type.equals("int")) {
	       				temp[i] =  (Integer)Integer.parseInt(val);
	       			} else if (type.equals("double")) {
	       				temp[i] = (Double) Double.parseDouble(val);
	       			} else if (type.equals("tinyint")) {
	       				temp[i] = Boolean.parseBoolean(val);
	       			} else if (type.equals("timestamp")) {
	       				temp[i] = java.sql.Timestamp.valueOf(val);
	       			} else {
	       				temp[i] = val;
	       			}

	       		}
	       		
	       		temp[colums.length-1] = false;
	       		res.add(temp);
	       	 }
       	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public String[] getColTypes (String table, String [] cols) {
		String [] res = new String[cols.length];
		
		for (int i = 0; i < cols.length-1; ++i) {
			res[i] = getColType(table, cols[i]);
		}
		res[cols.length-1] = "boolean";
		
		return res;
	}
	
	public List<String> getTables() {
		List<String> tables = new ArrayList<String>();
		
		try {
			PreparedStatement ps = 
					connection.prepareStatement("SELECT `TABLE_NAME` FROM INFORMATION_SCHEMA.TABLES WHERE `TABLE_TYPE` = 'BASE TABLE' AND `TABLE_SCHEMA`='"+base_name+"' ");
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

	public void updateRow(String table, Object [] row, String [] names, String [] types) {
		try {
			
			String query = "UPDATE `" + table + "` SET ";
			
			for (int i = 1; i < types.length -1; ++i) {
				query += "`" + names[i] + "` = (?)";
				if (i != types.length - 2) {
					query += ",";
				}
			}
			
			query += " WHERE `id`=(?)";

			PreparedStatement ps =  connection.prepareStatement(query);
			
			for (int i = 1; i < types.length-1; ++i) {
				String type = types[i];
				Object obj = row[i];
				
				if (type.equals("int")) {
					ps.setInt(i, (int)obj);
       			} else if (type.equals("double")) {
       				ps.setDouble(i, (double)obj);
       			} else if (type.equals("tinyint")) {
       				ps.setBoolean(i, (Boolean)obj);
       			} else if (type.equals("timestamp")) {
       				java.sql.Timestamp x = (java.sql.Timestamp)obj;
       				ps.setTimestamp(i, x);
       			} else {
       				ps.setString(i, (String)obj);
       			}
				
			}

			ps.setInt(types.length-1, (Integer)row[0]);
			
			ps.execute();

			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while UPDATING a table. Check data types or field length.");
			Start.error_flag = true;
			e.printStackTrace();
		}
	}
	
	public void insertRow(String table, Object [] row, String [] names, String [] types) {
		try {
			
			String query = "INSERT INTO `" + table + "` SET ";
			
			for (int i = 1; i < types.length-1; ++i) {
				query += "`" + names[i] + "` = (?)";
				if (i != types.length - 2) {
					query += ",";
				}
			}

			PreparedStatement ps =  connection.prepareStatement(query);
			
			for (int i = 1; i < types.length-1; ++i) {
				String type = types[i];
				Object obj = row[i];
				
				if (type.equals("int")) {
					ps.setInt(i, (int)obj);
       			} else if (type.equals("double")) {
       				ps.setDouble(i, (double)obj);
       			} else if (type.equals("tinyint")) {
       				ps.setBoolean(i, (Boolean)obj);
       			} else if (type.equals("timestamp")) {
       				java.sql.Timestamp x = (java.sql.Timestamp)obj;
       				ps.setTimestamp(i, x);
       			} else {
       				ps.setString(i, (String)obj);
       			}
				
			}

			ps.execute();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while ADDING a table. Check data types or field length.");
			Start.error_flag = true;
			e.printStackTrace();
		}
	}
	
	public void removeRow(String table, int id) {
		try {
			PreparedStatement ps =  connection.prepareStatement("DELETE FROM `" + table + "` WHERE `id`=? ");
			System.out.println("DELETE FROM `" + table + "` WHERE `id`= " + id);
       		ps.setInt(1, id);
			ps.execute();

			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong while REMOVING a table. Check data types or field length.");
			Start.error_flag = true;
			e.printStackTrace();
		}
	}

	
}
