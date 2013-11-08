package dataBase;
import java.awt.EventQueue;

import javax.swing.UIManager;


public class Main {
	
	
	
	private static Start frame;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.put("ScrollBarUI", "dataBase.MetroScrollBar");
					frame = new Start();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
}
