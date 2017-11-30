//============================================================================//
// Name        : MainMenu.java                                                //
// Author      : Joshua Ciffer                                                //
// Date        : 11/30/2017                                                   //
//============================================================================//

package src.bank.gui ;
import javax.swing.JPanel ;
import javax.swing.JLabel ;
import javax.swing.JButton ;
import javax.swing.SwingConstants ;
import java.awt.Font ;

public final class MainMenu extends JPanel {
	
	private JLabel mainMenuLabel ;
	private JButton loginButton, createAccountButton, exitButton ;
	
 	public MainMenu() {
 		super(null) ;
 		this.mainMenuLabel = new JLabel("ATM Main Menu") ;
		this.mainMenuLabel.setFont(new Font("Dialog", Font.BOLD, 14)) ;
 		this.mainMenuLabel.setHorizontalAlignment(SwingConstants.CENTER) ;
 		this.mainMenuLabel.setBounds(150, 10, 200, 50) ;
		this.loginButton = new JButton("Login") ;
		this.loginButton.setBounds(150, 85, 200, 50) ;
		this.createAccountButton = new JButton("Create Account") ;
		this.createAccountButton.setBounds(150, 180, 200, 50) ;
		this.exitButton = new JButton("Exit") ;
		this.exitButton.setBounds(150, 280, 200, 50) ;
 		this.add(this.mainMenuLabel) ;
 		this.add(this.loginButton) ;
 		this.add(this.createAccountButton) ;
 		this.add(this.exitButton) ;
 	}
	
 	public JLabel getMainMenuLabel() {
 		return this.mainMenuLabel ;
 	}
 	
 	public JButton getLoginButton() {
 		return this.loginButton ;
 	}
 	
 	public JButton getCreateAccountButton() {
 		return this.createAccountButton ;
 	}
 	
 	public JButton getExitButton() {
 		return this.exitButton ;
 	}
 	
}