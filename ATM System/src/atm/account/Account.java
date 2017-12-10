
package src.atm.account ;
import java.util.HashMap ;
import src.atm.pin.Pin;
import java.time.LocalDateTime ;
import java.time.format.DateTimeFormatter ;

public abstract class Account {

	static HashMap<Integer, Account> ACCOUNT_MAP = new HashMap<>() ;
	static DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm a") ;
	final int ACCOUNT_NUMBER ;
	String accountName ;
	Pin accountPin ;
	String accountHistory ;
	
	Account() {
		ACCOUNT_NUMBER = 000_000 ;
		accountName = "" ;
		accountPin = new Pin() ;
		accountHistory = "" ;
	}
	
	Account(final int ACCOUNT_NUMBER, String accountName, Pin accountPin, String accountHistory) {
		this.ACCOUNT_NUMBER = ACCOUNT_NUMBER ;
		this.accountName = accountName ;
		this.accountPin = accountPin ;
		this.accountHistory = accountHistory ;
	}
	
	public static final void CREATE_ACCOUNT(String accountName, String accountPin, String confirmPin) {
		
	}
	
	static final int GENERATE_ACCOUNT_NUMBER() {
		int accountNumber ;
		do {
			accountNumber = (int)(Math.random() * 1_000_000) ;
			if ((accountNumber < 100_000) || (accountNumber > 999_999)) {
				continue ;
			} else if (ACCOUNT_MAP.containsKey(accountNumber)) {
				continue ;
			} else {
				break ;
			}
		} while (true) ;
		return accountNumber ;
	}
	
	public static final Account GET_ACCOUNT(int accountNumber, String accountPin) {
		if (ACCOUNT_EXISTS(accountNumber)) {
			if (ACCOUNT_MAP.get(accountNumber).accountPin.getPin().equalsIgnoreCase(accountPin)) {
				return ACCOUNT_MAP.get(accountNumber) ;
			} else {
			}
		} else {
		}
	}
	
	public static final boolean ACCOUNT_EXISTS(int accountNumber) throws NullPointerException {
		if (ACCOUNT_MAP.containsKey(accountNumber)) {
			return true ;
		} else {
			throw new NullPointerException("The account you entered does not exist. Please create an account.") ;
		}
	}
	
	public static final void CLOSE_ACCOUNT(int accountNumber, String accountPin) throws AccountNotFoundException, IncorrectPinException {
		IncorrectPinException.CHECK_PIN(accountNumber, accountPin) ;
		AccountNotFoundException.FIND_ACCOUNT(accountNumber) ;
		ACCOUNT_MAP.remove(accountNumber) ;
	}
	
	public final void changeAccountPin(String oldPin, String newPin, String confirmPin) throws InvalidPinException, IncorrectPinException, PinMismatchException {
		accountPin.changePin(ACCOUNT_NUMBER, oldPin, newPin, confirmPin) ;
		accountHistory = accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Pin Changed.\n" ;
	}
	
	static final HashMap<Integer, Account> GET_ACCOUNT_MAP() {
		return ACCOUNT_MAP ;
	}
	
	static final DateTimeFormatter GET_DATE_TIME() {
		return DATE_TIME ;
	}
 	
	@Override
	public abstract boolean equals(Object account) ;
	
	@Override
	public abstract String toString() ;
	
	public final int getAccountNumber() {
		return ACCOUNT_NUMBER ;
	}
	
	public final String getAccountName() {
		return accountName ;
	}
	
	public final Pin getAccountPin() {
		return accountPin ;
	}
	
	public final String getAccountHistory() {
		return accountHistory ;
	}
	
}