//============================================================================//
// Name        : PinMismatchException.java                                    //
// Author      : Joshua Ciffer                                                //
// Date        : 12/02/2017                                                   //
//============================================================================//

package src.atm.pin ;

public final class PinMismatchException extends Exception {

	public PinMismatchException() {
		super() ;
	}
	
	public PinMismatchException(String message) {
		super(message) ;
	}
	
	public PinMismatchException(Throwable cause) {
		super(cause) ;
	}
	
	public PinMismatchException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	public PinMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace) ;
	}
	
	public static final void THROW(String pin, String confirmPin) throws InvalidPinException, PinMismatchException {
		InvalidPinException.THROW(pin) ;
		InvalidPinException.THROW(confirmPin) ;
		if (pin.equalsIgnoreCase(confirmPin) == false) {
			throw new PinMismatchException() ;
		}
	}
	
}