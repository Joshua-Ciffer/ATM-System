//============================================================================//
// Name        : Login.java                                                   //
// Author      : Joshua Ciffer                                                //
// Date        : 11/30/2017                                                   //
//============================================================================//

package src.atm.gui ; 
import javax.swing.JPanel ;
import javax.swing.JLabel ;
import javax.swing.JTextField ;
import javax.swing.JPasswordField ;
import javax.swing.JButton ;
import javax.swing.SwingConstants ;
import java.awt.Font ;

final class Login extends JPanel {

	private JLabel loginLabel, accountNumberLabel, accountPinLabel ;
	private JTextField accountNumberField ;
	private JPasswordField accountPinField ;
	private JButton backButton, loginButton ;
	private String accountPin ;
	private int accountNumber ;
	
	public Login() {
		super(null) ;
		loginLabel = new JLabel("Login") ;
		loginLabel.setBounds(150, 10, 200, 50) ;
		loginLabel.setFont(new Font("Dialog", Font.BOLD, 14)) ;
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER) ;
		loginLabel.setVerticalAlignment(SwingConstants.CENTER) ;
		accountNumberLabel = new JLabel("Account Number:") ;
		accountNumberLabel.setSize(200, 50) ;
		accountNumberLabel.setLocation(75, 85) ;
		accountNumberLabel.setFont(new Font("Dialog", Font.BOLD, 14)) ;
		accountNumberLabel.setVerticalAlignment(SwingConstants.CENTER) ;
		accountPinLabel = new JLabel("Account PIN:") ;
		accountPinLabel.setSize(200, 50) ;
		accountPinLabel.setLocation(75, 185) ;
		accountPinLabel.setFont(new Font("Dialog", Font.BOLD, 14)) ;
		accountPinLabel.setVerticalAlignment(SwingConstants.CENTER) ;
		accountNumberField = new JTextField() ;
		accountNumberField.setToolTipText("Enter your account number.") ;
		accountNumberField.setBounds(225, 85, 200, 50) ;
		accountNumberField.setFont(new Font("Dialog", Font.BOLD, 14)) ;
		accountPinField = new JPasswordField() ;
		accountPinField.setToolTipText("Enter your account PIN.") ;
		accountPinField.setBounds(225, 185, 200, 50) ;
		accountPinField.setFont(new Font("Dialog", Font.BOLD, 14)) ;
		backButton = new JButton("Back") ;
		backButton.setToolTipText("Return to the main menu.") ;
		backButton.setBounds(75, 285, 150, 50) ;
		loginButton = new JButton("Login") ;
		loginButton.setToolTipText("Login to your account.") ;
		loginButton.setBounds(275, 285, 150, 50) ;
		this.add(loginLabel) ;
		this.add(accountNumberLabel) ;
		this.add(accountNumberField) ;
		this.add(accountPinLabel) ;
		this.add(accountPinField) ;
		this.add(backButton) ;
		this.add(loginButton) ;
	}
	
	public JLabel getLoginLabel() {
		return this.loginLabel ;
	}
	
	public JLabel getAccountNumberLabel() {
		return this.accountNumberLabel ;
	}
	
	public JLabel getAccountPinLabel() {
		return this.accountPinLabel ;
	}
	
	public JTextField getAccountNumberField() {
		return this.accountNumberField ;
	}
	
	public JPasswordField getAccountPinField() {
		return this.accountPinField ;
	}
	
	public JButton getBackButton() {
		return this.backButton ;
	}
	
	public JButton getLoginButton() {
		return this.loginButton ;
	}
	
	public String getAccountPin() {
		return this.accountPin ;
	}
	
	public int getAccountNumber() {
		return this.accountNumber ;
	}
	
}