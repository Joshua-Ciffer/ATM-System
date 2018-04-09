package src.atm;

import src.atm.gui.GUI;

import static src.atm.util.Constants.INVALID_COMMAND_ARGS_CODE;

/**
 * This class contains the main entry point for the ATM System program. By default the main() method will create a GUI frame to interface with the
 * program. This class also contains methods to provide a command line implementation of the program if the command line argument "--no-gui" is
 * passed.
 * 
 * @author Joshua Ciffer
 * @version 04/08/2018
 */
public final class Main {

	/**
	 * Don't let anyone instantiate this class.
	 */
	private Main() {}

	/**
	 * Main entry point for the program.
	 * 
	 * @param args
	 *        Command line arguments.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			new GUI();
		} else {
			System.out.println("Invalid command line arguments.");
			System.exit(INVALID_COMMAND_ARGS_CODE);
		}
	}

}