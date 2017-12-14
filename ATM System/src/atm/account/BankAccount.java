package src.atm.account ;
import java.math.BigDecimal ;
import java.text.NumberFormat ;
import java.time.LocalDateTime ;
import java.util.Locale ;
import src.atm.pin.Pin ;

public final class BankAccount extends Account {
	
	private static NumberFormat US_DOLLARS = NumberFormat.getCurrencyInstance(Locale.US) ;
	
	private BigDecimal accountBalance ;
	
	public BankAccount(String accountName, Pin accountPin, BigDecimal accountBalance) {
		super(accountName, accountPin, DATE_TIME.format(LocalDateTime.now()) + " - Account Opened\n") ;
		this.accountBalance = accountBalance.setScale(2, BigDecimal.ROUND_HALF_UP) ;	// Sets accountBalance to round to 2 significant digits
	}
	
	public static String TO_CURRENCY_FORMAT(double amount) {
		return US_DOLLARS.format(amount) ;
	}
	
	public static String TO_CURRENCY_FORMAT(BigDecimal amount) {
		return US_DOLLARS.format(amount) ;
	}
	
	public static boolean IS_POSITIVE_AMOUNT(double amount) throws IllegalArgumentException {
		if (amount > 0) {
			return true ;
		} else {
			throw new IllegalArgumentException("Please enter a positive amount.") ;
		}
	}
	
	public boolean hasSufficientBalance(double amount) throws IllegalArgumentException {
		if (accountBalance.compareTo(new BigDecimal(amount)) > 0) {	
			return true ;
		} else {
			throw new IllegalArgumentException("The amount you entered is greater than your account balance.") ;
		}
	}
	
	public void transfer(int receivingAccount, double transferAmount) throws IllegalArgumentException, NullPointerException {
		if (ACCOUNT_EXISTS(receivingAccount) && IS_POSITIVE_AMOUNT(transferAmount) && hasSufficientBalance(transferAmount)) {
			accountBalance = accountBalance.subtract(new BigDecimal(transferAmount)) ;
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Transfered " + TO_CURRENCY_FORMAT(transferAmount) + ".\n" ;
			((BankAccount)GET_ACCOUNT_MAP().get(receivingAccount)).accountBalance = 
					((BankAccount)GET_ACCOUNT_MAP().get(receivingAccount)).accountBalance.add(new BigDecimal(transferAmount)) ;
			((BankAccount)GET_ACCOUNT_MAP().get(receivingAccount)).accountHistory =
					((BankAccount)GET_ACCOUNT_MAP().get(receivingAccount)).accountHistory + DATE_TIME.format(LocalDateTime.now()) + 
					" - Transfered " + TO_CURRENCY_FORMAT(receivingAccount) + " to account #" + receivingAccount + ".\n" ;
		}
	}
	
	public void deposit(double depositAmount) throws IllegalArgumentException {
		if (IS_POSITIVE_AMOUNT(depositAmount)) {
			accountBalance = accountBalance.add(new BigDecimal(depositAmount)) ;
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Deposited " + TO_CURRENCY_FORMAT(depositAmount) + ".\n" ;
		}
	}
	
	public void withdraw(double withdrawalAmount) throws IllegalArgumentException {
		if (IS_POSITIVE_AMOUNT(withdrawalAmount) && hasSufficientBalance(withdrawalAmount)) {
			accountBalance = accountBalance.subtract(new BigDecimal(withdrawalAmount)) ;
			accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Withdrew " + TO_CURRENCY_FORMAT(withdrawalAmount) + ".\n" ;
		}
	}
	
	@Override
	public boolean equals(Object bankAccount) {
		if (this.toString().equalsIgnoreCase(((BankAccount)bankAccount).toString())) {	// Casts bankAccount to type Bank Account
			return true ;
		} else {
			return false ;
		}
	}
	
	@Override
	public String toString() {
		return "Account Number: " + ACCOUNT_NUMBER + "\nAccount Name: " + accountName + "\nAccount Pin: " + accountPin + "\nAccount Balance: " + 
				TO_CURRENCY_FORMAT(accountBalance) + "\nAccount History: " + accountHistory ;
	}

	void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance.setScale(2, BigDecimal.ROUND_HALF_UP) ;
	}
	
	public BigDecimal getAccountBalance() {
		return accountBalance ;
	}
	
}