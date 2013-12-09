package sms;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import Controller.NotifyController;

public class DataBase {
	private String base_server = "gofrie.mysql.ukraine.com.ua";
	private String base_name = "gofrie_vlad";
	private String base_user = "gofrie_vlad";
	private String base_pass = "e9937rdk";
	
	private String dbName = "//" + base_server + "/" + base_name + "?user=" + base_user + "&password=" + base_pass;
	private Connection connection;
	
	
	public DataBase () {
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
	
	
	public int lastId() {
		int lastid = -1;
		try {
			Statement st = connection.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT `id` FROM `sms` ORDER BY id DESC LIMIT 1  ");
			if (rs.next()) {
				lastid = Integer.parseInt(rs.getString("id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			//lastid = -1;
		}

		return lastid;
	}
	
	public int getLastSmsId() {
		int res = 0;
		
		try {
			int lastId = lastId();
			if (lastId >= 0) {
				PreparedStatement ps = connection.prepareStatement("SELECT MAX(sms_id) AS smsId FROM `sms` ");
				//ps.setInt(1, lastId);
				
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					res = Integer.parseInt(rs.getString("smsId"));
				}
			} else {
				return res;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			//lastid = -1;
		}
		
		return res;
	}
	
	private double getBalance(String account) {
		double res = -1;
		int acc = Integer.parseInt(account);
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT `balance` FROM `Accounts` WHERE `id`=(?) ");
			ps.setInt(1, acc);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				res = rs.getDouble("balance");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	enum smsMode {
		BALANCE,
		SEND
	};
	
	public void addSmsRow(int smsId, String sender, String message) {
		try {
			PreparedStatement ps =  connection.prepareStatement("INSERT INTO `sms` (sms_id, sender_number, message) VALUES (?, ?, ?) ");
			ps.setInt(1, smsId);
			ps.setString(2, sender);
			ps.setString(3, message);
			System.out.println("SMS Row added: smsId:" + smsId + ", sender: " + sender + ", message: " + message);
			
			ps.executeUpdate();
			
			smsMode mode = smsMode.BALANCE;
			
			String accounts[] = new String[3];
			
			if (message.contains("joke")) {
				NotifyController.INSTANCE.sendSms(sender, getJoke());
				return;
			}
			
			
			if (message.contains("send")) {
				message = message.replaceAll("send", "").trim();
				
				String accs[] = message.split("\\+");
				
				if (accs.length != 3) {
					System.out.println("Error input message");
					return;
				}

				accounts[0] = accs[0].trim();
				accounts[1] = accs[1].trim();
				accounts[2] = accs[2].trim();
				
				if (!accExists(accounts[1]) || Integer.parseInt(accounts[2]) < 0) {
					System.out.println("Error: acc doesn't exist or sum is negative");
					return;
				}
				
				mode = smsMode.SEND;
			} else {
				accounts[0] = message;
			}
			
			
			String data[] = getUserData(accounts[0]);
			
			if (data[2] != null && data[2].equals(sender)) {
				
				switch (mode) {
					case BALANCE:
						double balance = getBalance(accounts[0]);
						NotifyController.INSTANCE.sendSms(sender, "Hello, " + data[0] + " " + data[1] + "! Your balance is: " + balance + "$.");
						System.out.println("Hello, " + data[0] + " " + data[1] + "! Your balance is: " + balance + "$.");
						break;
					case SEND:
						if (accounts[1] != null && accounts[2] != null && !accounts[1].equals("") && !accounts[2].equals("")) {
							System.out.println("Sent >" + accounts[2] + "< $ from you acc >" + accounts[0] + "< to acc >" + accounts[1] + "<");
							transfer(accounts[0], accounts[1], accounts[2]);
							double bal = getBalance(accounts[0]);
							
							NotifyController.INSTANCE.sendSms(sender, "Hello, " + data[0] + " " + data[1] + "!" 
							+ " You transfered " +  accounts[2] + "$ to account #" + accounts[1] + "." 
							+" Your current balance is: " + bal + "$.");

						} else {
							System.out.println("error sending money");
						}
						
						break;
				}
				
				
				
				System.out.println("Notification must have been send");
			} else {
				System.out.println("Trying to access balance from wrong phone or no user with this acc");
			}
			
			

		} catch (SQLException e) {
			e.printStackTrace();
			//lastid = -1;
		}
	}
	
	public int getOwnerId(String account) {
		int accId = Integer.parseInt(account);
		int res = -1;
		
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT `owner_id`  FROM `Accounts` WHERE id=(?) ");
			ps.setInt(1, accId);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				res = Integer.parseInt(rs.getString("owner_id"));
			} 


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public String[] getUserData(String accNumber) {
		String res[] = new String[3];
		int owner = getOwnerId(accNumber);
		
		if (owner < 0) {
			System.out.println("here is no owner for this account");
			return res;
		}
		
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM `Users` WHERE `id`=(?) LIMIT 1");
			ps.setInt(1, owner);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				//res = Integer.parseInt(rs.getString("smsId"));
				res[0] = rs.getString("first_name");
				res[1] = rs.getString("last_name");
				res[2] = rs.getString("phone");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return res;
	
	}
	
	public boolean accExists(String acc) {
		boolean exists = false;
		
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM `Accounts` WHERE `id`=(?)");
			ps.setInt(1, Integer.parseInt(acc));
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				exists = true;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exists;
	}
	
	
	public void transfer(String from, String to, String sum) {
		
		try {

			PreparedStatement ps = connection.prepareStatement("UPDATE  `Accounts` SET balance=balance-(?) WHERE `id`=(?) ");
			ps.setInt(1, Integer.parseInt(sum));
			ps.setInt(2, Integer.parseInt(from));
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {

			PreparedStatement ps = connection.prepareStatement("UPDATE  `Accounts` SET balance=balance+(?) WHERE `id`=(?) ");
			ps.setInt(1, Integer.parseInt(sum));
			ps.setInt(2, Integer.parseInt(to));
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public String getJoke() {
		String res = "";
		try {
			 DataInputStream in = new DataInputStream(new FileInputStream("jokes.txt"));
			 BufferedReader br = new BufferedReader(new InputStreamReader(in));
			 String strLine;
			 ArrayList<String> jokes = new ArrayList<String>();
			 
			 while ((strLine = br.readLine()) != null)   {
				 jokes.add(strLine);
			 }
			 
			 Random rand = new Random();
			 res = jokes.get(rand.nextInt(jokes.size()-1));
			 
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return res;
	}
	
}
