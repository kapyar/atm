package MYGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Starter.Test;
import Controller.CashController;

public class CheckView extends RightPanel {

	private MetroPanel check;
	private MyButton left;
	private MyButton right;
	private final int rightOffset = 5;
	private final String div = "-------------------------------------------------------";
	private String data;
	private CheckJlabel inner;

	public CheckView(int spent, int rest, boolean negative) {
		init();
		setHeader();
		addRest(spent, rest, negative);
		setFooter();
	}

	public CheckView() {
		init();

	}

	public CheckView(int rest) {
		init();
		setHeader();
		addRest(rest);
		setFooter();
	}

	public CheckView(HashMap<Integer, Integer> bills, int spent, int rest,
			boolean negative) {
		init();
		setHeader();
		addRest(spent, rest, negative);
		addWithdrawalData(bills);
		setFooter();

		// CashController.INSTANCE.addCash(350);
	}

	private void addInnerListener() {
		left.addActionListener(new InnerListener());
		right.addActionListener(new InnerListener());
	}

	private class InnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			System.out.println("InnerListenerCheckView");
			if (source == getLeft()) {
				Test.getController().getMainConteiner()
						.resetPanel(Test.getController().getStart());
			}
			if (source == getRight()) {
				Test.getController().getWrap()
						.resetRightPanel(Test.getController().getIntroSplash());
				Test.getController().getMainConteiner()
						.resetPanel(Test.getController().getRePin());
			}

		}

	}// END InnerListener

	private void init() {
		setMyTitle("Your check:");

		check = new MetroPanel();
		check.setBackground(new Color(255, 255, 255));
		check.setSize(300, 430);
		check.setLayout(null);

		int middle = (this.getWidth() - check.getWidth()) / 2;
		check.setLocation(middle, 120);

		left = ButtonFactory.getCheckNavi("EXIT");
		right = ButtonFactory.getCheckNavi("CONTINUE");

		left.setLocation(2, (int) check.getLocation().getY() - left.getHeight()
				+ check.getHeight());
		right.setLocation(
				this.getWidth() - right.getWidth() - rightOffset,
				(int) check.getLocation().getY() - left.getHeight()
						+ check.getHeight());

		this.add(left);
		this.add(right);
		this.add(check);
		addInnerListener();
	}

	private void setHeader() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		data = "<html>";

		data += "<center>ATM (by MPK)<center>";
		data += "<font size=3>" + dateFormat.format(cal.getTime())
				+ "</font><br>";
		data += div;
	}

	private void addRest(int rest) {

		data += "<div align='left'>" + "<br/>" + "Balance:" + "</div>";
		data += "<div align='right'>" + rest + "</div>";

	}

	public void setData(int suma) {
		inner = null;
		data = "";
		setHeader();
		addRest(suma);
		setFooter();
	}

	public void setData(HashMap<Integer, Integer> bills, int spent, int rest,
			boolean negative) {
		inner = null;
		data = "";
		setHeader();
		addRest(spent, rest, negative);
		addWithdrawalData(bills);
		setFooter();
	}

	private void addRest(int spent, int rest, boolean negative) {
		data += "<div align='left'>";

		if (negative) {
			data += "Spent:";
		} else {
			data += "Added:";
		}

		data += "</div>" + "<div align='right'>" + spent + "</div>";

		data += "<div align='left'>" + "Balance:" + "</div>";
		data += "<div align='right'>" + rest + "</div>";
	}

	private void addWithdrawalData(HashMap<Integer, Integer> bills) {
		data += div + "<br>";
		data += "<div align='left'>Your bills:<br><br>";

		for (Map.Entry<Integer, Integer> entry : bills.entrySet()) {
			if (entry.getValue() != 0) {
				data += String.valueOf(entry.getKey()) + "$ &#160; x"
						+ String.valueOf(entry.getValue()) + "<br>";
			}
		}
	}

	private void setFooter() {
		data += "<br><br>" + div + "<br>";
		data += "<center><font size=3>Thanks for using our ATM!</font></center>";
		data += "</html>";

		inner = new CheckJlabel(data, check.getWidth(), check.getHeight());
		check.add(inner);
	}

	public MyButton getLeft() {
		return left;
	}

	public MyButton getRight() {
		return right;
	}

}