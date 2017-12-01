//============================================================================//
// Name        : GUI.java                                                     //
// Author      : Joshua Ciffer                                                //
// Date        : 11/30/2017                                                   //
//============================================================================//

package src.atm.gui ;
import javax.swing.JFrame ;
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;
import java.awt.CardLayout ;
import src.atm.account.BankAccount ;

public final class GUI extends JFrame {

	private MainMenu mainMenuPanel ;
	private Login loginPanel ;
	private CreateAccount createAccountPanel ; 
	
	public static void main(String[] args) {
		new GUI() ;
	}

	public GUI() {
		this.setVisible(true) ;
		this.setTitle("ATM") ;
		this.setSize(500, 500) ;
		this.setResizable(false) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		this.setLocationRelativeTo(null) ;
		this.setLayout(new CardLayout()) ;
		this.add(mainMenuPanel = new MainMenu()) ;
		this.add(loginPanel = new Login()) ;
		this.add(createAccountPanel = new CreateAccount()) ;
		mainMenuPanel.getLoginButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Login Pressed") ;
				mainMenuPanel.setVisible(false) ;
				loginPanel.setVisible(true) ;
			}
		}) ;
		mainMenuPanel.getCreateAccountButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Create Account Pressed") ;
				mainMenuPanel.setVisible(false) ;
				createAccountPanel.setVisible(true) ;
			}
		}) ;
		mainMenuPanel.getExitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Exit Pressed") ;
				System.exit(0) ;
			}
		}) ;
		loginPanel.getBackButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Back Pressed") ;
				loginPanel.setVisible(false) ;
				mainMenuPanel.setVisible(true) ;
			}
		}) ;
		loginPanel.getLoginButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Login Pressed") ;
				try {
					System.out.println(BankAccount.GET_ACCOUNT(loginPanel.getAccountNumber(), loginPanel.getAccountPin()).toString()) ;
				} catch (Exception e) {
					e.printStackTrace() ;
				}
			}
		}) ;
		createAccountPanel.getBackButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Back Pressed") ;
				createAccountPanel.setVisible(false) ;
				mainMenuPanel.setVisible(true) ;
			}
		}) ;
		createAccountPanel.getCreateAccountButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Create Account Pressed") ;
				System.out.println(createAccountPanel.getAccountName() + createAccountPanel.getAccountPin() + createAccountPanel.getConfirmPin()) ; 
				try {
					BankAccount.CREATE_ACCOUNT(0, createAccountPanel.getAccountName(), createAccountPanel.getAccountPin(), createAccountPanel.getConfirmPin(), 0) ;
				} catch (Exception e) {
					e.printStackTrace() ;
				}
			}
		}) ;
	}
	
}