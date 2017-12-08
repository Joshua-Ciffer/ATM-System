
package src.atm.account ; 
import java.math.BigDecimal ;
import src.atm.pin.Pin ;

public final class AdminAccount extends Account {

	public AdminAccount() {
		super() ;
	}

	public AdminAccount(int ACCOUNT_NUMBER, String accountName, Pin accountPin, String accountHistory) {
		super(ACCOUNT_NUMBER, accountName, accountPin, accountHistory) ;
	}

	public boolean equals(Object account) {
		AdminAccount comparingAccount = (AdminAccount)account ;
		if (this.toString().equalsIgnoreCase(comparingAccount.toString())) {
			return true ;
		} else {
			return false ;
		}
	}

	@Override
	public String toString() {
		return this.ACCOUNT_NUMBER + ", " + this.accountName + ", " + this.accountPin + ", " + this.accountHistory + "," ;
	}

}