package GUIClient;

import MYGUI.NumbersWithTextField;
import MYGUI.RightPanel;
import javax.swing.JLabel;

public class ChooseYourCash extends RightPanel {

	private NumbersWithTextField panel;

	public ChooseYourCash() {
		
		setMyTitle("Choose Your Cash");
		
		panel = new NumbersWithTextField();
		panel.setLocation(224, 291);
		add(panel);
		
	}
}
