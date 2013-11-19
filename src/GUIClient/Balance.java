package GUIClient;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import MYGUI.MetroPanel;
import MYGUI.MyButton;
import MYGUI.RightPanel;

public class Balance extends RightPanel {

	private MyButton btnOnDissplay;
	private MyButton btnPrint;
	private JLabel lblNewLabel;

	public Balance() {

		setMyTitle("Balance");

		btnOnDissplay = new MyButton("On display");
		btnOnDissplay.setBounds(0, 338, 132, 45);
		add(btnOnDissplay);

		btnPrint = new MyButton("Print");
		btnPrint.setBounds(553, 338, 132, 45);
		add(btnPrint);

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblNewLabel.setBounds(256, 113, 223, 45);
		add(lblNewLabel);

		addInnerListener();
	}

	private void addInnerListener() {
		btnOnDissplay.addActionListener(new ActionListenerBalance());
		btnPrint.addActionListener(new ActionListenerBalance());
	}

	private class ActionListenerBalance implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			System.out.println("In the ActionListenerBalance");

			if (source == btnOnDissplay) {
				// need to add model which show how much do we have
				lblNewLabel.setText("Your current balance: ");
			}

			if (source == btnPrint) {
				lblNewLabel.setText("Please take your receipt");
			}

		}
	}// END ActionListenerBalance
}