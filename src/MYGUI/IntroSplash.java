package MYGUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class IntroSplash extends RightPanel {
	
	public IntroSplash () {
		JLabel logo = new JLabel();
		logo.setSize(400, 400);
		logo.setLocation(110, 70);
		ImageIcon start = new ImageIcon("resources\\logo.png");
		logo.setIcon(start);
		this.add(logo);
	}
	
}
