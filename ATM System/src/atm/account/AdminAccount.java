package src.atm.account;
import src.atm.pin.Pin;

/**
 * This class provides special functionality and privileges that allow direct access and control of other bank accounts. An admin account can change values
 * stored in the accounts and view account information.
 * <br><br>
 * This class cannot be inherited.
 * This class inherits Account.
 * 
 * @author Joshua Ciffer
 * @version 01/18/2018
 */
public final class AdminAccount extends Account {

	public AdminAccount(String accountName, Pin accountPin, String accountHistory) {
		super(accountName, accountPin, accountHistory);
	}

	public final String listAccounts() {
		Account[] listOfAccounts = GET_ACCOUNT_MAP().values().toArray(new Account[GET_ACCOUNT_MAP().size()]);	 // HashMap to Collection, to Account[]
		String accountList = listOfAccounts.length + " accounts created.\n";
		for (Account a : listOfAccounts) {
			accountList = accountList + a.toString() + "\n";
		}
		return accountList;
	}

	@Override
	public boolean equals(Object adminAccount) {
		if (this.toString().equalsIgnoreCase(((AdminAccount)adminAccount).toString())) {	// Casts adminAccount to type AdminAccount
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Account Number: " + ACCOUNT_NUMBER + "\nAccount Name: " + accountName + "\nAccount Pin: " + accountPin + "\nAccount History: " + accountHistory;
	}

}