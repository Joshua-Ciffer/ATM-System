
package src.atm.pin ;

/**
 * This class acts as a wrapper class for a 4 digit numerical PIN.
 * 
 * This class cannot be extended.
 * 
 * @author Joshua Ciffer
 * @version 12/03/2017
 */
public final class Pin {
	
	/**
	 * Stores the 4 digit PIN in a String once it has been validated and has had any leading zeros added.
	 */
	private static String formattedPin ;
	
	/**
	 * Stores a PIN in a short to check if the number is a positive integer.
	 */
	private static short unformattedPin ;
	
	/**
	 * Stores the user's 4 digit PIN in a String.
	 */
	private String pin ;
	
	/**
	 * Constructs the default PIN, which is 0000.
	 */
	public Pin() {
		this.pin = "0000" ;
	}
	
	/**
	 * Constructs a PIN with the given String.
	 * 
	 * @param pin The PIN to be boxed in the wrapper class.
	 */
	private Pin(String pin) {
		this.pin = pin ; 
	}
	
	/**
	 * Creates a PIN object with the given String pin.
	 * 
	 * This method cannot be overridden.
	 * 
	 * @param pin The PIN to be wrapped in a PIN object.
	 * @param confirmPin Used to compare to the first PIN to make sure the user did not enter the wrong PIN.
	 * @return A PIN object with the String pin stored inside.
	 * @throws InvalidPinException Thrown if either pin or confirmPin are not valid PINs.
	 * @throws PinMismatchException Thrown if pin or confirmPin do not match.
	 */
	public static final Pin CREATE_PIN(String pin, String confirmPin) throws InvalidPinException, PinMismatchException {
		PinMismatchException.COMPARE_PINS(pin, confirmPin) ;	// Throws exception if either of the pins are not valid or they do not match.
		unformattedPin = Short.parseShort(pin) ;	// Converts String pin to short to make sure PIN is a number.
		formattedPin = String.format("%04d", unformattedPin) ; 	  // If the length of the short is less than 4, it adds the leading zeros that would
		return new Pin(formattedPin) ;								// be left off when converting to a short. Returns the PIN wrapped in an Pin object.
	}
	
	/**
	 * Changes the PIN stored in the object the method is invoked on.
	 * 
	 * This method cannot be overridden.
	 * 
	 * @param accountNumber
	 * @param oldPin
	 * @param newPin
	 * @param confirmPin
	 * @throws IncorrectPinException
	 * @throws InvalidPinException
	 * @throws PinMismatchException
	 */
	public final void changePin(int accountNumber, String oldPin, String newPin, String confirmPin) throws IncorrectPinException, InvalidPinException, PinMismatchException {
		IncorrectPinException.CHECK_PIN(accountNumber, oldPin) ;
		PinMismatchException.COMPARE_PINS(newPin, confirmPin) ;
		this.pin = newPin ;
	}
	
	/**
	 * Compares the content of the given Pin object with that of the object
	 * the method was invoked on.
	 * 
	 * This method overrides java.lang.Object.equals().
	 * This method cannot be overridden.
	 * 
	 * @param confirmPin Pin object to compare to.
	 * @return True if the objects are equal, false if they are not.
	 */
	public final boolean equals(Object confirmPin) {	  // Takes an Object as a parameter to properly override the super class equals method
		Pin comparingPin = (Pin)confirmPin ;	// Casts Object to type Pin
		if (this.toString().equalsIgnoreCase(comparingPin.toString())) {
			return true ;
		} else {
			return false ;
		}
	}
	
	/**
	 * Returns a String representation of this Pin object.
	 * 
	 * This method overrides java.lang.Object.toString().
	 * This method cannot be overridden.
	 * 
	 * @return String representation of this object.
	 */
	public final String toString() {
		return this.pin ;
	}
	
	/**
	 * Returns the PIN in an unboxed String form.
	 * 
	 * This method cannot be overridden.
	 * 
	 * @return Object variable pin.
	 */
	public final String getPin() {
		return this.pin ;
	}
	
}