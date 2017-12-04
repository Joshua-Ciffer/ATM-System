
package src.atm.pin ;

/**
 * InvalidPinException is thrown when a given PIN is not a 4 digit number.
 * 
 * This class cannot be extended.
 * This class extends Exception.
 * 
 * @author Joshua Ciffer
 * @version 12/03/2017
 */
public final class InvalidPinException extends Exception {

	/**
	 * Constructs an InvalidPinException with no details.
	 */
	public InvalidPinException() {
		super() ;
	}
	
	/**
	 * Constructs an InvalidPinException with a given message.
	 * 
	 * @param message The message to displayed with the stack trace.
	 */
	public InvalidPinException(String message) {
		super(message) ;
	}
	
	/**
	 * Constructs an InvalidPinException with a given cause.
	 * 
	 * @param cause The cause of this exception.
	 */
	public InvalidPinException(Throwable cause) {
		super(cause) ;
	}
	
	/**
	 * Constructs an InvalidPinException with a given message and cause.
	 * 
	 * @param message The message to displayed with the stack trace.
	 * @param cause The cause of this exception.
	 */
	public InvalidPinException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	/**
	 * Constructs an InvalidPinException with a given message, cause, and specifies
	 * whether suppression is enabled or disabled or if the stack trace is writable
	 * or not.
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidPinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace) ;
	}
	
	/**
	 * Checks to see if the given PIN is 4 digits long, contains no non-numerical
	 * characters, and is not a negative number.
	 * 
	 * This method cannot be overridden.
	 * 
	 * @param pin The PIN the user entered.
	 * @throws InvalidPinException Thrown if the PIN the user entered contains
	 * non-numerical characters, or is not 4 digits long or positive.
	 */
	public static final void VALIDATE_PIN(String pin) throws InvalidPinException {
		try {
			Short.parseShort(pin) ; 	// Throws NumberFormatException
			if (pin.length() != 4) {
				throw new InvalidPinException("Please enter a 4 digit PIN.") ;
			}
			if (Short.parseShort(pin) < 0) {
				throw new InvalidPinException("Please enter a positive number for a PIN.") ;
			}
		} catch (NumberFormatException e) {
			throw new InvalidPinException("Please enter a 4 digit numerical PIN.", new NumberFormatException()) ;
		}
	}
	
}