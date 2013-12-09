package GUIClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import MYGUI.NumbersWithTextField;
import MYGUI.RightPanel;
import Model.Model;

public class AddMoney extends RightPanel {

	private NumbersWithTextField panel;

	private int _W;
	private int _H;
	private int _x;
	private int _y;

	private JProgressBar progressBar;

	public AddMoney() {
		_W = super.getWidth();
		_H = super.getHeight();
		_x = super.getX();
		_y = super.getY();

		setMyTitle("Add Money");
		panel = new NumbersWithTextField();
		panel.setBounds(_W / 2 - 115, 210, 230, 326);
		add(panel);

		JLabel lblNewLabel = new JLabel("Input value of money");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setBounds(16, 0, 200, 14);
		panel.add(lblNewLabel);

		progressBar = new JProgressBar();
		progressBar.setBounds(191, 105, 312, 29);
		progressBar.setVisible(false);
		add(progressBar);

		addInnerListener();
	}

	private void addInnerListener() {
		panel.getMyButton_Cancel().addActionListener(new InnerListener());
		// panel.getMyButton_Enter().addActionListener(new InnerListener());
	}

	public void addOuterListener(ActionListener a) {
		panel.getMyButton_Enter().addActionListener(a);

	}

	private class InnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == panel.getMyButton_Cancel()) {
				panel.getTextView().getTextField().setText("");
			}
			
		}
	}// END InnerListener

	public NumbersWithTextField getPanel() {
		return panel;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
}