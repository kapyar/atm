package GUIClient;

import Controller.Friend;
import MYGUI.ButtonFactory;
import MYGUI.MetroEditablePane;
import MYGUI.MyButton;
import MYGUI.Numbers;
import MYGUI.RightPanel;
import Model.Model;
import Server.SQLwrapper;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SendMoney extends RightPanel {

	private MetroEditablePane how;
	private MetroEditablePane whome;
	private MyButton btnOK;
	private MyButton mbtnCancel;
	private Numbers numb;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton;
	private JComboBox comboBox;
	private JLabel lblYourFriends;
	private JLabel lblNewLabel;
	private JLabel lblHowMuch;
	private SQLwrapper dataBase;
	private ArrayList<Friend> listOfFriends;
	private String[] aa;

	public SendMoney() {

		setMyTitle("Send Money");

		dataBase = new SQLwrapper();

		radioButton = new JRadioButton("");
		radioButton.setBackground(new Color(51, 153, 255));
		radioButton.setBounds(202, 300, 21, 23);
		radioButton.setSelected(true);
		add(radioButton);

		how = new MetroEditablePane();
		how.setLocation(225, 296);
		add(how);

		whome = new MetroEditablePane();
		whome.setBounds(469, 239, 210, 31);
		add(whome);

		numb = new Numbers();
		numb.setLocation(16, 228);
		add(numb);

		radioButton_1 = new JRadioButton("");
		radioButton_1.setBackground(SystemColor.textHighlight);
		radioButton_1.setBounds(446, 243, 21, 23);
		add(radioButton_1);

		ButtonGroup b = new ButtonGroup();
		b.add(radioButton);
		b.add(radioButton_1);

		lblNewLabel = new JLabel("To Whome");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setBounds(469, 224, 69, 14);
		add(lblNewLabel);

		lblHowMuch = new JLabel("How Much\r\n");
		lblHowMuch.setForeground(Color.WHITE);
		lblHowMuch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblHowMuch.setBounds(225, 281, 69, 14);
		add(lblHowMuch);

		comboBox = new JComboBox();
		listOfFriends = getListOfFriends();

		setStringArray();
		comboBox.setModel(new DefaultComboBoxModel(aa));
		comboBox.setBounds(225, 239, 210, 31);
		add(comboBox);

		lblYourFriends = new JLabel("Your friends");
		lblYourFriends.setForeground(Color.WHITE);
		lblYourFriends.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblYourFriends.setBounds(225, 224, 69, 14);
		add(lblYourFriends);

		addInnerListener();

	}

	private void setStringArray() {
		System.out.println("StringArray");
		System.out.println(getListOfFriends().size());
		aa = new String[getListOfFriends().size() + 1];
		aa[0] = "Another: 0";
		for (int i = 0; i < aa.length - 1; ++i)
			aa[i + 1] = listOfFriends.get(i).toString();
	}

	private void addInnerListener() {
		for (MyButton a : numb.getListOfComponents()) {
			a.addActionListener(new InnerListener());
		}
		comboBox.addActionListener(new InnerActionComboBox());
	}

	private class InnerActionComboBox implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (comboBox.getSelectedIndex() == 0) {
				radioButton_1.setVisible(true);
				whome.setVisible(true);
				lblNewLabel.setVisible(true);
			} else {
				radioButton.setSelected(true);
				radioButton_1.setVisible(false);
				whome.setVisible(false);
				lblNewLabel.setVisible(false);
				whome.getTextField().setText("");
			}

		}

	}// END InnerActionComboBox

	private class InnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == numb.getMyButton_0()) {
				writeIt("0");
			}
			if (source == numb.getMyButton_1()) {
				writeIt("1");
			}
			if (source == numb.getMyButton_2()) {
				writeIt("2");
			}
			if (source == numb.getMyButton_3()) {
				writeIt("3");
			}
			if (source == numb.getMyButton_4()) {
				writeIt("4");
			}
			if (source == numb.getMyButton_5()) {
				writeIt("5");
			}
			if (source == numb.getMyButton_6()) {
				writeIt("6");
			}
			if (source == numb.getMyButton_7()) {
				writeIt("7");
			}
			if (source == numb.getMyButton_8()) {
				writeIt("8");
			}
			if (source == numb.getMyButton_9()) {
				writeIt("9");
			}

			if (source == numb.getMyButton_Enter()) {
				int howMuch = 0;
				int toWhome = 0;
				try {
					Model.getInstance().doSendMoney(howMuch, toWhome);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (source == numb.getMyButton_Cancel()) {
				how.getTextField().setText("");
				whome.getTextField().setText("");
			}

		}

		private void writeIt(String s) {
			if (radioButton.isSelected()) {
				how.getTextField().setText(how.getTextField().getText() + s);
			} else if (radioButton_1.isSelected()) {
				whome.getTextField()
						.setText(whome.getTextField().getText() + s);
			}
		}

	}

	private ArrayList<Friend> getListOfFriends() {
		if (listOfFriends == null)
			listOfFriends = dataBase.getListFriends(Model.CURRENT_LOGIN);
		return listOfFriends;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}
}// END InnerListener

