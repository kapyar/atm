package GUIClient;

import java.awt.Font;

import javax.swing.JLabel;

import MYGUI.RightPanel;

public class PayBill extends RightPanel {

	public PayBill() {
		JLabel lblBalance = new JLabel("PayBill");
		lblBalance.setFont(new Font("Viner Hand ITC", Font.PLAIN, 27));
		lblBalance.setBounds(305, 62, 124, 36);
		add(lblBalance);
	}

}
