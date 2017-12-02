//============================================================================//
// Name        : NegativeAmountException.java                                 //
// Author      : Joshua Ciffer                                                //
// Date        : 12/02/2017                                                   //
//============================================================================//

package src.atm.account ;

public final class NegativeAmountException extends Exception {

	public NegativeAmountException() {
		super() ;
	}
	
	public NegativeAmountException(String message) {
		super(message) ;
	}
	
	public NegativeAmountException(Throwable cause) {
		super(cause) ;
	}
	
	public NegativeAmountException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	public NegativeAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace) ;
	}
	 
	public static final void THROW(double amount) throws NegativeAmountException {
		if (amount < 0) {
			throw new NegativeAmountException() ;
		}
	}
	
}