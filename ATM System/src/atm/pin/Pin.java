package src.atm.pin ;
import src.atm.account.Account ;

/**
 * This class acts as a wrapper object for a 4 digit numerical PIN.
 * It also provides methods for validating Pin parameters and throwing appropriate exceptions.
 * <br><br>
 * This class cannot be extended.
 * 
 * @author Joshua Ciffer
 * @version 12/13/2017
 */
public final class Pin {
	
	/**
	 * The 4 digit numerical PIN stored in a wrapper Pin object.
	 */
	private String pin ;
	
	/**
	 * Constructs a Pin object with a given String pin.
	 * 
	 * @param pin - A 4 digit numerical PIN.
	 * @param confirmPin - The same 4 digit numerical PIN entered again for confirmation.
	 * @throws IllegalArgumentException Thrown if the PIN entered is not valid, or if the PINs do not match.
	 */
	public Pin(String pin, String confirmPin) throws IllegalArgumentException {
		if (PINs_MATCH(pin, confirmPin)) {
			this.pin = String.format("%04d", Short.parseShort(pin)) ;	// "%04d" is regex that adds leading zeros to the String
		}                                                              // if the length is less than 4.
	}
	
	/**
	 * Changes the PIN contained in the Pin wrapper object.
	 * 
	 * @param accountNumber - The account that contains this Pin object.
	 * @param oldPin - The current PIN.
	 * @param newPin - The new PIN that will be saved.
	 * @param confirmPin - The new PIN entered again for confirmation.
	 * @throws IllegalArgumentException Thrown If any of the PINs are invalid, or the newPin and confirmPin do not match.
	 */
	public void changePin(int accountNumber, String oldPin, String newPin, String confirmPin) throws IllegalArgumentException {
		if (IS_CORRECT_PIN(accountNumber, oldPin) && PINs_MATCH(newPin, confirmPin)) {
			pin = newPin ;
		}
	}
	
	/**
	 * Checks to see if the given PIN is 4 numerical digits.
	 * 
	 * @param pin - The PIN to be validated.
	 * @return true - If the PIN is valid.
	 * @throws IllegalArgumentException Thrown if the PIN is invalid.
	 */
	public static boolean IS_VALID_PIN(String pin) throws IllegalArgumentException {
		try {
			if ((Short.parseShort(pin) >= 0) && (pin.length() == 4)) {
				return true ;
			} else {
				throw new IllegalArgumentException("Please enter a 4 digit PIN.") ;
			}
		} catch (NumberFormatException e) {                                                     // Sets NumberFormatException as cause.
			throw new IllegalArgumentException("Please enter a 4 digit numerical PIN.", new NumberFormatException()) ;
		}
	}
	
	/**
	 * Determines whether or not the user has entered the correct PIN by asking for the PIN twice to confirm.
	 * 
	 * @param pin - The PIN to be confirmed.
	 * @param confirmPin - The same PIN entered again.
	 * @return true - If both PINs are the same.
	 * @throws IllegalArgumentException Thrown if the PINs are not the same.
	 */
	public static boolean PINs_MATCH(String pin, String confirmPin) throws IllegalArgumentException {
		if ((IS_VALID_PIN(pin) && IS_VALID_PIN(confirmPin)) && pin.equals(confirmPin)) {
			return true ;
		} else {
			throw new IllegalArgumentException("The PINs you entered do not match.") ;
		}
	}
	
	/**
	 * Performs a check to see if the user has entered the correct PIN to login to their account.
	 * 
	 * @param accountNumber - The account trying to be accessed.
	 * @param accountPin - The PIN the user enters.
	 * @return true - If the user enters the correct PIN for their account.
	 * @throws IllegalArgumentException Thrown if the incorrect PIN is entered.
	 * @throws NullPointerException Thrown if the account could not be found.
	 */
	public static boolean IS_CORRECT_PIN(int accountNumber, String accountPin) throws IllegalArgumentException, NullPointerException {
		if (Account.GET_ACCOUNT(accountNumber, accountPin).getAccountPin().getPin().equals(accountPin)) {
			return true ;
		} else {
			throw new IllegalArgumentException("The PIN you entered is incorrect.") ;
		}
	}
	
	/**
	 * Checks to see if the content of two Pin objects are equal.
	 * <br><br>
	 * This method overrides Object.equals()
	 * 
	 * @param confirmPin - The Pin object to test equality with.
	 * @return true - If both objects' content are equal. </br> 
	 *         false - If both objects' content are not equal.
	 */
	@Override
	public boolean equals(Object confirmPin) {
		if (this.toString().equalsIgnoreCase(((Pin)confirmPin).toString())) {	// Casts confirmPin to type Pin.
			return true ;                                                   // This forces it to use Pin.toString()
		} else {
			return false ;
		}
	}
	
	/**
	 * Returns a String representation of the Pin object.
	 * <br><br>
	 * This method overrides Object.toString()
	 * 
	 * @return String representation of the content of this object.
	 */
	@Override
	public String toString() {
		return "PIN: " + pin ;
	}
	
	/**
	 * Returns the PIN stored in this object.
	 *  
	 * @return 4 digit numerical PIN.
	 */
	public String getPin() {
		return pin ;
	}

}