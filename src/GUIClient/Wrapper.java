package GUIClient;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import MYGUI.ButtonFactory;
import MYGUI.MetroPanel;
import MYGUI.MetroScrollBar;
import MYGUI.MyButton;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

//which wrapped Left(scroll) and right panels
public class Wrapper extends MetroPanel {

	private MetroPanel pnlBalance;
	private JScrollPane scrollPane;

	private MetroPanel pnlSide;

	private ArrayList<MyButton> listOfBalanceButton;
	private MyButton btnWithdrawal;

	private MyButton btnSendMoney;
	private MyButton btnPayBill;
	private MyButton btnAddMoney;
	private MyButton btnAddMoneyPhone;
	private MyButton btnContactList;
	private MyButton btnBalance;
	private MyButton btnPrint;
	private MyButton btnOnDissplay;
	private JLabel lblNewLabel;

	public Wrapper() {
		System.out.println("Wrapper");
		setPnlSide();
		setScrollPane();
		
		UIManager.put("ProgressBar.background", Color.white);
		UIManager.put("ProgressBar.foreground", new Color(63, 210, 253));
		UIManager.put("ProgressBar.selectionBackground", Color.red);
		UIManager.put("ProgressBar.selectionForeground", Color.green);
	}

	public void resetRightPanel(MetroPanel m) {
		
		
		if(pnlBalance!= null){
			remove(pnlBalance);
			repaint();
			revalidate();
		}
		pnlBalance = m;
		add(pnlBalance);
		repaint();
		revalidate();

	}

	// add listeners
	public void addNavigationListeners(ActionListener a) {
		for (MyButton b : listOfBalanceButton) {
			b.addActionListener(a);
		}
	}

	private void setScrollPane() {
		System.out.println("Scroll panel navigation");
		scrollPane = new JScrollPane(pnlSide,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 116, 595);
		int increment = 20;
		scrollPane.getVerticalScrollBar().setUI(new MetroScrollBar());
		scrollPane.getVerticalScrollBar().setUnitIncrement(increment);
		scrollPane.getHorizontalScrollBar().setUI(new MetroScrollBar());
		scrollPane.getHorizontalScrollBar().setUnitIncrement(increment);
		add(scrollPane);

	}

	private void setPnlSide() {
		pnlSide = new MYGUI.MetroPanel();
		//pnlSide.setBounds(0, 0, 110, 700);
		pnlSide.setPreferredSize(new Dimension(110,720));
		setButtons();

	}

	private void setButtons() {

		listOfBalanceButton = new ArrayList<MyButton>();
		pnlSide.setLayout(null);

		btnBalance = ButtonFactory.getIconButton(
				"resources\\imagesClient\\balance_100x100.png",
				"current balance");
		btnBalance.setBounds(0, 0, 100, 100);
		pnlSide.add(btnBalance);
		listOfBalanceButton.add(btnBalance);

		btnWithdrawal = ButtonFactory.getIconButton(
				"resources\\imagesClient\\money_bag.png", "get withdrawal");
		btnWithdrawal.setBounds(0, 100, 100, 100);
		pnlSide.add(btnWithdrawal);
		listOfBalanceButton.add(btnWithdrawal);

		btnSendMoney = ButtonFactory.getIconButton(
				"resources\\imagesClient\\money_transfer.png", "send money");
		btnSendMoney.setBounds(0, 200, 100, 100);
		pnlSide.add(btnSendMoney);
		listOfBalanceButton.add(btnSendMoney);

		btnPayBill = ButtonFactory.getIconButton(
				"resources\\imagesClient\\pay_bill.png", "pay bill");
		btnPayBill.setBounds(0, 300, 100, 100);
		pnlSide.add(btnPayBill);
		listOfBalanceButton.add(btnPayBill);

		btnAddMoney = ButtonFactory.getIconButton(
				"resources\\imagesClient\\money_pig.png", "add money");
		btnAddMoney.setBounds(0, 400, 100, 100);
		pnlSide.add(btnAddMoney);
		listOfBalanceButton.add(btnAddMoney);

		btnAddMoneyPhone = ButtonFactory.getIconButton(
				"resources\\imagesClient\\phone.png", "add money to phone");
		btnAddMoneyPhone.setBounds(0, 500, 100, 100);
		pnlSide.add(btnAddMoneyPhone);
		listOfBalanceButton.add(btnAddMoneyPhone);

		btnContactList = ButtonFactory.getIconButton(
				"resources\\imagesClient\\contact_list.png", "contact list");
		btnContactList.setBounds(0, 600, 100, 100);
		pnlSide.add(btnContactList);
		listOfBalanceButton.add(btnContactList);
	}

	public MyButton getBtnWithdrawal() {
		return btnWithdrawal;
	}

	public MyButton getBtnSendMoney() {
		return btnSendMoney;
	}

	public MyButton getBtnPayBill() {
		return btnPayBill;
	}

	public MyButton getBtnAddMoney() {
		return btnAddMoney;
	}

	public MyButton getBtnAddMoneyPhone() {
		return btnAddMoneyPhone;
	}

	public MyButton getBtnContactList() {
		return btnContactList;
	}

	public MyButton getBtnBalance() {
		return btnBalance;
	}
}
