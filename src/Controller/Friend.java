package Controller;

public class Friend {

	String name;
	Integer accountNumber;

	public Friend(String name, Integer accountNumber) {
		this.name = name;
		this.accountNumber = accountNumber;
	}

	public String toString() {
		return name + ": " + accountNumber;
	}

	public String getName() {
		return name;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}
}
