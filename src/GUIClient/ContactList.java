package GUIClient;

import java.awt.Font;

import javax.swing.JLabel;

import MYGUI.RightPanel;

public class ContactList extends RightPanel {

	public ContactList() {
		JLabel lblBalance = new JLabel("Contact List");
		lblBalance.setFont(new Font("Viner Hand ITC", Font.PLAIN, 27));
		lblBalance.setBounds(305, 62, 140, 36);
		add(lblBalance);
	}

}
