package test;

import api.advanced.Bundle;
import api.advanced.BundleCreator;
import api.basic.PINConstants;

/**
 * Showcasing {@link api.advanced.BundleCreator BundleCreator}
 * 
 * @author JustAnotherJavaProgrammer &amp; Creepler13
 * @see api.advanced.BundleCreator
 */
public class Example3 {

	public static void main(String[] args) {
		// Bundles are very useful to save combinations of commands and execute them
		// simultaneously. But it quickly becomes tedious to add every instruction
		// individually to the bundle.
		// This is why there is a convenience class called BundleCreator to create
		// bundles which are already initialised to handle the most common use cases of
		// Bundles.

		// Create a Bundle which moves servos of the front left leg simultaneously for
		// 0.5 (500 milliseconds) seconds and save it in the variable moveLeg
		// Hip position: 1000
		// Knee position: 1500
		// Foot position: 0
		Bundle moveLeg = BundleCreator.moveLeg(PINConstants.FRONT + PINConstants.LEFT, 1000, 1500, 0, 500);

		// Execute the Bundle
		moveLeg.exec();

		// Wait until the leg has finished moving plus 0.5 seconds
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Create a Bundle which moves all legs to the same position for 2.5 seconds
		// (2500 milliseconds) and save it in the variable moveAll
		// Hip position of all legs: 45
		// Knee position of all legs: 750
		// Foot position of all legs: 1430
		Bundle moveAll = BundleCreator.moveAllLegs(45, 750, 1430, 2500);

		// Execute the Bundle
		moveAll.execBlocking();

		// Execute the first Bundle again and move the front left leg back into position
		moveLeg.exec();
	}

}
