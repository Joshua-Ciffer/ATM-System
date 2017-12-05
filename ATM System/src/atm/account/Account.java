/**
 * @author Joshua
 */
package src.atm.account;
import java.util.HashMap ;

/**
 * @author Joshua
 * @version 12/03/2017
 */

interface Account {

	public static HashMap<Integer, Account> ACCOUNT_MAP = new HashMap<>() ;
	
	public default int GENERATE_ACCOUNT_NUMBER(int accountNumber) {
		do {
			accountNumber = (int)(Math.random() * 1_000_000) ;
			if (accountNumber < 100_000 || accountNumber > 999_999 ) {
				continue ;
			} else if (ACCOUNT_MAP.containsKey(accountNumber)) {
				continue ;
			} else {
				break ;
			}
		} while (true) ;
		return accountNumber ;
	}
	
}