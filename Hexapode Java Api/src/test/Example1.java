package test;

import api.basic.Hexapode;
// import api.basic.PINConstants;

// Import the constants from PINConstants to use them without "PINConstants."
import static api.basic.PINConstants.*;

/**
 * Move a leg
 * 
 * @author JustAnotherJavaProgrammer
 */
public class Example1 {

	public static void main(String[] args) {
		// Rotate the front left leg for 1 second to position 1500
		Hexapode.getInstance().moveServo(100, 1500, 1000);

		// For beginners: It might be hard to remember which pin meant which leg.
		// Luckily, there is a system behind the numbers which is explained in the
		// Javadoc for PINConfig
		// And if you just want to quickly create a working instruction, you can also
		// use PINConstants to generate the correct PIN number:
		Hexapode.getInstance().moveServo(FRONT + RIGHT + FOOT, 1500, 2000);
		// As you might have guessed, this moves the "foot" (lowest joint of the leg) of
		// the front right leg to position 1500 for 2 seconds (1000 milliseconds = 1
		// second).
		// Add the following line at the top of your file (before "class") to use them
		// without PINConstants. (also present in this file because programmers are as
		// lazy as physicists ;) ):
		// import static api.basic.PINConstants.*;
		// If you didn't add this line, the command above would look like this
		// (uncomment the next two lines and comment the previous command to see it in
		// action, also uncomment the import statement for PINConstants in line 4):
		// Hexapode.getInstance().moveServo(PINConstants.FRONT + PINConstants.RIGHT +
		// PINConstants.FOOT, 1500, 2000);
	}

}
