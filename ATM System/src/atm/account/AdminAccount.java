package src.atm.account;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

	public AdminAccount(String accountName, Pin accountPin) {
		super(accountName, accountPin, DATE_TIME.format(LocalDateTime.now()) + " - Account Opened\n");
	}

	public int createAdminAccount(String accountName, Pin accountPin) throws IllegalArgumentException {
		int newAccountNumber = new AdminAccount(accountName, accountPin).getAccountNumber();
		accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Created Admin Account #" + newAccountNumber + ".\n";
		return newAccountNumber;
	}

	public int createBankAccount(String accountName, Pin accountPin, BigDecimal accountBalance) throws IllegalArgumentException {
		int newAccountNumber = new BankAccount(accountName, accountPin, accountBalance).getAccountNumber();
		accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Created Bank Account #" + newAccountNumber + ".\n";
		return newAccountNumber;
	}

	public void editAccountName(int accountNumber, String accountName) throws NullPointerException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			GET_ACCOUNT_MAP().get(accountNumber).setAccountName(accountName);
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Changed Name On Account #" + accountNumber + ".\n";
		}
	}

	public void editAccountPin(int accountNumber, Pin accountPin) throws NullPointerException, IllegalArgumentException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			GET_ACCOUNT_MAP().get(accountNumber).setAccountPin(accountPin);
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Changed Pin On Account #" + accountNumber + ".\n";
		}
	}

	public void editAccountHistory(int accountNumber, String accountHistory) throws NullPointerException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			GET_ACCOUNT_MAP().get(accountNumber).setAccountHistory(accountHistory);
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Changed History On Account #" + accountNumber + ".\n";
		}
	}

	public void editAccountBalance(int accountNumber, BigDecimal accountBalance) throws NullPointerException, IllegalArgumentException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			if (GET_ACCOUNT_MAP().get(accountNumber) instanceof BankAccount) {
				if (BankAccount.IS_POSITIVE_AMOUNT(accountBalance.doubleValue())) {
					((BankAccount)GET_ACCOUNT_MAP().get(accountNumber)).setAccountBalance(accountBalance);
					accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Changed Balance On Account #" + accountNumber + ".\n";
				}
			} else {
				throw new IllegalArgumentException("This account is not a bank account.");
			}
		}
	}

	public void deleteAccount(int accountNumber) throws NullPointerException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			GET_ACCOUNT_MAP().remove(accountNumber, GET_ACCOUNT_MAP().get(accountNumber));
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Deleted Account #" + accountNumber + ".\n";
		}
	}

	public String getListOfAccounts() {
		Account[] listOfAccounts = GET_ACCOUNT_MAP().values().toArray(new Account[GET_ACCOUNT_MAP().size()]);	 // HashMap to Collection, to Account[]
		String accountList = listOfAccounts.length + " in the system.\n";
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