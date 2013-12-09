package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataBase.Utils;
import Controller.Friend;

public enum SQLwrapper {
	DB;

	private String base_server = "162.211.226.101:3306";
	private String base_name = "gofrie_vlad";
	private String base_user = "atm";
	private String base_pass = "mndfra19";

	private String dbName = "//" + base_server + "/" + base_name + "?user="
			+ base_user + "&password=" + base_pass;
	private Connection connection;

	SQLwrapper() {
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

	// return owner_id by id in table
	private int getUserByAcc(Integer acc) {
		int result = -1;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection
					.prepareStatement("SELECT `owner_id` FROM `Accounts` WHERE `id`=? ");
			ps.setInt(1, acc);

			rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getInt("owner_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.closePSt(ps);
			Utils.closeRs(rs);
		}

		return result;
	}

	public Boolean userExists(Integer accNum) {
		return getUserByAcc(accNum) >= 0;
	}

	public Boolean passMatches(Integer accNum, String pass) {

		String result = "";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection
					.prepareStatement("SELECT `password_hash` FROM `Users` WHERE `id`=? ");
			ps.setInt(1, getUserByAcc(accNum));

			rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getString("password_hash");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.closePSt(ps);
			Utils.closeRs(rs);
		}

		return result.equals(pass);
	}

	public Boolean setSession(Integer accNum, String session) {
		Boolean res = true;
		PreparedStatement ps = null;
		try {
			ps = connection
					.prepareStatement("UPDATE `Accounts` SET `session_id`=(?) WHERE `id`=(?) ");

			ps.setString(1, session);
			ps.setInt(2, accNum);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		} finally {
			Utils.closePSt(ps);
		}
		return res;
	}

	public String getCurrentSession(Integer blogin) {
		String result = "";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection
					.prepareStatement("SELECT `session_id` FROM `Accounts` WHERE `id`=(?) ");

			// System.out.println("bLogin: " + blogin);
			ps.setInt(1, blogin);

			rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getString("session_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.closePSt(ps);
			Utils.closeRs(rs);
		}

		return result;
	}

	public double getBalance(String bSession) {
		double result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection
					.prepareStatement("SELECT `balance` FROM `Accounts` WHERE `session_id`=? ");
			ps.setString(1, bSession);
			rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getDouble("balance");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.closePSt(ps);
			Utils.closeRs(rs);
		}
		return result;
	}

	public int getCashLeft(int atmId) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection
					.prepareStatement("SELECT `value2` FROM `TechInfo` WHERE `name`='cashAmmount' AND `value`=(?) ");
			ps.setString(1, String.valueOf(atmId));

			rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getInt("value2");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.closePSt(ps);
			Utils.closeRs(rs);
		}

		return result;
	}

	public boolean addCash(int ammount, int atmId) {
		int cash = ammount;
		Boolean res = true;
		PreparedStatement ps = null;

		try {
			ps = connection
					.prepareStatement("UPDATE `TechInfo` SET `value2`=(?) WHERE `name`='cashAmmount' AND `value` = (?) ");

			ps.setString(1, String.valueOf(cash));
			ps.setString(2, String.valueOf(atmId));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		} finally {
			Utils.closePSt(ps);
		}
		return res;
	}

	public boolean setBalance(double newBal, Integer accNum) {
		Boolean res = true;
		PreparedStatement ps = null;

		try {
			ps = connection
					.prepareStatement("UPDATE `Accounts` SET `balance`=(?) WHERE `id`=(?) ");

			ps.setDouble(1, newBal);
			ps.setInt(2, accNum);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		} finally  {
			Utils.closePSt(ps);
		}
		return res;

	}
	
	public Integer getAccByUser(Integer userId) {
		Integer result = -1;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection
					.prepareStatement("SELECT `id` FROM `Accounts` WHERE `owner_id`=(?) LIMIT 1");
			ps.setInt(1, userId);

			rs = ps.executeQuery();
			
			while (rs.next()) {
				result = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally  {
			Utils.closePSt(ps);
			Utils.closeRs(rs);
		}
		
		return result;
	}

	// need to finish
	public ArrayList<Friend> getListFriends(Integer accNum) {
		ArrayList<Friend> list = new ArrayList<Friend>();
		PreparedStatement ps = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		ResultSet rs = null;

		try {
			ps = connection
					.prepareStatement("SELECT `first_name`,`last_name` FROM `Users` INNER JOIN `Contacts` ON "
							+ "Users.id = Contacts.friend_id AND Contacts.user_id = (?)");

			pst = connection
					.prepareStatement("SELECT Accounts.id FROM `Accounts` INNER JOIN `Contacts` ON "
							+ "Accounts.owner_id = Contacts.friend_id AND Contacts.user_id = (?)");

			ps.setInt(1, accNum);
			pst.setInt(1, accNum);

			rst = pst.executeQuery();
			rs = ps.executeQuery();

			while (rs.next() && rst.next()) {
				String name = rs.getString("last_name") + ' '
						+ rs.getString("first_name");

				Integer ac = getAccByUser(rs.getInt("id"));
				

				list.add(new Friend(name, ac));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally  {
			Utils.closePSt(ps);
			Utils.closePSt(pst);
			Utils.closeRs(rs);
			Utils.closeRs(rst);
		}

		return list;
	}

	public boolean sendMoney(Integer twlogin, Integer twToWhome,
			Integer twHowMuch) {

		double fromWhome = (int) getBalance(twlogin);
		double toWhome = (int) getBalance(twToWhome);

		fromWhome -= twHowMuch.doubleValue();
		if (fromWhome < 0)
			return false;
		toWhome += twHowMuch.doubleValue();
		
		setBalance(fromWhome, twlogin);
		setBalance(toWhome, twToWhome);

		return true;

	}

	private double getBalance(Integer twlogin) {
		double result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection
					.prepareStatement("SELECT `balance` FROM `Accounts` WHERE `owner_id`=? ");
			ps.setInt(1, twlogin);
			rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getDouble("balance");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.closePSt(ps);
			Utils.closeRs(rs);
		}
		return result;

	}
}
