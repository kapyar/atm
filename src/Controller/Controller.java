package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUIClient.AddMoney;
import GUIClient.AddMoneyPhone;
import GUIClient.Balance;
import GUIClient.ChooseYourCash;
import GUIClient.ContactList;
import GUIClient.PayBill;
import GUIClient.SendMoney;
import GUIClient.Withdrawal;
import GUIClient.Wrapper;
import GUIClient.MainFormClient;
import GUIClient.StartFrame;

public class Controller {

	private StartFrame start;
	private Wrapper wrapper;
	private MainFormClient mainConteiner;
	private Withdrawal withdrawal;
	private ChooseYourCash chooseYourCash;

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
				System.out.println("Pressed the Enter");
				// balance.addNavigationListeners(new NavigationListeners());
				mainConteiner.resetPanel(wrapper);
			}
		}

	}// END OuterStartActionListener

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
				wrapper.resetRightPanel(new AddMoney());
			}

			if (source == wrapper.getBtnAddMoneyPhone()) {
				wrapper.resetRightPanel(new AddMoneyPhone());
			}
			if (source == wrapper.getBtnContactList()) {
				wrapper.resetRightPanel(new ContactList());
			}
		}

	}// END NavigationListeners

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
				System.out.println("get my cash");
			}
			if (source == chooseYourCash.getBtnCancel()) {
				wrapper.resetRightPanel(withdrawal);
			}

		}

	}// END ChooseYourCashListener
}
