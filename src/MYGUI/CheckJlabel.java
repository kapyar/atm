package MYGUI;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CheckJlabel extends JLabel {
	
	public CheckJlabel (String data, int width, int height) {
		super(data);
		setSize(width, height);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("resources\\dotty.ttf").toURI().toURL().openStream());
			font = font.deriveFont(Font.PLAIN,15);
			this.setFont(font);
			
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		setVerticalAlignment(SwingConstants.TOP);
		setHorizontalAlignment(SwingConstants.LEFT);

	}
	
}
