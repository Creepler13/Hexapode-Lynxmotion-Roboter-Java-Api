package api.basic;

/**
 * This class contains the pin-mapping for all servos of the hexapod
 * <p>
 * The mapping is as follows: <br>
 * 
 * <pre>
 * <code>
 * <b>First digit:</b>
 * Front: 1
 * Middle: 2
 * Back: 3
 * 
 * <b>Second digit:</b>
 * Left: 0
 * Right: 1
 * 
 * <b>Third digit:</b>
 * "Hip" (leg rotation): 0
 * "Knee" (Middle servo): 1
 * "Foot" (lower servo): 2
 * </code>
 * </pre>
 * 
 * <p>
 * <b>Example</b> <br>
 * 
 * <pre>
 * <code>Middle right knee = 211</code>
 * </pre>
 * 
 * @author JustAnotherJavaProgrammer &amp; Creepler13
 */
public class PINConfig {

	/**
	 * Initialize the {@link api.basic.Hexapode#PIN_MAPPING PIN_MAPPING-array} in
	 * {@linkplain api.basic.Hexapode Hexapode.java}
	 * 
	 * <br>
	 * This method is called by the constructor so it does not need to be called
	 * manually
	 */
	protected static void initPINConfig() {
		// Front left hip
		Hexapode.PIN_MAPPING[0][0] = 29;
		Hexapode.PIN_MAPPING[0][1] = 100;
		Hexapode.PIN_MAPPING[0][2] = 625;
		Hexapode.PIN_MAPPING[0][3] = 1500 / (2450 - 625);
		// Front left knee
		Hexapode.PIN_MAPPING[1][0] = 30;
		Hexapode.PIN_MAPPING[1][1] = 101;
		Hexapode.PIN_MAPPING[1][2] = 1000;
		Hexapode.PIN_MAPPING[1][3] = 1500 / (2450 - 1000);
		// Front left foot
		Hexapode.PIN_MAPPING[2][0] = 31;
		Hexapode.PIN_MAPPING[2][1] = 102;
		Hexapode.PIN_MAPPING[2][2] = 525;
		Hexapode.PIN_MAPPING[2][3] = 1500 / (2450 - 525);

		// Front right hip
		Hexapode.PIN_MAPPING[3][0] = 13;
		Hexapode.PIN_MAPPING[3][1] = 110;
		Hexapode.PIN_MAPPING[3][2] = 625;
		Hexapode.PIN_MAPPING[3][3] = 1500 / (2450 - 625);
		// Front right knee
		Hexapode.PIN_MAPPING[4][0] = 14;
		Hexapode.PIN_MAPPING[4][1] = 111;
		Hexapode.PIN_MAPPING[4][2] = 1000;
		Hexapode.PIN_MAPPING[4][3] = 1500 / (2450 - 1000);
		// Front right foot
		Hexapode.PIN_MAPPING[5][0] = 15;
		Hexapode.PIN_MAPPING[5][1] = 112;
		Hexapode.PIN_MAPPING[5][2] = 525;
		Hexapode.PIN_MAPPING[5][3] = 1500 / (2450 - 525);

		// Middle left hip
		Hexapode.PIN_MAPPING[6][0] = 25;
		Hexapode.PIN_MAPPING[6][1] = 200;
		Hexapode.PIN_MAPPING[6][2] = 625;
		Hexapode.PIN_MAPPING[6][3] = 1500 / (2450 - 625);
		// Middle left knee
		Hexapode.PIN_MAPPING[7][0] = 26;
		Hexapode.PIN_MAPPING[7][1] = 201;
		Hexapode.PIN_MAPPING[7][2] = 1000;
		Hexapode.PIN_MAPPING[7][3] = 1500 / (2450 - 1000);
		// Middle left foot
		Hexapode.PIN_MAPPING[8][0] = 27;
		Hexapode.PIN_MAPPING[8][1] = 202;
		Hexapode.PIN_MAPPING[8][2] = 525;
		Hexapode.PIN_MAPPING[8][3] = 1500 / (2450 - 525);

		// Middle right hip
		Hexapode.PIN_MAPPING[9][0] = 9;
		Hexapode.PIN_MAPPING[9][1] = 210;
		Hexapode.PIN_MAPPING[9][2] = 625;
		Hexapode.PIN_MAPPING[9][3] = 1500 / (2450 - 625);
		// Middle right knee
		Hexapode.PIN_MAPPING[10][0] = 10;
		Hexapode.PIN_MAPPING[10][1] = 211;
		Hexapode.PIN_MAPPING[10][2] = 1000;
		Hexapode.PIN_MAPPING[10][3] = 1500 / (2450 - 1000);
		// Middle right foot
		Hexapode.PIN_MAPPING[11][0] = 11;
		Hexapode.PIN_MAPPING[11][1] = 212;
		Hexapode.PIN_MAPPING[11][2] = 525;
		Hexapode.PIN_MAPPING[11][3] = 1500 / (2450 - 525);

		// Back left hip
		Hexapode.PIN_MAPPING[12][0] = 21;
		Hexapode.PIN_MAPPING[12][1] = 300;
		Hexapode.PIN_MAPPING[12][2] = 625;
		Hexapode.PIN_MAPPING[12][3] = 1500 / (2450 - 625);
		// Back left knee
		Hexapode.PIN_MAPPING[13][0] = 22;
		Hexapode.PIN_MAPPING[13][1] = 301;
		Hexapode.PIN_MAPPING[13][2] = 1000;
		Hexapode.PIN_MAPPING[13][3] = 1500 / (2450 - 1000);
		// Back left foot
		Hexapode.PIN_MAPPING[14][0] = 23;
		Hexapode.PIN_MAPPING[14][1] = 302;
		Hexapode.PIN_MAPPING[14][2] = 525;
		Hexapode.PIN_MAPPING[14][3] = 1500 / (2450 - 525);

		// Back right hip
		Hexapode.PIN_MAPPING[15][0] = 5;
		Hexapode.PIN_MAPPING[15][1] = 310;
		Hexapode.PIN_MAPPING[15][2] = 625;
		Hexapode.PIN_MAPPING[15][3] = 1500 / (2450 - 625);
		// Back right knee
		Hexapode.PIN_MAPPING[16][0] = 6;
		Hexapode.PIN_MAPPING[16][1] = 311;
		Hexapode.PIN_MAPPING[16][2] = 1000;
		Hexapode.PIN_MAPPING[16][3] = 1500 / (2450 - 1000);
		// Back right foot
		Hexapode.PIN_MAPPING[17][0] = 7;
		Hexapode.PIN_MAPPING[17][1] = 312;
		Hexapode.PIN_MAPPING[17][2] = 525;
		Hexapode.PIN_MAPPING[17][3] = 1500 / (2450 - 525);

	}

}
