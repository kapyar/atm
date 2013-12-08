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
	private MyButton btmExit;
	private MyButton btnPrint;
	private MyButton btnOnDissplay;
	private JLabel lblNewLabel;

	public Wrapper() {
		System.out.println("Wrapper");
		setPnlSide();
		setScrollPane();
		
		UIManager.put("ProgressBar.background", Color.white);
		UIManager.put("ProgressBar.foreground", new Color(63, 210, 253));
//		UIManager.put("ProgressBar.selectionBackground", Color.red);
//		UIManager.put("ProgressBar.selectionForeground", Color.green);
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
		pnlSide.setPreferredSize(new Dimension(110,820));
		setButtons();

	}

	private void setButtons() {
		int _W = 100;
		int _H = 100;
		int _x = 0;
		int _y = 0;
		int _delta = 100;
		
		listOfBalanceButton = new ArrayList<MyButton>();
		pnlSide.setLayout(null);

		btnBalance = ButtonFactory.getIconButton(
				"resources\\imagesClient\\balance_100x100.png",
				"current balance");
		btnBalance.setBounds(_x, _y+_delta*0, _W, _H);
		pnlSide.add(btnBalance);
		listOfBalanceButton.add(btnBalance);

		btnWithdrawal = ButtonFactory.getIconButton(
				"resources\\imagesClient\\money_bag.png", "get withdrawal");
		btnWithdrawal.setBounds(_x, _y+_delta*1, _W, _H);
		pnlSide.add(btnWithdrawal);
		listOfBalanceButton.add(btnWithdrawal);

		btnSendMoney = ButtonFactory.getIconButton(
				"resources\\imagesClient\\money_transfer.png", "send money");
		btnSendMoney.setBounds(_x, _y+_delta*2, _W, _H);
		pnlSide.add(btnSendMoney);
		listOfBalanceButton.add(btnSendMoney);

		btnPayBill = ButtonFactory.getIconButton(
				"resources\\imagesClient\\pay_bill.png", "pay bill");
		btnPayBill.setBounds(_x, _y+_delta*3, _W, _H);
		pnlSide.add(btnPayBill);
		listOfBalanceButton.add(btnPayBill);

		btnAddMoney = ButtonFactory.getIconButton(
				"resources\\imagesClient\\money_pig.png", "add money");
		btnAddMoney.setBounds(_x, _y+_delta*4, _W, _H);
		pnlSide.add(btnAddMoney);
		listOfBalanceButton.add(btnAddMoney);

		btnAddMoneyPhone = ButtonFactory.getIconButton(
				"resources\\imagesClient\\phone.png", "add money to phone");
		btnAddMoneyPhone.setBounds(_x, _y+_delta*5, _W, _H);
		pnlSide.add(btnAddMoneyPhone);
		listOfBalanceButton.add(btnAddMoneyPhone);

		btnContactList = ButtonFactory.getIconButton(
				"resources\\imagesClient\\contact_list.png", "contact list");
		btnContactList.setBounds(_x, _y+_delta*6, _W, _H);
		pnlSide.add(btnContactList);
		listOfBalanceButton.add(btnContactList);
		
		btmExit = ButtonFactory.getIconButton(
				"resources\\imagesClient\\btnExit.png", "finish fork");
		btmExit.setBounds(_x, _y+_delta*7, _W, _H);
		pnlSide.add(btmExit);
		listOfBalanceButton.add(btmExit);
		
		
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
	public MyButton getBtmExit() {
		return btmExit;
	}
	
}
