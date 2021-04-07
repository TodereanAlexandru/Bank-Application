import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class BankFrame extends JFrame  {
	public BankFrame(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setTitle("Banca");
		this.setSize(1000, 700);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		
		/*Bank bank = new Bank();
		
		//test
		Person p1 = new Person("Person1", "111", "person1@person.com");
		Person p2 = new Person("Person2", "222", "person2@person.com");
		Person p3 = new Person("Person3", "333", "person3@person.com");
		bank.addPerson(p1);
		bank.addPerson(p2);
		bank.addPerson(p3);
		bank.addAccount(p1, new SavingsAccount("123", 100));
		bank.addAccount(p1, new SpendingsAccount("124", -20));
		bank.addAccount(p1, new SavingsAccount("125", 50));
		bank.addAccount(p2, new SavingsAccount("223", 1000));
		bank.addAccount(p3, new SpendingsAccount("124", -50));*/
		
		Bank savedBank = null;
		try {
			FileInputStream streamIn = new FileInputStream("banca.dat");
		    ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
		    savedBank = (Bank) objectinputstream.readObject();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		Bank bank = savedBank;
		bank.refreshObservers();
		
		String[] columnNamesPerson = new String[]{"Nume", "CNP", "E-mail", "Balanta"};
		String[] columnNamesAccount = new String[]{"Tip", "IBAN", "Balanta"};
		List<Person> personsList = new ArrayList<Person>();
		List<Account> accountsList = new ArrayList<Account>();
		bank.getPersons(personsList);
		AbstractTableModel tModelPersons = new AbstractTableModel(){
			 /**
			 * 
			 */
			private static final long serialVersionUID = -3067595567881302230L;
			public String getColumnName(int col) {
			        return columnNamesPerson[col];
			    }
			    public int getRowCount() { 
			    	return personsList.size(); 
			    }
			    public int getColumnCount() { 
			    	return columnNamesPerson.length; 
			    }
			    public Object getValueAt(int row, int col) {
			    	Person p = personsList.get(row);
			    	if(col == 0){
			    		return p.getNume();
			    	}else if(col == 1){
			    		return p.getCnp();
			    	}else if(col == 2){
			    		return p.getEmail();
			    	}else{
			    		return "" + p.getMoney();
			    	}
			    }
			    public boolean isCellEditable(int row, int col){ 
			    	if(col == 0 || col == 2){
			    		return true;
			    	}else{
			    		return false;
			    	}
			    }
			    public void setValueAt(Object value, int row, int col) {
			    	Person p = personsList.get(row);
			    	if(col == 0){
			    		p.setNume((String)value);
			    	}else if(col == 1){
			    		p.setCnp((String)value);
			    	}else if(col == 2){
			    		p.setEmail((String)value);
			    	}
			        fireTableCellUpdated(row, col);
			    }
		 };
		 
		 AbstractTableModel tModelAccounts = new AbstractTableModel(){
			 /**
			 * 
			 */
			private static final long serialVersionUID = -3067595567881302240L;
			public String getColumnName(int col) {
			        return columnNamesAccount[col];
			    }
			    public int getRowCount() { 
			    	return accountsList.size(); 
			    }
			    public int getColumnCount() { 
			    	return columnNamesAccount.length; 
			    }
			    public Object getValueAt(int row, int col) {
			    	Account a = accountsList.get(row);
			    	if(col == 0){
			    		return a.getClass().getName();
			    	}else if(col == 1){
			    		return a.getAccountNumber();
			    	}else{
			    		return "" + a.getBalance();
			    	}
			    }
			    public boolean isCellEditable(int row, int col){ 
			    	if(col == 1 || col == 2){
			    		return true;
			    	}else{
			    		return false;
			    	}
			    }
			    public void setValueAt(Object value, int row, int col) {
			    	Account a = accountsList.get(row);
			    	if(col == 1){
			    		a.setAccountNumber((String)value);
			    	}else if(col == 2){
			    		a.setBalance(Integer.valueOf((String)value));
			    	}
			        fireTableCellUpdated(row, col);
			        panel.repaint();
			    }
		 };
		 
		 
		 
		 JTable tablePersons = new JTable(tModelPersons);
		 JScrollPane scrollPanePersons = new JScrollPane(tablePersons);
		 tablePersons.setFillsViewportHeight(true);
		 panel.add(scrollPanePersons, BorderLayout.WEST);
		 
		 JTable tableAccounts = new JTable(tModelAccounts);
		 JScrollPane scrollPaneAccounts = new JScrollPane(tableAccounts);
		 tableAccounts.setFillsViewportHeight(true);
		 panel.add(scrollPaneAccounts, BorderLayout.EAST);
		 
		 JPanel buttonsPanel = new JPanel();
		 buttonsPanel.setLayout(new BorderLayout());
		 JPanel buttonsNorth = new JPanel();
		 JPanel buttonsSouth = new JPanel();
		 buttonsPanel.add(buttonsNorth, BorderLayout.NORTH);
		 buttonsPanel.add(buttonsSouth, BorderLayout.SOUTH);
		 
		 JButton btnAddPerson = new JButton("Add person");
		 buttonsNorth.add(btnAddPerson);
		 JButton btnDeletePerson = new JButton("Delete person");
		 buttonsNorth.add(btnDeletePerson);
		 JButton btnAddSpendingsAccount = new JButton("Add spendings account");
		 buttonsNorth.add(btnAddSpendingsAccount);
		 JButton btnAddSavingsAccount = new JButton("Add savings account");
		 buttonsNorth.add(btnAddSavingsAccount);
		 JButton btnDeleteAccount = new JButton("Delete account");
		 buttonsNorth.add(btnDeleteAccount);
		 JButton btnInterest = new JButton("Compute interests");
		 buttonsSouth.add(btnInterest);
		 
		 JPanel withdraw = new JPanel();
		 JButton btnWithdraw = new JButton("Withdraw");
		 buttonsSouth.add(btnWithdraw);   //withdraw si deposit
		 btnWithdraw.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int selected = tableAccounts.getSelectedRow();
					if(selected < 0){
						return;
					}
					Account a = accountsList.get(selected);
					String nr2 = JOptionPane.showInputDialog(
							"dati suma de retragere");
					
					if(a.withdraw(Integer.parseInt(nr2))){
						JOptionPane.showMessageDialog(null, "withdraw successful");
					}else{
						JOptionPane.showMessageDialog(null, "withdraw failed");
					}
					panel.repaint();
				}
			});
		 
		 JPanel deposit= new JPanel();
		 JButton btnAddDeposit = new JButton("Deposit");
		 buttonsSouth.add(btnAddDeposit);
		 btnAddDeposit.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int selected = tableAccounts.getSelectedRow();
					if(selected < 0){
						return;
					}
					Account a = accountsList.get(selected);
					String nr2 = JOptionPane.showInputDialog(
							"dati suma de depozitare");
					
					if(a.deposit(Integer.parseInt(nr2))){
						JOptionPane.showMessageDialog(null, "Deposit successful");
					}else{
						JOptionPane.showMessageDialog(null, "Deposit failed");
					}
					panel.repaint();
				}
			});
		 
		 
		 btnInterest.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					for(Person p: personsList){
						for(Account a: bank.getAccounts(p)){
							a.computeInterest();
						}
					}
					panel.repaint();
				}
			});
		 
		 btnAddPerson.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String cnp = JOptionPane.showInputDialog(
						"Please specify person's CNP");
				Person p = new Person("", cnp, "");
				if(bank.addPerson(p)){                            
					personsList.add(p); //doar daca am reusit sa adaugam la banca adaugam si in lista
					panel.repaint();
				}else{
					JOptionPane.showMessageDialog(null, "The CNP already exists");
				}
			}
		});
		 
		 btnDeletePerson.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int[] selected = tablePersons.getSelectedRows();
				for(int i=0; i<selected.length; i++){
					Person p = personsList.get(selected[i]);
					bank.removePerson(p);
				}
				bank.getPersons(personsList);
				accountsList.clear();
				panel.repaint();
			}
		});
		 
		 tablePersons.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					// TODO Auto-generated method stub
					//System.out.println("Selected person " + tablePersons.getSelectedRow());
					Person p = personsList.get(tablePersons.getSelectedRow());
					accountsList.clear();
					accountsList.addAll(bank.getAccounts(p));
					panel.repaint();
					//System.out.println(accountsList.size());
				}
		    });
		 
		 this.addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	            	try {
	            		FileOutputStream fout = new FileOutputStream("banca.dat");
		            	ObjectOutputStream oos = new ObjectOutputStream(fout);
						oos.writeObject(bank);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                e.getWindow().dispose();
	            }
	        });
		 
		 btnAddSavingsAccount.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int selected = tablePersons.getSelectedRow();
					if(selected < 0){
						return;
					}
					Person p = personsList.get(selected);
					String nr = JOptionPane.showInputDialog(
							"Please specify account number");
					Account a = new SavingsAccount(nr, 0);
					bank.addAccount(p, a);
					accountsList.clear();
					accountsList.addAll(bank.getAccounts(p));
					panel.repaint();
				}
			});
		 
		 btnAddSpendingsAccount.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int selected = tablePersons.getSelectedRow();
					if(selected < 0){
						return;
					}
					Person p = personsList.get(selected);
					String nr = JOptionPane.showInputDialog(
							"Please specify account number");
					Account a = new SpendingsAccount(nr, 0);
					bank.addAccount(p, a);
					accountsList.clear();
					accountsList.addAll(bank.getAccounts(p));
					panel.repaint();
				}
			});
			 
			 btnDeleteAccount.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int selectedPerson = tablePersons.getSelectedRow();
					if(selectedPerson < 0){
						return;
					}
					Person p = personsList.get(selectedPerson);
					int[] selected = tableAccounts.getSelectedRows();
					for(int i=0; i<selected.length; i++){
						Account a = accountsList.get(selected[i]);
						bank.removeAccount(p, a);
					}
					accountsList.clear();
					accountsList.addAll(bank.getAccounts(p));
					panel.repaint();
				}
			});
		 
		 panel.add(buttonsPanel, BorderLayout.SOUTH);
		 this.add(panel);
	     this.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankFrame bf = new BankFrame();
	}
}
