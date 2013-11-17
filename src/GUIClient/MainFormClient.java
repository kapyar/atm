package GUIClient;

import javax.swing.JFrame;

import java.awt.Dimension;

import java.awt.Toolkit;

import MYGUI.MetroPanel;

public class MainFormClient extends JFrame {

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
		

	}

	public void resetPanel(MetroPanel m) {
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().revalidate();

		getContentPane().add(m);
		getContentPane().repaint();
		getContentPane().revalidate();
	}
}
