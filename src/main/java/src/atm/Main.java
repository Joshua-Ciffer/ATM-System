package src.atm;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.math.BigDecimal;

import src.atm.account.Account;
import src.atm.account.AdminAccount;
import src.atm.account.BankAccount;
import src.atm.account.SavingsAccount;
import src.atm.account.Pin;
import src.atm.gui.GUI;

import static src.atm.util.Constants.SUCCESSFUL_EXECUTION_CODE;
import static src.atm.util.Constants.INVALID_COMMAND_ARGS_CODE;

/**
 * This class contains the main entry point for the ATM System program. By default the main() method will create a GUI frame to interface with the
 * program. This class also contains methods to provide a command line implementation of the program if the command line argument "--no-gui" is
 * passed.
 * 
 * @author Joshua Ciffer
 * @version 04/08/2018
 */
public final class Main {

	/**
	 * Don't let anyone instantiate this class.
	 */
	private Main() {}

	/**
	 * Main entry point for the program.
	 * 
	 * @param args
	 *        Command line arguments.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			new GUI();
		} else if (args[0].equalsIgnoreCase("--no-gui")) {
			System.out.println("Launching in no GUI mode.\n");
			cli_userInput = new Scanner(System.in);
			CLI_MAIN_MENU();
		} else {
			System.out.println("Invalid command line arguments.");
			System.exit(INVALID_COMMAND_ARGS_CODE);
		}
	}

	/**
	 * Accepts all user input for menu prompts.
	 * This object is only used in command line mode.
	 */
	private static Scanner cli_userInput;

	/**
	 * Stores user responses for picking menu options.
	 * This variable is only used in command line mode.
	 */
	private static short cli_userResponse;

	/**
	 * Temporary storage for the account that is currently logged in.
	 * This object is only used in command line mode.
	 */
	private static Account cli_currentAccount;

	/**
	 * Keeps track of whether a user is logged in.
	 * This variable is only used in command line mode.
	 */
	private static boolean cli_loggedIn;

	/**
	 * The main menu that displays when a user is not logged in. The user has the options to login to an account, create an account, or exit.
	 * This method is only used in command line mode.
	 */
	private static void CLI_MAIN_MENU() {
		do {
			System.out.print("ATM Main Menu\n (1) Login\n (2) Create Account\n (3) Exit\nEnter an option: ");
			try {
				cli_userResponse = cli_userInput.nextShort();
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter one of the given options.\n");
				cli_userInput.next();
				continue;
			}
			switch (cli_userResponse) {
				case 1: {	// Login.
					CLI_LOGIN();
					break;
				}
				case 2: {	// Create account.
					CLI_CREATE_ACCOUNT();
					break;
				}
				case 3: {	// Exit.
					CLI_EXIT();
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

	/**
	 * Prompts the user to enter their login credentials. If the correct information has been entered, they log into their account.
	 * This method is only used in command line mode.
	 */
	private static void CLI_LOGIN() {
		int accountNumber;
		String accountPin;
		System.out.print("\n");
		do {
			System.out.print("Enter your account number: #");
			try {
				accountNumber = cli_userInput.nextInt();
				Account.ACCOUNT_EXISTS(accountNumber);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter your account number.\n");
				cli_userInput.next();
				continue;
			} catch (NullPointerException e) {
				System.out.println("\n" + e.getMessage());
				break;
			}
			do {
				System.out.print("Enter your account PIN: ");
				try {
					accountPin = cli_userInput.next();
					Pin.IS_CORRECT_PIN(accountNumber, accountPin);
				} catch (IllegalArgumentException e) {
					System.out.println("\n" + e.getMessage());
					break;
				}
				cli_currentAccount = Account.GET_ACCOUNT(accountNumber, accountPin);
				cli_loggedIn = true;
				if (cli_currentAccount instanceof BankAccount) {
					CLI_BANK_ACCOUNT_MENU();
				} else if (cli_currentAccount instanceof AdminAccount) {
					CLI_ADMIN_ACCOUNT_MENU();
				}
				break;
			} while (true);
			break;
		} while (true);
	}

	/**
	 * Prompts the user to enter information to open a bank account. An account is created and added to the account database.
	 * This method is only used in command line mode.
	 */
	private static void CLI_CREATE_ACCOUNT() {
		String accountType, accountName, accountPin, confirmPin;
		double accountBalance = 0, interestRate = 0;
		cli_userInput.nextLine();
		System.out.print("\n");
		do {
			System.out.print("Would you like to create a bank account or a savings account?: ");
			accountType = cli_userInput.nextLine();
			switch (accountType.toLowerCase()) {
				case "savings account": {
					do {
						System.out.print("Please enter the interest rate for your account: %");
						try {
							interestRate = cli_userInput.nextDouble();
							BankAccount.IS_POSITIVE_AMOUNT(interestRate);
						} catch (InputMismatchException e) {
							System.out.println("\nPlease enter the interest rate for your account.\n");
							cli_userInput.next();
							continue;
						} catch (IllegalArgumentException e) {
							System.out.println("\n" + e.getMessage() + "\n");
							continue;
						}
						break;
					} while (true);
				}
				case "bank account": {
					do {
						System.out.print("Enter your starting balance: $");
						try {
							accountBalance = cli_userInput.nextDouble();
							BankAccount.IS_POSITIVE_AMOUNT(accountBalance);
						} catch (InputMismatchException e) {
							System.out.println("\nPlease enter your account's starting balance.\n");
							cli_userInput.next();
							continue;
						} catch (IllegalArgumentException e) {
							System.out.println("\n" + e.getMessage() + "\n");
							continue;
						}
						break;
					} while (true);
				}
				case "admin account": {
					cli_userInput.nextLine();
					do {
						System.out.print("Enter your name: ");
						accountName = cli_userInput.nextLine();
						do {
							System.out.print("Create an account PIN: ");
							try {
								accountPin = cli_userInput.next();
								Pin.IS_VALID_PIN(accountPin);
							} catch (IllegalArgumentException e) {
								System.out.println("\n" + e.getMessage() + "\n");
								continue;
							}
							do {
								System.out.print("Confirm your account PIN: ");
								try {
									confirmPin = cli_userInput.next();
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
					if (accountType.equalsIgnoreCase("savings account")) {
						System.out.println("\nAccount created. Your account number is #"
								+ new SavingsAccount(accountName, new Pin(accountPin, confirmPin), new BigDecimal(accountBalance), interestRate).getAccountNumber() + ".");
					} else if (accountType.equalsIgnoreCase("bank account")) {
						System.out.println("\nAccount created. Your account number is #"
								+ new BankAccount(accountName, new Pin(accountPin, confirmPin), new BigDecimal(accountBalance)).getAccountNumber() + ".");
					} else if (accountType.equalsIgnoreCase("admin account")) {
						System.out.println(
								"\nAccount created. Your account number is #" + new AdminAccount(accountName, new Pin(accountPin, confirmPin)).getAccountNumber() + ".");
					}
					break;
				}
				default: {
					System.out.println("\nPlease specify the type of account you would like to create.");
					break;
				}
			}
			break;
		} while (true);
	}

	/**
	 * Terminates the program.
	 * This method is only used in command line mode.
	 */
	private static void CLI_EXIT() {
		System.exit(SUCCESSFUL_EXECUTION_CODE);
	}

	/**
	 * Displays options the user can select when they are logged into their account.
	 * This method is only used in command line mode.
	 */
	private static void CLI_BANK_ACCOUNT_MENU() {
		System.out.print("\n");
		do {
			System.out.print("Account Menu\n (1) Deposit\n (2) Withdraw\n (3) Transfer\n (4) Check Balance\n (5) Account Options\n (6) Logout\nEnter an option: ");
			try {
				cli_userResponse = cli_userInput.nextShort();
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter one of the given options.\n");
				cli_userInput.next();
				continue;
			}
			switch (cli_userResponse) {
				case 1: {	// Deposit.
					CLI_DEPOSIT();
					break;
				}
				case 2: {	// Withdraw.
					CLI_WITHDRAW();
					break;
				}
				case 3: {	// Transfer.
					CLI_TRANSFER();
					break;
				}
				case 4: {	// Check balance.
					CLI_CHECK_BALANCE();
					break;
				}
				case 5: {	// Account options.
					CLI_ACCOUNT_OPTIONS();
					break;
				}
				case 6: {	// Logout.
					CLI_LOGOUT();
					break;
				}
				default: {	// Error.
					System.out.println("\nPlease enter one of the given options.\n");
					continue;
				}
			}
		} while (cli_loggedIn);
	}

	/**
	 * Prompts the user to enter an amount of money to be deposited to their account.
	 * This method is only used in command line mode.
	 */
	private static void CLI_DEPOSIT() {
		double depositAmount;
		System.out.print("\n");
		do {
			System.out.print("Enter the amount you want to deposit: $");
			try {
				depositAmount = cli_userInput.nextDouble();
				BankAccount.IS_POSITIVE_AMOUNT(depositAmount);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter the amount you want to deposit.\n");
				cli_userInput.next();
				continue;
			} catch (IllegalArgumentException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				continue;
			}
			if (cli_currentAccount instanceof SavingsAccount) {
				((SavingsAccount)cli_currentAccount).deposit(depositAmount);
			} else {
				((BankAccount)cli_currentAccount).deposit(depositAmount);
			}
			System.out.println("\nDeposited " + BankAccount.TO_CURRENCY_FORMAT(depositAmount) + " to your account.\n");
			break;
		} while (true);
	}

	/**
	 * Prompts the user to enter an amount of money that will be withdrawn from their account if they have a sufficient balance.
	 * This method is only used in command line mode.
	 */
	private static void CLI_WITHDRAW() {
		double withdrawAmount;
		System.out.print("\n");
		do {
			System.out.print("Enter the amount you want to withdraw: $");
			try {
				withdrawAmount = cli_userInput.nextDouble();
				BankAccount.IS_POSITIVE_AMOUNT(withdrawAmount);
				((BankAccount)cli_currentAccount).hasSufficientBalance(withdrawAmount);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter the amount you want to withdraw.\n");
				cli_userInput.next();
				continue;
			} catch (IllegalArgumentException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				if (e.getMessage().equalsIgnoreCase("You have an insufficient balance to complete this transaction.")) {
					break;
				} else {
					continue;
				}
			}
			((BankAccount)cli_currentAccount).withdraw(withdrawAmount);
			System.out.println("\nWithdrew " + BankAccount.TO_CURRENCY_FORMAT(withdrawAmount) + " from your account.\n");
			break;
		} while (true);
	}

	/**
	 * Prompts the user to enter an account number to transfer a specified amount of money to.
	 * This method is only used in command line mode.
	 */
	private static void CLI_TRANSFER() {
		int receivingAccount;
		double transferAmount;
		System.out.print("\n");
		do {
			System.out.print("Enter the account number that you want to transfer to: #");
			try {
				receivingAccount = cli_userInput.nextInt();
				Account.ACCOUNT_EXISTS(receivingAccount);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter the account number that you want to transfer to.\n");
				cli_userInput.next();
				continue;
			} catch (NullPointerException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				break;
			}
			do {
				System.out.print("Enter the amount that you want to transfer: $");
				try {
					transferAmount = cli_userInput.nextDouble();
					BankAccount.IS_POSITIVE_AMOUNT(transferAmount);
					((BankAccount)cli_currentAccount).hasSufficientBalance(transferAmount);
				} catch (InputMismatchException e) {
					System.out.println("\nPlease enter the amount that you want to transfer.\n");
					cli_userInput.next();
					continue;
				} catch (IllegalArgumentException e) {
					System.out.println("\n" + e.getMessage() + "\n");
					if (e.getMessage().equalsIgnoreCase("You have an insufficient balance to complete this transaction.")) {
						break;
					} else {
						continue;
					}
				}
				((BankAccount)cli_currentAccount).transfer(receivingAccount, transferAmount);
				System.out.println("\nTransfered " + BankAccount.TO_CURRENCY_FORMAT(transferAmount) + " from your account to account #" + receivingAccount + ".\n");
				break;
			} while (true);
			break;
		} while (true);
	}

	/**
	 * Displays the account's balance.
	 * This method is only used in command line mode.
	 */
	private static void CLI_CHECK_BALANCE() {
		System.out.println("\nYour account balance is " + BankAccount.TO_CURRENCY_FORMAT(((BankAccount)cli_currentAccount).getAccountBalance()) + ".\n");
	}

	/**
	 * Displays additional options for accounts of any type.
	 * This method is only used in command line mode.
	 */
	private static void CLI_ACCOUNT_OPTIONS() {
		boolean exitAccountOptions = false;
		System.out.print("\n");
		do {
			System.out.print("Account Options\n (1) Change PIN\n (2) View Account History\n (3) Delete Account\n (4) Back\nEnter an option: ");
			try {
				cli_userResponse = cli_userInput.nextShort();
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter one of the given options.\n");
				cli_userInput.next();
				continue;
			}
			switch (cli_userResponse) {
				case 1: {	// Change PIN.
					CLI_CHANGE_PIN();
					break;
				}
				case 2: {	// View account history.
					CLI_VIEW_ACCOUNT_HISTORY();
					break;
				}
				case 3: {	// Delete account.
					CLI_DELETE_ACCOUNT();
					if (cli_currentAccount == null) {
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

	/**
	 * Prompts the user to create and confirm a new account PIN.
	 * This method is only used in command line mode.
	 */
	private static void CLI_CHANGE_PIN() {
		String currentPin, newPin, confirmPin;
		System.out.print("\n");
		do {
			System.out.print("Enter your current PIN: ");
			try {
				currentPin = cli_userInput.next();
				Pin.IS_CORRECT_PIN(cli_currentAccount.getAccountNumber(), currentPin);
			} catch (IllegalArgumentException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				break;
			}
			do {
				System.out.print("Create your new PIN: ");
				try {
					newPin = cli_userInput.next();
					Pin.IS_VALID_PIN(newPin);
				} catch (IllegalArgumentException e) {
					System.out.println("\n" + e.getMessage() + "\n");
					continue;
				}
				do {
					System.out.print("Confirm your new PIN: ");
					try {
						confirmPin = cli_userInput.next();
						Pin.PINs_MATCH(newPin, confirmPin);
					} catch (IllegalArgumentException e) {
						System.out.println("\n" + e.getMessage() + "\n");
						continue;
					}
					cli_currentAccount.changeAccountPin(currentPin, newPin, confirmPin);
					System.out.println("\nYour account PIN has been changed.\n");
					break;
				} while (true);
				break;
			} while (true);
			break;
		} while (true);
	}

	/**
	 * Displays a record of account information and transaction history.
	 * This method is only used in command line mode.
	 */
	private static void CLI_VIEW_ACCOUNT_HISTORY() {
		System.out.println("\nAccount History\n" + cli_currentAccount.getAccountHistory());
	}

	/**
	 * Prompts the user to confirm whether or not they want their account to be deleted.
	 * This method is only used in command line mode.
	 */
	private static void CLI_DELETE_ACCOUNT() {
		String confirmAccountDeletion, accountPin;
		cli_userInput.nextLine();
		System.out.print("\n");
		do {
			System.out.print("Are you sure you want to delete your account?\nType \"YES\" to confirm: ");
			confirmAccountDeletion = cli_userInput.next();
			if (confirmAccountDeletion.equalsIgnoreCase("YES")) {
				do {
					System.out.print("Enter your PIN to complete your account deletion: ");
					try {
						accountPin = cli_userInput.next();
						if (Pin.IS_CORRECT_PIN(cli_currentAccount.getAccountNumber(), accountPin)) {
							cli_currentAccount.closeAccount(accountPin);
							System.out.println("\nYour account has been deleted.");
							CLI_LOGOUT();
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

	/**
	 * Removes any pointers to the current account and sets the login status to false.
	 * This method is only used in command line mode.
	 */
	private static void CLI_LOGOUT() {
		cli_currentAccount = null;
		cli_loggedIn = false;
	}

	/**
	 * Displays administrator options with additional permissions.
	 * This method is only used in command line mode.
	 */
	private static void CLI_ADMIN_ACCOUNT_MENU() {
		System.out.print("\n");
		do {
			System.out.print("Account Menu\n (1) Create Account\n (2) Edit Account\n (3) Delete Account\n (4) Account Options\n (5) Logout\nEnter an option: ");
			try {
				cli_userResponse = cli_userInput.nextShort();
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter one of the given options.\n");
				cli_userInput.next();
				continue;
			}
			switch (cli_userResponse) {
				case 1: {	// Create account.
					CLI_ADMIN_CREATE_ACCOUNT();
					break;
				}
				case 2: {	// Edit account.
					CLI_ADMIN_EDIT_ACCOUNT();
					break;
				}
				case 3: {	// Delete account.
					CLI_ADMIN_DELETE_ACCOUNT();
					break;
				}
				case 4: {	// Account options.
					CLI_ACCOUNT_OPTIONS();
					break;
				}
				case 5: {	// Logout.
					CLI_LOGOUT();
					break;
				}
			}
		} while (cli_loggedIn);
	}

	// TODO: Review admin method code.
	// TODO: Write admin account method javadoc.

	/**
	 *
	 *
	 */
	private static void CLI_ADMIN_CREATE_ACCOUNT() {
		String accountType, accountName, accountPin, confirmPin;
		double accountBalance, interestRate = 0;
		System.out.print("\n");
		do {
			System.out.print("Enter the type of account you would like to create. Admin, Bank, or Savings?: ");
			accountType = cli_userInput.next();
			do {
				System.out.print("Enter the name: ");
				accountName = cli_userInput.nextLine();
				do {
					System.out.print("Create an account PIN: ");
					try {
						accountPin = cli_userInput.next();
						Pin.IS_VALID_PIN(accountPin);
					} catch (IllegalArgumentException e) {
						System.out.println("\n" + e.getMessage() + "\n");
						continue;
					}
					do {
						System.out.print("Confirm the account PIN: ");
						try {
							confirmPin = cli_userInput.next();
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
					System.out.println("\nAccount created. The account number is #" + new AdminAccount(accountName, new Pin(accountPin, confirmPin)).getAccountNumber() + ".");
					break;
				}
				case "savings": {
					do {
						System.out.print("Enter the account's interest rate: ");
						try {
							interestRate = cli_userInput.nextDouble();
							BankAccount.IS_POSITIVE_AMOUNT(interestRate);
						} catch (InputMismatchException e) {
							System.out.println("\nPlease enter the account's interest rate.\n");
							cli_userInput.next();
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
							accountBalance = cli_userInput.nextDouble();
							BankAccount.IS_POSITIVE_AMOUNT(accountBalance);
						} catch (InputMismatchException e) {
							System.out.println("\nPlease enter the account's starting balance.\n");
							cli_userInput.next();
							continue;
						} catch (IllegalArgumentException e) {
							System.out.println("\n" + e.getMessage() + "\n");
							continue;
						}
						if (accountType.equalsIgnoreCase("savings")) {
							System.out.println("\nAccount created. The account number is #"
									+ new SavingsAccount(accountName, new Pin(accountPin, confirmPin), new BigDecimal(accountBalance), interestRate).getAccountNumber() + ".");
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

	/**
	 *
	 *
	 */
	private static void CLI_ADMIN_EDIT_ACCOUNT() {
		int accountNumber;
		Account editAccount = null;
		System.out.print("\n");
		do {
			System.out.print("Enter the number of the account you want to edit: #");
			try {
				accountNumber = cli_userInput.nextInt();
				Account.ACCOUNT_EXISTS(accountNumber);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter the number of the account you want to edit.\n");
				cli_userInput.next();
				continue;
			} catch (IllegalArgumentException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				break;
			}
			do {
				if (editAccount instanceof AdminAccount) {

				} else if (editAccount instanceof BankAccount) {

				} else if (editAccount instanceof SavingsAccount) {

				} else {

				}
				break;
			} while (true);
			break;
		} while (true);
	}

	/**
	 *
	 *
	 */
	private static void CLI_ADMIN_DELETE_ACCOUNT() {
		int accountNumber;
		System.out.print("\n");
		do {
			System.out.print("Enter the number of the account that you want to delete: #");
			try {
				accountNumber = cli_userInput.nextInt();
				Account.ACCOUNT_EXISTS(accountNumber);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter the number of the account that you want to delete.\n");
				cli_userInput.next();
				continue;
			} catch (NullPointerException e) {
				System.out.println("\n" + e.getMessage() + "\n");
				break;
			}
			((AdminAccount)cli_currentAccount).deleteAccount(accountNumber);
			System.out.println("\nDeleted account #" + accountNumber + ".\n");
			break;
		} while (true);
	}

}