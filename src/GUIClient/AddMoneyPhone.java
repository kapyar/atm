package GUIClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;


import MYGUI.MetroEditablePane;
import MYGUI.MyButton;
import MYGUI.Numbers;
import MYGUI.RightPanel;

public class AddMoneyPhone extends RightPanel {

	private JRadioButton radioButton;
	private MetroEditablePane numbOfBill;
	private MetroEditablePane sum;
	private Numbers numbPane;
	private JRadioButton radioButton_1;

	public AddMoneyPhone() {
		setMyTitle("Add Phone Money");

		radioButton = new JRadioButton("");
		radioButton.setBackground(new Color(51, 153, 255));
		radioButton.setBounds(344, 356, 21, 23);
		radioButton.setSelected(true);
		add(radioButton);

		numbOfBill = new MetroEditablePane();
		numbOfBill.setLocation(367, 352);
		add(numbOfBill);

		sum = new MetroEditablePane();
		sum.setBounds(367, 295, 210, 31);
		add(sum);

		numbPane = new Numbers();
		numbPane.setLocation(142, 284);
		add(numbPane);

		radioButton_1 = new JRadioButton("");
		radioButton_1.setBackground(SystemColor.textHighlight);
		radioButton_1.setBounds(344, 299, 21, 23);
		add(radioButton_1);

		ButtonGroup b = new ButtonGroup();
		b.add(radioButton);
		b.add(radioButton_1);

		JLabel lblNewLabel = new JLabel("Phone Number");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNewLabel.setBounds(367, 280, 88, 14);
		add(lblNewLabel);

		JLabel lblHowMuch = new JLabel("How Much\r\n");
		lblHowMuch.setForeground(Color.WHITE);
		lblHowMuch.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblHowMuch.setBounds(367, 337, 69, 14);
		add(lblHowMuch);

		addInnerListener();

	}

	private void addInnerListener() {
		for (MyButton a : numbPane.getListOfComponents()) {
			a.addActionListener(new InnerListener());
		}
	}

	private class InnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == numbPane.getMyButton_0()) {
				writeIt("0");
			}
			if (source == numbPane.getMyButton_1()) {
				writeIt("1");
			}
			if (source == numbPane.getMyButton_2()) {
				writeIt("2");
			}
			if (source == numbPane.getMyButton_3()) {
				writeIt("3");
			}
			if (source == numbPane.getMyButton_4()) {
				writeIt("4");
			}
			if (source == numbPane.getMyButton_5()) {
				writeIt("5");
			}
			if (source == numbPane.getMyButton_6()) {
				writeIt("6");
			}
			if (source == numbPane.getMyButton_7()) {
				writeIt("7");
			}
			if (source == numbPane.getMyButton_8()) {
				writeIt("8");
			}
			if (source == numbPane.getMyButton_9()) {
				writeIt("9");
			}

			if (source == numbPane.getMyButton_Enter()) {
				// need to send request to server
				int t = JOptionPane.showConfirmDialog(AddMoneyPhone.this,
						"You send " + sum.getTextField().getText() + "UAH\n"
								+ "to " + numbOfBill.getTextField().getText()
								+ " number", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (t == 0) {
					System.out.println("Send " + sum.getTextField().getText());
					sum.getTextField().setText("");
					numbOfBill.getTextField().setText("");
				}
			}
			if (source == numbPane.getMyButton_Cancel()) {
				numbOfBill.getTextField().setText("");
				sum.getTextField().setText("");
			}

		}

		private void writeIt(String s) {
			if (radioButton.isSelected()) {
				numbOfBill.getTextField().setText(
						numbOfBill.getTextField().getText() + s);
			} else if (radioButton_1.isSelected()) {
				sum.getTextField().setText(sum.getTextField().getText() + s);
			}
		}

	}// END InnerListener

}
