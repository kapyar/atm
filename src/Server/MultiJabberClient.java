package Server;

import java.net.*;
import java.io.*;

public class MultiJabberClient {
	static final int MAX_THREADS = 5;

	public static void main(String[] args) throws IOException,
			InterruptedException {
		InetAddress addr = InetAddress.getByName(null);
		System.out.println(addr);

		new ClientThread("first");
		new ClientThread("second");
		new ClientThread("third");

	}
}
