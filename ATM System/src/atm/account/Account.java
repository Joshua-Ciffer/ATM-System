
package src.atm.account ;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap ;
import java.math.BigDecimal ;
import src.atm.pin.Pin ;
import src.atm.pin.InvalidPinException ;
import src.atm.pin.IncorrectPinException ;
import src.atm.pin.PinMismatchException ;

public abstract class Account {

	protected static HashMap<Integer, Account> ACCOUNT_MAP = new HashMap<>() ;
	protected static DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm a") ;
	protected final int ACCOUNT_NUMBER ;
	protected String accountName ;
	protected String accountHistory ;
	protected Pin accountPin ;
	protected BigDecimal accountBalance ;
	
	protected Account() {
		this.ACCOUNT_NUMBER = 000_000 ;
		this.accountName = "" ;
		this.accountHistory = "" ;
		this.accountPin = new Pin() ;
	}
	
	protected Account(final int ACCOUNT_NUMBER, String accountName, Pin accountPin, String accountHistory) {
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
	
	public static final boolean ACCOUNT_EXISTS(int accountNumber) throws AccountNotFoundException {
		if (ACCOUNT_MAP.containsKey(accountNumber)) {
			return true ;
		} else {
			throw new AccountNotFoundException() ;
		}
	}
	
	public final void changeAccountPin(String oldPin, String newPin, String confirmPin) throws InvalidPinException, IncorrectPinException, PinMismatchException {
		this.accountPin.changePin(this.ACCOUNT_NUMBER, oldPin, newPin, confirmPin) ;
		this.accountHistory = this.accountHistory + DATE_TIME.format(LocalDateTime.now()) + " - Pin Changed.\n" ;
	}
	
	public static final HashMap<Integer, Account> GET_ACCOUNT_MAP() {
		return Account.ACCOUNT_MAP ;
	}
	
	public abstract boolean equals(Object account) ;
	
	public abstract String toString() ;
	
	public final int getAccountNumber() {
		return this.ACCOUNT_NUMBER ;
	}
	
	public final String getAccountName() {
		return this.accountName ;
	}
	
	public final String getAccountHistory() {
		return this.accountHistory ;
	}
	
	public final Pin getAccountPin() {
		return this.accountPin ;
	}
	
	public abstract BigDecimal getAccountBalance() ;
	
}