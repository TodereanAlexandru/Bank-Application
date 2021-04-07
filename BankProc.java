
public interface BankProc {
	//asserturi
	/** @pre p != null && p.getCnp().length() > 2
	 * 
	 */
	public boolean addPerson(Person p);
	
	/**
	 * @pre p != null && data.get(p) != null
	 * @post data.get(p) == null
	 */
	public void removePerson(Person p);
	
	/**
	 * @pre p != null && a != null && data.get(p) != null
	 * @post data.get(p).contains(a)
	 */
	public void addAccount(Person p, Account a);
	//assert addPerson();

}
