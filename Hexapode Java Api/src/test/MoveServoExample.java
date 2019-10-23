package test;

import api.basic.Hexapode;

/**
 * Move a leg
 * 
 * @author JustAnotherJavaProgrammer
 */
public class MoveServoExample {

	public static void main(String[] args) {
		// Rotate the front left leg for 1 second to position 1500
		Hexapode.getInstance().moveServo(100, 1500, 1000);
	}

}
