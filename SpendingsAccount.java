
public class SpendingsAccount extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6798203460463746781L;

	public SpendingsAccount(String accountNumber, int balance) {
		super(accountNumber, balance);
		// TODO Auto-generated constructor stub
	}
	
	public boolean checkBalance(int balance){
		if(balance <= 0){
			return true;
		}else{
			return false;
		}
	}
	
	public void computeInterest(){
	}

	@Override
	public boolean withdraw(int suma) {
		if(this.getBalance() - suma >= -1000){
			int s = this.getBalance()-suma;
			this.setBalance(s);
			if(this.getBalance() == s){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}

	@Override
	public boolean deposit(int suma) {
		// TODO Auto-generated method stub
		this.setBalance(this.getBalance()+suma);
		return true;
	}
	
}
