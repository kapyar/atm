package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import Server.SQLwrapper;

public enum CashController {
	INSTANCE;
	
	private final int ATM_ID = 100;
	private InnerBank bank;
	private HashMap<Integer, Integer> lastWidthdraw;
	private HashMap<Integer, Integer> lastAdd;
	
	private CashController() {
		bank = new InnerBank();
	}
	
	
	public void addCash(int sum) {
		Integer bills [] = {10, 20, 50, 100, 200};		
		HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();
		
		
		for (int i = 4; i >= 0; --i) {
			int ammountOf = sum / bills[i];
			int sumDec = bills[i] * ammountOf;

			res.put(bills[i], ammountOf);
			sum -= sumDec;
		}
		
		uploadBills(res);
		
	}
	
	public void loadLocalCash() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("cash.data");
		ObjectInputStream oin = new ObjectInputStream(fis);

		HashMap<Integer, Integer> billPack = (HashMap<Integer, Integer>) oin.readObject();
		bank.setBillPack(billPack);
		
		System.out.println("Loaded:  " + bank.getCashAmmount() + " $ of cash into ATM");
		
		oin.close();
	}
	
	public void saveLocalCash() throws IOException {
		FileOutputStream fos = new FileOutputStream("cash.data");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(bank.getBillPack());
		System.out.println("Saved:  " + bank.getCashAmmount() + " $ of cash from ATM");
		oos.flush();
		oos.close();
		
		SQLwrapper bd = SQLwrapper.DB;
		
		if (!bd.addCash(bank.getCashAmmount(), ATM_ID)) {
			throw new RuntimeException("Cash Controller can't add cash for some reason");
		}
	}
	
	public Integer getCashLeft() {
		return bank.getCashAmmount();
	}
	
	public boolean hasEnoughCash(Integer ammount) {
		return (bank.getCashAmmount() - ammount) >= 0;
	}
	
	public boolean withdraw(int sum) {
		boolean result = true;
		
		Integer bills [] = {10, 20, 50, 100, 200};		
		HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();
		
		
		for (int i = 4; i >= 0; --i) {
			int ammountOf = sum / bills[i];
			int sumDec = 0;
			if (ammountOf > bank.getBillPack().get(bills[i])) {
				sumDec = bills[i] *  bank.getBillPack().get(bills[i]);
				ammountOf = bank.getBillPack().get(bills[i]);
			} else {
				sumDec = bills[i] * ammountOf;
			}
			
			res.put(bills[i], ammountOf);
			sum -= sumDec;
		}
		
		if (sum != 0) {
			result = false;
		} else {
			System.out.println("------------------------------------------");
			System.out.println("ATM gives you: ");
			
			for (Map.Entry<Integer, Integer> entry : res.entrySet()) {
				System.out.println("bill: " + entry.getKey() + " - " + entry.getValue());
			}
			
			System.out.println("------------------------------------------");
			
			for (Map.Entry<Integer, Integer> entry : res.entrySet()) {
				Integer temp =  bank.getBillPack().get(entry.getKey());
				bank.getBillPack().put(entry.getKey(), temp - res.get(entry.getKey()));
				
				//System.out.println("bill: " + entry.getKey() + " - " + entry.getValue());
			}
			
			System.out.println("After withdrawal:");
			
			for (Map.Entry<Integer, Integer> entry : bank.getBillPack().entrySet()) {
				System.out.println("bill: " + entry.getKey() + " - " + entry.getValue());
			}
			
			System.out.println("------------------------------------------");
			
			lastWidthdraw = res;
		}
		
		return result;
	}
	
	public HashMap<Integer, Integer> getLastWithdraw() {
		return lastWidthdraw;
	}
	
	private void uploadBills (HashMap<Integer, Integer> billPack) {
		Integer total = 0;
		System.out.println("****************************************************");
		System.out.println("Adding bills:");
		for (Map.Entry<Integer, Integer> entry : billPack.entrySet()) {
			total += entry.getValue() * entry.getKey();
			Integer tempCash = bank.getBillPack().get(entry.getKey());
			
			bank.getBillPack().put(entry.getKey(), tempCash + entry.getValue());
			System.out.println("Adding " + entry.getValue() + " of " + entry.getKey() + " $ banknotes");
		}
		
		System.out.println("Added " + total + " $$$");
		System.out.println("****************************************************");
	}
	
	public HashMap<Integer, Integer> getCashDesk() {
		return bank.getBillPack();
	}
	
	public HashMap<Integer, Integer> getLastAdd() {
		return lastAdd;
	}
	
	private class InnerBank {
		private HashMap<Integer, Integer> bills;
		
		public InnerBank() {
			bills = new HashMap<Integer, Integer>();
		}
		
		public void setBillPack(HashMap<Integer, Integer> billPack) {
			bills = billPack;
		}
		
		public HashMap<Integer, Integer> getBillPack() {
			return bills;
		}
		
		public Integer getCashAmmount() {
			Integer total = 0;
			for (Map.Entry<Integer, Integer> entry : bills.entrySet()) {
				total += entry.getValue() * entry.getKey();
			}
			
			return total;
		}
		
	}
	
	
}
