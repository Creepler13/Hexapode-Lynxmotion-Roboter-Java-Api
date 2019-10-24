package api.advanced;

import static api.basic.PINConstants.*;

/**
 * A convenience class to create {@linkplain Bundle Bundles} for the most common
 * use cases
 * 
 * @author JustAnotherJavaProgrammer
 * @see Bundle
 */
public class BundleCreator {

	/**
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
}
