package sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SMS {
	public final static DataBase db =  DataBase.DB;
	
	private  void sendGet() throws Exception {
		 
		String url = "http://smsc.ua/sys/get.php?get_answers=1&login=MPK&psw=dd0b3d9c09c13390c55747399e9c02f5";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");

		//int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		int lastSmsId = db.getLastSmsId();
		
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			//System.out.println(inputLine);
			
			if (!inputLine.contains("ERROR = 9")) {
				String data [] = inputLine.split(",");
				int id = Integer.parseInt(data[0].substring(5));
				String phone = data[2].substring(9).trim();
				String msg = data[3].substring(16).trim().toLowerCase();
				//System.out.println("message is " + msg);
				
				if (id > lastSmsId) {
					db.addSmsRow(id, phone, msg);
				}
			} else {
				System.out.println("Too many connections");
			}
		}
		in.close();
 
		//System.out.println(response.toString());
		
	}
	
	public void start() throws Exception {
		while (true) {
			System.out.println("Check...");
			
			sendGet();
			
			Thread.sleep(30000);
		}

	
	}
	
	public static void main (String [] args) throws Exception {		
		new SMS().start();
	}
}
