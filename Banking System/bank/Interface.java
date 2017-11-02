// Joshua Ciffer 10/30/2017 //
/* Bugs
 * -
 */

package bank ;
import bank.BankAccount ;
import java.util.Scanner ;
import java.util.InputMismatchException ;
import bank.Exceptions.AccountNotFoundException ;
import bank.Exceptions.IncorrectPinException ;
import bank.Exceptions.NegativeAmountException ;
import bank.Exceptions.InsufficientBalanceException ;
import bank.Exceptions.InvalidPinException ;
import bank.Exceptions.PinMismatchException ;

public class Interface {
	
	private static Scanner userInput ;
	private static String accountName, accountPin, confirmPin, newPin, closeAccountConfirm ;
	private static short menuOption ;
	private static int accountNumber, transferingAccount, receivingAccount ;
	private static double accountBalance, depositAmount, withdrawalAmount, transferAmount ;
	private static boolean deleteAccount ;
	
	public static void main(String[] args) {
		MAIN_MENU() ;
	}
	
	private static void MAIN_MENU() {
		do {	// Begin Main Menu Loop
			userInput = new Scanner(System.in) ;
			System.out.println("----Bank Main Menu-----") ;
			System.out.println("-(1) Login") ;
			System.out.println("-(2) Create Account") ;
			System.out.println("-(3) List Accounts (DEBUG USE)") ;
			System.out.println("-(4) Exit") ;
			System.out.print("\nEnter An Option: ") ;
			try {
				menuOption = userInput.nextShort() ;
			} catch (InputMismatchException e) {
				System.out.println("\nEnter One Of The Given Options.\n") ;
				userInput.next() ;
				continue ;
			}
			switch (menuOption) {	// Begin Menu Switch Statement
				case 1: {
					LOGIN() ;
					continue ;
				}
				case 2: {
					CREATE_ACCOUNT() ;
					continue ;
				}
				case 3: {
					LIST_ACCOUNTS() ;
					continue ;
				}
				case 4: {
					EXIT() ;
					break ;
				}
				default: {
					System.out.println("\nEnter One Of The Given Options.\n") ;
					continue ;
				}
			}	// End Menu Switch Statement
			break ;
		} while (true) ;	// End Main Menu Loop
	}

	private static void CREATE_ACCOUNT() {
		userInput.nextLine() ;
		do {	// Begin Account Name Loop
			System.out.print("\nEnter Your Name: ") ;
			try {
				accountName = userInput.nextLine() ;
				break ;
			} catch (InputMismatchException e) {
				System.out.println("\nPlease Enter Your Account Name.\n") ;
				userInput.next() ;
				continue ;
			}
		} while (true) ;	// End Account Name Loop
		do {	// Begin Account Pin Loop
			System.out.print("Create An Account Pin: ") ;
			try {
				accountPin = userInput.nextLine() ;
				InvalidPinException.THROW(accountPin) ;
				break ;
			} catch (InputMismatchException e) {
				System.out.println("\nPlease Create An Account Pin.\n") ;
				userInput.next() ;
				continue ;
			} catch (InvalidPinException e) {
				continue ;
			}
		} while (true) ;	// End Account Pin Loop
		do {	// Begin Confirm Pin Loop
			System.out.print("Confirm Your Account Pin: ") ;
			try {
				confirmPin = userInput.nextLine() ;
				PinMismatchException.THROW(accountPin, confirmPin) ;
				break ;
			} catch (InputMismatchException e) {
				System.out.println("\nPlease Confirm Your Account Pin.\n") ;
				userInput.next() ;
				continue ;
			} catch (InvalidPinException e) {
				continue ;
			} catch (PinMismatchException e) {
				continue ;
			}
		} while (true) ;	// End Confirm Pin Loop
		do {	// Begin Account Balance Loop
			System.out.print("Enter Your Account's Starting Balance: $") ;
			try {
				accountBalance = userInput.nextDouble() ;
				NegativeAmountException.THROW(accountBalance) ;
				break ;
			} catch (InputMismatchException e) {
				System.out.println("\nPlease Enter A Dollar Amount.\n") ;
				userInput.next() ;
				continue ;
			} catch (NegativeAmountException e) {
				continue ;
			}
		} while (true) ;	// End Account Balance Loop
		try {
			BankAccount.CREATE_ACCOUNT(accountNumber, accountName, accountPin, confirmPin, accountBalance) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}

	private static void LOGIN() {
		do {	// Begin Account Number Loop
			System.out.print("\nEnter Your Account Number: #") ;
			try {
				accountNumber = userInput.nextInt() ;
				AccountNotFoundException.THROW(accountNumber) ;
			} catch (InputMismatchException e) {
				System.out.println("\nPlease Enter Your Account Number.") ;
				userInput.next() ;
				continue ;
			} catch (AccountNotFoundException e) {
				break ;
			}
			userInput.nextLine() ;
			do {	// Begin Account Pin Loop
				System.out.print("Enter Your Account Pin: ") ;
				try {
					accountPin = userInput.nextLine() ;
					IncorrectPinException.THROW(accountNumber, accountPin) ;
				} catch (InputMismatchException e) {
					System.out.println("\nPlease Enter Your Account Pin.\n") ;
					userInput.next() ;
					continue ;
				} catch (InvalidPinException e) {
					continue ;
				} catch (IncorrectPinException e) {
					break ;
				}
				ACCOUNT_MENU() ;
				break ;
			} while (true) ;	// End Account Pin Loop
			break ;
		} while (true) ;	// End Account Number Loop
	}

	private static void LIST_ACCOUNTS() {
		System.out.println("\n---Account List---") ;
		BankAccount.LIST_ACCOUNTS() ;
		System.out.print("\n") ;
	}
	
	private static void ACCOUNT_MENU() {
		System.out.print("\n") ;
		do {	// Begin Account Menu Loop
			System.out.println("-----Account Menu-----") ;
			System.out.println("-(1) Make A Deposit") ;
			System.out.println("-(2) Make A Withdrawal") ;
			System.out.println("-(3) Make A Transfer") ;
			System.out.println("-(4) Check Balance") ;
			System.out.println("-(5) Change Pin") ;
			System.out.println("-(6) Account History") ;
			System.out.println("-(7) Close Account") ;
			System.out.println("-(8) Logout") ;
			System.out.print("Enter An Option: ") ;
			try {
				menuOption = userInput.nextShort() ;
			} catch (InputMismatchException e) {
				System.out.println("\nEnter One Of The Given Options.\n") ;
				userInput.next() ;
				continue ;
			}
			switch (menuOption) {	// Begin Account Menu Switch Statement
				case 1: {
					MAKE_A_DEPOSIT() ;
					continue ;
				}
				case 2: {
					MAKE_A_WITHDRAWAL() ;
					continue ;
				}
				case 3: {
					MAKE_A_TRANSFER() ;
					continue ;
				}
				case 4: {
					CHECK_BALANCE() ;
					continue ;
				}
				case 5: {
					CHANGE_PIN() ;
					continue ;
				}
				case 6: {
					ACCOUNT_HISTORY() ;
					continue ;
				}
				case 7: {
					CLOSE_ACCOUNT() ;
					if (deleteAccount) {
						break ;
					} else {
						continue ;
					}
				}
				case 8: {
					System.out.print("\n") ;
					break ;
				}
				default: {
					System.out.println("\nEnter One Of The Given Options.\n") ;
					continue ;
				}
			}	// End Account Menu Switch Statement
			break ;
		} while (true) ;	// End Account Menu Loop
	}
	
	private static void MAKE_A_DEPOSIT() {
		System.out.print("\n") ;
		do {	// Begin Deposit Amount Loop
			System.out.print("Enter The Amount You Want To Deposit: $") ;
			try {
				depositAmount = userInput.nextDouble() ;
				NegativeAmountException.THROW(depositAmount) ;
			} catch (InputMismatchException e) {
				System.out.println("\nPlease Enter A Dollar Amount.\n") ;
				userInput.next() ;
				continue ;
			} catch (NegativeAmountException e) {
				continue ;
			}
			try {
				BankAccount.GET_ACCOUNT(accountNumber, accountPin).deposit(depositAmount) ;
			} catch (Exception e) {
				e.printStackTrace() ;
			}
			break ;
		} while (true) ;	// End Deposit Amount Loop
	}

	private static void MAKE_A_WITHDRAWAL() {
		System.out.print("\n") ;
		do {	// Begin Withdrawal Amount Loop
			System.out.print("Enter The Amount You Want To Withdrawal: $") ;
			try {
				withdrawalAmount = userInput.nextDouble() ;
				NegativeAmountException.THROW(withdrawalAmount) ;
				InsufficientBalanceException.THROW(accountNumber, accountPin, withdrawalAmount) ;
				break ;
			} catch (InputMismatchException e) {
				System.out.println("\nPlease Enter A Dollar Amount.\n") ;
				userInput.next() ;
				continue ;
			} catch (NegativeAmountException e) {
				continue ;
			} catch (InsufficientBalanceException e) {
				break ;
			}
		} while (true) ;	// End Withdrawal Amount Loop
		try {
			BankAccount.GET_ACCOUNT(accountNumber, accountPin).withdraw(withdrawalAmount) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}

	private static void MAKE_A_TRANSFER() {
		transferingAccount = accountNumber ;
		System.out.print("\n") ;
		do {	// Begin Receiving Account Number Loop
			System.out.print("Enter The Account Number You Want To Transfer To: #") ;
			try {
				receivingAccount = userInput.nextInt() ;
				AccountNotFoundException.THROW(receivingAccount) ;
			} catch (InputMismatchException e) {
				System.out.println("\nPlease Enter The Account Number You Want To Transfer To.\n") ;
				userInput.next() ;
				continue ;
			} catch (AccountNotFoundException e) {
				break ;
			}
			do {	// Begin Transfer Amount Loop
				System.out.print("Enter The Amount You Want To Transfer: $") ;
				try {
					transferAmount = userInput.nextDouble() ;
					NegativeAmountException.THROW(transferAmount) ;
					InsufficientBalanceException.THROW(transferingAccount, accountPin, transferAmount) ;
				} catch (InputMismatchException e) {
					System.out.println("\nPlease Enter A Dollar Amount.\n") ;
					userInput.next() ;
					continue ;
				} catch (NegativeAmountException e) {
					continue ;
				} catch (InsufficientBalanceException e) {
					break ;
				}
				try {
					BankAccount.TRANSFER(transferingAccount, accountPin, receivingAccount, transferAmount) ;
				} catch (Exception e) {
					e.printStackTrace() ;
				}
				break ;
			} while (true) ;	// End Transfer Amount Loop
			break ;
		} while (true) ;	// End Receiving Account Number Loop
	}

	private static void CHECK_BALANCE() {
		try {
			System.out.println("\nYour Account Balance Is: " + BankAccount.TO_CURRENCY_FORMAT(BankAccount.GET_ACCOUNT(accountNumber, accountPin).getAccountBalance()) + ".\n") ;
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	private static void CHANGE_PIN() {
		userInput.nextLine() ;
		System.out.print("\n") ;
		do {	// Begin Account Pin Loop
			System.out.print("Enter Your Account Pin: ") ;
			try {
				accountPin = userInput.nextLine() ;
				IncorrectPinException.THROW(accountNumber, accountPin) ;
			} catch (InputMismatchException e) {
				System.out.println("\nPlease Enter Your Account Pin.\n") ;
				userInput.next() ;
				continue ;
			} catch (InvalidPinException e) {
				continue ;
			} catch (IncorrectPinException e) {
				break ;
			}
			do {	// Begin Create Pin Loop
				System.out.print("Create A New Pin: ") ;
				try {
					newPin = userInput.nextLine() ;
					InvalidPinException.THROW(newPin) ;
				} catch (InputMismatchException e) {
					System.out.println("\nPlease Create A New Pin.\n") ;
					userInput.next() ;
					continue ;
				} catch (InvalidPinException e) {
					continue ;
				}
				do {	// Begin Confirm Pin Loop
					System.out.print("Confirm Your New Pin: ") ;
					try {
						confirmPin = userInput.nextLine() ;
						PinMismatchException.THROW(newPin, confirmPin) ;
					} catch (InputMismatchException e) {
						System.out.println("\nPlease Confirm Your New Pin.\n") ;
						System.out.println("foo3");
						userInput.next() ;
						continue ;
					} catch (InvalidPinException e) {
						continue ;
					} catch (PinMismatchException e) {
						continue ;
					}
					try {
						BankAccount.GET_ACCOUNT(accountNumber, accountPin).changeAccountPin(accountPin, newPin, confirmPin) ;
						accountPin = newPin ;
					} catch (Exception e) {
						e.printStackTrace() ;
					}
					break ;
				} while (true) ;	// End Confirm Pin Loop
				break ;
			} while (true) ;	// End Create Pin Loop	
			break ;
		} while (true) ;	// End Account Pin Loop
	}
	
	private static void ACCOUNT_HISTORY() {
		try {
			System.out.println("\n" + BankAccount.GET_ACCOUNT(accountNumber, accountPin).getAccountHistory()) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	
	private static void CLOSE_ACCOUNT() {
		userInput.nextLine() ;
		System.out.print("\n") ;
		do {	// Begin Confirm Loop
			System.out.print("Type \"Yes\" To Confirm Account Deletion: ") ;
			try {
				closeAccountConfirm = userInput.nextLine() ;
			} catch (InputMismatchException e) {
				System.out.println("\nPlease Confirm Your Account Deletion.\n") ;
				userInput.next() ;
				continue ;
			}
			if (closeAccountConfirm.equalsIgnoreCase("Yes")) {
				try {
					deleteAccount = true ;
					BankAccount.CLOSE_ACCOUNT(accountNumber) ;
					break ;
				} catch (Exception e) {
					e.printStackTrace() ;
				}
			} else {
				deleteAccount = false ;
				System.out.println("\nYour Account Will Not Be Deleted.\n") ;
				break ;
			}
		} while (true) ;	// End Confirm Loop
	}
	
	private static void EXIT() {
		userInput.close() ;
		userInput = null ;
		System.exit(0) ;
	}

}	// End Interface Class