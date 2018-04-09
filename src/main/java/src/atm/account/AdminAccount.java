package src.atm.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This class provides special functionality and privileges that allow direct access and control of other bank accounts. An admin account can change values
 * stored in the accounts and view account information.
 * 
 * @author Joshua Ciffer
 * @version 04/08/2018
 */
public final class AdminAccount extends Account {

	// TODO: Review admin code.

	/**
	 * Creates a new admin account with the user's name and PIN. Upon calling the constructor in Account, this account is added to the account map. If any
	 * of the parameters are not valid, the account is closed and removed from the account map.
	 *
	 * @param accountName
	 *        The account holder's name.
	 * @param accountPin
	 *        The user's 4 digit PIN.
	 */
	public AdminAccount(String accountName, Pin accountPin) {
		super(accountName, accountPin);
	}

	/**
	 * Creates a new admin account.
	 *
	 * @param accountName
	 *        The account holder's name.
	 * @param accountPin
	 *        The account's PIN.
	 * @return The number of the account created.
	 */
	public int createAdminAccount(String accountName, Pin accountPin) {
		int newAccountNumber = new AdminAccount(accountName, accountPin).getAccountNumber();
		accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Created Admin Account #" + newAccountNumber + ".\n";
		return newAccountNumber;
	}

	/**
	 * Creates a new bank account.
	 *
	 * @param accountName
	 *        The account holder's name.
	 * @param accountPin
	 *        The account's PIN.
	 * @param accountBalance
	 *        The account's starting balance.
	 * @return The number of the account created.
	 * @throws IllegalArgumentException
	 *         Thrown if any of the parameters are invalid.
	 */
	public int createBankAccount(String accountName, Pin accountPin, BigDecimal accountBalance) throws IllegalArgumentException {
		int newAccountNumber = new BankAccount(accountName, accountPin, accountBalance).getAccountNumber();
		accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Created Bank Account #" + newAccountNumber + ".\n";
		return newAccountNumber;
	}

	/**
	 * Creates a new savings account.
	 *
	 * @param accountName
	 *        The account holder's name.
	 * @param accountPin
	 *        The account's PIN.
	 * @param accountBalance
	 *        The account's starting balance.
	 * @param interestRate
	 *        The account's interest rate.
	 * @return The number of the account created.
	 * @throws IllegalArgumentException
	 *         Thrown if any of the parameters are invalid.
	 */
	public int createSavingsAccount(String accountName, Pin accountPin, BigDecimal accountBalance, double interestRate) throws IllegalArgumentException {
		int newAccountNumber = new SavingsAccount(accountName, accountPin, accountBalance, interestRate).getAccountNumber();
		accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Created Savings Account #" + newAccountNumber + ".\n";
		return newAccountNumber;
	}

	/**
	 * Changes the name of an account.
	 * 
	 * @param accountNumber
	 *        The number of the account to modify.
	 * @param accountName
	 *        The new name to set.
	 * @throws NullPointerException
	 *         Thrown if an account with the specified account number does not exist.
	 */
	public void editAccountName(int accountNumber, String accountName) throws NullPointerException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			GET_ACCOUNT_MAP().get(accountNumber).setAccountName(accountName);
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Changed Name On Account #" + accountNumber + ".\n";
		}
	}

	/**
	 * Changes the PIN of an account.
	 *
	 * @param accountNumber
	 *        The number of the account to modify.
	 * @param accountPin
	 *        The new PIN to set.
	 * @throws NullPointerException
	 *         Thrown if an account with the specified account number does not exist.
	 */
	public void editAccountPin(int accountNumber, Pin accountPin) throws NullPointerException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			GET_ACCOUNT_MAP().get(accountNumber).setAccountPin(accountPin);
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Changed Pin On Account #" + accountNumber + ".\n";
		}
	}

	/**
	 * Changes the account history of an account.
	 *
	 * @param accountNumber
	 *        The number of the account to modify.
	 * @param accountHistory
	 *        The new account history to set.
	 * @throws NullPointerException
	 *         Thrown if an account with the specified account number does not exist.
	 */
	public void editAccountHistory(int accountNumber, String accountHistory) throws NullPointerException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			GET_ACCOUNT_MAP().get(accountNumber).setAccountHistory(accountHistory);
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Changed History On Account #" + accountNumber + ".\n";
		}
	}

	/**
	 * Changes the account balance of a bank account.
	 *
	 * @param accountNumber
	 *        The number of the account to modify.
	 * @param accountBalance
	 *        The new account balance to set.
	 * @throws NullPointerException
	 *         Thrown if an account with the specified account number does not exist.
	 * @throws IllegalArgumentException
	 *         Thrown if the account with the specified is not a bank account.
	 */
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

	/**
	 * Changes the interest rate of a savings account.
	 *
	 * @param accountNumber
	 *        The number of the account to modify.
	 * @param interestRate
	 *        The new interest rate to set.
	 * @throws NullPointerException
	 *         Thrown if an account with the specified account number does not exist.
	 * @throws IllegalArgumentException
	 *         Thrown if the account with the specified is not a savings account.
	 */
	public void editInterestRate(int accountNumber, double interestRate) throws NullPointerException, IllegalArgumentException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			if (GET_ACCOUNT_MAP().get(accountNumber) instanceof SavingsAccount) {
				if (BankAccount.IS_POSITIVE_AMOUNT(interestRate)) {
					((SavingsAccount)GET_ACCOUNT_MAP().get(accountNumber)).setInterestRate(interestRate);
					accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Changed Interest Rate On Account #" + accountNumber + ".\n";
				}
			} else {
				throw new IllegalArgumentException("This account is not a savings account.");
			}
		}
	}

	/**
	 * Deletes a specified account.
	 *
	 * @param accountNumber
	 *        The number of the account to be deleted.
	 * @throws NullPointerException
	 *         Thrown if an account with the specified account number does not exist.
	 */
	public void deleteAccount(int accountNumber) throws NullPointerException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			GET_ACCOUNT_MAP().remove(accountNumber, GET_ACCOUNT_MAP().get(accountNumber));
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Deleted Account #" + accountNumber + ".\n";
		}
	}

	/**
	 * @return A concatenated list of all the accounts stored in the account database.
	 */
	public String getListOfAccounts() {
		Account[] listOfAccounts = GET_ACCOUNT_MAP().values().toArray(new Account[GET_ACCOUNT_MAP().size()]);	 // HashMap to Collection, to Account[]
		String accountList = listOfAccounts.length + " in the system.\n";
		for (Account a : listOfAccounts) {
			accountList = accountList + a.toString() + "\n";
		}
		return accountList;
	}

	/**
	 * Compares the content of two Admin Accounts for equality.
	 * 
	 * @param adminAccount
	 *        The admin account to test equality with.
	 * @return True, if the account's are equal, false if otherwise.
	 */
	@Override
	public boolean equals(Object adminAccount) {
		if (this.toString().equalsIgnoreCase(((AdminAccount)adminAccount).toString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return A string representation of the content of this object.
	 */
	@Override
	public String toString() {
		return "Account Number: " + ACCOUNT_NUMBER + "\nAccount Name: " + accountName + "\nAccount Pin: " + accountPin + "\nAccount History: " + accountHistory;
	}

}