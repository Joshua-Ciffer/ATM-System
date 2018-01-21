package src.atm.account;
import java.math.BigDecimal;
import java.text.NumberFormat;

public final class SavingsAccount extends BankAccount {

	private static final NumberFormat PERCENTAGE = NumberFormat.getPercentInstance();
	
	private double interestRate;

	public SavingsAccount(String accountName, Pin accountPin, BigDecimal accountBalance, double interestRate) throws IllegalArgumentException {
		super(accountName, accountPin, accountBalance);
	}

	public static final String TO_PERCENTAGE_FORMAT(double percentage) {
		return null;
	}
	
	public final double getInterest(double amount) {
		return (amount * (interestRate / 100));
	}

	public final void deposit(double depositAmount) throws IllegalArgumentException {
		super.deposit(depositAmount + getInterest(depositAmount));
	}
	
	@Override
	public final boolean equals(Object savingsAccount) {
		if (this.toString().equalsIgnoreCase(((SavingsAccount)savingsAccount).toString())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public final String toString() {
		return "Account Number: " + ACCOUNT_NUMBER + "\nAccount Name: " + accountName + "\nAccount Pin: " + accountPin.getPin() + "\nAccount Balance: "
				+ TO_CURRENCY_FORMAT(accountBalance) + "\nInterest Rate: " + (interestRate) + "\nAccount History: " + accountHistory;
	}

	public final double getInterestRate() {
		return interestRate;
	}

	final void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

}