package GUIClient;

import java.io.IOException;

public class StartGUIClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainForm m;
		try {
			m = new MainForm();
			m.setVisible(true);
			m.setSize(300, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
