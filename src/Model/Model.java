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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Server.MultiJabberClient;
import Server.Server;

public class Model {

	public String doDummy(String command) throws InterruptedException,
			ExecutionException, UnknownHostException, IOException {

		System.out.println("Inside dummy");
		ExecutorService ex = Executors.newCachedThreadPool();
		Future<String> res = ex.submit(new MultiJabberClient(command));
		System.out.println("get back in dummy: " + res.get());
		ex.shutdown();

		return res.get();
	}

	public boolean checkLogIn(String text, String text2) {

		return true;

	}

}
