package GUIClient;

import java.awt.event.ActionListener;

import MYGUI.MyButton;
import MYGUI.NumbersWithTextField;
import MYGUI.RightPanel;

public class ChooseYourCash extends RightPanel {

	private NumbersWithTextField panel;

	public ChooseYourCash() {

		setMyTitle("Choose Your Cash");

		panel = new NumbersWithTextField();
		panel.setSize(218, 309);
		panel.setLocation(224, 253);
		panel.setAlignmentY(0);
		add(panel);

	}

	// delegate it
	public void addOuterListener(ActionListener a) {
		panel.addOuterListener(a);
	}

	public MyButton getBtnEnter() {
		return panel.getMyButton_Enter();
	}

	public MyButton getBtnCancel() {
		return panel.getMyButton_Cancel();
	}

}
