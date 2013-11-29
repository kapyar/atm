package Server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import cryptography.Algos;
import dataBase.Pair;
import Actions.Action;

public class ServeOneJabber extends Thread {

	private Socket socket;
	// private HashMap<Actions.Action, Object> dataFromUser;

	private ObjectInputStream osIn;
	private ObjectOutputStream osOut;
	private SQLwrapper dataBase;

	public ServeOneJabber(Socket s) throws IOException {
		System.out.println("Constructor ServerOneJabber");
		socket = s;
		dataBase = new SQLwrapper();

		this.start();
	}

	@Override
	public void run() {
		try {
			System.out.println("ServerOneJabber run()");

			osIn = new ObjectInputStream(socket.getInputStream());
			osOut = new ObjectOutputStream(socket.getOutputStream());

			HashMap<Action, Object> in = (HashMap<Action, Object>) osIn
					.readObject();
			HashMap<Action, Object> out = new HashMap<Action, Object>();

			switch ((Action) in.get(Action.ACTION)) {
			case LOG_IN:
				Integer login = (Integer) in.get(Action.LOGIN_FIELD);
				String pass = (String) in.get(Action.PASS_FIELD);

				if (!dataBase.userExists(login)) {
					out.put(Action.ERROR_CODE, Action.ERROR_NO_USER);
					break;
				}

				if (!dataBase.passMatches(login, pass)) {
					out.put(Action.ERROR_CODE, Action.EREOR_NOT_MATCHES);
					break;
				}

				String session = "session_uid";

				if (!dataBase.setSession(login, session)) {
					out.put(Action.ERROR_CODE, Action.ERROR_SETTING_SESSION);
					break;
				}

				out.put(Action.SESSION_ID, Algos.serverHash(pass));
				break;
			}

			osOut.writeObject(out);

			osOut.flush();
			socket.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Finally part ServerOneJabber");
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Cought smth ...");
			}
		}
	}

}
