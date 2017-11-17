//============================================================================//
// Name        : MainMenu.java              								  //
// Author      : Joshua Ciffer												  //
// Date        : 11/15/2017													  //
//============================================================================//

package bank.gui ;
import javax.swing.JFrame ;
import java.awt.GridLayout ;
import javax.swing.JLabel ;
import javax.swing.JButton ;
import javax.swing.SwingConstants ;
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;

public class MainMenu extends JFrame {
	
	private GridLayout mainMenuLayout ;
	private JLabel mainMenuLabel ;
	private JButton loginButton, createAccountButton, exitButton ;
	
	 
	public static void main(String[] args) {
		new MainMenu() ;
	}
	
 	public MainMenu() {
		menuFrameSetup() ;
		layoutSetup() ;
		componentSetup() ;
		
	}
	
	private void menuFrameSetup() {
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