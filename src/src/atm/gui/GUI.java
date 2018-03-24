// ============================================================================//
// Name : GUI.java //
// Author : Joshua Ciffer //
// Date : 11/30/2017 //
// ============================================================================//

package src.atm.gui;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.math.BigDecimal;
import src.atm.account.Account;
import src.atm.account.BankAccount;
import src.atm.account.Pin;

public final class GUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainMenu mainMenuPanel;
	private Login loginPanel;
	private CreateAccount createAccountPanel;
	private AccountMenu accountMenuPanel;
	private Deposit depositPanel;
	private int accountNumber;
	private String accountPin;
	private BankAccount currentAccount;

	public static void main(String[] args) {
		new GUI();
	}

	public GUI() {
		this.setVisible(true);
		this.setTitle("ATM");
		this.setSize(500, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new CardLayout());
		this.add(mainMenuPanel = new MainMenu());
		this.add(loginPanel = new Login());
		this.add(createAccountPanel = new CreateAccount());
		this.add(accountMenuPanel = new AccountMenu());
		this.add(depositPanel = new Deposit());
		mainMenuPanel.getLoginButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Login Pressed");
				mainMenuPanel.setVisible(false);
				loginPanel.setVisible(true);
			}
		});
		mainMenuPanel.getCreateAccountButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Create Account Pressed");
				mainMenuPanel.setVisible(false);
				createAccountPanel.setVisible(true);
			}
		});
		mainMenuPanel.getExitButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Exit Pressed");
				System.exit(0);
			}
		});
		loginPanel.getBackButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Back Pressed");
				loginPanel.setVisible(false);
				mainMenuPanel.setVisible(true);
			}
		});
		loginPanel.getLoginButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Login Pressed");
				try {
					System.out.println(Account.GET_ACCOUNT(loginPanel.getAccountNumber(), loginPanel.getAccountPin()).toString());
					accountNumber = loginPanel.getAccountNumber();
					accountPin = loginPanel.getAccountPin();
					currentAccount = (BankAccount)Account.GET_ACCOUNT(accountNumber, accountPin);
					loginPanel.setVisible(false);
					accountMenuPanel.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		createAccountPanel.getBackButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Back Pressed");
				createAccountPanel.setVisible(false);
				mainMenuPanel.setVisible(true);
			}
		});
		createAccountPanel.getCreateAccountButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Create Account Pressed");
				System.out.println("Input: " + createAccountPanel.getAccountName() + createAccountPanel.getAccountPin() + createAccountPanel.getConfirmPin());
				try {
					BankAccount x = new BankAccount(createAccountPanel.getAccountName(), new Pin(createAccountPanel.getAccountPin(), createAccountPanel.getConfirmPin()),
							new BigDecimal(0));
					System.out.println(x.toString());
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		accountMenuPanel.getMakeDepositButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Depoist Pressed");
				accountMenuPanel.setVisible(false);
				depositPanel.setVisible(true);
			}
		});
		accountMenuPanel.getMakeWithdrawalButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Withdraw Pressed");
			}
		});
		accountMenuPanel.getMakeTransferButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Transfer Pressed");
			}
		});
		accountMenuPanel.getCheckBalanceButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Check Balance Pressed");
				try {
					JOptionPane.showMessageDialog(null, BankAccount.TO_CURRENCY_FORMAT(((BankAccount)Account.GET_ACCOUNT(accountNumber, accountPin)).getAccountBalance()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		accountMenuPanel.getAccountOptionsButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Account Options Pressed");
			}
		});
		accountMenuPanel.getLogoutButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Logout Pressed.");
				accountMenuPanel.setVisible(false);
				mainMenuPanel.setVisible(true);
			}
		});
		depositPanel.getBackButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Back Pressed");
				depositPanel.setVisible(false);
				accountMenuPanel.setVisible(true);
			}
		});
		depositPanel.getDepositButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.out.println("Deposit Pressed");
				try {
					currentAccount.deposit(depositPanel.getDepositAmount());
					JOptionPane.showMessageDialog(null, "Deposited" + BankAccount.TO_CURRENCY_FORMAT(new BigDecimal(depositPanel.getDepositAmount())) + " to your account.", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void actionPerformed(ActionEvent a) {
		if (a.getSource() == depositPanel.getBackButton()) {

		}
	}

}