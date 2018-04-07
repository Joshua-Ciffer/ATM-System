package src.atm.account;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * This class provides methods and functionality for users to have bank accounts that can store their balances and make deposits, withdrawals, or transfers.
 * 
 * @author Joshua Ciffer
 * @version 02/26/2018
 */
public class BankAccount extends Account {

	/**
	 * Used to format currency values from a numerical data type to a formatted string with the pattern $XXX.XX.
	 */
	static final NumberFormat US_DOLLARS = NumberFormat.getCurrencyInstance(Locale.US);

	/**
	 * Stores the user's account balance saved to two significant decimal places.
	 */
	BigDecimal accountBalance;

	/**
	 * Opens a new bank account with the user's name, PIN, and starting balance. Upon calling the constructor in Account, this account is added to the account map.
	 * If any of the parameters are not valid, the account is closed and removed from the account map.
	 * 
	 * @param accountName
	 *        The account holder's name.
	 * @param accountPin
	 *        The user's 4 digit PIN.
	 * @param accountBalance
	 *        The account's starting balance.
	 * @throws IllegalArgumentException
	 *         Thrown if a negative amount is entered for the account's balance.
	 */
	public BankAccount(String accountName, Pin accountPin, BigDecimal accountBalance) throws IllegalArgumentException {
		super(accountName, accountPin);
		try {
			if (IS_POSITIVE_AMOUNT(accountBalance.doubleValue())) {
				this.accountBalance = accountBalance.setScale(2, RoundingMode.HALF_UP);		// Sets accountBalance to round to 2 significant digits.
			}
		} catch (IllegalArgumentException e) {
			closeAccount(this.accountPin.getPin());
			throw new IllegalArgumentException("Please enter a positive amount for your account balance.");
		}
	}

	/**
	 * Formats a numerical amount into a USD currency format.
	 * 
	 * @param amount
	 *        The number to be formatted.
	 * @return A string in USD currency format.
	 */
	public static final String TO_CURRENCY_FORMAT(double amount) {
		return US_DOLLARS.format(amount);
	}

	/**
	 * Formats a numerical amount into a USD currency format.
	 * 
	 * @param amount
	 *        The number to be formatted.
	 * @return A string in USD currency format.
	 */
	public static final String TO_CURRENCY_FORMAT(BigDecimal amount) {
		return US_DOLLARS.format(amount);
	}

	/**
	 * Performs a check to see if the user has entered a positive amount for any currency values.
	 * 
	 * @param amount
	 *        The amount to check if positive.
	 * @return True, if the amount is greater than zero.
	 * @throws IllegalArgumentException
	 *         Thrown if the amount is less than zero.
	 */
	public static final boolean IS_POSITIVE_AMOUNT(double amount) throws IllegalArgumentException {
		if (amount >= 0) {
			return true;
		} else {
			throw new IllegalArgumentException("Please enter a positive amount.");
		}
	}

	/**
	 * Performs a check to see if the user has a sufficient balance to complete a transaction.
	 * 
	 * @param amount
	 *        The amount to see if the user has in their account balance.
	 * @return True, if the user has enough money in their balance to complete a transaction.
	 * @throws IllegalArgumentException
	 *         Thrown if the amount the user needs is greater than their balance.
	 */
	public final boolean hasSufficientBalance(double amount) throws IllegalArgumentException {
		if (accountBalance.compareTo(new BigDecimal(amount)) >= 0) {
			return true;
		} else {
			throw new IllegalArgumentException("You have an insufficient balance to complete this transaction.");
		}
	}

	/**
	 * User chooses another account to send money to. Money is withdrawn from their account and deposited in the account they specify. This method adds interest if
	 * the
	 * accounts in use are savings accounts.
	 * 
	 * @param receivingAccount
	 *        The account to receive the money.
	 * @param transferAmount
	 *        The amount of money to transfer.
	 * @throws IllegalArgumentException
	 *         Thrown if the user enters a negative amount of money to transfer, or they have an insufficient balance.
	 * @throws NullPointerException
	 *         Thrown if the account the user wants to transfer money to does not exist.
	 */
	public final void transfer(int receivingAccount, double transferAmount) throws IllegalArgumentException, NullPointerException {
		if (ACCOUNT_EXISTS(receivingAccount) && IS_POSITIVE_AMOUNT(transferAmount) && hasSufficientBalance(transferAmount)) {
			accountBalance = accountBalance.subtract(new BigDecimal(transferAmount));
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Transfered " + TO_CURRENCY_FORMAT(transferAmount) + " to account #"
					+ receivingAccount + ".\n";
			if (GET_ACCOUNT_MAP().get(receivingAccount) instanceof SavingsAccount) {
				SavingsAccount tempReceivingAccount = (SavingsAccount)GET_ACCOUNT_MAP().get(receivingAccount);
				tempReceivingAccount.accountBalance = tempReceivingAccount.accountBalance
						.add(new BigDecimal(transferAmount + tempReceivingAccount.getInterest(transferAmount)));
				tempReceivingAccount.accountHistory = tempReceivingAccount.accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Received "
						+ TO_CURRENCY_FORMAT(transferAmount + tempReceivingAccount.getInterest(transferAmount)) + " from account #" + ACCOUNT_NUMBER + ".\n";
			} else {
				BankAccount tempReceivingAccount = (BankAccount)GET_ACCOUNT_MAP().get(receivingAccount);
				tempReceivingAccount.accountBalance = tempReceivingAccount.accountBalance.add(new BigDecimal(transferAmount));
				tempReceivingAccount.accountHistory = tempReceivingAccount.accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Received "
						+ TO_CURRENCY_FORMAT(transferAmount) + " from account #" + ACCOUNT_NUMBER + ".\n";
			}
		}
	}

	/**
	 * Deposits a specified amount of money into the user's account.
	 * 
	 * @param depositAmount
	 *        The amount to deposit.
	 * @throws IllegalArgumentException
	 *         Thrown if the deposit amount is negative.
	 */
	public void deposit(double depositAmount) throws IllegalArgumentException {
		if (IS_POSITIVE_AMOUNT(depositAmount)) {
			accountBalance = accountBalance.add(new BigDecimal(depositAmount));
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Deposited " + TO_CURRENCY_FORMAT(depositAmount) + ".\n";
		}
	}

	/**
	 * Withdrawals a specified amount of money from the user's account.
	 * 
	 * @param withdrawalAmount
	 *        The amount to withdrawal.
	 * @throws IllegalArgumentException
	 *         Thrown if the withdrawal amount is negative or the user has an insufficient balance.
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
	 * @param bankAccount
	 *        The account to test equality with.
	 * @return True, if the account's contents are equal, false if otherwise.
	 */
	@Override
	public boolean equals(Object bankAccount) {
		if (this.toString().equalsIgnoreCase(((BankAccount)bankAccount).toString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return A concatenated string with all of the information stored in the account.
	 */
	@Override
	public String toString() {
		return "Account Number: " + ACCOUNT_NUMBER + " \nAccount Name: " + accountName + " \nAccount Pin: " + accountPin.getPin() + " \nAccount Balance: "
				+ TO_CURRENCY_FORMAT(accountBalance) + " \nAccount History: " + accountHistory + " ";
	}

	/**
	 * @return The account's balance.
	 */
	public final BigDecimal getAccountBalance() {
		return accountBalance;
	}

	/**
	 * @param accountBalance
	 *        The new account balance.
	 */
	final void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance.setScale(2, RoundingMode.HALF_UP);	  // Sets two significant decimal places.
	}

}