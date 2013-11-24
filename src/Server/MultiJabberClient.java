package Server;

import java.net.*;
import java.util.concurrent.Callable;
import java.io.*;

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

	public MultiJabberClient(String textFromModel) throws UnknownHostException,
			IOException {
		System.out.println("Constructor MultiJabberClient");
		socket = new Socket(HOST_NAME, PORT_NUMBER);
		
		bfReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		prWriter = new PrintWriter(socket.getOutputStream(), true);
		
		this.textFromModel = textFromModel;

	}

	@Override
	public String call() throws Exception {
		
		
		System.out.println("MultyJabberClient call");
		prWriter.println("hola hola");

		String answer = bfReader.readLine();
		System.out.println("Answer in call(): " + answer);

		return answer;
	}
}
