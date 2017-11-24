//============================================================================//
// Name        : Login.java                                                   //
// Author      : Joshua Ciffer                                                //
// Date        : 11/22/2017                                                   //
//============================================================================//

package src.bank.gui ; 
import javax.swing.JFrame ;
import javax.swing.JLabel ;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton ;
import javax.swing.JTextField ;
import javax.swing.JPasswordField ;
import javax.swing.SwingConstants;

final class Login extends JPanel {

	//DEBUG FRAME
	//public JFrame testFrame = new JFrame() ;
	private JLabel loginLabel, accountNumberLabel, accountPinLabel ;
	private JTextField accountNumberField ;
	private JPasswordField accountPinField ;
	private JButton backButton, loginButton ;
	private String accountPin ;
	private int accountNumber ;
	
	public static void main(String[] args) {
		new Login() ;
	}
	
	public Login() {
		super(null) ;
		this.componentSetup() ;
		//this.testFrameSetup() ;
	}
	
	private void componentSetup() {
		loginLabel = new JLabel("Login") ;
		loginLabel.setBounds(150, 10, 200, 50) ;
		loginLabel.setFont(new Font("Dialog", Font.BOLD, 14)) ;
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER) ;
		
		accountNumberLabel = new JLabel("Account Number:") ;
		accountNumberLabel.setSize(200, 50) ;
		accountNumberLabel.setLocation(75, 85) ;
		accountNumberLabel.setFont(new Font("Dialog", Font.BOLD, 14)) ;
		
		accountPinLabel = new JLabel("Account Pin:") ;
		accountPinLabel.setSize(200, 50) ;
		accountPinLabel.setLocation(75, 185) ;
		accountPinLabel.setFont(new Font("Dialog", Font.BOLD, 14)) ;
		
		accountNumberField = new JTextField() ;
		accountNumberField.setBounds(225, 85, 200, 50) ;
		accountNumberField.setFont(new Font("Dialog", Font.BOLD, 14)) ;
		
		accountPinField = new JPasswordField() ;
		accountPinField.setBounds(225, 185, 200, 50) ;
		accountPinField.setFont(new Font("Dialog", Font.BOLD, 14)) ;
		
		backButton = new JButton("Back") ;
		backButton.setBounds(75, 285, 150, 50) ;
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Back Pressed") ;
			}
		}) ;
		loginButton = new JButton("Login") ;
		loginButton.setBounds(275, 285, 150, 50) ;
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Login Pressed") ;
				try {
					accountNumber = Integer.parseInt(accountNumberField.getText()) ;
				} catch (NumberFormatException e) {
					
				}
				accountPin = String.valueOf(accountPinField.getPassword()) ;
				System.out.println("Account Number: " + accountNumber + "\nAccount Pin: " + accountPin) ;
			}
		}) ;
		this.add(loginLabel) ;
		this.add(accountNumberLabel) ;
		this.add(accountNumberField) ;
		this.add(accountPinLabel) ;
		this.add(accountPinField) ;
		this.add(backButton) ;
		this.add(loginButton) ;
	}
	
	// DEBUG USE
	//public void testFrameSetup() {
	//	testFrame = new JFrame() ;
	//	testFrame.setVisible(true) ;
	//	testFrame.setTitle("ATM") ;
	//	testFrame.setSize(500, 500) ;
	//	testFrame.setResizable(false) ;
	//	testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
	//	testFrame.getContentPane().add(this) ;
	//}
}