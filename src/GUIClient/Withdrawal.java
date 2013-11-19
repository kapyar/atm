package GUIClient;

import MYGUI.ButtonFactory;
import MYGUI.MyButton;
import MYGUI.RightPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Font;

public class Withdrawal extends RightPanel {

	private MyButton btn20;
	private ArrayList<MyButton> list;
	private MyButton btn50;
	private MyButton btn100;
	private MyButton btn200;
	private MyButton btnChoose;

	public Withdrawal() {

		setMyTitle("Withdrawal");

		list = new ArrayList<MyButton>();
		btn20 = ButtonFactory.getSelectBtn("20");
		btn20.setLocation(0, 260);
		list.add(btn20);
		add(btn20);

		btn50 = ButtonFactory.getSelectBtn("50");
		btn50.setLocation(0, 340);
		list.add(btn50);
		add(btn50);

		btn100 = ButtonFactory.getSelectBtn("100");
		btn100.setLocation(0, 420);

		list.add(btn100);
		add(btn100);

		btn200 = ButtonFactory.getSelectBtn("200");
		btn200.setBounds(553, 260, 132, 45);
		list.add(btn200);
		add(btn200);

		MyButton btn500 = ButtonFactory.getSelectBtn("500");
		btn500.setBounds(553, 340, 132, 45);
		list.add(btn500);
		add(btn500);

		btnChoose = ButtonFactory.getSelectBtn("Choose");
		btnChoose.setBounds(553, 420, 132, 45);
		// list.add(btnChoose);
		add(btnChoose);

		addInnerListener();

	}

	// set OuterListener to be able to switch panel to input own
	// sum of money
	public void addOuterListener(ActionListener a) {
		btnChoose.addActionListener(a);
	}

	private void addInnerListener() {

		for (MyButton a : list) {
			a.addActionListener(new InnerActionListener());
		}
	}

	private class InnerActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == btn20) {
				System.out.println("Get your 20");
			}
			// /the same

		}

	}// END InnerActionListener

	public MyButton getBtnChoose() {
		return btnChoose;
	}
}
