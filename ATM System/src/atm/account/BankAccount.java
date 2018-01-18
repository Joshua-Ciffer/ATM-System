package src.atm.account;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;
import src.atm.pin.Pin;

/**
 * This class provides methods and functionality for users to have bank accounts that can store their balances and make deposits, withdrawals, or transfers.
 * <br><br>
 * This class cannot be inherited.
 * This class inherits Account.
 * 
 * @author Joshua Ciffer
 * @version 01/18/2018
 */
public final class BankAccount extends Account {

	/**
	 * Used to format currency values from a numerical data type to a formatted string with the pattern $XXX.XX.  
	 */
	private static NumberFormat US_DOLLARS = NumberFormat.getCurrencyInstance(Locale.US);

	/**
	 * Stores the user's account balance saved to two significant decimal places.
	 */
	private BigDecimal accountBalance;

	/**
	 * Opens a new bank account with the user's name, PIN, and starting balance.  Upon calling the constructor in Account, this account is added to the account map.
	 * 
	 * @param accountName - The account holder's name.
	 * @param accountPin - The user's 4 digit PIN.
	 * @param accountBalance - The account's starting balance.
	 * @throws IllegalArgumentException Thrown if a negative amount is entered for the account's balance.
	 */
	public BankAccount(String accountName, Pin accountPin, BigDecimal accountBalance) throws IllegalArgumentException {
		super(accountName, accountPin, DATE_TIME.format(LocalDateTime.now()) + " - Account Opened\n");
		try {
			if (IS_POSITIVE_AMOUNT(accountBalance.doubleValue())) {
				this.accountBalance = accountBalance.setScale(2, RoundingMode.HALF_UP);	// Sets accountBalance to round to 2 significant digits
			}
		} catch (IllegalArgumentException e) {
			closeAccount(this.accountPin.getPin());
			throw new IllegalArgumentException("Please enter a positive amount for your account balance.");
		}
	}

	/**
	 * Formats a numerical amount into a USD currency format.
	 * 
	 * @param amount - The number to be formatted.
	 * @return A string in USD currency format.
	 */
	public static String TO_CURRENCY_FORMAT(double amount) {
		return US_DOLLARS.format(amount);
	}

	/**
	 * Formats a numerical amount into a USD currency format.
	 * 
	 * @param amount - The number to be formatted.
	 * @return A string in USD currency format.
	 */
	public static String TO_CURRENCY_FORMAT(BigDecimal amount) {
		return US_DOLLARS.format(amount);
	}

	/**
	 * Performs a check to see if the user has entered a positive amount for any currency values.
	 *  
	 * @param amount - The amount to check if positive.
	 * @return True - If the amount is greater than zero.
	 * @throws IllegalArgumentException Thrown if the amount is less than zero.
	 */
	public static boolean IS_POSITIVE_AMOUNT(double amount) throws IllegalArgumentException {
		if (amount >= 0) {
			return true;
		} else {
			throw new IllegalArgumentException("Please enter a positive amount.");
		}
	}

	/**
	 * Performs a check to see if the user has a sufficient balance to complete a transaction.
	 * 
	 * @param amount - The amount to see if the user has in their account balance.
	 * @return - True if the user has enough money in their balance to complete a transaction.
	 * @throws IllegalArgumentException Thrown if the amount the user needs is greater than their balance.
	 */
	public boolean hasSufficientBalance(double amount) throws IllegalArgumentException {
		if (accountBalance.compareTo(new BigDecimal(amount)) > 0) {
			return true;
		} else {
			throw new IllegalArgumentException("The amount you entered is greater than your account balance.");
		}
	}

	/**
	 * User chooses another account to send money to.  Money is withdrawn from their account and deposited in the account they specify.
	 * 
	 * @param receivingAccount - The account to receive the money.
	 * @param transferAmount - The amount of money to transfer.
	 * @throws IllegalArgumentException Thrown if the user enters a negative amount of money to transfer.
	 * @throws NullPointerException Thrown if the account the user wants to transfer money to does not exist.
	 */
	public void transfer(int receivingAccount, double transferAmount) throws IllegalArgumentException, NullPointerException {
		if (ACCOUNT_EXISTS(receivingAccount) && IS_POSITIVE_AMOUNT(transferAmount) && hasSufficientBalance(transferAmount)) {
			accountBalance = accountBalance.subtract(new BigDecimal(transferAmount));
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Transfered " + TO_CURRENCY_FORMAT(transferAmount) + ".\n";
			((BankAccount)GET_ACCOUNT_MAP().get(receivingAccount)).accountBalance = ((BankAccount)GET_ACCOUNT_MAP().get(receivingAccount)).accountBalance
					.add(new BigDecimal(transferAmount));
			((BankAccount)GET_ACCOUNT_MAP().get(receivingAccount)).accountHistory = ((BankAccount)GET_ACCOUNT_MAP().get(receivingAccount)).accountHistory
					+ DATE_TIME.format(LocalDateTime.now()) + " - Transfered " + TO_CURRENCY_FORMAT(receivingAccount) + " to account #" + receivingAccount + ".\n";
		}
	}

	/**
	 * Deposits a specified amount of money into the user's account.
	 * 
	 * @param depositAmount - The amount to deposit.
	 * @throws IllegalArgumentException Thrown if the deposit amount is negative.
	 */
	public void deposit(double depositAmount) throws IllegalArgumentException {
		if (IS_POSITIVE_AMOUNT(depositAmount)) {
			accountBalance = accountBalance.add(new BigDecimal(depositAmount));
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Deposited " + TO_CURRENCY_FORMAT(depositAmount) + ".\n";
		}
	}

	/**
	 * Withdrawals a specified amount of money into the user's account.
	 * 
	 * @param withdrawalAmount - The amount to withdrawal.
	 * @throws IllegalArgumentException Thrown if the withdrawal amount is negative or the user has an insufficient balance.
	 */
	public void withdraw(double withdrawalAmount) throws IllegalArgumentException {
		if (IS_POSITIVE_AMOUNT(withdrawalAmount) && hasSufficientBalance(withdrawalAmount)) {
			accountBalance = accountBalance.subtract(new BigDecimal(withdrawalAmount));
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Withdrew " + TO_CURRENCY_FORMAT(withdrawalAmount) + ".\n";
		}
	}

	/**
	 * Compares the contents of two bank account objects to test for equality.
	 * 
	 * @param bankAccount - The account to test equality with.
	 * @return True - If the account's contents are equal. <br>
	 * False - If the account's contents are not equal.
	 */
	@Override
	public boolean equals(Object bankAccount) {
		if (this.toString().equalsIgnoreCase(((BankAccount)bankAccount).toString())) {	// Casts bankAccount to type Bank Account
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Provides a string representation of the content of this object.
	 * 
	 * @return A concatenated string with all of the information stored in the account.
	 */
	@Override
	public String toString() {
		return "Account Number: " + ACCOUNT_NUMBER + "\nAccount Name: " + accountName + "\nAccount Pin: " + accountPin + "\nAccount Balance: "
				+ TO_CURRENCY_FORMAT(accountBalance) + "\nAccount History: " + accountHistory;
	}

	/**
	 * Sets the account's balance. <br>
	 * 
	 * This method is only accessible from src.atm.account.
	 * @param accountBalance
	 */
	void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

}