//============================================================================//
// Name        : MainMenu.java                                                //
// Author      : Joshua Ciffer                                                //
// Date        : 11/22/2017                                                   //
//============================================================================//

package src.bank.gui ;
import javax.swing.JPanel ;
import javax.swing.JLabel ;
import javax.swing.JButton ;
import javax.swing.SwingConstants ;
import java.awt.Font ;
import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent ;
import javax.swing.JFrame ;

final public class MainMenu extends JPanel {
	
	//public JFrame testFrame = new JFrame() ;
	private JLabel mainMenuLabel ;
	private JButton loginButton, createAccountButton, exitButton ;
	
	public static void main(String[] args) {
		new MainMenu() ;
	}
	
 	public MainMenu() {
 		super(null) ;
 		this.componentSetup() ;
 		//this.testFrameSetup() ;
 	}
	
	private void componentSetup() {
		mainMenuLabel = new JLabel("ATM Main Menu") ;
		mainMenuLabel.setFont(new Font("Dialog", Font.BOLD, 14)) ;
 		mainMenuLabel.setHorizontalAlignment(SwingConstants.CENTER) ;
 		mainMenuLabel.setBounds(150, 10, 200, 50) ;
		loginButton = new JButton("Login") ;
		loginButton.setBounds(150, 85, 200, 50) ;
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Login Pressed") ;
				//setVisible(false) ;
				add(new Login()).setVisible(true) ;
				
			}
		}) ;
		createAccountButton = new JButton("Create Account") ;
		createAccountButton.setBounds(150, 180, 200, 50) ;
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Create Account Pressed") ;
				new CreateAccount() ;
			}
		}) ;
		exitButton = new JButton("Exit") ;
		exitButton.setBounds(150, 280, 200, 50) ;
 		exitButton.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent a) {
 				System.out.println("Exit Pressed") ;
 				System.exit(0) ;
 			}
 		});
 		this.add(mainMenuLabel) ;
 		this.add(loginButton) ;
 		this.add(createAccountButton) ;
 		this.add(exitButton) ;
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
}	// End MainMenu Class