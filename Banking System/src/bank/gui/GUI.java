//============================================================================//
// Name        : GUI.java                                                     //
// Author      : Andrewg Ciffer                                                //
// Date        : 11/21/2017                                                   //
//============================================================================//

package src.bank.gui ;
import javax.swing.JFrame ;
import javax.swing.JPanel;

public final class GUI extends JFrame {
	
	private JPanel mainMenu, login, createAccount ;
	
	public static void main(String[] args) {
		new GUI() ;
	}

	public GUI() {
		frameSetup() ;
		this.add(new MainMenu()) ;
		
	}
	
	private void frameSetup() {
		this.setVisible(true) ;
		this.setTitle("ATM") ;
		this.setSize(500, 500) ;
		this.setResizable(false) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		this.setLocationRelativeTo(null) ;
	}
	
}