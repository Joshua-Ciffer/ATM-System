//============================================================================//
// Name        : InvalidPinException.java                                     //
// Author      : Joshua Ciffer                                                //
// Date        : 12/02/2017                                                   //
//============================================================================//

package src.atm.pin ;

public final class InvalidPinException extends Exception {

	public InvalidPinException() {
		super() ;
	}
	
	public InvalidPinException(String message) {
		super(message) ;
	}
	
	public InvalidPinException(Throwable cause) {
		super(cause) ;
	}
	
	public InvalidPinException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	public InvalidPinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace) ;
	}
	
	public static final void THROW(String pin) throws InvalidPinException {
		try {
			Short.parseShort(pin) ; 	// Throws NumberFormatException
			if ((pin.length() != 4) || (Short.parseShort(pin) < 0)) {
				throw new InvalidPinException() ;
			}
		} catch (NumberFormatException e) {
			throw new InvalidPinException() ;
		}
	}
	
}