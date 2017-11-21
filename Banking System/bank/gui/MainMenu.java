//============================================================================//
// Name        : MainMenu.java              								  //
// Author      : Joshua Ciffer												  //
// Date        : 11/15/2017													  //
//============================================================================//

package bank.gui ;
import javax.swing.JFrame ;
import java.awt.GridLayout ;
import javax.swing.JLabel ;
import javax.swing.JPanel;
import javax.swing.JButton ;
import javax.swing.SwingConstants ;
import bank.account.BankAccount ;
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;

final public class MainMenu extends JPanel {
	
	private GridLayout mainMenuLayout ;
	private JLabel mainMenuLabel ;
	private JButton loginButton, createAccountButton, exitButton ;
	
	 
	public static void main(String[] args) {
		try {
			BankAccount.CREATE_ACCOUNT(0, "Josh", "1234", "1234", 523) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		BankAccount.LIST_ACCOUNTS() ;
		new MainMenu() ;
	}
	
 	public MainMenu() {
		frameSetup() ;
		layoutSetup() ;
		componentSetup() ;
		
	}
	
	private void frameSetup() {
		setVisible(true) ;
		setTitle("Bank Main Menu") ;
		setSize(500, 500) ;
		setResizable(false) ;
		setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		setLayout(mainMenuLayout = new GridLayout()) ;
	}
	
	private void layoutSetup() {
		mainMenuLayout.setColumns(1) ;
		mainMenuLayout.setRows(4) ;
		//layout.setHgap(400) ;
		mainMenuLayout.setVgap(50) ;
	}
	
	private void componentSetup() {
		mainMenuLabel = new JLabel("Main Menu") ;
		loginButton = new JButton("Login") ;
		createAccountButton = new JButton("Create Account") ;
		exitButton = new JButton("Exit") ;
		mainMenuLabel.setHorizontalAlignment(SwingConstants.CENTER) ;
		
		
		add(mainMenuLabel) ;
		add(loginButton) ;
		add(createAccountButton) ;
		add(exitButton) ;
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login() ;
				System.out.println("Login Pressed") ;
			}
		}) ;
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create Account Pressed") ;
			}
		}) ;
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit Pressed") ;
				System.exit(0) ;
			}
		}) ;
	}
	
}