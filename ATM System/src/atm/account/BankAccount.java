
package src.atm.account ;
import java.math.BigDecimal ;
import java.text.NumberFormat ;
import java.util.Locale ;
import java.time.LocalDateTime ;
import src.atm.pin.Pin;
import src.atm.account.InsufficientBalanceException ;

public class BankAccount extends Account {

	
	private static NumberFormat US_DOLLARS = NumberFormat.getCurrencyInstance(Locale.US) ;
	
	
	private BigDecimal accountBalance ;
	
	public BankAccount() {
		super() ;
		this.accountBalance = new BigDecimal(0.00) ;	// Default Balance of $0.00
		this.accountBalance = this.accountBalance.setScale(2, BigDecimal.ROUND_HALF_UP) ;	// Sets accountBalance to round to 2 significant digits
	}

	public BankAccount(String accountName, Pin accountPin, BigDecimal accountBalance) {
		super(accountName, accountPin, DATE_TIME.format(LocalDateTime.now()) + " - Account Opened\n") ;
		this.accountBalance = accountBalance ;
		this.accountBalance = this.accountBalance.setScale(2, BigDecimal.ROUND_HALF_UP) ;	// Sets accountBalance to round to 2 significant digits
	}
	
	public static final String TO_CURRENCY_FORMAT(BigDecimal amount) {
		return US_DOLLARS.format(amount) ;
	}
	
	public static final boolean isPositiveAmount(double amount) throws IllegalArgumentException {
		if (amount > 0) {
			return true ;
		} else {
			throw new IllegalArgumentException("Please enter a positive amount.") ;
		}
	}
	
	public static final void TRANSFER(int transferringAccount, String accountPin, int receivingAccount, double transferAmount) throws InsufficientBalanceException {
		if (ACCOUNT_EXISTS(transferringAccount) && ACCOUNT_EXISTS(receivingAccount)) {
			InsufficientBalanceException.CHECK_BALANCE(transferringAccount, accountPin, transferAmount) ;
			((BankAccount)GET_ACCOUNT_MAP().get(transferringAccount)).accountBalance = ((BankAccount)GET_ACCOUNT_MAP().get(transferringAccount)).accountBalance.subtract(new BigDecimal(transferAmount)) ;			
			((BankAccount)GET_ACCOUNT_MAP().get(receivingAccount)).accountBalance = ((BankAccount)GET_ACCOUNT_MAP().get(receivingAccount)).accountBalance.add(new BigDecimal(transferAmount)) ;
			GET_ACCOUNT_MAP().get(transferringAccount).accountHistory = GET_ACCOUNT_MAP().get(transferringAccount).accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Transfered " + TO_CURRENCY_FORMAT(new BigDecimal(transferAmount)) + "\n" ;
			GET_ACCOUNT_MAP().get(receivingAccount).accountHistory = GET_ACCOUNT_MAP().get(receivingAccount).accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Received " + TO_CURRENCY_FORMAT(new BigDecimal(transferAmount)) + "\n" ;
			System.out.println("\nTransfered " + TO_CURRENCY_FORMAT(new BigDecimal(transferAmount)) + " To Account #" + receivingAccount + ". Your Balance Is Now " + TO_CURRENCY_FORMAT(((BankAccount)GET_ACCOUNT_MAP().get(transferringAccount)).getAccountBalance()) + "\n") ;
		}
	}
	
	public final void deposit(double depositAmount) {
		this.accountBalance = this.accountBalance.add(new BigDecimal(depositAmount)) ;
		this.accountHistory = this.accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Deposited " + TO_CURRENCY_FORMAT(new BigDecimal(depositAmount)) + "\n" ;
		System.out.println("\nDeposited " + TO_CURRENCY_FORMAT(new BigDecimal(depositAmount)) + " To Your Account. Your Balance Is Now " + TO_CURRENCY_FORMAT(this.accountBalance) + "\n") ;
	}
	
	public final void withdraw(double withdrawalAmount) throws InsufficientBalanceException {
		InsufficientBalanceException.CHECK_BALANCE(this.ACCOUNT_NUMBER, this.accountPin.getPin(), withdrawalAmount) ;
		this.accountBalance = this.accountBalance.subtract(new BigDecimal(withdrawalAmount)) ;
		this.accountHistory = this.accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Withdrew " + TO_CURRENCY_FORMAT(new BigDecimal(withdrawalAmount)) + "\n" ;
		System.out.println("\nWithdrew " + TO_CURRENCY_FORMAT(new BigDecimal(withdrawalAmount)) + " From Your Account. Your Balance Is Now " + TO_CURRENCY_FORMAT(this.accountBalance) + "\n") ;
	}
	
	public boolean equals(Object account) {		// Accepts Object as parameter so this method properly overrides the super class equals method
		BankAccount comparingAccount = (BankAccount)account ;	// Casts Object to type BankAccount
		if (this.toString().equalsIgnoreCase(comparingAccount.toString())) {
			return true ;
		} else {
			return false ;
		}
	}
	
	public String toString() {
		return "#" + this.ACCOUNT_NUMBER + ", " + this.accountName + ", " + TO_CURRENCY_FORMAT(this.accountBalance) + ", " + this.accountPin.toString() ;
	}

	
	
	
	public final BigDecimal getAccountBalance() {
		return this.accountBalance ;
	}
	
}