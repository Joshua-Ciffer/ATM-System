//============================================================================//
// Name        : InsufficientBalanceException.java                            //
// Author      : Joshua Ciffer                                                //
// Date        : 11/15/2017                                                   //
//============================================================================//

package bank.account ;
import java.math.BigDecimal ;
import bank.account.BankAccount ;

final public class InsufficientBalanceException extends Exception {

	public InsufficientBalanceException() {
		System.out.println("\nYou Have An Insufficient Balance To Complete This Transaction.\n") ;
	}
	
	public static void THROW(int accountNumber, String accountPin, double amount) throws InsufficientBalanceException {
		try {
			if (BankAccount.GET_ACCOUNT(accountNumber, accountPin).getAccountBalance().compareTo(new BigDecimal(amount)) < 0) {
				throw new InsufficientBalanceException() ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	
}