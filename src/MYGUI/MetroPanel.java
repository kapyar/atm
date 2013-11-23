package MYGUI;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class MetroPanel extends JPanel {
	public MetroPanel () {
		this.setSize(new Dimension(800, 600));
		this.setBackground(new Color(51, 153, 255));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
	}


}
