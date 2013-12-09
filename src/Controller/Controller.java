package Controller;

import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.jws.WebParam.Mode;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import Actions.Action;
import Actions.ObjLogIn;
import GUIClient.AddMoney;
import GUIClient.AddMoneyPhone;
import GUIClient.AddNewFriend;
import GUIClient.Balance;
import GUIClient.ChooseYourCash;
import GUIClient.ContactList;
import GUIClient.PayBill;
import GUIClient.RePin;
import GUIClient.SendMoney;
import GUIClient.WatchFriends;
import GUIClient.Withdrawal;
import GUIClient.Wrapper;
import GUIClient.MainFormClient;
import GUIClient.StartFrame;
import MYGUI.CheckView;
import MYGUI.IntroSplash;
import MYGUI.RightPanel;
import Model.Model;
import Starter.Test;

public class Controller {

	private StartFrame start;
	private Wrapper wrapper;
	private MainFormClient mainConteiner;
	private Withdrawal withdrawal;
	private ChooseYourCash chooseYourCash;
	private ContactList contactList;
	private AddMoney addMoney;
	private RePin rePin;
	private Balance balance;
	private IntroSplash introSplash;
	private ArrayList<Friend> listOfFriend;

	public void gotoStart() {
		start.clearFields();
		mainConteiner.resetPanel(start);
		start.getCardRadioBtn().setSelected(true);
	}

	public Controller(MainFormClient mainConteiner, StartFrame start,
			Wrapper wrapper) {
		System.out.println("Constructing Controller");
		this.mainConteiner = mainConteiner;
		this.start = start;
		this.wrapper = wrapper;
		this.introSplash = new IntroSplash();

		this.rePin = new RePin();
		this.rePin.addOuterListener(new RePinListener());
		this.mainConteiner.resetPanel(this.start);
		this.start.addOuterListener(new OuterStartActionListener());
		this.wrapper.addNavigationListeners(new NavigationListeners());
		this.mainConteiner.setVisible(true);

	}

	private class RePinListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == rePin.getPanel().getMyButton_Enter()) {
				class MyWorker extends SwingWorker<String, Object> {
					protected String doInBackground() {
						rePin.getProgressBar().setVisible(true);
						rePin.getProgressBar().setIndeterminate(true);
						String login = Model.CURRENT_LOGIN.toString();
						try {
							Model.SESSION_ID = Model.getInstance().doLogIn(
									login,
									rePin.getPanel().getTextView().getPass()
											.getText());
						} catch (InterruptedException | ExecutionException
								| IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						return "Done.";
					}

					protected void done() {
						rePin.getProgressBar().setVisible(false);

						if (Model.SESSION_ID.equals("Failed")) {
							JOptionPane.showConfirmDialog(start,
									"Wrong input data", "Error",
									JOptionPane.PLAIN_MESSAGE,
									JOptionPane.NO_OPTION);
							rePin.getPanel().getTextView().getPass()
									.setText("");
						} else {
							rePin.clearField();
							mainConteiner.resetPanel(wrapper);
						}

					}
				}
				new MyWorker().execute();
			}
		}
	}// END RePinListener

	public class OuterStartActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == start.getMyButton_Enter()) {
				System.out
						.println("-----------------TEST------------------------");
				// System.out.println(Model.getInstance().getlistOfFriends(1));

				System.out.println("-----------------------------------------");

				if (start.checkInputData()) {// if all fields had written
					class MyWorker extends SwingWorker<String, Object> {
						protected String doInBackground() {
							start.progressBar.setVisible(true);
							start.progressBar.setIndeterminate(true);
							try {
								Model.SESSION_ID = Model.getInstance()
										.doLogIn(
												start.getTxt().getTextField()
														.getText(),
												start.getPin().getPass()
														.getText());
								System.out.println("Result autheraised: "
										+ Model.SESSION_ID);
							} catch (InterruptedException | ExecutionException
									| IOException e1) {
								System.out.println("Exception");
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							return "Done.";
						}

						protected void done() {
							start.progressBar.setVisible(false);
							if (Model.SESSION_ID.equals("Failed")) {
								JOptionPane.showConfirmDialog(start,
										"Wrong input data", "Error",
										JOptionPane.PLAIN_MESSAGE,
										JOptionPane.NO_OPTION);
								start.clearFields();
							}

							else {
								wrapper.resetRightPanel(new IntroSplash());
								mainConteiner.resetPanel(wrapper);
								start.clearFields();
							}

						}
					}
					new MyWorker().execute();

					// /END myWORKER

				} else {
					JOptionPane.showConfirmDialog(start, "Wrong input data",
							"Error", JOptionPane.PLAIN_MESSAGE,
							JOptionPane.NO_OPTION);
					start.clearFields();
				}
			}
		}// IF field not empty
	}// END OuterStartActionListener

	private class NavigationListeners implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			if (source == wrapper.getBtnBalance()) {
				balance = new Balance();
				balance.addOuterListener(new BalanceListener());
				wrapper.resetRightPanel(balance);
			}

			if (source == wrapper.getBtnPayBill()) {
				wrapper.resetRightPanel(new PayBill());
			}

			if (source == wrapper.getBtnWithdrawal()) {
				withdrawal = new Withdrawal();
				withdrawal.addOuterListener(new WithdrawalListener());
				wrapper.resetRightPanel(withdrawal);
			}

			if (source == wrapper.getBtnSendMoney()) {
				wrapper.resetRightPanel(new SendMoney());
			}

			if (source == wrapper.getBtnAddMoney()) {
				addMoney = new AddMoney();
				addMoney.addOuterListener(new AddMoneyListener());
				wrapper.resetRightPanel(addMoney);
			}

			if (source == wrapper.getBtnAddMoneyPhone()) {
				wrapper.resetRightPanel(new AddMoneyPhone());
			}
			if (source == wrapper.getBtnContactList()) {
				contactList = new ContactList();
				wrapper.resetRightPanel(contactList);
			}
			if (source == wrapper.getBtmExit()) {
				// System.out.println("Finish Work");
				gotoStart();

			}

		}
	}// END NavigationListeners

	private class BalanceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == balance.getBtnOnDissplay()) {
				class MyWorker extends SwingWorker<String, Object> {

					@Override
					protected String doInBackground() throws Exception {
						balance.getProgressBar().setVisible(true);
						balance.getProgressBar().setIndeterminate(true);

						double bln = Model.getInstance().doBalance();
						balance.getLblNewLabel().setText(
								"You've got: " + bln + " UAH");
						balance.getProgressBar().setVisible(false);
						return "Done";
					}

					protected void done() {
						// balance.getProgressBar().setVisible(false);
						// this way or not - who knows
						try {
							TimeUnit.SECONDS.sleep(3);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						mainConteiner.resetPanel(rePin);

					}

				}
				new MyWorker().execute();
			}
			if (source == balance.getBtnPrint()) {
				class MyWorker extends SwingWorker<String, Object> {
					double bln = 0;
					CheckView checkView;

					@Override
					protected String doInBackground() throws Exception {
						balance.getProgressBar().setVisible(true);
						balance.getProgressBar().setIndeterminate(true);

						bln = Model.getInstance().doBalance();
						checkView = new CheckView((int) bln);

						return "Done";
					}

					protected void done() {
						balance.getProgressBar().setVisible(false);
						wrapper.resetRightPanel(checkView);

					}

				}
				new MyWorker().execute();
			}

		}
	}// END BalanceListener

	private class AddMoneyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == addMoney.getPanel().getMyButton_Enter()) {
				class MyWorker extends SwingWorker<String, Object> {

					double bln = 0;
					CheckView checkView;

					@Override
					protected String doInBackground() throws Exception {
						addMoney.getProgressBar().setVisible(true);
						addMoney.getProgressBar().setIndeterminate(true);

						Integer d = Integer.parseInt(addMoney.getPanel()
								.getTextView().getTextField().getText());

						Model.getInstance().doAddMonney(d);
						CashController.INSTANCE.addCash(d);
						bln = Model.getInstance().doBalance();
						checkView = new CheckView((int) bln);

						return "Done";
					}

					protected void done() {
						addMoney.getProgressBar().setVisible(false);
						wrapper.resetRightPanel(checkView);
					}

				}
				new MyWorker().execute();
			}

		}
	}// END AddMoneyListener

	private class WithdrawalListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == withdrawal.getBtnChoose()) {
				chooseYourCash = new ChooseYourCash();
				chooseYourCash.addOuterListener(new ChooseYourCashListener());
				wrapper.resetRightPanel(chooseYourCash);
			}
		}

	}// END WithdrawalListener

	private class ChooseYourCashListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == chooseYourCash.getBtnEnter()) {
				final Integer howMuch = Integer.parseInt(chooseYourCash
						.getPanel().getTextView().getTextField().getText());
				chooseYourCash.getProgressBar().setVisible(true);
				chooseYourCash.getProgressBar().setIndeterminate(true);
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				final Date date = new Date();

				if (!CashController.INSTANCE.hasEnoughCash(howMuch)) {
					int t = JOptionPane.showConfirmDialog(chooseYourCash, date
							+ "Not enough bills in ATM", "Withdrawal",
							JOptionPane.PLAIN_MESSAGE, JOptionPane.NO_OPTION);
					return;
				}
				class MyWorker extends SwingWorker<String, Object> {

					@Override
					protected String doInBackground() throws Exception {
						double res = Model.getInstance().doWithdrawal(howMuch);
						if (res == -1.0
								|| !CashController.INSTANCE.withdraw(howMuch)) {
							chooseYourCash.getProgressBar().setVisible(false);
							int t = JOptionPane.showConfirmDialog(
									chooseYourCash, date + "Cant get this sum",
									"Withdrawal", JOptionPane.PLAIN_MESSAGE,
									JOptionPane.NO_OPTION);
						} else {
							// cant use side panel

							Test.getController()
									.getWrap()
									.resetRightPanel(
											new CheckView(
													CashController.INSTANCE
															.getLastWithdraw(),
													howMuch, (int) res, true));

						}
						return "Done";

					}

					protected void done() {

						chooseYourCash.getProgressBar().setVisible(false);
					}

				}// END MyWorker
				new MyWorker().execute();

			}
			if (source == chooseYourCash.getBtnCancel()) {
				wrapper.resetRightPanel(withdrawal);
			}

		}

	}// END ChooseYourCashListener

	public Wrapper getWrap() {
		return wrapper;
	}

	public MainFormClient getMainConteiner() {
		return mainConteiner;
	}

	public StartFrame getStart() {
		return start;
	}

	public IntroSplash getIntroSplash() {
		return introSplash;
	}

	public RePin getRePin() {
		return rePin;
	}
}
