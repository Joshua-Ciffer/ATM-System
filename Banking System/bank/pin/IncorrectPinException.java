//============================================================================//
// Name        : IncorrectPinException.java									  //
// Author      : Joshua Ciffer												  //
// Date        : 11/15/2017													  //
//============================================================================//

package bank.pin ;
import bank.account.BankAccount ;
import bank.account.AccountNotFoundException ;

final public class IncorrectPinException extends Exception {

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
