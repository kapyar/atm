package GUIClient;

import MYGUI.ButtonFactory;
import MYGUI.MyButton;
import MYGUI.RightPanel;
import Model.Model;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.awt.Font;
import java.io.IOException;

public class Withdrawal extends RightPanel {

	private MyButton btn20;
	private ArrayList<MyButton> list;
	private MyButton btn50;
	private MyButton btn100;
	private MyButton btn200;
	private MyButton btnChoose;
	private MyButton btn500;

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

		btn500 = ButtonFactory.getSelectBtn("500");
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
				int howMuch = 20;
				try {
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					double res = Model.getInstance().doWithdrawal(howMuch);
					if (res == -1.0) {
						int t = JOptionPane.showConfirmDialog(Withdrawal.this,
								date + "Cant get this sum", "Withdrawal",
								JOptionPane.PLAIN_MESSAGE,
								JOptionPane.NO_OPTION);
					} else {
						int t = JOptionPane
								.showConfirmDialog(Withdrawal.this, date
										+ "\nYour current balance:" + res
										+ " UAH", "Balance",
										JOptionPane.PLAIN_MESSAGE,
										JOptionPane.NO_OPTION);
					}

				} catch (IOException | InterruptedException
						| ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}// 20
			if (source == btn50) {
				int howMuch = 50;
				try {
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					double res = Model.getInstance().doWithdrawal(howMuch);
					if (res == -1.0) {
						int t = JOptionPane.showConfirmDialog(Withdrawal.this,
								date + "Cant get this sum", "Withdrawal",
								JOptionPane.PLAIN_MESSAGE,
								JOptionPane.NO_OPTION);
					} else {
						int t = JOptionPane
								.showConfirmDialog(Withdrawal.this, date
										+ "\nYour current balance:" + res
										+ " UAH", "Balance",
										JOptionPane.PLAIN_MESSAGE,
										JOptionPane.NO_OPTION);
					}
				} catch (IOException | InterruptedException
						| ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (source == btn100) {
				int howMuch = 100;
				try {
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					double res = Model.getInstance().doWithdrawal(howMuch);
					if (res == -1.0) {
						int t = JOptionPane.showConfirmDialog(Withdrawal.this,
								date + "Cant get this sum", "Withdrawal",
								JOptionPane.PLAIN_MESSAGE,
								JOptionPane.NO_OPTION);
					} else {
						int t = JOptionPane
								.showConfirmDialog(Withdrawal.this, date
										+ "\nYour current balance:" + res
										+ " UAH", "Balance",
										JOptionPane.PLAIN_MESSAGE,
										JOptionPane.NO_OPTION);
					}
				} catch (IOException | InterruptedException
						| ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (source == btn200) {
				int howMuch = 200;
				try {
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					double res = Model.getInstance().doWithdrawal(howMuch);
					if (res == -1.0) {
						int t = JOptionPane.showConfirmDialog(Withdrawal.this,
								date + "Cant get this sum", "Withdrawal",
								JOptionPane.PLAIN_MESSAGE,
								JOptionPane.NO_OPTION);
					} else {
						int t = JOptionPane
								.showConfirmDialog(Withdrawal.this, date
										+ "\nYour current balance:" + res
										+ " UAH", "Balance",
										JOptionPane.PLAIN_MESSAGE,
										JOptionPane.NO_OPTION);
					}
				} catch (IOException | InterruptedException
						| ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (source == btn500) {
				int howMuch = 500;
				try {
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					double res = Model.getInstance().doWithdrawal(howMuch);
					if (res == -1.0) {
						int t = JOptionPane.showConfirmDialog(Withdrawal.this,
								date + "Cant get this sum", "Withdrawal",
								JOptionPane.PLAIN_MESSAGE,
								JOptionPane.NO_OPTION);
					} else {
						int t = JOptionPane
								.showConfirmDialog(Withdrawal.this, date
										+ "\nYour current balance:" + res
										+ " UAH", "Balance",
										JOptionPane.PLAIN_MESSAGE,
										JOptionPane.NO_OPTION);
					}
				} catch (IOException | InterruptedException
						| ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// /the same

		}

	}// END InnerActionListener

	public MyButton getBtnChoose() {
		return btnChoose;
	}
}
