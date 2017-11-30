//============================================================================//
// Name        : Pin.java                                                     //
// Author      : Joshua Ciffer                                                //
// Date        : 11/15/2017                                                   //
//============================================================================//

package src.atm.pin ;
import src.atm.pin.InvalidPinException ;
import src.atm.pin.PinMismatchException ;
import src.atm.pin.IncorrectPinException ;

final public class Pin {
	
	private static String formattedPin ;
	private static short unformattedPin ;
	private String pin ;
	
	public Pin() {
		this.pin = "0000" ;
	}
	
	private Pin(String pin) {
		this.pin = pin ; 
	}
	
	public static Pin CREATE_PIN(String pin, String confirmPin) throws InvalidPinException, PinMismatchException {
		PinMismatchException.THROW(pin, confirmPin) ;
		unformattedPin = Short.parseShort(pin) ;	// Converts String pin to short
		formattedPin = String.format("%04d", unformattedPin) ; 	  // If the length of the short is less than 4, it adds the leading zeros that would
		return new Pin(formattedPin) ;								// be left off when converting to a short. Returns the pin in String form
	}
	
	public void changePin(int accountNumber, String oldPin, String newPin, String confirmPin) throws IncorrectPinException, InvalidPinException, PinMismatchException {
		IncorrectPinException.THROW(accountNumber, oldPin) ;
		PinMismatchException.THROW(newPin, confirmPin) ;
		this.pin = newPin ;
	}
	
	public boolean equals(Object confirmPin) {	  // Takes an Object as a parameter to properly override the super class equals method
		Pin comparingPin = (Pin)confirmPin ;	// Casts Object to type Pin
		if (this.toString().equalsIgnoreCase(comparingPin.toString())) {
			return true ;
		} else {
			return false ;
		}
	}
	
	public String toString() {
		return this.pin ;
	}
	
	public String getPin() {
		return this.pin ;
	}
	
}	// End Pin Class