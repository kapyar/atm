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
	private HashMap<Action, Object> command;

	final String HOST_NAME = "162.211.226.101";
	final int PORT_NUMBER = 8081;

	public MultiJabberClient(HashMap<Action, Object> command)
			throws IOException {
		System.out.println("Constructor MultiJabberClient");
		try {
			socket = new Socket("localhost", PORT_NUMBER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		osOut = new ObjectOutputStream(socket.getOutputStream());
		osIn = new ObjectInputStream(socket.getInputStream());

		this.command = command;

	}

	@Override
	public HashMap<Action, Object> call() throws Exception {
		System.out.println("call()");
		HashMap<Action, Object> in = new HashMap<Action, Object>();
		osOut.writeObject(command);// write in ServeOneJabber
		osOut.flush();

		System.out.println("Before read");
		in = (HashMap<Action, Object>) osIn.readObject();

		socket.close();

		return in;
	}
}
