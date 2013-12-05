package GUIClient;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.HashMap;

import Controller.CashController;
import MYGUI.MetroPanel;

public class MainFormClient extends JFrame implements WindowListener  {

	/*
	 * Conteiner in which we iput panels to deploy
	 */

	public MainFormClient() {
		System.out.println("Constructing the View");
		setResizable(false);
		setSize(new Dimension(800, 600));
		getContentPane().setLayout(null);

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"resources\\imagesClient\\pig.png"));
		addWindowListener(this); 
		
		try {
			CashController.INSTANCE.loadLocalCash();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Couldn't load local cash ammount");
			e.printStackTrace();
		}
		
//		HashMap<Integer, Integer> bills = new HashMap<Integer, Integer>();
//		bills.put(100,  3);
//		bills.put(50,  6);
//		bills.put(10,  1);
//		CashController.INSTANCE.uploadBills(bills);
	}

	public void resetPanel(MetroPanel m) {
		System.out.println("ResetPanel");
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().revalidate();

		getContentPane().add(m);
		getContentPane().repaint();
		getContentPane().revalidate();
	}

	
	@Override
	public void windowClosing (WindowEvent e) {
		
		System.out.println("Заврешуємо прогу");
		try {
			CashController.INSTANCE.saveLocalCash();
		} catch (IOException e1) {
			System.out.println("Could'n save cash ammount in ATM");
			e1.printStackTrace();
		}
		System.exit(0);
	}

	
	
	
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}

}
