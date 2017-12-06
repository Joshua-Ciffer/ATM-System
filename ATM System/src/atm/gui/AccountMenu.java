/**
 * @author Joshua Ciffer
 * @version 12/06/2017
 */
package src.atm.gui ;
import javax.swing.JPanel ;
import javax.swing.JLabel ;
import javax.swing.JButton ;
import javax.swing.SwingConstants ;
import java.awt.Font ;

final class AccountMenu extends JPanel {
	
	private static Font labelFont = new Font("Dialog", Font.BOLD, 14) ;
	private JLabel accountMenuLabel ;
	private JButton makeDepositButton, makeWithdrawalButton, makeTransferButton, checkBalanceButton, accountOptionsButton, logoutButton ;
	
	
	public AccountMenu() {
		super(null) ;
		
		accountMenuLabel = new JLabel("Account Menu") ;
		accountMenuLabel.setFont(labelFont) ;
		accountMenuLabel.setHorizontalAlignment(SwingConstants.CENTER) ;
		accountMenuLabel.setVerticalAlignment(SwingConstants.CENTER) ;
		accountMenuLabel.setBounds(150, 10, 200, 50) ;
		
		makeDepositButton = new JButton("Deposit") ;
		makeDepositButton.setToolTipText("Make a deposit to your account.") ;
		makeDepositButton.setBounds(150, 71, 200, 50) ;
		
		makeWithdrawalButton = new JButton("Withdraw") ;
		makeWithdrawalButton.setToolTipText("Make a withdrawal from your account.") ;
		makeWithdrawalButton.setBounds(150, 132, 200, 50) ;
		
		makeTransferButton = new JButton("Transfer") ;
		makeTransferButton.setToolTipText("Make a transfer from your account to another account.") ;
		makeTransferButton.setBounds(150, 212, 200, 50) ;
		
		checkBalanceButton = new JButton("Check Balance") ;
		checkBalanceButton.setToolTipText("Check your account balance.") ;
		checkBalanceButton.setBounds(150, 273, 200, 50) ;
		
		accountOptionsButton = new JButton("Account Options") ;
		accountOptionsButton.setToolTipText("More account options.") ;
		accountOptionsButton.setBounds(150, 334, 200, 50) ;
		
		logoutButton = new JButton("Logout") ;
		logoutButton.setToolTipText("Logout from your account.") ;
		logoutButton.setBounds(150, 418, 200, 50) ;
		
		this.add(accountMenuLabel) ;
		this.add(makeDepositButton) ;
		this.add(makeWithdrawalButton) ;
		this.add(makeTransferButton) ;
		this.add(checkBalanceButton) ;
		this.add(accountOptionsButton) ;
		this.add(logoutButton) ;
	}

	public JLabel getAccountMenuLabel() {
		return this.accountMenuLabel ;
	}
	
	public JButton getMakeDepositButton() {
		return this.makeDepositButton ;
	}
	
	public JButton getMakeWithdrawalButton() {
		return this.makeWithdrawalButton ;
	}
	
	public JButton getMakeTransferButton() {
		return this.makeTransferButton ;
	}
	
	public JButton getCheckBalanceButton() {
		return this.checkBalanceButton ;
	}
	
	public JButton getAccountOptionsButton() {
		return this.accountOptionsButton ;
	}
	
	public JButton getLogoutButton() {
		return this.logoutButton ;
	}
	
}