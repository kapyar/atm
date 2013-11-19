package Model;

import MYGUI.MetroPanel;

/*
 * 
 * Need to send request to BD and notify about responds from server
 * Consists of all DB fields
 * 
 */
public class Model {

	private static Model firstInstance = null;

	private Model() {
		System.out.println("Constructing the Model");
	}

	public static Model getInstance() {
		if (firstInstance == null) {
			firstInstance = new Model();
		}
		return firstInstance;
	}

	public boolean checkLogIn(String text, String text2) {

		return true;

	}

}
