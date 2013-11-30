package Model;

import java.awt.image.RescaleOp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Actions.Action;
import Server.ClientMain;
import Server.MultiJabberClient;
import Server.Server;

public class Model {

	public static String SESSION_ID;

	private static Model instance = null;

	private Model() {

	}

	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;

	}

	public String doDummy(String login, String pass)
			throws InterruptedException, ExecutionException,
			UnknownHostException, IOException {

		// make a command to server
		HashMap<Action, Object> command = new HashMap<Action, Object>();
		command.put(Action.ACTION, Action.LOG_IN);
		Integer intLogin = Integer.parseInt(login);
		command.put(Action.LOGIN_FIELD, intLogin);
		command.put(Action.PASS_FIELD, pass);

		System.out.println("Inside dummy");
		ExecutorService ex = Executors.newCachedThreadPool();
		Future<HashMap<Action, Object>> res = ex.submit(new MultiJabberClient(
				command));
		// System.out.println("get back in dummy: " + res.get());
		ex.shutdown();

		if (checkLogIn(res.get()))
			return (String) res.get().get(Action.SESSION_ID);
		else
			return "Failed";
	}

	private boolean checkLogIn(HashMap<Action, Object> hashMap) {

		if ((Action) hashMap.get(Action.ERROR_CODE) != null) {
			switch ((Action) hashMap.get(Action.ERROR_CODE)) {

			case ERROR_NOT_MATCHES:
				System.out.println("ERROR_NOT_MATCHES");
				return false;

			case ERROR_NO_USER:
				System.out.println("ERROR_NO_USER");
				return false;

			case ERROR_SETTING_SESSION:
				System.out.println("ERROR_SETTING_SESSION");
				return false;
			}
		}

		return true;

	}

	public boolean checkLogIn(String text, String text2) {

		return true;

	}

}
