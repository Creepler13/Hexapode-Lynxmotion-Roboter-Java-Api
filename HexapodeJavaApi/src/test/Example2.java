package test;

import api.advanced.Bundle;

/**
 * Showcasing {@link api.advanced.Bundle Bundles}
 * 
 * @author Creepler13 &amp; JustAnotherJavaProgrammer
 * @see api.advanced.Bundle
 */
public class Example2 {

	public static void main(String[] args) {

		// Create a Bundle
		// 1000: The Bundle will run for 1000 milliseconds (1 second)
		Bundle bundle = new Bundle(1000);

		// Make the front left leg rotate to position 1500 when this bundle is executed
		bundle.add(100, 1500);
		// Also make the knee servo of the front left leg move to position 1500 when
		// this bundle is executed
		bundle.add(110, 1500);
		// Tip: You can add as many servos as you like

		// Merge two Bundles (all command will run with the Time of here:"bundle")
		Bundle bundle2 = new Bundle(2000);
		bundle.add(bundle2);

		// Execute this Bundle
		// The two commands added to this Bundle run for 1 second as defined in the
		// constructor and will not block the Thread
		bundle.exec();

		// Wait, until the leg has stopped moving (+ 0.5 seconds)
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Remove the instruction to rotate the leg
		bundle.remove(100);
		// Add an instruction to rotate the leg in the other direction instead (must be
		// done in two steps, see Javadoc for add)
		bundle.add(100, 0);

		// Do the same thing for the knee servo
		bundle.remove(110);
		bundle.add(110, 0);

		// Execute this Bundle
		// The two commands will run simultaneously for 500 milliseconds as specified
		// by the argument
		// By using execBlocking, Thread,sleep is automatically called to wait while the
		// command is being executed
		bundle.execBlocking(500);

		// Print the command which is executed by calling exec without a custom time
		// argument (the first one)
		System.out.println("bundle.getCommand():\n" + bundle.getCommand());

		// Print the command which is executed by calling exec but without the duration
		// at the end
		System.out.println("bundle.getRawCommand():\n" + bundle.getRawCommand());

		// Print the default execution time (specified in the constructor; here: 1000)
		System.out.println("bundle.getTime():\n" + bundle.getTime());
		// Tip: You can also change the default execution time by calling setTime like
		// this (uncomment to try it out):
		// bundle.setTime(2000);

		// Do the same as getCommand
		System.out.println(bundle.getTree());

		// Shorthand for System.out.println(bundle.getTree);
		bundle.printTree();

	}

}
