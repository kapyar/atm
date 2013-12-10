package GUIClient;

import java.awt.event.ActionListener;

import javax.swing.JProgressBar;

import MYGUI.MyButton;
import MYGUI.NumbersWithTextField;
import MYGUI.RightPanel;

public class ChooseYourCash extends RightPanel {

	private NumbersWithTextField panel;
	private JProgressBar progressBar;

	public ChooseYourCash() {

		setMyTitle("Choose Your Cash");

		panel = new NumbersWithTextField();
		panel.getTextView().getDel().setLocation(176, 3);
		panel.getTextView().getTextField().setBounds(8, 5, 147, 20);
		panel.getTextView().setBounds(16, 15, 210, 31);
		panel.setSize(250, 309);
		panel.setLocation(224, 253);
		panel.setAlignmentY(0);
		add(panel);
		
		
		progressBar = new JProgressBar();
		progressBar.setBounds(191, 105, 312, 29);
		progressBar.setVisible(false);
		add(progressBar);

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

	public NumbersWithTextField getPanel() {
		return panel;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

}
