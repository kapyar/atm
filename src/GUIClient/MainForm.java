package GUIClient;

import java.io.File;
import java.io.IOException;

import dataBase.MetroPanel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainForm extends JFrame{
	
	public MainForm() throws IOException {
		getContentPane().setLayout(null);
		
		JPanel panel = new MetroPanel();
		panel.setBounds(10, 11, 414, 239);
		getContentPane().add(panel);
		
		ImageIcon start = new ImageIcon(ImageIO.read(new 
				File("C:\\Users\\Yaroslav\\git\\ATMers\\imagesForATM\\balance.png")));
		//setIcon(new ImageIcon(ImageIO.read(new File("path/to/image.png"))))
		JButton btnNewButton = new JButton(start);
		panel.add(btnNewButton);
	}
}
