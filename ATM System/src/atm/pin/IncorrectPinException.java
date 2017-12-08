
package src.atm.pin ;
import src.atm.account.Account;
import src.atm.account.AccountNotFoundException ;

/**
 * IncorrectPinException is thrown when a user enters the wrong PIN when
 * trying to log into their account.
 * 
 * This class cannot be extended.
 * This class extends Exception.
 * 
 * @author Joshua Ciffer
 * @version 12/03/2017
 */
public final class IncorrectPinException extends Exception {

	/**
	 * Constructs an IncorrectPinException with no details.
	 */
	public IncorrectPinException() {
		super() ;
	}
	
	/**
	 * Constructs an IncorrectPinException a given message.
	 * 
	 * @param message The message to be displayed with the stack trace.
	 */
	public IncorrectPinException(String message) {
		super(message) ;
	}
	
	/**
	 * Constructs an IncorrectPinException with a given cause.
	 * 
	 * @param cause The cause of this exception.
	 */
	public IncorrectPinException(Throwable cause) {
		super(cause) ;
	}
	
	/**
	 * Constructs an IncorrectPinException with a given message and cause.
	 * 
	 * @param message The message to be displayed with the stack trace.
	 * @param cause The cause of this exception.
	 */
	public IncorrectPinException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	/**
	 * Constructs an IncorrectPinException with a given message, cause, and specifies
	 * whether suppression is enabled or disabled or if the stack trace is writable 
	 * or not.
	 * 
	 * @param message The message to displayed with the stack trace.
	 * @param cause The cause of this exception.
	 * @param enableSuppression Specifies whether suppression is enabled or disabled.
	 * @param writableStackTrace Specifies whether the stack trace is writable.
	 */
	public IncorrectPinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace) ;
	}
	 
	/**
	 * Checks to see if the user enters the correct PIN to login to their account.
	 * 
	 * @param accountNumber The user's account number.
	 * @param accountPin The PIN the user enters.
	 * @throws InvalidPinException Thrown if the user enters non-numerical characters, or
	 * a number that is not 4 digits or positive.
	 * @throws IncorrectPinException Thrown if the PIN the user entered is not correct.
	 */
	public static void CHECK_PIN(int accountNumber, String accountPin) throws IncorrectPinException {
		try {
			Account.GET_ACCOUNT(accountNumber, accountPin) ;	// Could throw InvalidPinException or an
		} catch (AccountNotFoundException e) {    // an AccountNotFoudndException.  Throws an
			e.printStackTrace() ;                                       // IncorrectPinException if the PIN is wrong.
		}
	}
	
}