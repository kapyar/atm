package MYGUI;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Controller.NumberCheker;

public class MetroTextView extends JTextField {

	public MetroTextView() {
		super();
		setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
	}
	
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
			if (c > 31 && c < 127) {
				Integer.parseInt(c + "");
			}
			super.processKeyEvent(ev);
		} catch (NumberFormatException nfe) {
			// Do nothing. Character inputted is not a number, so ignore it.
		}
	};
		
//	@Override
//	public void keyReleased(KeyEvent e) {
//		
//		if ((e.getKeyCode() > 30) && e.getKeyCode()<112) {
//			if (e.getKeyChar() < '0' || e.getKeyChar() > '9') {
//				System.out.println("keyT " + e.getKeyCode());
//				String s = this.getText();
//				// cleanField(this);
//				if (!s.isEmpty()) {
//					s = s.substring(0, s.length() - 1);
//					this.setText(s);
//				}
//			}
//		}
//		if (e.getKeyCode() == 17) {
//			if (!NumberCheker.isNumber(this.getText())) {
//				this.setText("");
//			}
//		}
	};
	
	

