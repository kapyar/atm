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
	
	private CashController() {
		bank = new InnerBank();
	}
	
	
	private void addCash(int ammount) {
		SQLwrapper bd = new SQLwrapper();
		
		if (!bd.addCash(ammount, ATM_ID)) {
			throw new RuntimeException("Cash Controller can't add cash for some reason");
		}
		
	}
	
	public void loadLocalCash() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("cash.data");
		ObjectInputStream oin = new ObjectInputStream(fis);
		// read the data about banknotes left
		HashMap<Integer, Integer> billPack = (HashMap<Integer, Integer>) oin.readObject();
		bank.setBillPack(billPack);
		
		System.out.println(bank.getCashAmmount());
		
		oin.close();
	}
	
	public void saveLocalCash() throws IOException {
		FileOutputStream fos = new FileOutputStream("cash.data");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(bank.getBillPack());
		oos.flush();
		oos.close();
	}
	
	public Integer getCashLeft() {
		return bank.getCashAmmount();
	}
	
	public boolean hasEnoughCash(Integer ammount) {
		return (bank.getCashAmmount() - ammount) >= 0;
	}
	
	public void uploadBills (HashMap<Integer, Integer> billPack) {
		Integer total = 0;
		for (Map.Entry<Integer, Integer> entry : billPack.entrySet()) {
			total += entry.getValue() * entry.getKey();
			Integer tempCash = bank.getBillPack().get(entry.getKey());
			
			bank.getBillPack().put(entry.getKey(), tempCash + entry.getValue());
			System.out.println("Adding " + entry.getValue() + " of " + entry.getKey() + " $ banknotes");
		}
		
		addCash(total);
		System.out.println("Added " + total + " $$$");
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
