import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class Bank implements BankProc, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5425408842048825094L;
	private LinkedHashMap<Person, List<Account>> data;
	
	public Bank() {
		data = new LinkedHashMap<Person, List<Account>>();
		assert isWellFormed();
	} 
	
	public boolean addPerson(Person p){
		assert isWellFormed();
		assert p != null && p.getCnp().length() > 2;
		if(data.get(p) != null){
			return false;
		}else{
			List<Account> personAccounts = new ArrayList<Account>();
			data.put(p, personAccounts);
            return true;
		}
	}
	public void removePerson(Person p){
		assert isWellFormed();
		assert p != null && data.get(p) != null;
		data.remove(p);
		assert data.get(p) == null;
	}
	public void addAccount(Person p, Account a){
		assert isWellFormed();
		assert p != null && a != null && data.get(p) != null;
		List<Account> personAccounts = data.get(p);
		personAccounts.add(a);
		a.addObserver(p);
		a.initialNotification();
		assert data.get(p).contains(a);
	}
	
	public void removeAccount(Person p, Account a){
		assert isWellFormed();
		assert p != null && a != null && data.get(p) != null;
		if(a.getBalance() != 0){
			return;
		}
		List<Account> personAccounts = data.get(p);
		personAccounts.remove(a);
	}
	
	public void getPersons(List<Person> personList){
		personList.clear();
		for(Person p: data.keySet()){
			personList.add(p);
		}
	}
	
	public List<Account> getAccounts(Person p){
		assert p != null && data.get(p) != null && isWellFormed();
		return data.get(p);
	}
	
	public void refreshObservers(){
		assert isWellFormed();
		for (Entry<Person, List<Account>> entry : data.entrySet()){
			Person p = entry.getKey();
			for(Account a: entry.getValue()){
				a.addObserver(p);
			}
		}
	}
	
	private boolean isWellFormed(){
		for (Entry<Person, List<Account>> entry : data.entrySet()){
			Person p = entry.getKey();
			if(p == null){
				return false;
			}
			for(Account a: entry.getValue()){
				if(a == null){
					return false;
				}
			}
		}
		return true;
	}
}
