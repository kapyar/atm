package MYGUI;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Controller.CashController;


public class CheckView extends RightPanel {
	
	private MetroPanel check;
	private MyButton left;
	private MyButton right;
	private final int rightOffset = 5;
	private final String div = "-------------------------------------------------------";
	private String data;
	private CheckJlabel inner;
	
	public CheckView (int spent, int rest, boolean negative) {
		init();
		setHeader();
		addRest(spent, rest, negative);
		setFooter();
	}
	
	public CheckView (HashMap<Integer, Integer> bills, int spent, int rest, boolean negative) {
		init();
		setHeader();
		addRest(spent, rest, negative);
		addWithdrawalData(bills);
		setFooter();
		
		//CashController.INSTANCE.addCash(350);
	}
	
	private void init() {
		setMyTitle("Your check:");
		
		check = new MetroPanel();
		check.setBackground(new Color(255, 255, 255));
		check.setSize(300, 430);
		check.setLayout(null);

		int middle = (this.getWidth() - check.getWidth())/2;
		check.setLocation(middle, 120);
		
		left  = ButtonFactory.getCheckNavi("EXIT");
		right = ButtonFactory.getCheckNavi("CONTINUE");
		
		left.setLocation(2, (int)check.getLocation().getY() - left.getHeight() + check.getHeight());
		right.setLocation(this.getWidth() - right.getWidth() - rightOffset, (int)check.getLocation().getY() - left.getHeight() + check.getHeight());

		this.add(left);
		this.add(right);
		this.add(check);
	}
	
	private void setHeader() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
	
		data = "<html>";
		
		data += "<center>ATM (by MPK)<center>";
		data += "<font size=3>"+dateFormat.format(cal.getTime()) + "</font><br>";
		data += div; 
	}

	public void addRest(int spent, int rest, boolean negative) {
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
	
	public void addWithdrawalData(HashMap<Integer, Integer> bills) {
		data += div + "<br>";
		data += "<div align='left'>Your bills:<br><br>";
		
		for (Map.Entry<Integer, Integer> entry : bills.entrySet()) {
			if (entry.getValue() != 0) {
				data += String.valueOf(entry.getKey()) + "$ &#160; x" + String.valueOf(entry.getValue()) + "<br>";
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
	
	
}