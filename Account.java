import java.io.Serializable;
import java.util.Observable;

public abstract class Account extends Observable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8021835824746829356L;
	
	
	private String accountNumber;
	private int balance;
	
	public Account(String accountNumber, int balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	protected void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getBalance() {
		return balance;
	}
	
	public abstract void computeInterest();
	public abstract boolean checkBalance(int balance);
	public abstract boolean withdraw(int suma);
	public abstract boolean deposit(int suma);
	
	public void setBalance(int balance) {
		if(checkBalance(balance) == false){
			return;
		}
		Integer oldBalance = this.balance;
		this.balance = balance;
		  System.out.println("balance=" + balance + "; oldBalance=" + oldBalance);
		this.setChanged();
		this.notifyObservers(oldBalance);
	}
	
	public void initialNotification(){
		Integer oldBalance = 0;
		this.setChanged();
		this.notifyObservers(oldBalance);
	}
}
