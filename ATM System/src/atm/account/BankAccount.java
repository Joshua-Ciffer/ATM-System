
package src.atm.account ;
import java.math.BigDecimal ;
import java.text.NumberFormat ;
import java.util.Locale ;
import java.time.LocalDateTime ;
import src.atm.account.AccountNotFoundException ;
import src.atm.account.NegativeAmountException ;
import src.atm.account.InsufficientBalanceException ;
import src.atm.pin.Pin ;
import src.atm.pin.InvalidPinException ;
import src.atm.pin.PinMismatchException ;
import src.atm.pin.IncorrectPinException ;

/**
 * This class contains the constructors, variables, and methods for creating a BankAccount object.
 * 
 * 
 * 
 * @author Joshua Ciffer
 * @version 12/03/2017
 */
public class BankAccount extends Account {

	
	/**
	 * A formatter object that is used to properly format U.S. currency values.
	 */
	private static NumberFormat US_DOLLARS = NumberFormat.getCurrencyInstance(Locale.US) ;
	
	
	private BigDecimal accountBalance ;
	
	public BankAccount() { 
		super() ;
		this.accountBalance = new BigDecimal(0.00) ;	// Default Balance of $0.00
		this.accountBalance = this.accountBalance.setScale(2, BigDecimal.ROUND_HALF_UP) ;	// Sets accountBalance to round to 2 significant digits
	}

	private BankAccount(final int ACCOUNT_NUMBER, String accountName, Pin accountPin, BigDecimal accountBalance) {
		super(ACCOUNT_NUMBER, accountName, accountPin, DATE_TIME.format(LocalDateTime.now()) + " - Account Opened\n") ;
		this.accountBalance = accountBalance ;
		this.accountBalance = this.accountBalance.setScale(2, BigDecimal.ROUND_HALF_UP) ;	// Sets accountBalance to round to 2 significant digits
	}
	
	public static void CREATE_ACCOUNT(int accountNumber, String accountName, String accountPin, String confirmPin, double accountBalance) throws NegativeAmountException, InvalidPinException, PinMismatchException {
		NegativeAmountException.CHECK_AMOUNT(accountBalance) ;
		PinMismatchException.COMPARE_PINS(accountPin, confirmPin) ;
		accountNumber = GENERATE_ACCOUNT_NUMBER(accountNumber) ;
		ACCOUNT_MAP.put(accountNumber, new BankAccount(accountNumber, accountName, Pin.CREATE_PIN(accountPin, confirmPin), new BigDecimal(accountBalance))) ;
		try {
			System.out.println("\nSuccessfully Created Account #" + accountNumber + " For " + accountName + ", With A Starting Balance Of " + TO_CURRENCY_FORMAT(((BankAccount) GET_ACCOUNT(accountNumber, accountPin)).getAccountBalance()) + ". Your Pin Is " + GET_ACCOUNT(accountNumber, accountPin).getAccountPin().getPin() + ".\n") ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	
	private static final int GENERATE_ACCOUNT_NUMBER(int accountNumber) {
		do {
			accountNumber = (int)(Math.random() * 1_000_000) ;	  // Generates 6 digit Account Number
			if (accountNumber < 100_000 || accountNumber > 999_999) {
				continue ;
			} else if (ACCOUNT_MAP.containsKey(accountNumber)) {
				continue ;
			} else {
				break ;
			}
		} while (true) ;
		return accountNumber ;
	}
	
	public static final String TO_CURRENCY_FORMAT(BigDecimal amount) {
		return US_DOLLARS.format(amount) ;
	}
	
	public static final void TRANSFER(int transferringAccount, String accountPin, int receivingAccount, double transferAmount) throws AccountNotFoundException, InvalidPinException, IncorrectPinException, NegativeAmountException, InsufficientBalanceException {
		AccountNotFoundException.FIND_ACCOUNT(transferringAccount) ;
		AccountNotFoundException.FIND_ACCOUNT(receivingAccount) ;
		IncorrectPinException.CHECK_PIN(transferringAccount, accountPin) ;
		NegativeAmountException.CHECK_AMOUNT(transferAmount) ;
		InsufficientBalanceException.CHECK_BALANCE(transferringAccount, accountPin, transferAmount) ;
		((BankAccount)GET_ACCOUNT_MAP().get(transferringAccount)).accountBalance = ((BankAccount)ACCOUNT_MAP.get(transferringAccount)).accountBalance.subtract(new BigDecimal(transferAmount)) ;			
		((BankAccount)ACCOUNT_MAP.get(receivingAccount)).accountBalance = ((BankAccount)ACCOUNT_MAP.get(receivingAccount)).accountBalance.add(new BigDecimal(transferAmount)) ;
		ACCOUNT_MAP.get(transferringAccount).accountHistory = ACCOUNT_MAP.get(transferringAccount).accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Transfered " + TO_CURRENCY_FORMAT(new BigDecimal(transferAmount)) + "\n" ;
		ACCOUNT_MAP.get(receivingAccount).accountHistory = ACCOUNT_MAP.get(receivingAccount).accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Received " + TO_CURRENCY_FORMAT(new BigDecimal(transferAmount)) + "\n" ;
		System.out.println("\nTransfered " + TO_CURRENCY_FORMAT(new BigDecimal(transferAmount)) + " To Account #" + receivingAccount + ". Your Balance Is Now " + TO_CURRENCY_FORMAT(((BankAccount)ACCOUNT_MAP.get(transferringAccount)).getAccountBalance()) + "\n") ;
	}
	
	public final void deposit(double depositAmount) throws NegativeAmountException {
		NegativeAmountException.CHECK_AMOUNT(depositAmount) ; 
		this.accountBalance = this.accountBalance.add(new BigDecimal(depositAmount)) ;
		this.accountHistory = this.accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Deposited " + TO_CURRENCY_FORMAT(new BigDecimal(depositAmount)) + "\n" ;
		System.out.println("\nDeposited " + TO_CURRENCY_FORMAT(new BigDecimal(depositAmount)) + " To Your Account. Your Balance Is Now " + TO_CURRENCY_FORMAT(this.accountBalance) + "\n") ;
	}
	
	public final void withdraw(double withdrawalAmount) throws NegativeAmountException, InsufficientBalanceException {
		NegativeAmountException.CHECK_AMOUNT(withdrawalAmount) ;
		InsufficientBalanceException.CHECK_BALANCE(this.ACCOUNT_NUMBER, this.accountPin.getPin(), withdrawalAmount) ;
		this.accountBalance = this.accountBalance.subtract(new BigDecimal(withdrawalAmount)) ;
		this.accountHistory = this.accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Withdrew " + TO_CURRENCY_FORMAT(new BigDecimal(withdrawalAmount)) + "\n" ;
		System.out.println("\nWithdrew " + TO_CURRENCY_FORMAT(new BigDecimal(withdrawalAmount)) + " From Your Account. Your Balance Is Now " + TO_CURRENCY_FORMAT(this.accountBalance) + "\n") ;
	}
	

	
	/**
	 * This method compares two BankAccount objects
	 * 
	 * @param account
	 * 		The BankAccount to compare to the BankAccount that the method was invoked on.
	 * @return true
	 * 		if both accounts content are equal.
	 * @return false
	 * 	if both accounts content are not equal.
	 */
	public boolean equals(Object account) {		// Accepts Object as parameter so this method properly overrides the super class equals method
		BankAccount comparingAccount = (BankAccount)account ;	// Casts Object to type BankAccount
		if (this.toString().equalsIgnoreCase(comparingAccount.toString())) {
			return true ;
		} else {
			return false ;
		}
	}
	
	/**
	 * This method returns a representation of the BankAccount object in the form of a concatenated string.
	 * 
	 * @return Concatenated string containing all of the object's fields.
	 */
	public String toString() {
		return "#" + this.ACCOUNT_NUMBER + ", " + this.accountName + ", " + TO_CURRENCY_FORMAT(this.accountBalance) + ", " + this.accountPin.toString() ;
	}

	
	
	
	/**
	 * This method returns the account's balance as a BigDecimal object.
	 * 
	 * @return Object variable accountBalance.
	 */
	public final BigDecimal getAccountBalance() {
		return this.accountBalance ;
	}
	
}