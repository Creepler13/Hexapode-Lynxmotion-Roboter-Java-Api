import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.SingleSelectionModel;

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
	private static String[] motors, defaults;
	private static String MapName;
	private static boolean debug = false;
	public static float hexaX = 50;
	public static float hexaY = 50;
	public static float hexaRotation = 0;

	public static void start(int homestart) {
		try {
			File theDir = new File("src/DebugMaps");
			File configfile = new File("src/DebugMaps/config.txt");
			if (!theDir.exists()) {
				theDir.mkdir();
				configfile.createNewFile();
			} else if (!configfile.exists()) {
				configfile.createNewFile();
			} else {
				try {
					loadconfig();
					SerialConfig config = new SerialConfig();
					config.device(SerialPort.getDefaultPort()).baud(Baud._9600).dataBits(DataBits._8)
							.parity(Parity.NONE).stopBits(StopBits._1).flowControl(FlowControl.NONE);
					serial.open(config);
				} catch (UnsupportedBoardType | InterruptedException e) {
					e.printStackTrace();
				}
				if (homestart == 1) {
					home();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		if (debug != true) {
			console.println(msg);
		}
	}

	public static void debug(String MapName) {
		debug = true;
		Hexapode.MapName = MapName;
		debugthread dbThread = new debugthread();
		dbThread.start();
	}

	public static void home() {
		try {
		
			serial.write(
					"#29 P1350 #30 P1650 #31 P1500 #25 P1350 #26 P1650 #27 P1500 #21 P1350 #22 P1650 #23 P1500 #13 P1350 #14 P1650 #15 P1500 #9 P1350 #10 P1650 #11 P1500 #5 P1350 #6 P1650 #7 P1500 T2500 <cr>");

			console.println("homed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void walk(int m) {
		if (debug != true) {

		} else {
			debugthread.movecalc(m);
		}
	}

	private static void loadconfig() {
		try {
			String def;
			String mot;
			String input;
			File file = new File("src/config.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			input = br.readLine();
			motors = input.split("/")[0].split(",");
			defaults = input.split("/")[1].split(",");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void rotate(int r) {
		if (debug != true) {

		} else {
			hexaRotation = r;
			debugthread.update();
		}
	}

	private static void configcreate() {

	}
}
