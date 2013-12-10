package MYGUI;

import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

public class MetroPassView extends JPasswordField{
	
	@Override
	public void setBorder(Border border) {
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createEmptyBorder());
	}
	
	@Override
	public void processKeyEvent(KeyEvent ev) {
		char c = ev.getKeyChar();
		try {
			// Ignore all non-printable characters. Just check the printable
			// ones.
			if (c > 31 && c < 5000) {
				Integer.parseInt(c + "");
			}
			super.processKeyEvent(ev);
		} catch (NumberFormatException nfe) {
			// Do nothing. Character inputted is not a number, so ignore it.
		}
	};

}
