package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Controller.Friend;

public class SQLwrapper {

	private String base_server = "gofrie.mysql.ukraine.com.ua";
	private String base_name = "gofrie_vlad";
	private String base_user = "gofrie_vlad";
	private String base_pass = "e9937rdk";

	private String dbName = "//" + base_server + "/" + base_name + "?user="
			+ base_user + "&password=" + base_pass;
	private Connection connection;

	public SQLwrapper() {
		init();
	}

	private void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql:" + dbName);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private int getUserByAcc(Integer acc) {
		int result = -1;
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT `owner_id` FROM `Accounts` WHERE `id`=? ");
			ps.setInt(1, acc);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getInt("owner_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Boolean userExists(Integer accNum) {
		return getUserByAcc(accNum) >= 0;
	}

	public Boolean passMatches(Integer accNum, String pass) {

		String result = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT `password_hash` FROM `Users` WHERE `id`=? ");
			ps.setInt(1, getUserByAcc(accNum));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getString("password_hash");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result.equals(pass);
	}

	public Boolean setSession(Integer accNum, String session) {
		Boolean res = true;
		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `Accounts` SET `session_id`=(?) WHERE `id`=(?) ");

			ps.setString(1, session);
			ps.setInt(2, accNum);

			// ps.executeQuery();
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	public String getCurrentSession(Integer blogin) {
		String result = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT `session_id` FROM `Accounts` WHERE `id`=(?) ");

			// ps.setInt(1, blogin);
			// int i = (int) blogin;
			System.out.println("bLogin: " + blogin);
			ps.setInt(1, blogin);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getString("session_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public double getBalance(String bSession) {
		double result = 0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT `balance` FROM `Accounts` WHERE `session_id`=? ");
			ps.setString(1, bSession);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getDouble("balance");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getCashLeft(int atmId) {
		int result = 0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT `value2` FROM `TechInfo` WHERE `name`='cashAmmount' AND `value`=(?) ");
			ps.setString(1, String.valueOf(atmId));
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("value2");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addCash(int ammount, int atmId) {
		int cash = getCashLeft(atmId) + ammount;
		
		Boolean res = true;
		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `TechInfo` SET `value2`=(?) WHERE `name`='cashAmmount' AND `value` = (?) ");

			ps.setString(1,String.valueOf(cash));
			ps.setString(2,String.valueOf(atmId));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	
	
	

	public boolean setBalance(double newBal, Integer accNum) {
		Boolean res = true;
		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE `Accounts` SET `balance`=(?) WHERE `id`=(?) ");

			ps.setDouble(1, newBal);
			ps.setInt(2, accNum);

			// ps.executeQuery();
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}
		return res;

	}

	// need to finish
	public Friend[] getListFriends(Integer accNum) {
		String result = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT `first_name` FROM `Accounts` WHERE `id`=(?) ");
			// ps.setInt(1, blogin);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getString("session_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
