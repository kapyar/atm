package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String command;

	public ClientThread(String comand) {
		this.command = comand;
		try {
			socket = new Socket("localhost", Server.PORT);
		} catch (IOException e) {
			System.err.println("ClientThread");
		}
		try {

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), true);
			start();
		} catch (IOException e) {
			// —окет маЇ бути закритий при будь
			// €к≥й помилц≥
			// кр≥м помилки конструктора сокета
			try {
				socket.close();
			} catch (IOException e2) {
				System.err.println("NOt closed ClientThread");
			}
		}
		// якщо все в≥дбудетьс€ нормально сокет
		// буде закрито
		// в метод≥ run() потоку.
	}

	@Override
	public void run() {
		out.println(command);
		String s = null;
		try {
			s = in.readLine();
		} catch (IOException e) {
			System.out.println("TRo-lo-lo clientThread");
		}

		System.out.println("ClientThread:" + s);

	}

}
