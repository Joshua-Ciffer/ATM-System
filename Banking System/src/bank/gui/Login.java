//============================================================================//
// Name        : Login.java                                                   //
// Author      : Joshua Ciffer                                                //
// Date        : 11/21/2017                                                   //
//============================================================================//

package src.bank.gui ; 
import javax.swing.JFrame ;
import java.awt.GridLayout ;
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;
import javax.swing.JLabel ;
import javax.swing.JPanel;
import javax.swing.JButton ;
import javax.swing.JTextField ;
import javax.swing.JPasswordField ;

final class Login extends JPanel {

	//DEBUG FRAME
	public JFrame testFrame = new JFrame() ;
	private JLabel loginLabel ;
	private JTextField accountNumberField ;
	private JPasswordField accountPinField ;
	private JButton backButton, loginButton ;
	
	public static void main(String[] args) {
		new Login() ;
	}
	
	public Login() {
		super(null) ;
		this.componentSetup() ;
		this.testFrameSetup() ;
	}
	
	private void componentSetup() {
		this.loginLabel = new JLabel("Login") ;
		this.accountNumberField = new JTextField("Account Number") ;
		this.accountPinField = new JPasswordField("Accoutn Pin") ;
		this.backButton = new JButton("Back") ;
		this.loginButton = new JButton("Login") ;
		
		
		
		
		
		this.add(loginLabel) ;
		this.add(accountNumberField) ;
		this.add(accountPinField) ;
		this.add(backButton) ;
		this.add(loginButton) ;
	}
	
	// DEBUG USE
	public void testFrameSetup() {
		testFrame = new JFrame() ;
		testFrame.setVisible(true) ;
		testFrame.setTitle("ATM") ;
		testFrame.setSize(500, 500) ;
		testFrame.setResizable(false) ;
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		testFrame.getContentPane().add(this) ;
	}
	
}