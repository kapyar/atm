package MYGUI;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MetroTextView extends JTextField{

	public MetroTextView() {
		super();
		setFont(new Font("Segoe UI", Font.PLAIN, 11));
	}
	
	@Override
	public void setBorder(Border border) {
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createEmptyBorder());
	}
}
