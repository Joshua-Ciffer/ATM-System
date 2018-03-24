// ============================================================================//
// Name : Login.java //
// Author : Joshua Ciffer //
// Date : 11/30/2017 //
// ============================================================================//

package src.atm.gui;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

final class Login extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Font labelFont = new Font("Dialog", Font.BOLD, 14);
	private JLabel loginLabel, accountNumberLabel, accountPinLabel;
	private JTextField accountNumberField;
	private JPasswordField accountPinField;
	private JButton backButton, loginButton;
	private String accountPin;
	private int accountNumber;

	public Login() {
		super(null);

		loginLabel = new JLabel("Login");
		loginLabel.setFont(labelFont);
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setVerticalAlignment(SwingConstants.CENTER);
		loginLabel.setBounds(150, 10, 200, 50);

		accountNumberLabel = new JLabel("Account Number:");
		accountNumberLabel.setFont(labelFont);
		accountNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
		accountNumberLabel.setBounds(75, 85, 200, 50);

		accountPinLabel = new JLabel("Account PIN:");
		accountPinLabel.setFont(labelFont);
		accountPinLabel.setVerticalAlignment(SwingConstants.CENTER);
		accountPinLabel.setBounds(75, 185, 200, 50);

		accountNumberField = new JTextField();
		accountNumberField.setFont(labelFont);
		accountNumberField.setToolTipText("Enter your account number.");
		accountNumberField.setBounds(225, 85, 200, 50);

		accountPinField = new JPasswordField();
		accountPinField.setFont(labelFont);
		accountPinField.setToolTipText("Enter your account PIN.");
		accountPinField.setBounds(225, 185, 200, 50);

		backButton = new JButton("Back");
		backButton.setToolTipText("Return to the main menu.");
		backButton.setBounds(75, 285, 150, 50);

		loginButton = new JButton("Login");
		loginButton.setToolTipText("Login to your account.");
		loginButton.setBounds(275, 285, 150, 50);

		this.add(loginLabel);
		this.add(accountNumberLabel);
		this.add(accountNumberField);
		this.add(accountPinLabel);
		this.add(accountPinField);
		this.add(backButton);
		this.add(loginButton);
	}

	public JLabel getLoginLabel() {
		return this.loginLabel;
	}

	public JLabel getAccountNumberLabel() {
		return this.accountNumberLabel;
	}

	public JLabel getAccountPinLabel() {
		return this.accountPinLabel;
	}

	public JTextField getAccountNumberField() {
		return this.accountNumberField;
	}

	public JPasswordField getAccountPinField() {
		return this.accountPinField;
	}

	public JButton getBackButton() {
		return this.backButton;
	}

	public JButton getLoginButton() {
		return this.loginButton;
	}

	public String getAccountPin() {
		this.accountPin = String.valueOf(accountPinField.getPassword());
		return this.accountPin;
	}

	public int getAccountNumber() {
		this.accountNumber = Integer.parseInt(accountNumberField.getText());
		return this.accountNumber;
	}

}