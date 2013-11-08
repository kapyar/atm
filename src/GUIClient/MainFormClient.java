package GUIClient;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

import MYGUI.MenuButton;
import MYGUI.MetroPanel;
import MYGUI.MetroScrollBar;

public class MainFormClient extends JFrame {

	private MetroPanel pnlSide;
	private MetroPanel pnlBalance;
	private MenuButton btnBalance;
	private MenuButton btnWithdrawal;
	private MenuButton btnSendMoney;
	private MenuButton btnPayBill;
	private MenuButton btnAddMoney;
	private MenuButton btnAddMoneyPhone;
	private MenuButton btnContactList;
	private JScrollPane scrollPane;

	public MainFormClient() throws IOException {

		setResizable(false);
		setSize(new Dimension(800, 600));
		getContentPane().setLayout(null);

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"resources\\imagesClient\\pig.png"));

		setPnlSide();
		setPnlBalance();
		addButtons();

		/*
		 * btnDimention = new Dimension(100, 100);
		 * 
		 * MetroPanel mainPanel = new MetroPanel();
		 * getContentPane().add(mainPanel); ImageIcon start = new ImageIcon(
		 * "resources\\imagesClient\\balance_100x100.png");
		 * 
		 * JPanel pnlSideLeft = new JPanel(); pnlSideLeft.setBounds(0, 0, 113,
		 * 571);
		 * 
		 * pnlSideLeft.setBackground(new Color(255,255,255)); // JScrollPane
		 * scrollPane = new JScrollPane(pnlSideLeft, //
		 * JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, //
		 * ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED); //
		 * pnlSideLeft.setLayout(new ScrollPaneLayout());
		 * 
		 * 
		 * MyButton btnNewButton = new MyButton();
		 * btnNewButton.setAlignmentY(0.0f); btnNewButton.setIcon(start);
		 * pnlSideLeft.add(btnNewButton);
		 * 
		 * 
		 * // // MyButton myButton = new MyButton(); //
		 * pnlSideLeft.add(myButton); // // MyButton myButton_1 = new
		 * MyButton(); // pnlSideLeft.add(myButton_1); // // MyButton myButton_2
		 * = new MyButton(); // pnlSideLeft.add(myButton_2);
		 * 
		 * 
		 * //scrollPane.setBounds(0, 0, 140, 571); //mainPanel.add(scrollPane);
		 * mainPanel.add(pnlSideLeft); pnlSideLeft.setLayout(new
		 * BoxLayout(pnlSideLeft, BoxLayout.Y_AXIS));
		 * 
		 * 
		 * }
		 */

	}

	private void setPnlBalance() {
		pnlBalance = new MYGUI.MetroPanel();
		pnlBalance.setBounds(119, 0, 675, 571);
		getContentPane().add(pnlBalance);
		pnlBalance.setLayout(null);

	}

	private void setPnlSide() {
		pnlSide = new MYGUI.MetroPanel();
		pnlSide.setBounds(0,0,120,700);
		scrollPane = new JScrollPane(pnlSide,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnlSide.setLayout(null);
		
		int increment = 20;
		scrollPane.getVerticalScrollBar().setUI(new MetroScrollBar());
		scrollPane.getVerticalScrollBar().setUnitIncrement(increment);
		scrollPane.setCursor(new Cursor(Cursor.HAND_CURSOR));//need to find wrist!!
		scrollPane.setBounds(0, 0, 120, 600);
		getContentPane().add(scrollPane);
		invalidate();
		validate();
		repaint();

	}

	private void addButtons() {
		btnBalance = new MenuButton(
				"resources\\imagesClient\\balance_100x100.png","current balance");
		btnBalance.setBounds(0, 0, 100, 100);
		pnlSide.add(btnBalance);
		
		btnWithdrawal = new MenuButton(
				"resources\\imagesClient\\money_bag.png","get withdrawal");
		btnWithdrawal.setBounds(0, 100, 100, 100);
		pnlSide.add(btnWithdrawal);
		
		btnSendMoney = new MenuButton(
				"resources\\imagesClient\\money_transfer.png","send money");
		btnSendMoney.setBounds(0, 200, 100, 100);
		pnlSide.add(btnSendMoney);
		
		btnPayBill = new MenuButton(
				"resources\\imagesClient\\pay_bill.png","pay bill");
		btnPayBill.setBounds(0, 300, 100, 100);
		pnlSide.add(btnPayBill);
		
		btnAddMoney = new MenuButton(
				"resources\\imagesClient\\money_pig.png","add money");
		btnAddMoney.setBounds(0, 400, 100, 100);
		pnlSide.add(btnAddMoney);
		
		btnAddMoneyPhone = new MenuButton(
				"resources\\imagesClient\\phone.png","add money to phone");
		btnAddMoneyPhone.setBounds(0, 500, 100, 100);
		pnlSide.add(btnAddMoneyPhone);
		
		btnContactList = new MenuButton(
				"resources\\imagesClient\\contact_list.png","contact list");
		btnContactList.setBounds(0, 600, 100, 100);
		pnlSide.add(btnContactList);
	}
}
