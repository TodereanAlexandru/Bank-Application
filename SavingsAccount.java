
public class SavingsAccount extends Account {
	/**
	 * 
	 */
	int withdraw,deposit;
	private static final long serialVersionUID = 1883513243540209023L;
	int retras=0;
	int depus=0;
	
	
	public SavingsAccount(String accountNumber, int balance) {
		super(accountNumber, balance);
		// TODO Auto-generated constructor stub
	}
	
	public boolean checkBalance(int balance){
		if(balance >= 0){
			return true;
		}else{
			return false;
		}
	}
	
	public void computeInterest(){
		setBalance(getBalance() * 105 / 100);
	}

	@Override
	public boolean withdraw(int suma) {
		// TODO Auto-generated method stub
		if (this.getBalance() == suma){
			this.setBalance(0);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deposit(int suma) {
		if(suma >= 200){
			this.setBalance(this.getBalance()+suma);
			return true;
		}else{
			return false;
		}
	}
}

