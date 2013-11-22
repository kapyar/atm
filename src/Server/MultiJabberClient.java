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

	public MultiJabberClient(String textFromModel) throws UnknownHostException,
			IOException {
		System.out.println("Constructor MultiJabberClient");
		socket = new Socket("localhost", 3434);
		bfReader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		prWriter = new PrintWriter(socket.getOutputStream());
		this.textFromModel = textFromModel;

	}

	@Override
	public String call() throws Exception {
		System.out.println("MultyJabberClient call");
		prWriter.println(textFromModel);

		String answer = bfReader.readLine();

		return answer;
	}
}
