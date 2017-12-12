package src.atm.account ;
import src.atm.pin.Pin ;

public final class AdminAccount extends Account {

	public AdminAccount(String accountName, Pin accountPin, String accountHistory) {
		super(accountName, accountPin, accountHistory) ;
	}

	public final String listAccounts() {
		Account[] listOfAccounts = GET_ACCOUNT_MAP().values().toArray(new Account[GET_ACCOUNT_MAP().size()]) ;	 // HashMap to Collection, to Account[]
		String accountList = listOfAccounts.length + " accounts created.\n" ;
		for (Account a : listOfAccounts) {
			accountList = accountList + a.toString() + "\n" ;
		}
		return accountList ;
	}
	
	@Override
	public boolean equals(Object adminAccount) {
		if (this.toString().equalsIgnoreCase(((AdminAccount)adminAccount).toString())) {	// Casts adminAccount to type AdminAccount
			return true ;
		} else {
			return false ;
		}
	}

	@Override
	public String toString() {
		return "Account Number: " + ACCOUNT_NUMBER + "\nAccount Name: " + accountName + "\nAccount Pin: " + accountPin + "\nAccount History: " + accountHistory ;
	}

}