//============================================================================//
// Name        : InsufficientBalanceException.java                            //
// Author      : Joshua Ciffer                                                //
// Date        : 12/02/2017                                                   //
//============================================================================//

package src.atm.account ;
import java.math.BigDecimal ;
import src.atm.account.BankAccount ;

public final class InsufficientBalanceException extends Exception {

	public InsufficientBalanceException() {
		super() ;
	}
	
	public InsufficientBalanceException(String message) {
		super(message) ;
	}
	
	public InsufficientBalanceException(Throwable cause) {
		super(cause) ;
	}
	
	public InsufficientBalanceException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	public InsufficientBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace) ;
	}
	
	public static final void THROW(int accountNumber, String accountPin, double amount) throws InsufficientBalanceException {
		try {
			if (BankAccount.GET_ACCOUNT(accountNumber, accountPin).getAccountBalance().compareTo(new BigDecimal(amount)) < 0) {
				throw new InsufficientBalanceException() ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	
}