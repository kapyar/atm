package GUIClient;

import MYGUI.ButtonFactory;
import MYGUI.MetroEditablePane;
import MYGUI.MyButton;
import MYGUI.Numbers;
import MYGUI.RightPanel;
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

public class SendMoney extends RightPanel {

	private MetroEditablePane how;
	private MetroEditablePane whome;
	private MyButton btnOK;
	private MyButton mbtnCancel;
	private Numbers numb;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton;

	public SendMoney() {

		JLabel lblNewLabel = new JLabel("Send Money");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.PLAIN, 27));
		lblNewLabel.setBounds(274, 64, 161, 28);
		add(lblNewLabel);

		radioButton = new JRadioButton("");
		radioButton.setBackground(new Color(51, 153, 255));
		radioButton.setBounds(66, 238, 21, 23);
		radioButton.setSelected(true);
		add(radioButton);

		how = new MetroEditablePane();
		how.setLocation(93, 234);
		add(how);

		whome = new MetroEditablePane();
		whome.setBounds(428, 234, 190, 31);
		add(whome);

		numb = new Numbers();
		numb.setLocation(249, 293);
		add(numb);

		radioButton_1 = new JRadioButton("");
		radioButton_1.setBackground(SystemColor.textHighlight);
		radioButton_1.setBounds(404, 238, 21, 23);
		add(radioButton_1);
		
		ButtonGroup b = new ButtonGroup();
		b.add(radioButton);
		b.add(radioButton_1);

		addInnerListener();

	}

	private void addInnerListener() {
		for (MyButton a : numb.getListOfComponents()) {
			a.addActionListener(new InnerListener());
		}
	}

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
				System.out.println("Pressed ENTER");
			}
			if (source == numb.getMyButton_Cancel()) {
				System.out.println("PRessed Cancel");
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

}// END InnerListener

