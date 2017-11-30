//============================================================================//
// Name        : GUI.java                                                     //
// Author      : Andrew Ciffer                                                //
// Date        : 11/21/2017                                                   //
//============================================================================//

package src.atm.gui ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame ;

public final class GUI extends JFrame {

	private MainMenu mainMenuPanel ;
	private Login loginPanel ;
	private CreateAccount createAccountPanel ; 
	
	public static void main(String[] args) {
		new GUI() ;
	}

	public GUI() {
		this.frameSetup() ;
		mainMenuPanel = new MainMenu() ;
		loginPanel = new Login() ;
		createAccountPanel = new CreateAccount() ;
		//login = new Login() ;
		//createAccount = new CreateAccount() ;
		this.add(mainMenuPanel) ;
		mainMenuPanel.setVisible(false) ;
		this.add(loginPanel) ;
		mainMenuPanel.getLoginButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Login Pressed") ;
				mainMenuPanel.setVisible(false) ;
				add(loginPanel) ;
				loginPanel.setVisible(true);
				
			}
		}) ;
		//this.add(createAccountPanel) ;
	}
	
	private void frameSetup() {
		this.setVisible(true) ;
		this.setTitle("ATM") ;
		this.setSize(500, 500) ;
		this.setResizable(false) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		this.setLocationRelativeTo(null) ;
		//this.setLayout(null) ;
	}
	
}