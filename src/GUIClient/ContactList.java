package GUIClient;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import MYGUI.MyButton;
import MYGUI.RightPanel;

public class ContactList extends RightPanel {

	private MyButton watch;
	private MyButton addFriend;
	private JLabel lblNewLabel;

	public ContactList() {
		setMyTitle("Contat List");

		watch = new MyButton("Peek Your Friend");
		watch.setBounds(0, 338, 132, 45);
		add(watch);

		addFriend = new MyButton("Add New Friend");
		addFriend.setBounds(553, 338, 132, 45);
		add(addFriend);

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblNewLabel.setBounds(256, 113, 223, 45);
		add(lblNewLabel);
	}

	public void addOuterListener(ActionListener a) {
		watch.addActionListener(a);
		addFriend.addActionListener(a);
	}

	public MyButton getWatch() {
		return watch;
	}

	public MyButton getAddFriend() {
		return addFriend;
	}

}
