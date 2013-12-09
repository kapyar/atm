package Controller;

import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

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

public class Controller {

	private StartFrame start;
	private Wrapper wrapper;
	private MainFormClient mainConteiner;
	private Withdrawal withdrawal;
	private ChooseYourCash chooseYourCash;
	private ContactList contactList;
	private AddMoney addMoney;

	public Controller(MainFormClient mainConteiner, StartFrame start,
			Wrapper wrapper) {
		System.out.println("Constructing Controller");

		// try {
		// Class.forName("GUIClient.AddMoney");
		// Class.forName("GUIClient.AddMoneyPhone");
		// Class.forName("GUIClient.Balance");
		// Class.forName("GUIClient.ContactList");
		// Class.forName("GUIClient.PayBill");
		// Class.forName("GUIClient.Withdrawal");
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		this.mainConteiner = mainConteiner;
		this.start = start;
		this.wrapper = wrapper;
		this.mainConteiner.resetPanel(start);
		this.start.addOuterListener(new OuterStartActionListener());
		this.wrapper.addNavigationListeners(new NavigationListeners());
		this.mainConteiner.setVisible(true);

	}

	public class OuterStartActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == start.getMyButton_Enter()) {
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
								
							}

						}
					}
					new MyWorker().execute();

					// /END myWORKER

				}// END OuterStartActionListener
			}
		}// IF field not empty
	}

	class NavigationListeners implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			if (source == wrapper.getBtnBalance()) {
				wrapper.resetRightPanel(new Balance());
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
				contactList.addOuterListener(new ContactListListener());
				wrapper.resetRightPanel(contactList);
			}
		}
	}// END NavigationListeners

	private class AddMoneyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == addMoney.getPanel().getMyButton_Enter()) {
				double d = Double.parseDouble(addMoney.getPanel().getTextView()
						.getTextField().getText());

				try {
					Model.getInstance().doAddMonney(d);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					int t = JOptionPane.showConfirmDialog(addMoney, "You add "
							+ addMoney.getPanel().getTextView().getTextField()
									.getText() + " UAH"
							+ "\n You current balance: "
							+ Model.getInstance().doBalance() + " UAH", "Info",
							JOptionPane.PLAIN_MESSAGE, JOptionPane.NO_OPTION);
				} catch (HeadlessException | InterruptedException
						| ExecutionException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

	}// END AddMoneyListener

	private class ContactListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == contactList.getWatch()) {
				wrapper.resetRightPanel(new WatchFriends());
			}

			if (source == contactList.getAddFriend()) {
				wrapper.resetRightPanel(new AddNewFriend());
			}

		}

	}// END ContactListListener
	
	private class CheckListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			/*if (source == withdrawal.getBtnChoose()) {
				chooseYourCash = new ChooseYourCash();
				chooseYourCash.addOuterListener(new ChooseYourCashListener());
				wrapper.resetRightPanel(chooseYourCash);
			}*/
		}

	}// END CheckListener

	private class WithdrawalListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			System.out.println("Key pressed");
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
				Integer howMuch = Integer.parseInt(chooseYourCash.getPanel()
						.getTextView().getTextField().getText());
				try {
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					double res = Model.getInstance().doWithdrawal(howMuch);
					if (res == -1.0) {
						int t = JOptionPane.showConfirmDialog(wrapper, date
								+ "\nCant get this sum", "Withdrawal",
								JOptionPane.PLAIN_MESSAGE,
								JOptionPane.NO_OPTION);

					} else {
						int t = JOptionPane.showConfirmDialog(wrapper, date
								+ "\nYour current balance:" + res + " UAH",
								"Balance", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.NO_OPTION);
					}
					chooseYourCash.getPanel().getTextView().getTextField()
							.setText("");
				} catch (IOException | InterruptedException
						| ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (source == chooseYourCash.getBtnCancel()) {
				wrapper.resetRightPanel(withdrawal);
			}

		}

	}// END ChooseYourCashListener
	
	// remove later
	public Wrapper getWrap() {
		return wrapper;
	}
}
