import java.io.IOException;

import com.pi4j.io.gpio.exception.UnsupportedBoardType;
import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPort;
import com.pi4j.io.serial.StopBits;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.util.Console;

public class Hexapode {
	/**
	 * @param args
	 */
	final static Serial serial = SerialFactory.createInstance();
	final static Console console = new Console();
	private static int[] lv = { 29, 30, 31 };// aussen = 0, mitte = 1, innen= 2 (Motoren)
	private static int[] lm = { 25, 26, 27 };// Strom == hinten
	private static int[] lh = { 21, 22, 23 };
	private static int[] rv = { 13, 14, 15 };
	private static int[] rm = { 9, 10, 11 };
	private static int[] rh = { 5, 6, 7 };
	private static Object[] all = { lv, lm, lh, rv, rm, rh };
	private static Object[] left = { lv, lm, lh };
	private static Object[] right = { rv, rm, rh };

	public static void start(int homestart, String args[]) {
		try {
			SerialConfig config = new SerialConfig();
			try {
				config.device(SerialPort.getDefaultPort()).baud(Baud._9600).dataBits(DataBits._8).parity(Parity.NONE)
						.stopBits(StopBits._1).flowControl(FlowControl.NONE);
			} catch (UnsupportedBoardType | InterruptedException e) {
				e.printStackTrace();
			}
			if (args.length > 0) {
				config = CommandArgumentParser.getSerialConfig(config, args);
			}
			serial.open(config);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (homestart == 1) {
			try {
				home();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void action(String in) {

		String[] commands = in.split(",");
		for (int j = 0; j < commands.length; j++) {

			switch (commands[j].split("/")[0]) {
			case "println":
				println(commands[j].split("/")[1].split("/")[0]);
				break;

			default:
				break;
			}

		}
	}

	public static void println(String msg) {
		console.println(msg);
	}

	public static void home() throws IOException {
		try {
			String home = "";
			for (int i = 0; i < all.length; i++) {
				int[] a = (int[]) all[i];
				for (int j = 0; j < 3; j++) {
					switch (j) {
					case 0:
						home = home + "#";
						home = home + a[0];
						home = home + " P1350 ";
						break;
					case 1:
						home = home + "#";
						home = home + a[1];
						home = home + " P1650 ";
						break;
					case 2:
						home = home + "#";
						home = home + a[2];
						home = home + " P1500 ";
						break;

					}
				}
			}

			home = home + "T2500 <cr>";
console.print(home);
			serial.write(home);
			serial.write((byte) 13);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

	}
}
