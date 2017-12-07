
package src.atm.account ; 
import java.math.BigDecimal ;
import src.atm.pin.Pin ;

public final class AdministratorAccount extends Account {

	public AdministratorAccount() {
		super() ;
	}

	public AdministratorAccount(int ACCOUNT_NUMBER, String accountName, Pin accountPin, String accountHistory) {
		super(ACCOUNT_NUMBER, accountName, accountPin, accountHistory) ;
	}

	public boolean equals(Object account) {
		AdministratorAccount comparingAccount = (AdministratorAccount)account ;
		if (this.toString().equalsIgnoreCase(comparingAccount.toString())) {
			return true ;
		} else {
			return false ;
		}
	}

	@Override
	public String toString() {
		return this.ACCOUNT_NUMBER + ", " + this.accountName + ", " + this.accountPin + ", " + this.accountHistory + "," + this.accountBalance ;
	}

	@Override
	public BigDecimal getAccountBalance() {
		return null;
	}

}
