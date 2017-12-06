
package src.atm.account ;
import java.util.HashMap ;
import java.math.BigDecimal ;
import java.text.NumberFormat ;
import java.util.Locale ;
import java.time.LocalDateTime ;
import java.time.format.DateTimeFormatter ;
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
public class BankAccount {

	/**
	 * A map using account number Integers as key references to BankAccount objects.
	 */
	private static HashMap<Integer, BankAccount> ACCOUNT_MAP = new HashMap<>() ;
	
	/**
	 * A formatter object that is used to properly format U.S. currency values.
	 */
	private static NumberFormat US_DOLLARS = NumberFormat.getCurrencyInstance(Locale.US) ;
	
	/**
	 * A date/time formatter object used to properly format the date and time used for account history.]
	 * Date and time is formatted in the pattern: MM/dd/yyyy HH:mm a.  Example: 12/03/2017 4:35 PM.
	 */
	private static DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm a") ;
	
	/**
	 * A randomly generated 6 digit integer used as a means of identifying a specific bank account.
	 */
	private final int ACCOUNT_NUMBER ;
	
	/**
	 * The account holder's name.
	 */
	private String accountName ; 
	
	/**
	 * 
	 */
	private String accountHistory ;
	
	/**
	 * A 4 digit number used as a password to login to an account stored in a wrapper object.
	 */
	private Pin accountPin ;
	
	/**
	 * The account's monetary balance.
	 */
	private BigDecimal accountBalance ;
	
	@SuppressWarnings("deprecation")
	public BankAccount() { 
		this.ACCOUNT_NUMBER = 000_000 ;	    // Default Account Number
		this.accountName = "" ;		// Blank Name
		this.accountHistory = "" ;
		this.accountPin = new Pin() ;	// Default Pin 0000
		this.accountBalance = new BigDecimal(0.00) ;	// Default Balance of $0.00
		this.accountBalance = this.accountBalance.setScale(2, BigDecimal.ROUND_HALF_UP) ;	// Sets accountBalance to round to 2 significant digits
	}

	@SuppressWarnings("deprecation")
	private BankAccount(final int ACCOUNT_NUMBER, String accountName, Pin accountPin, BigDecimal accountBalance) {
		this.ACCOUNT_NUMBER = ACCOUNT_NUMBER ;
		this.accountName = accountName ;
		this.accountHistory = DATE_TIME.format(LocalDateTime.now()) + " - Account Opened\n" ;
		this.accountPin = accountPin ;
		this.accountBalance = accountBalance ;
		this.accountBalance = this.accountBalance.setScale(2, BigDecimal.ROUND_HALF_UP) ;	// Sets accountBalance to round to 2 significant digits
	}
	
	public static void CREATE_ACCOUNT(int accountNumber, String accountName, String accountPin, String confirmPin, double accountBalance) throws NegativeAmountException, InvalidPinException, PinMismatchException {
		NegativeAmountException.CHECK_AMOUNT(accountBalance) ;
		PinMismatchException.COMPARE_PINS(accountPin, confirmPin) ;
		accountNumber = GENERATE_ACCOUNT_NUMBER(accountNumber) ;
		ACCOUNT_MAP.put(accountNumber, new BankAccount(accountNumber, accountName, Pin.CREATE_PIN(accountPin, confirmPin), new BigDecimal(accountBalance))) ;
		try {
			System.out.println("\nSuccessfully Created Account #" + accountNumber + " For " + accountName + ", With A Starting Balance Of " + TO_CURRENCY_FORMAT(GET_ACCOUNT(accountNumber, accountPin).accountBalance) + ". Your Pin Is " + GET_ACCOUNT(accountNumber, accountPin).accountPin.getPin() + ".\n") ;
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
	
	public static final void CLOSE_ACCOUNT(int accountNumber) throws AccountNotFoundException {
		AccountNotFoundException.FIND_ACCOUNT(accountNumber) ;
		ACCOUNT_MAP.remove(accountNumber) ;
		System.out.println("\nYour Account Has Been Closed.\n") ;
	}
	
	public static final BankAccount GET_ACCOUNT(int accountNumber, String accountPin) throws AccountNotFoundException, InvalidPinException, IncorrectPinException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			if (ACCOUNT_MAP.get(accountNumber).accountPin.getPin().equalsIgnoreCase(accountPin)) {
				return ACCOUNT_MAP.get(accountNumber) ;
			} else {
				throw new IncorrectPinException("The PIN you entered was incorrect.") ;
			}
		} else {
			throw new AccountNotFoundException("The account you entered does not exist. Please create an account.") ;
		}
	}
	
	public static final HashMap<Integer, BankAccount> GET_ACCOUNT_MAP() {
		return BankAccount.ACCOUNT_MAP ;
	}
	
	// When GET_ACCOUNT() is written this way, when creating or accessing an account, a stack overflow error is produced. 
	//public static BankAccount GET_ACCOUNT(int accountNumber, String accountPin) throws AccountNotFoundException, InvalidPinException, IncorrectPinException {
	//	AccountNotFoundException.THROW(accountNumber) ;
	//	IncorrectPinException.THROW(accountNumber, accountPin) ;
	//	return ACCOUNT_MAP.get(accountNumber) ;
	// }
	
	public static final boolean ACCOUNT_EXISTS(int accountNumber) throws AccountNotFoundException {
		if (ACCOUNT_MAP.containsKey(accountNumber)) {
			return true ;
		} else {
			throw new AccountNotFoundException("The account you entered does not exist. Please create an account.") ;
		}
	}

	public static final void LIST_ACCOUNTS() {	// DEBUG USE
		BankAccount[] listOfAccounts = ACCOUNT_MAP.values().toArray(new BankAccount[ACCOUNT_MAP.size()]) ;	 // HashMap to Collection, to BankAccount[]
		for (BankAccount ba : listOfAccounts) {
			System.out.println(ba.toString()) ;
		}
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
		ACCOUNT_MAP.get(transferringAccount).accountBalance = ACCOUNT_MAP.get(transferringAccount).accountBalance.subtract(new BigDecimal(transferAmount)) ;			
		ACCOUNT_MAP.get(receivingAccount).accountBalance = ACCOUNT_MAP.get(receivingAccount).accountBalance.add(new BigDecimal(transferAmount)) ;
		ACCOUNT_MAP.get(transferringAccount).accountHistory = ACCOUNT_MAP.get(transferringAccount).accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Transfered " + TO_CURRENCY_FORMAT(new BigDecimal(transferAmount)) + "\n" ;
		ACCOUNT_MAP.get(receivingAccount).accountHistory = ACCOUNT_MAP.get(receivingAccount).accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Received " + TO_CURRENCY_FORMAT(new BigDecimal(transferAmount)) + "\n" ;
		System.out.println("\nTransfered " + TO_CURRENCY_FORMAT(new BigDecimal(transferAmount)) + " To Account #" + receivingAccount + ". Your Balance Is Now " + TO_CURRENCY_FORMAT(ACCOUNT_MAP.get(transferringAccount).accountBalance) + "\n") ;
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
	
	public final void changeAccountPin(String oldPin, String newPin, String confirmPin) throws IncorrectPinException, InvalidPinException, PinMismatchException {
		this.accountPin.changePin(this.ACCOUNT_NUMBER, oldPin, newPin, confirmPin) ;
		this.accountHistory = this.accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Pin Changed\n" ; 
		System.out.println("\nYour Pin Has Been Changed.\n") ;
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
	 * This method returns the constant account number as an int.
	 * 
	 * @return Object constant ACCOUNT_NUMBER.
	 */
	public final int getAccountNumber() {
		return this.ACCOUNT_NUMBER ;
	}
	
	/**
	 * This method returns the account holder's name as a string.
	 * 
	 * @return Object variable accountName.
	 */
	public final String getAccountName() {
		return this.accountName ;
	}
	
	/**
	 * This method returns the account's history in the form of a concatenated string.
	 * 
	 * @return Object variable accountHistory.
	 */
	public final String getAccountHistory() {
		return this.accountHistory ;
	}
	
	/** 
	 * This method returns the account's pin as a Pin object.
	 * 
	 * @return Object variable accountPin.
	 */
	public final Pin getAccountPin() {
		return this.accountPin ;
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