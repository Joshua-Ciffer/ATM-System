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
 * This class contains methods and an entry point that runs a command line based interface which interacts with Account back-end code.
 * <br><br>
 * This class is abstract because it does not need to be instantiated.
 * 
 * @author Joshua Ciffer
 * @version 01/30/2018
 */
public abstract class ConsoleATM {

	/**
	 * Accepts all user input for menu prompts.
	 */
	private static final Scanner userInput = new Scanner(System.in);

	/**
	 * Stores user responses for picking menu options.
	 */
	private static short userResponse;

	/**
	 * Temporary storage for the account that is currently logged in.
	 */
	private static Account currentAccount;

	/**
	 * Keeps track of whether a user is logged in.
	 */
	private static boolean loggedIn;

	/**
	 * Main entry point for the program.
	 * 
	 * @param args - Any command line arguments.
	 */
	public static final void main(String[] args) {
		MAIN_MENU();
	}

	/**
	 * The main menu that displays when a user is not logged in. The user has the options to login to an account, create an account, or exit.
	 */
	private static final void MAIN_MENU() {
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

	/**
	 * Prompts the user to enter their login credentials. If the correct information has been entered, they log into their account.
	 */
	private static final void LOGIN() {
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
					ACCOUNT_MENU();
				} else if (currentAccount instanceof AdminAccount) {
					ADMIN_ACCOUNT_MENU();
				}
				break;
			} while (true);
			break;
		} while (true);
	}

	/**
	 * Prompts the user to enter information to open a bank account. An account is created and added to the account database.
	 */
	private static final void CREATE_ACCOUNT() {
		String accountType, accountName, accountPin, confirmPin;
		double accountBalance, interestRate = 0;
		userInput.nextLine();
		System.out.print("\n");
		do {
			System.out.print("Would you like to create a bank account or a savings account?: ");
			accountType = userInput.nextLine();
			switch (accountType.toLowerCase()) {
				case "savings account": {
					do {
						System.out.print("Please enter the interest rate for your account: ");
						try {
							interestRate = userInput.nextDouble();
							BankAccount.IS_POSITIVE_AMOUNT(interestRate);
						} catch (InputMismatchException e) {
							System.out.println("\nPlease enter the interest rate for your account.\n");
							userInput.next();
							continue;
						} catch (IllegalArgumentException e) {
							System.out.println("\n" + e.getMessage() + "\n");
							continue;
						}
						break;
					} while (true);
					userInput.nextLine();
				}
				case "bank account": {
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
									if (accountType.equalsIgnoreCase("savings account")) {
										System.out.println("\nAccount created. Your account number is #"
												+ new SavingsAccount(accountName, new Pin(accountPin, confirmPin), new BigDecimal(accountBalance), interestRate)
														.getAccountNumber());
									} else {
										System.out.println("\nAccount created. Your account number is #"
												+ new BankAccount(accountName, new Pin(accountPin, confirmPin), new BigDecimal(accountBalance)).getAccountNumber()
												+ ".");
									}
									break;
								} while (true);
								break;
							} while (true);
							break;
						} while (true);
						break;
					} while (true);
					break;
				}
			}
			break;
		} while (true);
	}

	/**
	 * Terminates the program.
	 */
	private static final void EXIT() {
		System.exit(0);
	}

	/**
	 * Displays options the user can select when they are logged into their account.
	 */
	private static final void ACCOUNT_MENU() {
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
					ACCOUNT_OPTIONS();
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

	/**
	 * Prompts the user to enter an amount of money to be deposited to their account.
	 */
	private static final void DEPOSIT() {
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

	/**
	 * Prompts the user to enter an amount of money that will be withdrawn from their account if they have a sufficient balance.
	 */
	private static final void WITHDRAW() {
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
				if (e.getMessage().equalsIgnoreCase("You have an insufficient balance to complete this transaction.")) {
					break;
				} else {
					continue;
				}
			}
			((BankAccount)currentAccount).withdraw(withdrawAmount);
			System.out.println("\nWithdrew " + BankAccount.TO_CURRENCY_FORMAT(withdrawAmount) + " from your account.\n");
			break;
		} while (true);
	}

	/**
	 * Prompts the user to enter an account number to transfer a specified amount of money to.
	 */
	private static final void TRANSFER() {
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
					if (e.getMessage().equalsIgnoreCase("You have an insufficient balance to complete this transaction.")) {
						break;
					} else {
						continue;
					}
				}
				((BankAccount)currentAccount).transfer(receivingAccount, transferAmount);
				System.out.println("\nTransfered " + BankAccount.TO_CURRENCY_FORMAT(transferAmount) + " from your account to account #" + receivingAccount + ".\n");
				break;
			} while (true);
			break;
		} while (true);
	}

	/**
	 * Displays the account's balance.
	 */
	private static final void CHECK_BALANCE() {
		System.out.println("\nYour account balance is " + BankAccount.TO_CURRENCY_FORMAT(((BankAccount)currentAccount).getAccountBalance()) + ".\n");
	}

	/**
	 * Displays additional options for accounts of any type.
	 */
	private static final void ACCOUNT_OPTIONS() {
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

	/**
	 * Prompts the user to create and confirm a new account PIN.
	 */
	private static final void CHANGE_PIN() {
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

	/**
	 * Displays a record of account information and transaction history.
	 */
	private static final void VIEW_ACCOUNT_HISTORY() {
		System.out.println("\nAccount History\n" + currentAccount.getAccountHistory());
	}

	/**
	 * Prompts the user to confirm whether or not they want their account to be deleted.
	 */
	private static final void DELETE_ACCOUNT() {
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

	/**
	 * Removes any pointers to the current account and sets the login status to false.
	 */
	private static final void LOGOUT() {
		currentAccount = null;
		loggedIn = false;
	}

	/**
	 * Displays administrator options with additional permissions.
	 */
	private static final void ADMIN_ACCOUNT_MENU() {
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
					ACCOUNT_OPTIONS();
					break;
				}
				case 5: {	// Logout.
					LOGOUT();
					break;
				}
			}
		} while (loggedIn);
	}

	private static final void ADMIN_CREATE_ACCOUNT() {
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

	private static final void ADMIN_EDIT_ACCOUNT() {
		int accountNumber;
		Account editAccount = null;
		System.out.print("\n");
		do {
			System.out.print("Enter the number of the account you want to edit: #");
			try {
				accountNumber = userInput.nextInt();
				Account.ACCOUNT_EXISTS(accountNumber);
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter the number of the account you want to edit.\n");
				userInput.next();
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

	private static final void ADMIN_DELETE_ACCOUNT() {
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

}