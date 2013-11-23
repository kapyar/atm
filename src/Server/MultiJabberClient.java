package Server;

import java.net.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.io.*;

import Actions.Action;

/*
 * 
 * this class is for testing multithreading
 */
public class MultiJabberClient implements Callable<String> {
	private String textFromModel;
	private Socket socket;
	private BufferedReader bfReader;
	private PrintWriter prWriter;
	final String HOST_NAME = "162.211.226.101";
	final int PORT_NUMBER = 8081;
	private HashMap<Action, Object> dataToSend;

	public MultiJabberClient(HashMap<Action, Object> dataToSend)
			throws UnknownHostException, IOException {
		System.out.println("Constructor MultiJabberClient");
		socket = new Socket(HOST_NAME, PORT_NUMBER);
		bfReader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		// true is very important
		prWriter = new PrintWriter(socket.getOutputStream(), true);
		this.dataToSend = dataToSend;

	}

	@Override
	public String call() throws Exception {
		System.out.println("MultyJabberClient call");
		// send data to server
		prWriter.println(dataToSend);

		System.out.println("call() before Reading");
		// Scanner s = new Scanner(socket.getInputStream());
		String answer = bfReader.readLine();
		// String answer = s.nextLine();
		System.out.println("Answer from server" + answer);

		prWriter.println("stop");

		System.out.println("finished showing serv data");
		System.out.println();

		bfReader.close();
		prWriter.close();
		socket.close();

		return answer;
	}
}
