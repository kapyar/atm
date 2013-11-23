package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServeOneJabber extends Thread {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public ServeOneJabber(Socket s) throws IOException {
		System.out.println("Constructor ServerOneJabber");
		socket = s;

		// включаємо автоматичне виштовхування
		/*
		 * out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
		 * socket.getOutputStream())), true);
		 */

		// Якщо будь-який з вище перерахованих викликів приведе до виникнення
		// виключення, тоді викликаючий буде відповідальний за закриття сокета
		// інакше потік закриє його
		start();
	}

	@Override
	public void run() {
		try {
			System.out.println("ServerOneJabber run()");
			/*
			 * PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			 * pw.println("What's you name?");
			 * 
			 * BufferedReader br = new BufferedReader(new
			 * InputStreamReader(socket.getInputStream())); String str =
			 * br.readLine();
			 * 
			 * pw.println("Hello, " + str);
			 */
			String str;
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			while ((str = br.readLine()) != null) {
				System.out.println("The message: " + str);

				if (str.equals("bye")) {
					pw.println("bye");
					break;
				} else {
					str = "Server returns " + str;
					pw.println(str);
				}
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			System.out.println("Finally part ServerOneJabber");
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Cought smt ...");
			}
		}
	}

}
