
package src.atm.account ;
import java.math.BigDecimal ;
import src.atm.pin.IncorrectPinException ;
import src.atm.pin.InvalidPinException ;

/**
 * InsufficientBalanceException is thrown when a user attempts to withdraw or
 * transfer an amount greater than their account balance.
 * 
 * This class cannot be extended.
 * This class extends Exception.
 * 
 * @author Joshua Ciffer
 * @version 12/03/2017
 *
 */
public final class InsufficientBalanceException extends Exception {

	/**
	 * Constructs an InsufficientBalanceException with no details.
	 */
	public InsufficientBalanceException() {
		super() ;
	}
	
	/**
	 * Constructs an InsufficientBalanceException with a given message.
	 * 
	 * @param message The message to displayed with the stack trace.
	 */
	public InsufficientBalanceException(String message) {
		super(message) ;
	}
	
	/**
	 * Constructs an InsufficientBalanceException with a given cause.
	 * 
	 * @param cause The cause of this exception.
	 */
	public InsufficientBalanceException(Throwable cause) {
		super(cause) ;
	}
	
	/**
	 * Constructs an InsufficientBalanceException with a given message and cause.
	 * 
	 * @param message The message to be displayed with the stack trace.
	 * @param cause The cause of this exception.
	 */
	public InsufficientBalanceException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	/**
	 * Constructs an InsufficientBalanceException with a given message, cause, and
	 * specifies whether suppression is enabled or disabled or if the stack trace
	 * is writable or not.
	 * 
	 * @param message The message to displayed with the stack trace.
	 * @param cause The cause of this exception.
	 * @param enableSuppression Specifies whether suppression is enabled or disabled.
	 * @param writableStackTrace Specifies whether the stack trace is writable or not.
	 */
	public InsufficientBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace) ;
	}
	
	/**
	 * Checks to see if the specified amount is greater than the account's balance.
	 * 
	 * This method cannot be overridden.
	 * 
	 * @param accountNumber The user's account number.
	 * @param accountPin The user's account PIN.
	 * @param amount The amount the user wishes to withdraw or transfer.
	 * @throws InsufficientBalanceException Thrown if the amount is greater than the user's account balance.
	 */
	public static final void CHECK_BALANCE(int accountNumber, String accountPin, double amount) throws InsufficientBalanceException {
		try {
			if (BankAccount.GET_ACCOUNT(accountNumber, accountPin).getAccountBalance().compareTo(new BigDecimal(amount)) < 0) {		// BankAccount.GET_ACCOUNT() Could throw an AccountNotFound or PinException.
				throw new InsufficientBalanceException("The amount you entered is greater than your account balance.") ;
			}
		} catch (AccountNotFoundException | InvalidPinException | IncorrectPinException e) {
			e.printStackTrace() ;
		}
	}
	
}