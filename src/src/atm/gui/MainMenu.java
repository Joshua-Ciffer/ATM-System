

package src.atm.gui;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

final class MainMenu extends JPanel {

	private static Font labelFont = new Font("Dialog", Font.BOLD, 14);
	private JLabel mainMenuLabel;
	private JButton loginButton, createAccountButton, exitButton;

	public MainMenu() {
		super(null);

		mainMenuLabel = new JLabel("ATM Main Menu");
		mainMenuLabel.setFont(labelFont);
		mainMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainMenuLabel.setVerticalAlignment(SwingConstants.CENTER);
		mainMenuLabel.setBounds(150, 10, 200, 50);

		loginButton = new JButton("Login");
		loginButton.setToolTipText("Login with your account number and PIN.");
		loginButton.setBounds(150, 85, 200, 50);

		createAccountButton = new JButton("Create Account");
		createAccountButton.setToolTipText("Create an account if you do not already have one.");
		createAccountButton.setBounds(150, 180, 200, 50);

		exitButton = new JButton("Exit");
		exitButton.setToolTipText("Exit.");
		exitButton.setBounds(150, 280, 200, 50);

		this.add(mainMenuLabel);
		this.add(loginButton);
		this.add(createAccountButton);
		this.add(exitButton);
	}

	public JLabel getMainMenuLabel() {
		return this.mainMenuLabel;
	}

	public JButton getLoginButton() {
		return this.loginButton;
	}

	public JButton getCreateAccountButton() {
		return this.createAccountButton;
	}

	public JButton getExitButton() {
		return this.exitButton;
	}

}