package test;

import api.advanced.Bundle;
import api.advanced.BundleCreator;

/**
 * Showcasing {@link api.advanced.BundleCreator BundleCreator}
 * 
 * @author JustAnotherJavaProgrammer &amp; Creepler13
 * @see api.advanced.BundleCreator
 */
public class Example3 {

	public static void main(String[] args) {

		// To create bundles faster you can use the Bundle Creator
		//
		// the first value is the pin of the leg. here: 100
		// the second value is the position the hip will move to. here: 1000
		// the third value is the position the knee will move to. here: 1500
		// the fourth value is the position the foot will move to. here: 0
		// the fifth value is the time the servos need to reach the positions. here: 500
		Bundle bundle = BundleCreator.moveLeg(100, 1000, 1500, 0, 500);
		
	}

}
