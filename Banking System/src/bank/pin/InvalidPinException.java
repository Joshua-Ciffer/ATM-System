//============================================================================//
// Name        : InvalidPinException.java                                     //
// Author      : Joshua Ciffer                                                //
// Date        : 11/15/2017                                                   //
//============================================================================//

package src.bank.pin ;

final public class InvalidPinException extends Exception {

	public InvalidPinException() {
		System.out.println("\nPlease Enter A Valid 4 Digit Pin.\n") ;
	}
	
	public static void THROW(String pin) throws InvalidPinException {
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