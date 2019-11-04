package Models;

public class Transaction {
	//Instance Variables
	String accountName, actionPerformed;
	int id;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(int id, String actionPerformed, String name) {
		super();
		this.accountName = name;
		this.actionPerformed = actionPerformed;
		this.id = id;
	}

	public String getName() {
		return accountName;
	}

	public void setName(String name) {
		this.accountName = name;
	}

	public String getActionPerformed() {
		return actionPerformed;
	}

	public void setActionPerformed(String actionPerformed) {
		this.actionPerformed = actionPerformed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Transaction [id = " + id + ", actionPerformed = " + actionPerformed + ", name = " + accountName + "]";
	}
	
}
