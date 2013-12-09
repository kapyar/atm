package Server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

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
					out.put(Action.ERROR_CODE, Action.ERROR_NOT_MATCHES);
					break;
				}

				String session = "session_uid";
				session += new Random().nextInt(Integer.MAX_VALUE);

				if (!dataBase.setSession(login, session)) {
					out.put(Action.ERROR_CODE, Action.ERROR_SETTING_SESSION);
					break;
				}

				out.put(Action.SESSION_ID, session);
				break;

			case BALANCE:
				String bSession = (String) in.get(Action.SESSION_ID);
				Integer blogin = (Integer) in.get(Action.LOGIN_FIELD);

				String s = dataBase.getCurrentSession(blogin);
				if (s.equals(bSession)) {
					System.out.println("Session is ok" + s);
					double balance = dataBase.getBalance(bSession);
					out.put(Action.BALANCE, balance);

				} else {
					out.put(Action.ERROR_CODE, Action.ERROR_SETTING_SESSION);

				}
				break;

			case WITHDRAWAL:
				
				String wSession = (String) in.get(Action.SESSION_ID);
				Integer wlogin = (Integer) in.get(Action.LOGIN_FIELD);
				Integer howMuch = (Integer) in.get(Action.WITHDRAWAL_SUM);

				String ss = dataBase.getCurrentSession(wlogin);
				if (ss.equals(wSession)) {
					double onAcc = dataBase.getBalance(wSession);
					if (onAcc - howMuch < 0.0) {
						out.put(Action.ERROR_CODE, Action.ERROE_NO_MONEY);
						break;
					} else {
						double newBal = onAcc - howMuch;
						if (dataBase.setBalance(newBal, wlogin)) {
							out.put(Action.BALANCE, newBal);
						} else {// something wrong in DB
							out.put(Action.ERROR_CODE, Action.ERROR_NOT_MATCHES);
						}
						break;
					}

				} else {
					out.put(Action.ERROR_CODE, Action.ERROR_SETTING_SESSION);

				}

				break;

			case ADD_MONEY:
				String mSession = (String) in.get(Action.SESSION_ID);
				Integer mlogin = (Integer) in.get(Action.LOGIN_FIELD);
				Double mhowMuch = (Double) in.get(Action.ADD_MONEY);

				String sss = dataBase.getCurrentSession(mlogin);
				if (sss.equals(mSession)) {
					double bal = dataBase.getBalance(mSession);
					bal += mhowMuch;

					if (dataBase.setBalance(bal, mlogin)) {
						out.put(Action.BALANCE, bal);
					} else {
						out.put(Action.ERROR_CODE, Action.ERROR_NOT_MATCHES);
					}
					break;

				} else {
					out.put(Action.ERROR_CODE, Action.ERROR_SETTING_SESSION);

				}

				break;
			}// END of switch

			System.out.println("All ok");
			// System.out.println("session is " + out.get(Action.SESSION_ID));

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
