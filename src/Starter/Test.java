package Starter;

import java.io.IOException;

import javax.swing.JFrame;

import Controller.Controller;
import GUIClient.Wrapper;
import GUIClient.MainFormClient;
import GUIClient.StartFrame;
import Model.Model;

public class Test {

	private static Controller controller;
	private Model model;
	private static MainFormClient form;
	private static StartFrame start;
	private static Wrapper balance;

	public static void main(String[] args) throws IOException {
		balance = new Wrapper();
		start = new StartFrame();
		form = new MainFormClient();
		controller = new Controller(form, start, balance);

	}
}