package Starter;



import Controller.Controller;
import GUIClient.MainFormClient;
import Model.Model;

public class Test {

	private static Controller controller;
	private static Model model;
	private static MainFormClient form;
   
	public static void main(String[] args) {
		model = new Model();
		form = new MainFormClient();
		controller = new Controller(form, model);
   
	}

}
