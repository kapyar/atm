package GUIClient;

import MYGUI.NumbersWithTextField;
import MYGUI.RightPanel;

public class AddNewFriend extends RightPanel {

	private NumbersWithTextField panel;
	
	public AddNewFriend() {
		setMyTitle("Add New Friend");
		panel = new NumbersWithTextField();
		add(panel);
		
	}

}
