//============================================================================//
// Name        : NegativeAmountException.java								  //
// Author      : Joshua Ciffer												  //
// Date        : 11/15/2017													  //
//============================================================================//

package bank.account ;
import java.lang.Exception ;

final public class NegativeAmountException extends Exception {

	public NegativeAmountException() {
		System.out.println("\nPlease Enter An Amount Of At Least $0.00.\n") ;
	}
	
	public static void THROW(double amount) throws NegativeAmountException {
		if (amount < 0) {
			throw new NegativeAmountException() ;
		}
	}
	
}