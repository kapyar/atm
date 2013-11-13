package GUIClient;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MYGUI.ButtonFactory;
import MYGUI.MetroPanel;
import MYGUI.MetroScrollBar;
import MYGUI.MyButton;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class MainFormClient extends JFrame {

	private MetroPanel pnlSide;
	private MetroPanel pnlBalance;
	private MetroPanel pnlStart;
	private MetroPanel panel;
	private MyButton btnBalance;
	private MyButton btnWithdrawal;
	private MyButton btnSendMoney;
	private MyButton btnPayBill;
	private MyButton btnAddMoney;
	private MyButton btnAddMoneyPhone;
	private MyButton btnContactList;
	private JScrollPane scrollPane;
	private JTextField txtCardNumb;

	private MyButton myButton_Cancel;
	private MyButton myButton_1;
	private MyButton myButton_2;
	private MyButton myButton_3;
	private MyButton myButton_6;
	private MyButton myButton_5;
	private MyButton myButton_4;
	private MyButton myButton_7;
	private MyButton myButton_8;
	private MyButton myButton_9;
	private MyButton myButton_0;
	private MyButton myButton_Enter;
	private JPasswordField txtPin;
	private MyButton btnDelNumbCard;
	private MyButton btnDelPin;
	
	private JRadioButton rdbtPass;
	private JRadioButton rdbtnCardNumb;

	private ArrayList<MyButton> listOfComponents = new ArrayList<MyButton>();

	public MainFormClient() {

		setResizable(false);
		setSize(new Dimension(800, 600));
		getContentPane().setLayout(null);

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"resources\\imagesClient\\pig.png"));

		setPnlStart();
		
	}

	private void setPnlStart() {
		pnlStart = new MetroPanel();
		pnlStart.setSize(new Dimension(800, 600));
		getContentPane().add(pnlStart);
		pnlStart.setLayout(null);

		panel = new MetroPanel();
		panel.setBounds(204, 116, 435, 296);
		panel.setOpaque(true);
		panel.setBackground(new Color(40, 140, 255));
		panel.setVisible(true);
		panel.setAlignmentX(pnlStart.CENTER_ALIGNMENT);
		pnlStart.add(panel);

		myButton_1 = ButtonFactory.getNumbButton("1", '1');
		myButton_1.setLocation(24, 79);
		panel.add(myButton_1);
		listOfComponents.add(myButton_1);

		myButton_2 = ButtonFactory.getNumbButton("2", '2');
		myButton_2.setBounds(76, 79, 42, 42);
		panel.add(myButton_2);
		listOfComponents.add(myButton_2);

		myButton_3 = ButtonFactory.getNumbButton("3", '3');
		myButton_3.setBounds(127, 79, 42, 42);
		panel.add(myButton_3);
		listOfComponents.add(myButton_3);

		myButton_6 = ButtonFactory.getNumbButton("6", '6');
		myButton_6.setBounds(127, 132, 42, 42);
		panel.add(myButton_6);
		listOfComponents.add(myButton_6);

		myButton_5 = ButtonFactory.getNumbButton("5", '5');
		myButton_5.setBounds(76, 132, 42, 42);
		panel.add(myButton_5);
		listOfComponents.add(myButton_5);

		myButton_4 = ButtonFactory.getNumbButton("4", '4');
		myButton_4.setBounds(24, 132, 42, 42);
		panel.add(myButton_4);
		listOfComponents.add(myButton_4);

		myButton_7 = ButtonFactory.getNumbButton("7", '7');
		myButton_7.setBounds(24, 185, 42, 42);
		panel.add(myButton_7);
		listOfComponents.add(myButton_7);

		myButton_8 = ButtonFactory.getNumbButton("8", '8');
		myButton_8.setBounds(76, 185, 42, 42);
		panel.add(myButton_8);
		listOfComponents.add(myButton_8);

		myButton_9 = ButtonFactory.getNumbButton("9", '9');
		myButton_9.setBounds(127, 185, 42, 42);
		panel.add(myButton_9);
		listOfComponents.add(myButton_9);

		myButton_0 = ButtonFactory.getNumbButton("0", '0');
		myButton_0.setBounds(76, 233, 42, 42);
		panel.add(myButton_0);
		listOfComponents.add(myButton_0);

		myButton_Enter = ButtonFactory.getNumbButton("Enter", 'E');
		myButton_Enter.setBounds(327, 233, 87, 42);
		panel.add(myButton_Enter);
		listOfComponents.add(myButton_Enter);

		myButton_Cancel = ButtonFactory.getNumbButton("Cancel", 'C');
		myButton_Cancel.setBounds(230, 233, 87, 42);
		panel.add(myButton_Cancel);
		listOfComponents.add(myButton_Cancel);

		txtCardNumb = new JTextField();
		txtCardNumb.setBounds(219, 79, 166, 20);
		panel.add(txtCardNumb);
		txtCardNumb.setColumns(10);

		txtPin = new JPasswordField();
		txtPin.setBounds(219, 110, 166, 20);
		panel.add(txtPin);

		btnDelNumbCard = ButtonFactory.getDelButton();
		btnDelNumbCard.setBounds(391, 78, 23, 21);
		panel.add(btnDelNumbCard); 
		listOfComponents.add(btnDelNumbCard);

		btnDelPin = ButtonFactory.getDelButton();
		btnDelPin.setBounds(391, 109, 23, 21);
		panel.add(btnDelPin);
		
		rdbtnCardNumb = new JRadioButton("");
		rdbtnCardNumb.setBounds(196, 76, 21, 23);
		rdbtnCardNumb.setBackground(new Color(40,140,255));
		rdbtnCardNumb.setSelected(true);
		panel.add(rdbtnCardNumb);
		
		rdbtPass = new JRadioButton("");
		rdbtPass.setBounds(196, 109, 21, 23);
		rdbtPass.setBackground(new Color(40,140,255));
		panel.add(rdbtPass);
		listOfComponents.add(btnDelPin);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnCardNumb);
		radioGroup.add(rdbtPass);
		

	}

	// delegate ActionListner to Controller class
	public void addMainFormListener(ActionListener NumbButtonActionListener) {

		for (MyButton x : listOfComponents) {
			x.addActionListener(NumbButtonActionListener);
		}
		rdbtPass.addActionListener(NumbButtonActionListener);
		rdbtnCardNumb.addActionListener(NumbButtonActionListener);
		txtCardNumb.addActionListener(NumbButtonActionListener);
		txtPin.addActionListener(NumbButtonActionListener);

	}

	public void setPnlBalance() {
		pnlBalance = new MYGUI.MetroPanel();
		pnlBalance.setBounds(119, 0, 675, 571);
		getContentPane().add(pnlBalance);
		pnlBalance.setLayout(null);

	}

	public void setPnlSide() {
		pnlSide = new MYGUI.MetroPanel();
		pnlSide.setBounds(0, 0, 120, 700);
		scrollPane = new JScrollPane(pnlSide,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnlSide.setLayout(null);

		int increment = 20;
		scrollPane.getVerticalScrollBar().setUI(new MetroScrollBar());
		scrollPane.getVerticalScrollBar().setUnitIncrement(increment);
		scrollPane.setCursor(new Cursor(Cursor.HAND_CURSOR));// need to find
																// wrist!!
		scrollPane.setBounds(0, 0, 120, 600);
		getContentPane().add(scrollPane);
		invalidate();
		validate();
		repaint();

	}

	public void setButtons() {
		btnBalance = ButtonFactory.getIconButton(
				"resources\\imagesClient\\balance_100x100.png",
				"current balance");
		btnBalance.setBounds(0, 0, 100, 100);
		pnlSide.add(btnBalance);

		btnWithdrawal = ButtonFactory.getIconButton(
				"resources\\imagesClient\\money_bag.png", "get withdrawal");
		btnWithdrawal.setBounds(0, 100, 100, 100);
		pnlSide.add(btnWithdrawal);

		btnSendMoney = ButtonFactory.getIconButton(
				"resources\\imagesClient\\money_transfer.png", "send money");
		btnSendMoney.setBounds(0, 200, 100, 100);
		pnlSide.add(btnSendMoney);

		btnPayBill = ButtonFactory.getIconButton(
				"resources\\imagesClient\\pay_bill.png", "pay bill");
		btnPayBill.setBounds(0, 300, 100, 100);
		pnlSide.add(btnPayBill);

		btnAddMoney = ButtonFactory.getIconButton(
				"resources\\imagesClient\\money_pig.png", "add money");
		btnAddMoney.setBounds(0, 400, 100, 100);
		pnlSide.add(btnAddMoney);

		btnAddMoneyPhone = ButtonFactory.getIconButton(
				"resources\\imagesClient\\phone.png", "add money to phone");
		btnAddMoneyPhone.setBounds(0, 500, 100, 100);
		pnlSide.add(btnAddMoneyPhone);

		btnContactList = ButtonFactory.getIconButton(
				"resources\\imagesClient\\contact_list.png", "contact list");
		btnContactList.setBounds(0, 600, 100, 100);
		pnlSide.add(btnContactList);
	}

	// //////////////SETTERS AND GETTERS///////////////////////////
	
	public JRadioButton getRdbtPass() {
		return rdbtPass;
	}

	public JRadioButton getRdbtnCardNumb() {
		return rdbtnCardNumb;
	}
	public MyButton getMyButton_Enter() {
		return myButton_Enter;
	}

	public void setMyButton_Enter(MyButton myButton_Enter) {
		this.myButton_Enter = myButton_Enter;
	}

	public MetroPanel getPnlSide() {
		return pnlSide;
	}

	public MetroPanel getPnlBalance() {
		return pnlBalance;
	}

	public MetroPanel getPnlStart() {
		return pnlStart;
	}

	public MyButton getBtnBalance() {
		return btnBalance;
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

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JTextField getTxtCardNumb() {
		return txtCardNumb;
	}

	public JTextField getTxtPin() {
		return txtPin;
	}

	public MyButton getMyButton_Cancel() {
		return myButton_Cancel;
	}

	public MyButton getMyButton_1() {
		return myButton_1;
	}

	public MyButton getMyButton_2() {
		return myButton_2;
	}

	public MyButton getMyButton_3() {
		return myButton_3;
	}

	public MyButton getMyButton_6() {
		return myButton_6;
	}

	public MyButton getMyButton_5() {
		return myButton_5;
	}

	public MyButton getMyButton_4() {
		return myButton_4;
	}

	public MyButton getMyButton_7() {
		return myButton_7;
	}

	public MyButton getMyButton_8() {
		return myButton_8;
	}

	public MyButton getMyButton_9() {
		return myButton_9;
	}

	public MyButton getMyButton_0() {
		return myButton_0;
	}

	public MetroPanel getPanel() {
		return panel;
	}

	public ArrayList<MyButton> getListOfComponents() {
		return listOfComponents;
	}

	public MyButton getBtnDelNumbCard() {
		return btnDelNumbCard;
	}

	public MyButton getBtnDelPin() {
		return btnDelPin;
	}
}