package api.advanced;

import static api.basic.PINConstants.FOOT;
import static api.basic.PINConstants.HIP;
import static api.basic.PINConstants.KNEE;

import api.basic.Hexapode;

/**
 * A convenience class to create {@linkplain Bundle Bundles} for the most common
 * use cases
 * 
 * @author JustAnotherJavaProgrammer
 * @see Bundle
 */
public class BundleCreator {

	/**
	 * Create a bundle which moves all joints of a leg in parallel
	 * 
	 * @param leg  The id of the leg (e.g. 310 for the back right leg; it is the sum
	 *             of PINConstants.BACK and PINConstants.RIGHT)
	 * @param hip  The value, the leg will rotate to
	 * @param knee The value, the knee will move to
	 * @param foot The value, the foot will move to
	 * @param time The default execution time of the generated Bundle
	 * @return The bundle initialized with the commands to move the leg in the
	 *         desired way
	 */
	public static Bundle moveLeg(int leg, int hip, int knee, int foot, int time) {
		Bundle bundle = new Bundle(time);
		bundle.add(leg + HIP, hip);
		bundle.add(leg + KNEE, knee);
		bundle.add(leg + FOOT, foot);
		return bundle;
	}

	/**
	 * Create a bundle which moves all joints of all legs in parallel
	 * 
	 * @param hip  The value, the legs will rotate to
	 * @param knee The value, the knees will move to
	 * @param foot The value, the feet will move to
	 * @param time The default execution time of the generated Bundle
	 * @return The bundle initialized with the commands to move the legs in the
	 *         desired way
	 */
	public static Bundle moveAllLegs(int hip, int knee, int foot, int time) {
		Bundle bundle = new Bundle(time);
		for (int i = 0; i < Hexapode.PIN_MAPPING.length; i++) {
			switch (i % 3) {
			case 0:
				bundle.add(Hexapode.PIN_MAPPING[i][1], hip);
				break;
			case 1:
				bundle.add(Hexapode.PIN_MAPPING[i][1], knee);
				break;
			default:
				bundle.add(Hexapode.PIN_MAPPING[i][1], foot);
			}
		}
		return bundle;
	}
}
