package Clients;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class TypicalClient implements Runnable{

	static private Socket connection;
	static private ObjectOutputStream output;
	static private ObjectInputStream input;
	public void run() {
		try {
			while (true){
				
				
				this.connection = new Socket(InetAddress.getByName("127.0.0.1"),6667);
				this.output = new ObjectOutputStream(connection.getOutputStream());
				this.input = new ObjectInputStream(connection.getInputStream());
				
			
			}
			
		}
		catch (IOException e) {e.printStackTrace();}
		catch (HeadlessException e) {e.printStackTrace();}
		}
		

	
	
	
	private static void sendData (Object obj){
		try {
			output.flush();
			output.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
}
