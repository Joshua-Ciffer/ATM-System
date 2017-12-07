
package src.atm.gui ;
import javax.swing.JPanel ;
import javax.swing.JLabel ;
import javax.swing.JTextField ;
import javax.swing.JButton ;
import javax.swing.JOptionPane ;
import javax.swing.SwingConstants ;
import java.awt.Font ;

final class Deposit extends JPanel {

	private static Font labelFont = new Font("Dialog", Font.BOLD, 14) ;
	private JLabel depositLabel, depositAmountLabel ;
	private JTextField depositAmountField ;
	private JButton backButton, depositButton ;
	private double depositAmount ;
	
	public Deposit() {
		super(null) ;
		
		depositLabel = new JLabel("Deposit") ;
		depositLabel.setFont(labelFont) ;
		depositLabel.setHorizontalAlignment(SwingConstants.CENTER) ;
		depositLabel.setVerticalAlignment(SwingConstants.CENTER) ;
		depositLabel.setBounds(150, 10, 200, 50) ;
		
		depositAmountLabel = new JLabel("Deposit Amount:") ;
		depositAmountLabel.setFont(labelFont) ;
		depositAmountLabel.setVerticalAlignment(SwingConstants.CENTER) ;
		depositAmountLabel.setBounds(75, 137, 200, 50) ;
		
		depositAmountField = new JTextField() ;
		depositAmountField.setFont(labelFont) ;
		depositAmountField.setToolTipText("Enter the amount you want to deposit to your account.") ;
		depositAmountField.setBounds(225, 138, 200, 50) ;
		
		backButton = new JButton("Back") ;
		backButton.setToolTipText("Return to the account menu.") ;
		backButton.setBounds(75, 285, 150, 50) ;
		
		depositButton = new JButton("Deposit") ;
		depositButton.setToolTipText("Deposit the desired amount into your account.") ;
		depositButton.setBounds(275, 285, 150, 50) ; 
		
		this.add(depositLabel) ;
		this.add(depositAmountLabel) ;
		this.add(depositAmountField) ;
		this.add(backButton) ;
		this.add(depositButton) ;
		
	}
	
	public JLabel getDepositLabel() {
		return this.depositLabel ;
	}
	
	public JLabel getDepositAmountLabel() {
		return this.depositAmountLabel ;
	}
	
	public JTextField getDepositAmountField() {
		return this.depositAmountField ;
	}
	
	public JButton getBackButton() {
		return this.backButton ;
	}
	
	public JButton getDepositButton() {
		return this.depositButton ;
	}
	
	public double getDepositAmount() {
		try {
			this.depositAmount = Double.parseDouble(depositAmountField.getText()) ;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE) ;
		}
		return this.depositAmount ;
	}
	
}