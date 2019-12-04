package api.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import api.advanced.BundleCreator;

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

	private static boolean pinsInitialized = false;

	/**
	 * Initialize the {@link api.basic.Hexapode#PIN_MAPPING PIN_MAPPING-array} in
	 * {@linkplain api.basic.Hexapode Hexapode.java}
	 * 
	 * <br>
	 * This method is called by the constructor so it does not need to be called
	 * manually
	 */
	public static void initPINConfig() {
		if (pinsInitialized)
			return;
		pinsInitialized = true;
		// Initializing default values

		// Front left hip
		Hexapode.PIN_MAPPING[0][0] = 29;
		Hexapode.PIN_MAPPING[0][1] = 100;
		Hexapode.PIN_MAPPING[0][2] = 625;
		Hexapode.POS_MULTIP[0] = 1500.0 / (2450.0 - 625);
		// Front left knee
		Hexapode.PIN_MAPPING[1][0] = 30;
		Hexapode.PIN_MAPPING[1][1] = 101;
		Hexapode.PIN_MAPPING[1][2] = 1000;
		Hexapode.POS_MULTIP[1] = 1500.0 / (2450.0 - 1000);
		// Front left foot
		Hexapode.PIN_MAPPING[2][0] = 31;
		Hexapode.PIN_MAPPING[2][1] = 102;
		Hexapode.PIN_MAPPING[2][2] = 525;
		Hexapode.POS_MULTIP[2] = 1500.0 / (2450.0 - 525);

		// Front right hip
		Hexapode.PIN_MAPPING[3][0] = 13;
		Hexapode.PIN_MAPPING[3][1] = 110;
		Hexapode.PIN_MAPPING[3][2] = 620;
		Hexapode.POS_MULTIP[3] = 1500.0 / (2470.0 - 620);
		// Front right knee
		Hexapode.PIN_MAPPING[4][0] = 14;
		Hexapode.PIN_MAPPING[4][1] = 111;
		Hexapode.PIN_MAPPING[4][2] = 2450;
		Hexapode.POS_MULTIP[4] = 1500.0 / (1000 - 2450.0);
		// Front right foot
		Hexapode.PIN_MAPPING[5][0] = 15;
		Hexapode.PIN_MAPPING[5][1] = 112;
		Hexapode.PIN_MAPPING[5][2] = 2450;
		Hexapode.POS_MULTIP[5] = 1500.0 / (525 - 2450.0);

		// Middle left hip
		Hexapode.PIN_MAPPING[6][0] = 25;
		Hexapode.PIN_MAPPING[6][1] = 200;
		Hexapode.PIN_MAPPING[6][2] = 625;
		Hexapode.POS_MULTIP[6] = 1500.0 / (2450.0 - 625);
		// Middle left knee
		Hexapode.PIN_MAPPING[7][0] = 26;
		Hexapode.PIN_MAPPING[7][1] = 201;
		Hexapode.PIN_MAPPING[7][2] = 1000;
		Hexapode.POS_MULTIP[7] = 1500.0 / (2450.0 - 1000);
		// Middle left foot
		Hexapode.PIN_MAPPING[8][0] = 27;
		Hexapode.PIN_MAPPING[8][1] = 202;
		Hexapode.PIN_MAPPING[8][2] = 525;
		Hexapode.POS_MULTIP[8] = 1500.0 / (2450.0 - 525);

		// Middle right hip
		Hexapode.PIN_MAPPING[9][0] = 9;
		Hexapode.PIN_MAPPING[9][1] = 210;
		Hexapode.PIN_MAPPING[9][2] = 625;
		Hexapode.POS_MULTIP[9] = 1500.0 / (2450.0 - 625);
		// Middle right knee
		Hexapode.PIN_MAPPING[10][0] = 10;
		Hexapode.PIN_MAPPING[10][1] = 211;
		Hexapode.PIN_MAPPING[10][2] = 2450;
		Hexapode.POS_MULTIP[10] = 1500.0 / (1000 - 2450.0);
		// Middle right foot
		Hexapode.PIN_MAPPING[11][0] = 11;
		Hexapode.PIN_MAPPING[11][1] = 212;
		Hexapode.PIN_MAPPING[11][2] = 2450;
		Hexapode.POS_MULTIP[11] = 1500.0 / (525 - 2450.0);

		// Back left hip
		Hexapode.PIN_MAPPING[12][0] = 21;
		Hexapode.PIN_MAPPING[12][1] = 300;
		Hexapode.PIN_MAPPING[12][2] = 625;
		Hexapode.POS_MULTIP[12] = 1500.0 / (2450.0 - 625);
		// Back left knee
		Hexapode.PIN_MAPPING[13][0] = 22;
		Hexapode.PIN_MAPPING[13][1] = 301;
		Hexapode.PIN_MAPPING[13][2] = 1000;
		Hexapode.POS_MULTIP[13] = 1500.0 / (2450.0 - 1000);
		// Back left foot
		Hexapode.PIN_MAPPING[14][0] = 23;
		Hexapode.PIN_MAPPING[14][1] = 302;
		Hexapode.PIN_MAPPING[14][2] = 525;
		Hexapode.POS_MULTIP[14] = 1500.0 / (2450.0 - 525);

		// Back right hip
		Hexapode.PIN_MAPPING[15][0] = 5;
		Hexapode.PIN_MAPPING[15][1] = 310;
		Hexapode.PIN_MAPPING[15][2] = 625;
		Hexapode.POS_MULTIP[15] = 1500.0 / (2450.0 - 625);
		// Back right knee
		Hexapode.PIN_MAPPING[16][0] = 6;
		Hexapode.PIN_MAPPING[16][1] = 311;
		Hexapode.PIN_MAPPING[16][2] = 2450;
		Hexapode.POS_MULTIP[16] = 1500.0 / (1000 - 2450.0);
		// Back right foot
		Hexapode.PIN_MAPPING[17][0] = 7;
		Hexapode.PIN_MAPPING[17][1] = 312;
		Hexapode.PIN_MAPPING[17][2] = 2450;
		Hexapode.POS_MULTIP[17] = 1500.0 / (525 - 2450.0);

		if (!Hexapode.getInstance().isClient())
			// Overwriting defaults with values from the config file
			loadPositionMap();
	}

	private static final String positionMapLocation = "pinMap.conf";

	/**
	 * Load the position map from a configuration file or ask if a wizard should be
	 * started to create one
	 */
	public static void loadPositionMap() {
		// new Exception().printStackTrace();
		System.out.println("Looking for a pin map...");
		if (Files.exists(Paths.get(positionMapLocation))) {
			System.out.println("Pin map found!\nLoading pin map...");
			try {
				List<String> map = Files.readAllLines(Paths.get(positionMapLocation));
				for (int i = 0; i < map.size(); i++) {
					if (i % 4 == 4) {
						Hexapode.POS_MULTIP[i / 4] = Double.parseDouble(map.get(i));
						continue;
					}
					Hexapode.PIN_MAPPING[i / 4][i % 4] = Integer.parseInt(map.get(i));
				}
				System.out.println("Pin map loaded!");
			} catch (IOException e) {
				if (Hexapode.DEBUGGING)
					e.printStackTrace();
				System.out.println("An error occurred while loading the pin map.\nUsing default values instead");
			}
		} else {
			System.out.print(
					"No pin map was found in your current directory! Would you like to continue with default values? (y/n) : ");
			try {
				BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
				if (r.readLine().equalsIgnoreCase("y")) {
					r.close();
					System.out.println("Using default values...");
					return;
				}
				while (true) {
					Console.clearScreen();
					Console.box("Hexapod calibration wizard",
							"Answer the questions below to create a configuration file",
							"(Answers in brackets are default values)");
					Console.emptyLine();
					System.out.println("--- Please enter the physical pin id corresponding to the servo ---");
					for (int i = 0; i < Hexapode.PIN_MAPPING.length; i++) {
						String fmb = "Unknown";
						switch (i / 6) {
						case 0:
							fmb = "Front";
							break;
						case 1:
							fmb = "Middle";
							break;
						case 2:
							fmb = "Back";
						}
						String hkf = "unknown";
						switch (i % 3) {
						case 0:
							hkf = "hip";
							break;
						case 1:
							hkf = "knee";
							break;
						case 2:
							hkf = "foot";
						}
						while (true) {
							System.out.print(fmb + " " + ((i / 3) % 2 == 0 ? "left" : "right") + " " + hkf + " ("
									+ Hexapode.PIN_MAPPING[i][0] + ") : ");
							String s = r.readLine();
							if (s.trim().isEmpty())
								break;
							try {
								Hexapode.PIN_MAPPING[i][0] = Integer.parseInt(s);
								break;
							} catch (NumberFormatException e) {
								System.out.println("Please enter a valid (integer) number!");
							}
						}
					}
					System.out.print("Is the data entered above correct? (y/n) : ");
					if (r.readLine().toLowerCase().equals("y"))
						break;
				}
				Console.clearScreen();
				BundleCreator.moveAllLegs(0, 0, 0, 500).exec();

				Console.box("Hexapod calibration wizard", "Calibrating the servos");
				Console.emptyLine();
				System.out.print("Are all motors at the same position? (y/n) : ");
				if (!r.readLine().toLowerCase().equals("y")) {
					System.out.println("Manual calibration required!");
				}
				savePositionMap();
			} catch (IOException e) {
				if (Hexapode.DEBUGGING)
					e.printStackTrace();
				System.out.println(
						"The programme could not read the text you entered in the command line.\nAutomatically continuing using default values and/or already entered ones...");
			}
		}
	}

	/**
	 * Save the current configuration to the configuration file<br>
	 * <b>Note:</b> The old config file is overwritten if it already existed
	 */
	public static void savePositionMap() {
		System.out.println("Saving the configuration file...");
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < Hexapode.PIN_MAPPING.length; i++) {

			for (int j = 0; j < Hexapode.PIN_MAPPING[i].length; j++) {
				strBuilder.append(Hexapode.PIN_MAPPING[i][j]);
				strBuilder.append("\n");
			}
			strBuilder.append(Hexapode.POS_MULTIP[i]);
			if (i < Hexapode.PIN_MAPPING.length - 1) {
				strBuilder.append("\n");
			}
		}
		try {
			Files.write(Paths.get(positionMapLocation), strBuilder.toString().getBytes(StandardCharsets.UTF_8),
					StandardOpenOption.SYNC, StandardOpenOption.TRUNCATE_EXISTING);
			System.out.println("Your settings have been saved!");
		} catch (IOException e) {
			if (Hexapode.DEBUGGING)
				e.printStackTrace();
			System.out.println("The operation failed!");
		}
		System.out.print("Press ENTER to continue...");
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			if (Hexapode.DEBUGGING)
				e.printStackTrace();
			System.out.println("An error occurred! Continuing automatically...");
		}
	}

}
