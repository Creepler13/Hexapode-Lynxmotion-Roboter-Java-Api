package api.advanced;

import static api.basic.PINConstants.BACK;
import static api.basic.PINConstants.FOOT;
import static api.basic.PINConstants.FRONT;
import static api.basic.PINConstants.HIP;
import static api.basic.PINConstants.KNEE;
import static api.basic.PINConstants.LEFT;
import static api.basic.PINConstants.MIDDLE;
import static api.basic.PINConstants.RIGHT;

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
	
	
	public static Sequence walk() {
		
		Bundle leftA = new Bundle(250);
		Bundle leftB = new Bundle(250);
		Bundle leftC = new Bundle(250);
		Bundle leftD = new Bundle(250);
		for (int i = 0; i < 3; i++) {
			int leg = 0;
			switch (i) {
			case 0:
				leg = FRONT;
				break;
			case 1:
				leg = MIDDLE;
				break;
			default:
				leg = BACK;
				break;
			}
			if (i == 1) {
				leftA.add(BundleCreator.moveLeg(leg + LEFT, 1500, 500, 1000, 250));
				leftB.add(BundleCreator.moveLeg(leg + LEFT, 1500, 750, 1575, 250));
				leftC.add(BundleCreator.moveLeg(leg + LEFT, 0, 750, 1575, 250));
				leftD.add(BundleCreator.moveLeg(leg + LEFT, 0, 500, 1000, 250));
			} else {
				leftA.add(BundleCreator.moveLeg(leg + LEFT, 0, 750, 1575, 250));
				leftB.add(BundleCreator.moveLeg(leg + LEFT, 0, 500, 1000, 250));
				leftC.add(BundleCreator.moveLeg(leg + LEFT, 1500, 500, 1000, 250));
				leftD.add(BundleCreator.moveLeg(leg + LEFT, 1500, 750, 1575, 250));
			}

		}

		Bundle rightA = new Bundle(250);
		Bundle rightB = new Bundle(250);
		Bundle rightC = new Bundle(250);
		Bundle rightD = new Bundle(250);
		for (int i = 0; i < 3; i++) {
			int leg = 0;
			switch (i) {
			case 0:
				leg = FRONT;
				break;
			case 1:
				leg = MIDDLE;
				break;
			default:
				leg = BACK;
				break;
			}
			if (i == 1) {
				rightA.add(BundleCreator.moveLeg(leg + RIGHT, 1500, 500, 1000, 250));
				rightB.add(BundleCreator.moveLeg(leg + RIGHT, 1500, 750, 1575, 250));
				rightC.add(BundleCreator.moveLeg(leg + RIGHT, 0, 750, 1575, 250));
				rightD.add(BundleCreator.moveLeg(leg + RIGHT, 0, 500, 1000, 250));
			} else {
				rightA.add(BundleCreator.moveLeg(leg + RIGHT, 0, 1000, 1575, 250));
				rightB.add(BundleCreator.moveLeg(leg + RIGHT, 0, 1500, 1000, 250));
				rightC.add(BundleCreator.moveLeg(leg + RIGHT, 1500, 1500, 1000, 250));
				rightD.add(BundleCreator.moveLeg(leg + RIGHT, 1500, 1000, 1575, 250));
			}
		}
		Collection a = new Collection();
		a.add(leftA);
		a.add(rightA);
		Collection b = new Collection();
		b.add(leftB);
		b.add(rightB);
		Collection c = new Collection();
		c.add(leftC);
		c.add(rightC);
		Collection d = new Collection();
		d.add(leftD);
		d.add(rightD);
		for (int i = 0; i < 20; i++) {
			a.execBlocking();
			b.execBlocking();
			c.execBlocking();
			d.execBlocking();
		}
		System.out.println("Done!");
		return new Sequence(new Collection[] {a,b,c,d});
		
	}
	
	
}
