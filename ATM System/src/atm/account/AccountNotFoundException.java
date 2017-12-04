
package src.atm.account ;

/**
 * AccountNotFoundException is thrown when an account with a given number
 * can not be found or does not exist.
 * 
 * This class cannot be extended.
 * This class extends Exception.
 *  
 * @author Joshua Ciffer
 * @version 12/03/2017
 */
public final class AccountNotFoundException extends Exception {
	
	/**
	 * Constructs an AccountNotFoundException with no details.
	 */
	public AccountNotFoundException() {
		super() ;
	}
	
	/**
	 * Constructs an AccountNotFoundException with a given message.
	 * 
	 * @param message The message to be displayed with the stack trace.
	 */
	public AccountNotFoundException(String message) {
		super(message) ;
	}
	
	/**
	 * Constructs an AccountNotFoundException with a given cause.
	 * 
	 * @param cause The cause of this exception.
	 */
	public AccountNotFoundException(Throwable cause) {
		super(cause) ;
	}
	
	/**
	 * Constructs an AccountNotFoundException with a given message and cause.
	 * 
	 * @param message The message to be displayed with the stack trace.
	 * @param cause The cause of this exception
	 */
	public AccountNotFoundException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	/**
	 * Constructs an AccountNotFoundException with a given message, cause, and specifies
	 * whether or not suppression is enabled or disabled or if the stack trace is writable.
	 * 
	 * @param message The message to be displayed with the stack trace.
	 * @param cause The cause of this exception.
	 * @param enableSuppression Specifies whether or not suppression is enabled.
	 * @param writableStackTrace Specifies whether or not the stack trace is writable.
	 */
	public AccountNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace) ;
	}
	
	/**
	 * Checks to see if an account with the given account number exists.
	 * 
	 * This method cannot be overridden.
	 * 
	 * @param accountNumber The account number to search for.
	 * @throws AccountNotFoundException Thrown if an account with the given account number cannot be found.
 	 */
	public static void FIND_ACCOUNT(int accountNumber) throws AccountNotFoundException {
		try {
			if (!BankAccount.GET_ACCOUNT_MAP().containsKey(accountNumber)) {	// Could throw a NullPointerException.
				throw new AccountNotFoundException("The account you entered does not exist. Please create an account.") ;
			}
		} catch (NullPointerException e) {
			throw new AccountNotFoundException("The account you entered does not exist. Please create an account.", new NullPointerException()) ;
		}
	}

}