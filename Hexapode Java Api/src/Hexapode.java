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
	public static String MapName;
	public static float hexaX = 0; 
	public static float hexaY = 0;
	public static float hexaRotation = 0; 
	
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
			home();
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
	
	public static void debug(String MapName) {
		Hexapode.MapName = MapName;
		 debugthread dbThread = new debugthread();
		  dbThread.start();
	}

	public static void home() {
		try {
			serial.write(
					"#29 P1350 #30 P1650 #31 P1500 #25 P1350 #26 P1650 #27 P1500 #21 P1350 #22 P1650 #23 P1500 #13 P1350 #14 P1650 #15 P1500 #9 P1350 #10 P1650 #11 P1500 #5 P1350 #6 P1650 #7 P1500 T2500 <cr>");
			serial.write((byte) 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
