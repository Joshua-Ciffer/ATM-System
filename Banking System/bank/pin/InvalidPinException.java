// Joshua Ciffer 11/2/2017 //

package bank.pin;
import java.lang.Short ;
import java.lang.NumberFormatException ;

public class InvalidPinException extends Exception {

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