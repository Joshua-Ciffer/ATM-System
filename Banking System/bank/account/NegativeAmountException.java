// Joshua Ciffer 11/2/2017 //

package bank.account ;


public class NegativeAmountException extends Exception {

	public NegativeAmountException() {
		System.out.println("\nPlease Enter An Amount Of At Least $0.00.\n") ;
	}
	
	public static void THROW(double amount) throws NegativeAmountException {
		if (amount < 0) {
			throw new NegativeAmountException() ;
		}
	}
	
}