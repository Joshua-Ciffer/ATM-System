
package src.atm.pin ;

/**
 * PinMismatchException is thrown when two PINs that need to be 
 * confirmed are not equal.
 * 
 * This class cannot be extended.
 * This class extends Exception.
 * 
 * @author Joshua Ciffer
 * @version 12/03/2017
 */
public final class PinMismatchException extends Exception {

	/**
	 * Constructs a PinMismatchException with no details.
	 */
	public PinMismatchException() {
		super() ;
	}
	
	/**
	 * Constructs a PinMismatchException with a given message.
	 * 
	 * @param message The message to be displayed with the stack trace.
	 */
	public PinMismatchException(String message) {
		super(message) ;
	}
	
	/**
	 * Constructs a PinMismatchException with a given cause.
	 * 
	 * @param cause The cause of this exception.
	 */
	public PinMismatchException(Throwable cause) {
		super(cause) ;
	}
	
	/**
	 * Constructs a PinMismatchException with a given message and cause.
	 * 
	 * @param message The message to displayed with the stack trace.
	 * @param cause The cause of this exception.
	 */
	public PinMismatchException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	/**
	 * Constructs a PinMismatchException with a given message, cause, and
	 * specifies whether suppression is enabled or disabled or if the stack
	 * trace is writable or not. 
	 * 
	 * @param message The message to displayed with the stack trace.
	 * @param cause The cause of this exception.
	 * @param enableSuppression Specifies whether suppression is enabled or disabled.
	 * @param writableStackTrace Specifies whether the stack trace is writable or not.
	 */
	public PinMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace) ;
	}
	
	/**
	 * Checks to see if the two entered PINs are equal.
	 * 
	 * This method cannot be overridden.
	 * 
	 * @param pin The first PIN entered.
	 * @param confirmPin The second PIN entered.
	 * @throws InvalidPinException Thrown if either of the PINs entered are not 4 digit numbers.
	 * @throws PinMismatchException Thrown if the two PINs entered are not equal.
	 */
	public static final void COMPARE_PINS(String pin, String confirmPin) throws InvalidPinException, PinMismatchException {
		InvalidPinException.VALIDATE_PIN(pin) ; 	// Checks to see if pin or confirm pin are
		InvalidPinException.VALIDATE_PIN(confirmPin) ; 	// valid PINs.  Throws InvalidPinException
		if (pin.equalsIgnoreCase(confirmPin) == false) {
			throw new PinMismatchException("The PINs you entered do not match.") ;
		}
	}
	
}