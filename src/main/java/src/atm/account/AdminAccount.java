package src.atm.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This class provides special functionality and privileges that allow direct access and control of other bank accounts. An admin account can change values
 * stored in the accounts and view account information.
 * 
 * @author Joshua Ciffer
 * @version 02/26/2018
 */
public final class AdminAccount extends Account {

	// TODO: Finish writing admin methods.
	// TODO: Review admin code.
	// TODO: Write admin javadoc.

	/**
	 *
	 *
	 * @param accountName
	 * @param accountPin
	 */
	public AdminAccount(String accountName, Pin accountPin) {
		super(accountName, accountPin);
	}

	/**
	 *
	 *
	 * @param accountName
	 * @param accountPin
	 * @return
	 * @throws IllegalArgumentException
	 */
	public int createAdminAccount(String accountName, Pin accountPin) throws IllegalArgumentException {
		int newAccountNumber = new AdminAccount(accountName, accountPin).getAccountNumber();
		accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Created Admin Account #" + newAccountNumber + ".\n";
		return newAccountNumber;
	}

	/**
	 *
	 *
	 * @param accountName
	 * @param accountPin
	 * @param accountBalance
	 * @return
	 * @throws IllegalArgumentException
	 */
	public int createBankAccount(String accountName, Pin accountPin, BigDecimal accountBalance) throws IllegalArgumentException {
		int newAccountNumber = new BankAccount(accountName, accountPin, accountBalance).getAccountNumber();
		accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Created Bank Account #" + newAccountNumber + ".\n";
		return newAccountNumber;
	}

	/**
	 *
	 *
	 * @param accountName
	 * @param accountPin
	 * @param accountBalance
	 * @param interestRate
	 * @return
	 * @throws IllegalArgumentException
	 */
	public int createSavingsAccount(String accountName, Pin accountPin, BigDecimal accountBalance, double interestRate) throws IllegalArgumentException {
		int newAccountNumber = new SavingsAccount(accountName, accountPin, accountBalance, interestRate).getAccountNumber();
		accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Created Savings Account #" + newAccountNumber + ".\n";
		return newAccountNumber;
	}

	/**
	 *
	 *
	 * @param accountNumber
	 * @param accountName
	 * @throws NullPointerException
	 */
	public void editAccountName(int accountNumber, String accountName) throws NullPointerException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			GET_ACCOUNT_MAP().get(accountNumber).setAccountName(accountName);
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Changed Name On Account #" + accountNumber + ".\n";
		}
	}

	/**
	 *
	 *
	 * @param accountNumber
	 * @param accountPin
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public void editAccountPin(int accountNumber, Pin accountPin) throws NullPointerException, IllegalArgumentException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			GET_ACCOUNT_MAP().get(accountNumber).setAccountPin(accountPin);
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Changed Pin On Account #" + accountNumber + ".\n";
		}
	}

	/**
	 *
	 *
	 * @param accountNumber
	 * @param accountHistory
	 * @throws NullPointerException
	 */
	public void editAccountHistory(int accountNumber, String accountHistory) throws NullPointerException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			GET_ACCOUNT_MAP().get(accountNumber).setAccountHistory(accountHistory);
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Changed History On Account #" + accountNumber + ".\n";
		}
	}

	/**
	 *
	 *
	 * @param accountNumber
	 * @param accountBalance
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
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
	 *
	 *
	 * @param accountNumber
	 * @param interestRate
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
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
	 *
	 *
	 * @param accountNumber
	 * @throws NullPointerException
	 */
	public void deleteAccount(int accountNumber) throws NullPointerException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			GET_ACCOUNT_MAP().remove(accountNumber, GET_ACCOUNT_MAP().get(accountNumber));
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Deleted Account #" + accountNumber + ".\n";
		}
	}

	/**
	 *
	 *
	 * @return
	 */
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
		if (this.toString().equalsIgnoreCase(((AdminAccount)adminAccount).toString())) {
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