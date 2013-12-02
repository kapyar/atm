package Server;

import java.net.*;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.io.*;

import Actions.Action;

/*
 * 
 * this class is for testing multithreading
 */
public class MultiJabberClient implements Callable<HashMap<Action, Object>> {
	private String textFromModel;
	private Socket socket;
	
	private ObjectOutputStream osOut;
	private ObjectInputStream osIn;
	
	final String HOST_NAME = "162.211.226.101";
	final int PORT_NUMBER = 8081;

	public MultiJabberClient(String textFromModel) throws UnknownHostException,IOException 
	{
		System.out.println("Constructor MultiJabberClient");
		socket = new Socket(HOST_NAME, PORT_NUMBER);
		
		osOut = new ObjectOutputStream(socket.getOutputStream());
		osIn = new ObjectInputStream(socket.getInputStream());
		
		this.textFromModel = textFromModel;

	}

	@Override
	public HashMap<Action, Object> call() throws Exception {

		HashMap<Action, Object> out = new HashMap<Action, Object>();
		HashMap<Action, Object> in = new HashMap<Action, Object>();
		
		out.put(Action.ACTION, Action.LOG_IN);
		out.put(Action.LOGIN_FIELD, 1);
		out.put(Action.PASS_FIELD, "12345");
		
		osOut.writeObject(out);		
		osOut.flush();
		
		in = (HashMap<Action, Object>) osIn.readObject();

		socket.close();

		return in;
	}
}
