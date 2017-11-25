//============================================================================//
// Name        : IncorrectPinException.java                                   //
// Author      : Joshua Ciffer                                                //
// Date        : 11/23/2017                                                   //
//============================================================================//

package src.bank.pin ;
import src.bank.account.BankAccount ;
import src.bank.account.AccountNotFoundException ;

public final class IncorrectPinException extends Exception {

	public IncorrectPinException() {
		System.out.println("\nThe Pin You Entered Is Incorrect.\n") ;
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
