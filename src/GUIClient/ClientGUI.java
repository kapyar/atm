package GUIClient;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.UIManager;

import dataBase.Start;

public class ClientGUI {

private static MainFormClient frame;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.put("ScrollBarUI", "MYGUI.MetroScrollBar");
					frame = new MainFormClient();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}
