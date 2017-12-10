
package src.atm.pin ;
import src.atm.account.Account ;

public final class Pin {
	
	private static String formattedPin ;
	private static short unformattedPin ;
	private String pin ;
	
	public Pin() {
		pin = "0000" ;
	}
	
	public Pin(String pin, String confirmPin) throws IllegalArgumentException {
		if (PINs_MATCH(pin, confirmPin)) {
			unformattedPin = Short.parseShort(pin) ;
			formattedPin = String.format("%04d", unformattedPin) ;
			this.pin = formattedPin ; 
		} else {
			throw new IllegalArgumentException("The PINs you entered do not match.") ;
		}
	}
	
	public void changePin(int accountNumber, String oldPin, String newPin, String confirmPin) throws IllegalArgumentException {
		if (IS_CORRECT_PIN(accountNumber, oldPin) && PINs_MATCH(newPin, confirmPin)) {
			pin = newPin ;
		}
	}
	
	public static boolean IS_VALID_PIN(String pin) throws IllegalArgumentException {
		try {
			if ((Short.parseShort(pin) >= 0) && (pin.length() == 4)) {
				return true ;
			} else {
				throw new IllegalArgumentException("Please enter a 4 digit PIN.") ;
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Please enter a 4 digit numerical PIN.", new NumberFormatException()) ;
		}
	}
	
	public static boolean PINs_MATCH(String pin, String confirmPin) throws IllegalArgumentException {
		if ((IS_VALID_PIN(pin) && IS_VALID_PIN(confirmPin)) && pin.equals(confirmPin)) {
			return true ;
		} else {
			throw new IllegalArgumentException("The PINs you entered do not match.") ;
		}
	}
	
	public static boolean IS_CORRECT_PIN(int accountNumber, String accountPin) throws IllegalArgumentException {
		if (Account.GET_ACCOUNT(accountNumber, accountPin).getAccountPin().getPin().equals(accountPin)) {
			return true ;
		} else {
			throw new IllegalArgumentException("The PIN you entered is incorrect.") ;
		}
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
		return "PIN: " + pin ;
	}
	
	public String getPin() {
		return pin ;
	}
	
}