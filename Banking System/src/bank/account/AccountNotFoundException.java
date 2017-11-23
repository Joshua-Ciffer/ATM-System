//============================================================================//
// Name        : AccountNotFoundException.java                                //
// Author      : Joshua Ciffer                                                //
// Date        : 11/15/2017                                                   //
//============================================================================//

package src.bank.account ;
import src.bank.account.BankAccount ;

final public class AccountNotFoundException extends Exception {
	
	public AccountNotFoundException() {
		System.out.println("\nThe Account You Entered Does Not Exist. Please Create An Account.\n") ;
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