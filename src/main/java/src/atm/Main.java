package src.atm;

import src.atm.gui.GUI;

import static src.atm.util.Constants.INVALID_COMMAND_ARGS_CODE;

/**
 * This class contains the main entry point for the ATM System program.
 * 
 * @author Joshua Ciffer
 * @version 04/09/2018
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