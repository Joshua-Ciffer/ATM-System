//============================================================================//
// Name        : GUI.java                                                     //
// Author      : Andrew Ciffer                                                //
// Date        : 11/21/2017                                                   //
//============================================================================//

package bank.gui ;
import javax.swing.JFrame ;

public final class GUI extends JFrame {

	public static void main(String[] args) {
		new GUI() ;
	}

	public GUI() {
		frameSetup() ;
		
	}
	
	private void frameSetup() {
		setVisible(true) ;
		setTitle("ATM") ;
		setSize(500, 500) ;
		setResizable(false) ;
		setDefaultCloseOperation(EXIT_ON_CLOSE) ;
	}
	
}