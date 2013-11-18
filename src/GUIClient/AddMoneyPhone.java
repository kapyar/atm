package GUIClient;

import java.awt.Font;

import javax.swing.JLabel;

import MYGUI.RightPanel;

public class AddMoneyPhone extends RightPanel {

	public AddMoneyPhone() {
		JLabel lblBalance = new JLabel("AddMoneyPhone");
		lblBalance.setFont(new Font("Viner Hand ITC", Font.PLAIN, 27));
		lblBalance.setBounds(305, 62, 225, 36);
		add(lblBalance);
	}

}
