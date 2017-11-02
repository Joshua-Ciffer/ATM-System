// Joshua Ciffer 11/2/2017 //

package bank.gui;
import javax.swing.JButton;
import javax.swing.JFrame ;

public class MainMenu extends JFrame {

	private JButton button = new JButton("Test") ;
	
	public MainMenu() {
		menuFrameSetup() ;
	}
	
	private void menuFrameSetup() {
		setVisible(true) ;
		setSize(500, 500) ;
		setResizable(false) ;
		setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		add(button) ;
	}
	
	public static void main(String[] args) {
		new MainMenu() ;
	}
	
}