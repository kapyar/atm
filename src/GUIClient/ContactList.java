package GUIClient;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import MYGUI.MyButton;
import MYGUI.NumbersWithTextField;
import MYGUI.RightPanel;

public class ContactList extends RightPanel {
	private JLabel lblNewLabel;
	private NumbersWithTextField panel;

	public ContactList() {
		setMyTitle("Contat List");

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblNewLabel.setBounds(256, 113, 223, 45);
		add(lblNewLabel);

		panel = new NumbersWithTextField();
		panel.setBounds(super.getWidth() / 2 - 115, 210, 230, 326);
		add(panel);

	}

}
