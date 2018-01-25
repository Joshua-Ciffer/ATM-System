package src.atm.app;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.math.BigDecimal;
import src.atm.account.Account;
import src.atm.account.BankAccount;
import src.atm.account.SavingsAccount;
import src.atm.account.AdminAccount;
import src.atm.account.Pin;

/**
 * 
 * @author Joshua Ciffer
 * @version 01/25/2018
 */
public abstract class ConsoleATM {

	private static final Scanner userInput = new Scanner(System.in);

	private static short userResponse;

	private static Account currentAccount;

	private static boolean loggedIn;

	public static void main(String[] args) {
		MAIN_MENU();
	}

	private static void MAIN_MENU() {
		do {
			System.out.print("ATM Main Menu\n (1) Login\n (2) Create Account\n (3) Exit\nEnter an option: ");
			try {
				userResponse = userInput.nextShort();
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter one of the given options.\n");
				userInput.next();
				continue;
			}
			switch (userResponse) {
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
			}
			System.out.print("\n");
		} while (true);
	}

	private static void LOGIN() {
		int accountNumber;
		String accountPin;
		System.out.print("\n");
		do {
			System.out.print("Enter your account number: #");
			try {
				accountNumber = userInput.nextInt();
				Account.ACCOUNT_EXISTS(accountNumber);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter your account number.\n");
				userInput.next();
				continue;
			} catch (NullPointerException e) {
				System.out.println("\n" + e.getMessage());
				break;
			}
			do {
				System.out.print("Enter your account PIN: ");
				try {
					accountPin = userInput.next();
					Pin.IS_CORRECT_PIN(accountNumber, accountPin);
				} catch (IllegalArgumentException e) {
					System.out.println("\n" + e.getMessage());
					break;
				}
				currentAccount = Account.GET_ACCOUNT(accountNumber, accountPin);
				loggedIn = true;
				if (currentAccount instanceof BankAccount) {
					BANK_ACCOUNT_MENU();
				} else if (currentAccount instanceof AdminAccount) {
					ADMIN_ACCOUNT_MENU();
				}
				break;
			} while (true);
			break;
		} while (true);
	}

	private static void CREATE_ACCOUNT() {
		String accountName, accountPin, confirmPin;
		double accountBalance;
		userInput.nextLine();
		System.out.print("\n");
		do {
			System.out.print("Enter your name: ");
			accountName = userInput.nextLine();
			do {
				System.out.print("Create an account PIN: ");
				try {
					accountPin = userInput.next();
					Pin.IS_VALID_PIN(accountPin);
				} catch (IllegalArgumentException e) {
					System.out.println("\n" + e.getMessage() + "\n");
					continue;
				}
				do {
					System.out.print("Confirm your account PIN: ");
					try {
						confirmPin = userInput.next();
						Pin.PINs_MATCH(accountPin, confirmPin);
					} catch (IllegalArgumentException e) {
						System.out.println("\n" + e.getMessage() + "\n");
						continue;
					}
					do {
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
								+ new BankAccount(accountName, new Pin(accountPin, confirmPin), new BigDecimal(accountBalance)).getAccountNumber() + ".");
						break;
					} while (true);
					break;
				} while (true);
				break;
			} while (true);
			break;
		} while (true);
	}

	private static void EXIT() {
		System.exit(0);
	}

	private static void BANK_ACCOUNT_MENU() {
		System.out.print("\n");
		do {
			System.out.print("Account Menu\n (1) Deposit\n (2) Withdraw\n (3) Transfer\n (4) Check Balance\n (5) Account Options\n (6) Logout\nEnter an option: ");
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
				case 2: {	// Withdraw.
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
					break;
				}
				default: {	// Error.
					System.out.println("\nPlease enter one of the given options.\n");
					continue;
				}
			}
		} while (loggedIn);
	}

	private static void DEPOSIT() {
		double depositAmount;
		System.out.print("\n");
		do {
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
			if (currentAccount instanceof SavingsAccount) {
				((SavingsAccount)currentAccount).deposit(depositAmount);
			} else {
				((BankAccount)currentAccount).deposit(depositAmount);
			}
			System.out.println("\nDeposited " + BankAccount.TO_CURRENCY_FORMAT(depositAmount) + " to your account.\n");
			break;
		} while (true);
	}

	private static void WITHDRAW() {
		double withdrawAmount;
		System.out.print("\n");
		do {
			System.out.print("Enter the amount you want to withdraw: $");
			try {
				withdrawAmount = userInput.nextDouble();
				BankAccount.IS_POSITIVE_AMOUNT(withdrawAmount);
				((BankAccount)currentAccount).hasSufficientBalance(withdrawAmount);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter the amount you want to withdraw.\n");
				userInput.next();
				continue;
			} catch (IllegalArgumentException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				break;
			}
			((BankAccount)currentAccount).withdraw(withdrawAmount);
			System.out.println("\nWithdrew " + BankAccount.TO_CURRENCY_FORMAT(withdrawAmount) + " from your account.\n");
			break;
		} while (true);
	}

	private static void TRANSFER() {
		int receivingAccount;
		double transferAmount;
		System.out.print("\n");
		do {
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
			do {
				System.out.print("Enter the amount that you want to transfer: $");
				try {
					transferAmount = userInput.nextDouble();
					BankAccount.IS_POSITIVE_AMOUNT(transferAmount);
					((BankAccount)currentAccount).hasSufficientBalance(transferAmount);
				} catch (InputMismatchException e) {
					System.out.println("\nPlease enter the amount that you want to transfer.\n");
					userInput.next();
					continue;
				} catch (IllegalArgumentException e) {
					System.out.println("\n" + e.getMessage() + "\n");
					continue;
				}
				((BankAccount)currentAccount).transfer(receivingAccount, transferAmount);
				System.out.println("\nTransfered " + BankAccount.TO_CURRENCY_FORMAT(transferAmount) + " from your account to account #" + receivingAccount + ".\n");
				break;
			} while (true);
			break;
		} while (true);
	}

	private static void CHECK_BALANCE() {
		System.out.println("\nYour account balance is " + BankAccount.TO_CURRENCY_FORMAT(((BankAccount)currentAccount).getAccountBalance()) + ".\n");
	}

	private static void BANK_ACCOUNT_OPTIONS() {
		boolean exitAccountOptions = false;
		System.out.print("\n");
		do {
			System.out.print("Account Options\n (1) Change PIN\n (2) View Account History\n (3) Delete Account\n (4) Back\nEnter an option: ");
			try {
				userResponse = userInput.nextShort();
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter one of the given options.\n");
				userInput.next();
				continue;
			}
			switch (userResponse) {
				case 1: {	// Change PIN.
					CHANGE_PIN();
					break;
				}
				case 2: {	// View account history.
					VIEW_ACCOUNT_HISTORY();
					break;
				}
				case 3: {	// Delete account.
					DELETE_ACCOUNT();
					if (currentAccount == null) {
						exitAccountOptions = true;
					}
					break;
				}
				case 4: {	// Back.
					exitAccountOptions = true;
					System.out.print("\n");
					break;
				}
				default: {	// Error.
					System.out.println("\nPlease enter one of the given options.\n");
					continue;
				}
			}
		} while (!exitAccountOptions);
	}

	private static void CHANGE_PIN() {
		String currentPin, newPin, confirmPin;
		System.out.print("\n");
		do {
			System.out.print("Enter your current PIN: ");
			try {
				currentPin = userInput.next();
				Pin.IS_CORRECT_PIN(currentAccount.getAccountNumber(), currentPin);
			} catch (IllegalArgumentException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				break;
			}
			do {
				System.out.print("Create your new PIN: ");
				try {
					newPin = userInput.next();
					Pin.IS_VALID_PIN(newPin);
				} catch (IllegalArgumentException e) {
					System.out.println("\n" + e.getMessage() + "\n");
					continue;
				}
				do {
					System.out.print("Confirm your new PIN: ");
					try {
						confirmPin = userInput.next();
						Pin.PINs_MATCH(newPin, confirmPin);
					} catch (IllegalArgumentException e) {
						System.out.println("\n" + e.getMessage() + "\n");
						continue;
					}
					currentAccount.changeAccountPin(currentPin, newPin, confirmPin);
					System.out.println("\nYour account PIN has been changed.\n");
					break;
				} while (true);
				break;
			} while (true);
			break;
		} while (true);
	}

	private static void VIEW_ACCOUNT_HISTORY() {
		System.out.println("\nAccount History\n" + currentAccount.getAccountHistory());
	}

	private static void DELETE_ACCOUNT() {
		String confirmAccountDeletion, accountPin;
		userInput.nextLine();
		System.out.print("\n");
		do {
			System.out.print("Are you sure you want to delete your account?\nType \"YES\" to confirm: ");
			confirmAccountDeletion = userInput.next();
			if (confirmAccountDeletion.equalsIgnoreCase("YES")) {
				do {
					System.out.print("Enter your PIN to complete your account deletion: ");
					try {
						accountPin = userInput.next();
						if (Pin.IS_CORRECT_PIN(currentAccount.getAccountNumber(), accountPin)) {
							currentAccount.closeAccount(accountPin);
							System.out.println("\nYour account has been deleted.");
							LOGOUT();
							break;
						}
					} catch (IllegalArgumentException e) {
						System.out.println("\n" + e.getMessage() + " Your account will not be deleted.\n");
						break;
					}
				} while (true);
				break;
			} else {
				System.out.println("\nYour account will not be deleted.\n");
				break;
			}
		} while (true);
	}

	private static void LOGOUT() {
		currentAccount = null;
		loggedIn = false;
	}

	private static void ADMIN_ACCOUNT_MENU() {
		System.out.print("\n");
		do {
			System.out.print("Account Menu\n (1) Create Account\n (2) Edit Account\n (3) Delete Account\n (4) Account Options\n (5) Logout\nEnter an option: ");
			try {
				userResponse = userInput.nextShort();
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter one of the given options.\n");
				userInput.next();
				continue;
			}
			switch (userResponse) {
				case 1: {	// Create account.
					ADMIN_CREATE_ACCOUNT();
					break;
				}
				case 2: {	// Edit account.
					ADMIN_EDIT_ACCOUNT();
					break;
				}
				case 3: {	// Delete account.
					ADMIN_DELETE_ACCOUNT();
					break;
				}
				case 4: {	// Account options.
					ADMIN_ACCOUNT_OPTIONS();
					break;
				}
				case 5: {	// Logout.
					LOGOUT();
					break;
				}
			}
		} while (loggedIn);
	}

	private static void ADMIN_CREATE_ACCOUNT() {
		String accountType, accountName, accountPin, confirmPin;
		double accountBalance, interestRate = 0;
		System.out.print("\n");
		do {
			System.out.print("Enter the type of account you would like to create. Admin, Bank, or Savings?: ");
			accountType = userInput.next();
			do {
				System.out.print("Enter the name: ");
				accountName = userInput.nextLine();
				do {
					System.out.print("Create an account PIN: ");
					try {
						accountPin = userInput.next();
						Pin.IS_VALID_PIN(accountPin);
					} catch (IllegalArgumentException e) {
						System.out.println("\n" + e.getMessage() + "\n");
						continue;
					}
					do {
						System.out.print("Confirm the account PIN: ");
						try {
							confirmPin = userInput.next();
							Pin.PINs_MATCH(accountPin, confirmPin);
						} catch (IllegalArgumentException e) {
							System.out.println("\n" + e.getMessage() + "\n");
							continue;
						}
						break;
					} while (true);
					break;
				} while (true);
				break;
			} while (true);
			switch (accountType.toLowerCase()) {
				case "admin": {
					System.out.println(
							"\nAccount created. The account number is #" + new AdminAccount(accountName, new Pin(accountPin, confirmPin)).getAccountNumber() + ".");
					break;
				}
				case "savings": {
					do {
						System.out.print("Enter the account's interest rate: ");
						try {
							interestRate = userInput.nextDouble();
							BankAccount.IS_POSITIVE_AMOUNT(interestRate);
						} catch (InputMismatchException e) {
							System.out.println("\nPlease enter the account's interest rate.\n");
							userInput.next();
							continue;
						} catch (IllegalArgumentException e) {
							System.out.println("\n" + e.getMessage() + "\n");
							continue;
						}
						break;
					} while (true);
				}
				case "bank": {
					do {
						System.out.print("Enter the starting balance: $");
						try {
							accountBalance = userInput.nextDouble();
							BankAccount.IS_POSITIVE_AMOUNT(accountBalance);
						} catch (InputMismatchException e) {
							System.out.println("\nPlease enter the account's starting balance.\n");
							userInput.next();
							continue;
						} catch (IllegalArgumentException e) {
							System.out.println("\n" + e.getMessage() + "\n");
							continue;
						}
						if (accountType.equalsIgnoreCase("savings")) {
							System.out.println("\nAccount created. The account number is #"
									+ new SavingsAccount(accountName, new Pin(accountPin, confirmPin), new BigDecimal(accountBalance), interestRate)
											.getAccountNumber()
									+ ".");
						} else {
							System.out.println("\nAccount created. The account number is #"
									+ new BankAccount(accountName, new Pin(accountPin, confirmPin), new BigDecimal(accountBalance)).getAccountNumber() + ".");
						}
						break;
					} while (true);
					break;
				}
				default: {
					System.out.println("\nPlease choose one of the account types to create.\n");
					continue;
				}
			}
			break;
		} while (true);
	}

	private static void ADMIN_EDIT_ACCOUNT() {

	}

	private static void ADMIN_DELETE_ACCOUNT() {
		int accountNumber;
		System.out.print("\n");
		do {
			System.out.print("Enter the number of the account that you want to delete: #");
			try {
				accountNumber = userInput.nextInt();
				Account.ACCOUNT_EXISTS(accountNumber);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter the number of the account that you want to delete.\n");
				userInput.next();
				continue;
			} catch (NullPointerException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				break;
			}
			((AdminAccount)currentAccount).deleteAccount(accountNumber);
			System.out.println("\nDeleted account #" + accountNumber + ".\n");
			break;
		} while (true);
	}

	private static void ADMIN_ACCOUNT_OPTIONS() {
		boolean exitAccountOptions = false;
		System.out.print("\n");
		do {
			System.out.print("Account Options\n (1) Change PIN\n (2) View Account History\n (3) Delete Account\n (4) Back\nEnter an option: ");
			try {
				userResponse = userInput.nextShort();
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter one of the given options.\n");
				userInput.next();
				continue;
			}
			switch (userResponse) {
				case 1: {	// Change PIN.
					CHANGE_PIN();
					break;
				}
				case 2: {	// View account history.
					VIEW_ACCOUNT_HISTORY();
					break;
				}
				case 3: {	// Delete account.
					DELETE_ACCOUNT();
					if (currentAccount == null) {
						exitAccountOptions = true;
					}
					break;
				}
				case 4: {	// Back.
					exitAccountOptions = true;
					System.out.print("\n");
					break;
				}
				default: {	// Error.
					System.out.println("\nPlease enter one of the given options.\n");
					continue;
				}
			}
		} while (!exitAccountOptions);
	}

}