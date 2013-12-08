package GUIClient;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import MYGUI.MetroPanel;
import MYGUI.NumbersWithTextField;
import javax.swing.JProgressBar;

public class RePin extends MetroPanel {

	private NumbersWithTextField panel;
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
		
		panel = new NumbersWithTextField();
		panel.setBounds(280, 159, 235, 325);
		add(panel);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(250, 109, 312, 29);
		progressBar.setVisible(false);
		add(progressBar);
	}
	
	public void addOuterListener(ActionListener a){
		panel.addOuterListener(a);
	}

	public NumbersWithTextField getPanel() {
		return panel;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
