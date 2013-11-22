package Server;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class ServeOneJabber extends Thread {

	public ServeOneJabber(Socket s) throws IOException {
		System.out.println("Constructor ServerOneJabber");
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
				System.out.println("ServerOneJabber run()");

				// System.out.println("Get string: " + str);
				out.println("answer");
				try {
					sleep(1121);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Cought smt ...");
			}
		}
	}

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
}
