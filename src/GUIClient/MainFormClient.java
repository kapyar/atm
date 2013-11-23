package GUIClient;

import javax.swing.JFrame;

import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
