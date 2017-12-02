//============================================================================//
// Name        : AccountNotFoundException.java                                //
// Author      : Joshua Ciffer                                                //
// Date        : 12/02/2017                                                   //
//============================================================================//

package src.atm.account ;
import src.atm.account.BankAccount ;

final public class AccountNotFoundException extends Exception {
	
	public AccountNotFoundException() {
		super() ;
	}
	
	public AccountNotFoundException(String message) {
		super(message) ;
	}
	
	public AccountNotFoundException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	public AccountNotFoundException(Throwable cause) {
		super(cause) ;
	}
	
	public AccountNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace) ;
	}
	
	public static void THROW(int accountNumber) throws AccountNotFoundException {
		try {
			if (BankAccount.ACCOUNT_EXISTS(accountNumber) == false) {
				throw new AccountNotFoundException() ;
			}
		} catch (NullPointerException e) {
			throw new AccountNotFoundException() ;
		}
	}

}