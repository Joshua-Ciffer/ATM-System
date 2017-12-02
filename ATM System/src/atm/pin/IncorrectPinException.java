//============================================================================//
// Name        : IncorrectPinException.java                                   //
// Author      : Joshua Ciffer                                                //
// Date        : 12/02/2017                                                   //
//============================================================================//

package src.atm.pin ;
import src.atm.account.BankAccount ;
import src.atm.account.AccountNotFoundException ;

public final class IncorrectPinException extends Exception {

	public IncorrectPinException() {
		super() ;
	}
	
	public IncorrectPinException(String message) {
		super(message) ;
	}
	
	public IncorrectPinException(Throwable cause) {
		super(cause) ;
	}
	
	public IncorrectPinException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	public IncorrectPinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace) ;
	}
	 
	public static void THROW(int accountNumber, String accountPin) throws InvalidPinException, IncorrectPinException {
		InvalidPinException.THROW(accountPin) ;
		try {
			BankAccount.GET_ACCOUNT(accountNumber, accountPin) ;
		} catch (AccountNotFoundException e) {
			e.printStackTrace() ;
		}
	}
	
}