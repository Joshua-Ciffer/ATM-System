// Joshua Ciffer 10/30/2017 //

package bank ;
import bank.BankAccount ;
import java.lang.Exception ;
import java.math.BigDecimal ;
import java.lang.NullPointerException ;
import java.lang.NumberFormatException ; 

public class Exceptions extends Exception {

	public static class AccountNotFoundException extends Exception {
		
		public AccountNotFoundException() {
			System.out.println("\nThe Account You Entered Does Not Exist. Please Create An Account.\n") ;
		}
		
		public static void THROW(int accountNumber) throws AccountNotFoundException {
			try {
				if (!BankAccount.ACCOUNT_EXISTS(accountNumber)) {
					throw new AccountNotFoundException() ;
				}
			} catch (NullPointerException e) {
				throw new AccountNotFoundException() ;
			}
		}
		
	}
	
	public static class IncorrectPinException extends Exception {
		
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
	
	public static class NegativeAmountException extends Exception {
		
		public NegativeAmountException() {
			System.out.println("\nPlease Enter An Amount Of At Least $0.00.\n") ;
		}
		
		public static void THROW(double amount) throws NegativeAmountException {
			if (amount < 0) {
				throw new NegativeAmountException() ;
			}
		}
		
	}
	
	public static class InsufficientBalanceException extends Exception {
		
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

	public static class InvalidPinException extends Exception {
		
		public InvalidPinException() {
			System.out.println("\nPlease Enter A Valid 4 Digit Pin.\n") ;
		}
		
		public static void THROW(String pin) throws InvalidPinException {
			try {
				Short.parseShort(pin) ; 	// Throws NumberFormatException
				if ((pin.length() != 4) || (Short.parseShort(pin) < 0)) {
					throw new InvalidPinException() ;
				}
			} catch (NumberFormatException e) {
				throw new InvalidPinException() ;
			}
		}
		
	}
	
	public static class PinMismatchException extends Exception {
		
		public PinMismatchException() {
			System.out.println("\nThe Pins You Entered Do Not Match.\n") ;
		}
		
		public static void THROW(String pin, String confirmPin) throws InvalidPinException, PinMismatchException {
			InvalidPinException.THROW(pin) ;
			InvalidPinException.THROW(confirmPin) ;
			if (!pin.equalsIgnoreCase(confirmPin)) {
				throw new PinMismatchException() ;
			}
		}
		
	}
	
} 	// End Exceptions Class