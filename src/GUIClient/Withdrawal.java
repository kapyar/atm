package GUIClient;

import Controller.CashController;
import Controller.Controller;
import Controller.NotifyController;
import MYGUI.ButtonFactory;
import MYGUI.CheckView;
import MYGUI.MyButton;
import MYGUI.RightPanel;
import Model.Model;
import Server.SQLwrapper;
import Starter.Test;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JProgressBar;

public class Withdrawal extends RightPanel {

	private MyButton btn20;
	private ArrayList<MyButton> list;
	private MyButton btn50;
	private MyButton btn100;
	private MyButton btn200;
	private MyButton btnChoose;
	private MyButton btn500;
	private JProgressBar progressBar;

	public Withdrawal() {

		setMyTitle("Withdrawal");

		list = new ArrayList<MyButton>();
		btn20 = ButtonFactory.getSelectBtn("20");
		btn20.setLocation(2, 260);
		list.add(btn20);
		add(btn20);

		btn50 = ButtonFactory.getSelectBtn("50");
		btn50.setLocation(2, 340);
		list.add(btn50);
		add(btn50);

		btn100 = ButtonFactory.getSelectBtn("100");
		btn100.setLocation(2, 420);

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

		progressBar = new JProgressBar();
		progressBar.setBounds(191, 105, 312, 29);
		progressBar.setVisible(false);
		add(progressBar);

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

		private void withdraw(final int howMuch) {

			Test.getController().getWrap().setDisablePnlSide();

			progressBar.setVisible(true);
			progressBar.setIndeterminate(true);

			class MyWorker extends SwingWorker<String, Object> {
				@Override
				protected String doInBackground() throws Exception {
					double res = Model.getInstance().doWithdrawal(howMuch);
					if (!CashController.INSTANCE.hasEnoughCash(howMuch)) {
						Controller.alert(Withdrawal.this,
								"Not enough bills in the ATM");
					} else if (howMuch % 10 != 0) {
						Controller.alert(Withdrawal.this,
								"Sum must be multiple by 10");
					} else if (howMuch == 0) {
						Controller.alert(Withdrawal.this,
								"You can't withdraw emptiness");
					} else if (res == -1.0
							|| !CashController.INSTANCE.withdraw(howMuch)) {
						progressBar.setVisible(false);
						Controller.alert(Withdrawal.this,
								"You don't have enougn money on your account");

					} else {
						CashController.INSTANCE.withdraw(howMuch);

						NotifyController.INSTANCE.sendSms(
								SQLwrapper.DB.getUserPhone(),
								"You've just withdrawn " + howMuch + "$. "
										+ res + "$ left.\nWith love, MPK");

						Test.getController()
								.getWrap()
								.resetRightPanel(
										new CheckView(CashController.INSTANCE
												.getLastWithdraw(), howMuch,
												(int) res, true));

					}
					return "Done";

				}

				protected void done() {

					progressBar.setVisible(false);
					Test.getController().getWrap().setEnablePnlSide();
				}
			}
			new MyWorker().execute();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == btn20) {
				int howMuch = 20;
				withdraw(howMuch);

			}// 20
			if (source == btn50) {
				int howMuch = 50;
				withdraw(howMuch);
			}
			if (source == btn100) {
				int howMuch = 100;
				withdraw(howMuch);
			}
			if (source == btn200) {
				int howMuch = 200;
				withdraw(howMuch);
			}
			if (source == btn500) {
				int howMuch = 500;
				withdraw(howMuch);
			}
			// /the same

		}

	}// END InnerActionListener

	public MyButton getBtnChoose() {
		return btnChoose;
	}
}
