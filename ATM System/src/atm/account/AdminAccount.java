
package src.atm.account ;

import src.atm.pin.Pin;

public final class AdminAccount extends Account {

	public AdminAccount(String accountName, Pin accountPin, String accountHistory) {
		super(accountName, accountPin, accountHistory) ;
	}

	public static final void LIST_ACCOUNTS() {
		Account[] listOfAccounts = GET_ACCOUNT_MAP().values().toArray(new Account[GET_ACCOUNT_MAP().size()]) ;	 // HashMap to Collection, to BankAccount[]
		for (Account a : listOfAccounts) {
			System.out.println(a.toString()) ;
		}
	}
	
	public boolean equals(Object account) {
		AdminAccount comparingAccount = (AdminAccount)account ;
		if (this.toString().equalsIgnoreCase(comparingAccount.toString())) {
			return true ;
		} else {
			return false ;
		}
	}

	
	public String toString() {
		return this.ACCOUNT_NUMBER + ", " + this.accountName + ", " + this.accountPin + ", " + this.accountHistory + "," ;
	}

}