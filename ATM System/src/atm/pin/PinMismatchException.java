//============================================================================//
// Name        : PinMismatchException.java                                    //
// Author      : Joshua Ciffer                                                //
// Date        : 11/15/2017                                                   //
//============================================================================//

package src.atm.pin ;

public final class PinMismatchException extends Exception {

	public PinMismatchException() {
		System.out.println("\nThe Pins You Entered Do Not Match.\n") ;
	}
	
	public static final void THROW(String pin, String confirmPin) throws InvalidPinException, PinMismatchException {
		InvalidPinException.THROW(pin) ;
		InvalidPinException.THROW(confirmPin) ;
		if (pin.equalsIgnoreCase(confirmPin) == false) {
			throw new PinMismatchException() ;
		}
	}
	
}