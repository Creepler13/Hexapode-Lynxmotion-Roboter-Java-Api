package api.basic;

/**
 * Contains constants which can be added up to make it easier to control the
 * servos
 * <p>
 * Add one of each kind to get the pin number of the corresponding servo,
 * e.g.:<br>
 * <code>BACK + LEFT + KNEE</code> is the pin of the back left knee. You can use
 * these sums with all methods that require a servo pin id.
 * </p>
 * 
 * @author JustAnotherJavaProgrammer
 * @see test.Example1
 */
public class PINConstants {
	public static final int FRONT = 100;
	public static final int MIDDLE = 200;
	public static final int BACK = 300;

	public static final int LEFT = 0;
	public static final int RIGHT = 10;

	public static final int HIP = 0;
	public static final int KNEE = 1;
	public static final int FOOT = 2;
}
