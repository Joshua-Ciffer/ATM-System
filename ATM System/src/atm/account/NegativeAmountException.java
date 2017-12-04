
package src.atm.account ;

/**
 * NegativeAmountException is thrown when a user enters a negative number in
 * a situation where negative input would not make sense.
 * 
 * This class cannot be extended.
 * This class extends Exception.
 * 
 * @author Joshua Ciffer
 * @version 12/03/2017
 */
public final class NegativeAmountException extends Exception {

	/**
	 * Constructs a NegativeAmountException with no details.
	 */
	public NegativeAmountException() {
		super() ;
	}
	
	/**
	 * Constructs a NegativeAmountException with a given message.
	 * 
	 * @param message The message to be displayed with the stack trace.
	 */
	public NegativeAmountException(String message) {
		super(message) ;
	}
	
	/**
	 * Constructs a NegativeAmountException with a given cause.
	 * 
	 * @param cause The cause of this exception.
	 */
	public NegativeAmountException(Throwable cause) {
		super(cause) ;
	}
	
	/**
	 * Constructs a NegativeAmountException with a given message or cause.
	 * 
	 * @param message The message to be displayed with the stack trace.
	 * @param cause The cause of this exception.
	 */
	public NegativeAmountException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	/**
	 * Constructs a NegativeAmountException with a given message, cause, and specifies
	 * whether suppression is enabled or if the stack trace is writable.
	 * 
	 * @param message The message to be displayed with the stack trace.
	 * @param cause The cause of this exception.
	 * @param enableSuppression Specifies whether suppression is enabled or disabled.
	 * @param writableStackTrace Specifies whether the stack trace is writable or not.
	 */
	public NegativeAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace) ;
	}
	
	/**
	 * Checks to see if the amount entered is negative.
	 * 
	 * This method cannot be overridden.
	 * 
	 * @param amount The amount given.
	 * @throws NegativeAmountException Thrown if the amount is negative.
	 */
	public static final void CHECK_AMOUNT(double amount) throws NegativeAmountException {
		if (amount < 0) {
			throw new NegativeAmountException("Please enter a positive number.") ;
		}
	}
	
}