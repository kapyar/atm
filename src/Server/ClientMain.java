package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMain {

	public static void main(String args[]) throws UnknownHostException,
			IOException {

		/*
		 * String hostName = "162.211.226.101"; int portNumber = 8081;
		 * 
		 * Socket echoSocket; try { echoSocket = new Socket(hostName,
		 * portNumber); PrintWriter out = new
		 * PrintWriter(echoSocket.getOutputStream(), true); BufferedReader in =
		 * new BufferedReader( new
		 * InputStreamReader(echoSocket.getInputStream())); BufferedReader stdIn
		 * = new BufferedReader( new InputStreamReader(System.in)); } catch (
		 * IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		Socket socket1;
		String hostName = "162.211.226.101";
		int portNumber = 8081;

		String str = "initialize";

		socket1 = new Socket(hostName, portNumber);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				socket1.getInputStream()));

		PrintWriter pw = new PrintWriter(socket1.getOutputStream(), true);

		/*
		 * pw.println("hello server"); pw.println("its me");
		 * pw.println("and i am very tired of you...");
		 */
		Scanner s = new Scanner(System.in);

		while (true) {
			System.out.println("starting cycle");
			String next = s.nextLine();
			if (next.equals("exit")) {
				break;
			}

			System.out.println("sending data to serv");
			pw.println(next);
			System.out.println("show serv response:");
			while (!(str = br.readLine()).equals("EOS")) {
				System.out.println(str);
				// System.out.println("in cycle");
			}
			System.out.println("finished showing serv data");
			System.out.println();

		}

		br.close();
		pw.close();
		socket1.close();

	}

}
