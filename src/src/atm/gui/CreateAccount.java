// ============================================================================//
// Name : CreateAccount.java //
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

final class CreateAccount extends JPanel {

	private static Font labelFont = new Font("Dialog", Font.BOLD, 14);
	private JLabel createAccountLabel, accountNameLabel, accountPinLabel, confirmPinLabel;
	private JTextField accountNameField;
	private JPasswordField accountPinField, confirmPinField;
	private JButton backButton, createAccountButton;
	private String accountName, accountPin, confirmPin;

	public CreateAccount() {
		super(null);

		createAccountLabel = new JLabel("Create Account");
		createAccountLabel.setFont(labelFont);
		createAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createAccountLabel.setVerticalAlignment(SwingConstants.CENTER);
		createAccountLabel.setBounds(150, 10, 200, 50);

		accountNameLabel = new JLabel("Name:");
		accountNameLabel.setFont(labelFont);
		accountNameLabel.setVerticalAlignment(SwingConstants.CENTER);
		accountNameLabel.setBounds(69, 73, 200, 50);

		accountPinLabel = new JLabel("Account PIN:");
		accountPinLabel.setFont(labelFont);
		accountPinLabel.setVerticalAlignment(SwingConstants.CENTER);
		accountPinLabel.setBounds(69, 136, 200, 50);

		confirmPinLabel = new JLabel("Confirm PIN:");
		confirmPinLabel.setFont(labelFont);
		confirmPinLabel.setVerticalAlignment(SwingConstants.CENTER);
		confirmPinLabel.setBounds(71, 199, 200, 50);

		accountNameField = new JTextField();
		accountNameField.setFont(labelFont);
		accountNameField.setToolTipText("Enter your name.");
		accountNameField.setBounds(225, 74, 200, 50);

		accountPinField = new JPasswordField();
		accountPinField.setFont(labelFont);
		accountPinField.setToolTipText("Create a 4 digit PIN.");
		accountPinField.setBounds(225, 136, 200, 50);

		confirmPinField = new JPasswordField();
		confirmPinField.setFont(labelFont);
		confirmPinField.setToolTipText("Confirm your 4 digit PIN.");
		confirmPinField.setBounds(225, 200, 200, 50);

		backButton = new JButton("Back");
		backButton.setToolTipText("Return to the main menu.");
		backButton.setBounds(75, 285, 150, 50);

		createAccountButton = new JButton("Create Account");
		createAccountButton.setToolTipText("Create your account.");
		createAccountButton.setBounds(275, 285, 150, 50);

		add(createAccountLabel);
		add(accountNameLabel);
		add(accountPinLabel);
		add(confirmPinLabel);
		add(accountNameField);
		add(accountPinField);
		add(confirmPinField);
		add(backButton);
		add(createAccountButton);
	}

	public JLabel getCreateAccountLabel() {
		return createAccountLabel;
	}

	public JLabel getAccountNameLabel() {
		return accountNameLabel;
	}

	public JLabel getAccountPinLabel() {
		return accountPinLabel;
	}

	public JLabel getConfirmPinLabel() {
		return confirmPinLabel;
	}

	public JTextField getAccountNameField() {
		return accountNameField;
	}

	public JPasswordField getAccountPinField() {
		return accountPinField;
	}

	public JPasswordField getConfirmPinField() {
		return confirmPinField;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JButton getCreateAccountButton() {
		return this.createAccountButton;
	}

	public String getAccountName() {
		this.accountName = accountNameField.getText();
		return this.accountName;
	}

	public String getAccountPin() {
		this.accountPin = String.valueOf(accountPinField.getPassword());
		return this.accountPin;
	}

	public String getConfirmPin() {
		this.confirmPin = String.valueOf(confirmPinField.getPassword());
		return this.confirmPin;
	}

}