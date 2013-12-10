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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import dataBase.Database;
import Actions.Action;
import Controller.Friend;
import Server.MultiJabberClient;
import Server.SQLwrapper;
import Server.Server;

public class Model {

	public static boolean isDone = false;

	public static String SESSION_ID = null;
	public static Integer CURRENT_LOGIN = null;

	private static Model instance = null;

	private SQLwrapper db;

	private Model() {
		db = SQLwrapper.DB;

	}

	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;

	}

	public String doLogIn(final String login, final String pass)
			throws InterruptedException, ExecutionException,
			UnknownHostException, IOException {

		// make a command to server
		HashMap<Action, Object> command = new HashMap<Action, Object>();
		command.put(Action.ACTION, Action.LOG_IN);
		Integer intLogin = Integer.parseInt(login);
		CURRENT_LOGIN = intLogin;
		command.put(Action.LOGIN_FIELD, intLogin);
		command.put(Action.PASS_FIELD, pass);

		ExecutorService ex = Executors.newCachedThreadPool();
		Future<HashMap<Action, Object>> res = ex.submit(new MultiJabberClient(
				command));
		ex.shutdown();

		if (checkLogIn(res.get())) {
			return (String) res.get().get(Action.SESSION_ID);
		} else
			return "Failed";
	}

	public double doBalance() throws InterruptedException, ExecutionException,
			IOException {
		// make a command to server
		HashMap<Action, Object> command = new HashMap<Action, Object>();
		command.put(Action.ACTION, Action.BALANCE);
		command.put(Action.SESSION_ID, SESSION_ID);
		command.put(Action.LOGIN_FIELD, CURRENT_LOGIN);

		ExecutorService ex = Executors.newCachedThreadPool();
		Future<HashMap<Action, Object>> res = ex.submit(new MultiJabberClient(
				command));
		ex.shutdown();

		res.get();
		double balance = (double) res.get().get(Action.BALANCE);

		// double balance = 0;
		return balance;
	}

	public double doWithdrawal(Integer howMuch) throws IOException,
			InterruptedException, ExecutionException {
		HashMap<Action, Object> command = new HashMap<Action, Object>();
		command.put(Action.ACTION, Action.WITHDRAWAL);
		command.put(Action.SESSION_ID, SESSION_ID);
		command.put(Action.LOGIN_FIELD, CURRENT_LOGIN);
		command.put(Action.WITHDRAWAL_SUM, howMuch);

		ExecutorService ex = Executors.newCachedThreadPool();
		Future<HashMap<Action, Object>> res = ex.submit(new MultiJabberClient(
				command));
		ex.shutdown();

		double toReturn = 0;
		if (checkWithdrawal(res.get())) {
			toReturn = (double) res.get().get(Action.BALANCE);
		} else {
			toReturn = -1;
		}
		return toReturn;
	}

	public void doAddMonney(Integer d) throws IOException {
		HashMap<Action, Object> command = new HashMap<Action, Object>();
		command.put(Action.ACTION, Action.ADD_MONEY);
		command.put(Action.SESSION_ID, SESSION_ID);
		command.put(Action.LOGIN_FIELD, CURRENT_LOGIN);
		command.put(Action.ADD_MONEY, d);

		ExecutorService ex = Executors.newCachedThreadPool();
		Future<HashMap<Action, Object>> res = ex.submit(new MultiJabberClient(
				command));
		ex.shutdown();

	}

	private boolean checkWithdrawal(HashMap<Action, Object> hashMap) {
		// TODO Auto-generated method stub
		if ((Action) hashMap.get(Action.ERROR_CODE) != null) {

			switch ((Action) hashMap.get(Action.ERROR_CODE)) {

			case ERROE_NO_MONEY:
				return false;

			case ERROR_NOT_MATCHES:
				return false;

			}// SWITCH
		}// IF

		return true;

	}

	// HELP FUNCTIONS
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

	// TEST
	public ArrayList<Friend> getlistOfFriends(Integer accNum) {
		ArrayList<Friend> list = db.getListFriends(accNum);
		return list;

	}

	public void doSendMoney(Integer howMuch, Integer toWhome)
			throws IOException {
		HashMap<Action, Object> command = new HashMap<Action, Object>();
		command.put(Action.ACTION, Action.SEND_MONEY);
		command.put(Action.SESSION_ID, SESSION_ID);
		command.put(Action.LOGIN_FIELD, CURRENT_LOGIN);
		command.put(Action.SEND_MONEY, howMuch);
		command.put(Action.TO_WHOME, toWhome);

		ExecutorService ex = Executors.newCachedThreadPool();
		Future<HashMap<Action, Object>> res = ex.submit(new MultiJabberClient(
				command));
		ex.shutdown();

	}

	public void addFriend(Integer id) throws IOException {
		HashMap<Action, Object> command = new HashMap<Action, Object>();
		command.put(Action.ACTION, Action.ADD_FRIEND);
		command.put(Action.SESSION_ID, SESSION_ID);
		command.put(Action.LOGIN_FIELD, CURRENT_LOGIN);
		command.put(Action.ADD_FRIEND, id);

		ExecutorService ex = Executors.newCachedThreadPool();
		Future<HashMap<Action, Object>> res = ex.submit(new MultiJabberClient(
				command));
		ex.shutdown();

	}

}
