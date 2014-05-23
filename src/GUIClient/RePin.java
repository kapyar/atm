package GUIClient;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import MYGUI.ConfigGUICLient;
import MYGUI.MetroEditablePin;
import MYGUI.MetroPanel;
import MYGUI.MetroPassView;
import MYGUI.NumbersWithPin;
import MYGUI.NumbersWithTextField;
import Starter.Test;

import javax.swing.JProgressBar;

public class RePin extends MetroPanel {

	private NumbersWithPin panel;
	private JLabel lblBalance;
	private JProgressBar progressBar;

	public RePin() {

		lblBalance = new JLabel("Input PIN", JLabel.CENTER);
		lblBalance.setFont(new Font(ConfigGUICLient.FontType, Font.PLAIN, 27));
		lblBalance.setBounds(((super.getWidth()) / 2) - ConfigGUICLient._WTL
				/ 2, ConfigGUICLient._yTL, ConfigGUICLient._WTL,
				ConfigGUICLient._HTL);
		lblBalance.setAlignmentX(CENTER_ALIGNMENT);
		add(lblBalance);

		panel = new NumbersWithPin();
		panel.setBounds(280, 159, 235, 325);
		panel.getMyButton_Cancel().setText("Exit");
		add(panel);

		progressBar = new JProgressBar();
		progressBar.setBounds(250, 109, 312, 29);
		progressBar.setVisible(false);
		add(progressBar);
	}

	public void addOuterListener(ActionListener a) {
		panel.addOuterListener(a);
		panel.getMyButton_Cancel().addActionListener(a);
	}

	public NumbersWithPin getPanel() {
		return panel;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void clearField() {
		panel.getTextView().getPass().setText("");
	}
}
