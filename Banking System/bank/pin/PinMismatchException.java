// Joshua Ciffer 11/2/2017 //

package bank.pin;


public class PinMismatchException extends Exception {

	public PinMismatchException() {
		System.out.println("\nThe Pins You Entered Do Not Match.\n") ;
	}
	
	public static void THROW(String pin, String confirmPin) throws InvalidPinException, PinMismatchException {
		InvalidPinException.THROW(pin) ;
		InvalidPinException.THROW(confirmPin) ;
		if (!pin.equalsIgnoreCase(confirmPin)) {
			throw new PinMismatchException() ;
		}
	}
	
}