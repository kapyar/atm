package MYGUI;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

public class MetroPassView extends JPasswordField{
	
	@Override
	public void setBorder(Border border) {
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createEmptyBorder());
	}

}
