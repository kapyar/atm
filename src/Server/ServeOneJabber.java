package Server;

import java.io.*;
import java.net.*;

public class ServeOneJabber extends Thread {

	public ServeOneJabber(Socket s) throws IOException {
		socket = s;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		// включаємо автоматичне виштовхування
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream())), true);
		// Якщо будь-який з вище перерахованих викликів приведе до виникнення
		// виключення, тоді викликаючий буде відповідальний за закриття сокета
		// інакше потік закриє його
		start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				// read from client
				String str = in.readLine();
				if (str != null) {
					System.out.println("Get string: " + str);
					out.println(str + "OUT FROM RUN SERVERONEJOBBER");
				}
			}

		} catch (IOException e) {
			System.err.println("IO Exception");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Cought smt ...");
			}
		}
		System.out.println("The END of run method");
	}

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
}
