import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Person implements Serializable, Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nume;
	private String cnp;
	private String email;
	private int money;
	
	public Person(String nume, String cnp, String email) {
		super();
		this.nume = nume;
		this.cnp = cnp;
		this.email = email;
		this.money = 0;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnp == null) ? 0 : cnp.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (cnp == null) {
			if (other.cnp != null)
				return false;
		} else if (!cnp.equals(other.cnp))
			return false;
		return true;
	}
	public Person() {
		this("", "", "");
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		//System.out.println("update called");
		Account a = (Account)arg0;
		Integer oldBalance = (Integer)arg1;
		money = money - oldBalance + a.getBalance();
	}
	public int getMoney() {
		return money;
	}
	
}
