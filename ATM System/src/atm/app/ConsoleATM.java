package src.atm.app;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.math.BigDecimal;
import src.atm.account.Account;
import src.atm.account.BankAccount;
import src.atm.account.AdminAccount;
import src.atm.pin.Pin;

public abstract class ConsoleATM {

	private static final Scanner userInput = new Scanner(System.in);

	private static short userResponse;

	private static Account currentAccount;

	public static void main(String[] args) {
		MAIN_MENU();
	}

	private static void MAIN_MENU() {
		do {	// Begin main menu loop.
			System.out.print("ATM Main Menu\n (1) Login\n (2) Create Account\n (3) Exit\nEnter an optioin: ");
			try {
				userResponse = userInput.nextShort();
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter one of the given options.\n");
				userInput.next();
				continue;
			}
			switch (userResponse) {		// Begin userResponse switch.
				case 1: {	// Login.
					LOGIN();
					break;
				}
				case 2: {	// Create account.
					CREATE_ACCOUNT();
					break;
				}
				case 3: {	// Exit.
					EXIT();
					break;
				}
				default: {	 // Error.
					System.out.println("\nPlease enter one of the given options.\n");
					continue;
				}
			}	// End userResponse switch.
		} while (true);		// End main menu loop.
	}

	private static void LOGIN() {
		int accountNumber;
		String accountPin;
		System.out.print("\n");
		do {	// Begin account number loop.
			System.out.print("Enter your account number: #");
			try {
				accountNumber = userInput.nextInt();
				Account.ACCOUNT_EXISTS(accountNumber);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter your account number.\n");
				userInput.next();
				continue;
			} catch (NullPointerException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				break;
			}
			do {	// Begin account PIN loop.
				System.out.print("Enter your account PIN: ");
				try {
					accountPin = userInput.next();
					Pin.IS_CORRECT_PIN(accountNumber, accountPin);
				} catch (InputMismatchException e) {
					System.out.println("\nPlease enter your account number.\n");
					userInput.next();
					continue;
				} catch (IllegalArgumentException e) {
					System.out.println("\n" + e.getMessage() + "\n");
					break;
				}
				currentAccount = Account.GET_ACCOUNT(accountNumber, accountPin);
				if (currentAccount instanceof BankAccount) {
					System.out.println(((BankAccount)currentAccount).toString());
					BANK_ACCOUNT_MENU();
				} else if (currentAccount instanceof AdminAccount) {
					ADMIN_ACCOUNT_MENU();
				}
				break;
			} while (true);		// End account PIN loop.
			break;
		} while (true);	    // End account number loop.
	}

	private static void CREATE_ACCOUNT() {
		String accountName, accountPin, confirmPin;
		double accountBalance;
		userInput.nextLine();
		System.out.print("\n");
		do {	// Begin account name loop.
			System.out.print("Enter your name: ");
			try {
				accountName = userInput.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter your name.\n");
				userInput.next();
				continue;
			}
			do {	// Begin account PIN loop.
				System.out.print("Create an account PIN: ");
				try {
					accountPin = userInput.next();
					Pin.IS_VALID_PIN(accountPin);
				} catch (InputMismatchException e) {
					System.out.println("\nPlease create an account PIN.\n");
					userInput.next();
					continue;
				} catch (IllegalArgumentException e) {
					System.out.println("\n" + e.getMessage() + "\n");
					continue;
				}
				do {	// Begin confirm PIN loop.
					System.out.print("Confirm your account PIN: ");
					try {
						confirmPin = userInput.next();
						Pin.PINs_MATCH(accountPin, confirmPin);
					} catch (InputMismatchException e) {
						System.out.println("\nPlease confirm your account PIN.\n");
						userInput.next();
						continue;
					} catch (IllegalArgumentException e) {
						System.out.println("\n" + e.getMessage() + "\n");
						continue;
					}
					do {	// Begin account balance loop.
						System.out.print("Enter your starting balance: $");
						try {
							accountBalance = userInput.nextDouble();
							BankAccount.IS_POSITIVE_AMOUNT(accountBalance);
						} catch (InputMismatchException e) {
							System.out.println("\nPlease enter your account's starting balance.\n");
							userInput.next();
							continue;
						} catch (IllegalArgumentException e) {
							System.out.println("\n" + e.getMessage() + "\n");
							continue;
						}
						System.out.println("\nAccount created. Your account number is #"
								+ new BankAccount(accountName, new Pin(accountPin, confirmPin), new BigDecimal(accountBalance)).getAccountNumber() + ".\n");
						break;
					} while (true);		// End account balance loop.
					break;
				} while (true);		// End confirm PIN loop.
				break;
			} while (true);		// End account PIN loop.
			break;
		} while (true);		// End account name loop.
	}

	private static void EXIT() {
		System.exit(0);
	}

	private static void BANK_ACCOUNT_MENU() {
		boolean loggedIn = true;
		do {	// Begin account menu loop.
			System.out.print("Account Menu\n (1) Deposit\n (2) Withdrawal\n (3) Transfer\n (4) Check Balance\n (5) Account Options\n (6) Logout\nEnter an option: ");
			try {
				userResponse = userInput.nextShort();
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter one of the given options.\n");
				userInput.next();
				continue;
			}
			switch (userResponse) {
				case 1: {	// Deposit.
					DEPOSIT();
					break;
				}
				case 2: {	// Withdrawal.
					WITHDRAW();
					break;
				}
				case 3: {	// Transfer.
					TRANSFER();
					break;
				}
				case 4: {	// Check balance.
					CHECK_BALANCE();
					break;
				}
				case 5: {	// Account options.
					BANK_ACCOUNT_OPTIONS();
					break;
				}
				case 6: {	// Logout.
					LOGOUT();
					loggedIn = false;
					break;
				}
				default: {	// Error.
					System.out.println("\nPlease enter one of the given options.\n");
					continue;
				}
			}
		} while (loggedIn);		// End account menu loop.
	}

	private static void ADMIN_ACCOUNT_MENU() {

	}

	private static void DEPOSIT() {
		double depositAmount;
		do {	// Begin deposit loop.
			System.out.print("Enter the amount you want to deposit: $");
			try {
				depositAmount = userInput.nextDouble();
				BankAccount.IS_POSITIVE_AMOUNT(depositAmount);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter the amount you want to deposit.\n");
				userInput.next();
				continue;
			} catch (IllegalArgumentException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				continue;
			}
			((BankAccount)currentAccount).deposit(depositAmount);
			System.out.println("\nDeposited " + BankAccount.TO_CURRENCY_FORMAT(depositAmount) + " to your account.\n");
			break;
		} while (true);		// End deposit loop.
	}	
	
	private static void WITHDRAW() {
		double withdrawalAmount;
		do {	// Begin withdraw loop.
			System.out.print("Enter the amount you want to withdrawal: $");
			try {
				withdrawalAmount = userInput.nextDouble();
				BankAccount.IS_POSITIVE_AMOUNT(withdrawalAmount);
				((BankAccount)currentAccount).hasSufficientBalance(withdrawalAmount);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter the amount you want to withdrawal.\n");
				userInput.next();
				continue;
			}
			((BankAccount)currentAccount).withdraw(withdrawalAmount);
			System.out.println("\nWithdrew " + BankAccount.TO_CURRENCY_FORMAT(withdrawalAmount) + " to your account.\n");
			break;
		} while (true);		// End withdraw loop.
	}
	
	private static void TRANSFER() {
		int receivingAccount;
		double transferAmount;
		do {	// Begin receiving account loop.
			System.out.print("Enter the account number that you want to transfer to: #");
			try {
				receivingAccount = userInput.nextInt();
				Account.ACCOUNT_EXISTS(receivingAccount);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter the account number that you want to transfer to.\n");
				userInput.next();
				continue;
			} catch (NullPointerException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				break;
			}
			do {	// Begin transfer amount loop.
				System.out.print("Enter the amount that you want to transfer: $");
				try {
					transferAmount = userInput.nextDouble();
					BankAccount.IS_POSITIVE_AMOUNT(transferAmount);
					((BankAccount)currentAccount).hasSufficientBalance(transferAmount);
				} catch (InputMismatchException e) {
					
				}
			} while (true);		// End transfer amount loop.
		} while (true);		// End receiving account loop.
	}
	
	private static void CHECK_BALANCE() {
		
	}
	
	private static void BANK_ACCOUNT_OPTIONS() {
		
	}
	
	private static void LOGOUT() {
		currentAccount = null;
	}
	
}