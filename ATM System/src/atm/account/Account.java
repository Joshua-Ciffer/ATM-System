
package src.atm.account ;
import java.util.HashMap ;
import java.time.LocalDateTime ;
import java.time.format.DateTimeFormatter ;
import src.atm.pin.Pin ;
import src.atm.pin.InvalidPinException ;
import src.atm.pin.IncorrectPinException ;
import src.atm.pin.PinMismatchException ;

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
	
	public static final Account GET_ACCOUNT(int accountNumber, String accountPin) throws AccountNotFoundException, IncorrectPinException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			if (ACCOUNT_MAP.get(accountNumber).accountPin.getPin().equalsIgnoreCase(accountPin)) {
				return ACCOUNT_MAP.get(accountNumber) ;
			} else {
				throw new IncorrectPinException() ;
			}
		} else {
			throw new AccountNotFoundException() ;
		}
	}
	
	public static final BankAccount GET_BANK_ACCOUNT(int accountNumber, String accountPin) throws AccountNotFoundException, IncorrectPinException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			if (ACCOUNT_MAP.get(accountNumber).accountPin.getPin().equalsIgnoreCase(accountPin)) {
				return (BankAccount)ACCOUNT_MAP.get(accountNumber) ;
			} else {
				throw new IncorrectPinException() ;
			}
		} else {
			throw new AccountNotFoundException() ;
		}
	}
	
	public static final AdminAccount GET_ADMIN_ACCOUNT(int accountNumber, String accountPin) throws AccountNotFoundException, IncorrectPinException {
		if (ACCOUNT_EXISTS(accountNumber)) {
			if (ACCOUNT_MAP.get(accountNumber).accountPin.getPin().equalsIgnoreCase(accountPin)) {
				return (AdminAccount)ACCOUNT_MAP.get(accountNumber) ;
			} else {
				throw new IncorrectPinException() ;
			}
		} else {
			throw new AccountNotFoundException() ;
		}
	}
	
	public static final boolean ACCOUNT_EXISTS(int accountNumber) throws AccountNotFoundException {
		if (ACCOUNT_MAP.containsKey(accountNumber)) {
			return true ;
		} else {
			throw new AccountNotFoundException() ;
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