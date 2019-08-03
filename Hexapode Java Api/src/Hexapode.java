import java.io.IOException;

import com.pi4j.io.gpio.exception.UnsupportedBoardType;
import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataEventListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPort;
import com.pi4j.io.serial.StopBits;
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

	public static void start(int homestart) {
		serial.addListener(new SerialDataEventListener() {
			@Override
			public void dataReceived(SerialDataEvent event) {
				try {
					console.println("[HEX DATA]   " + event.getHexByteString());
					console.println("[ASCII DATA] " + event.getAsciiString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		SerialConfig config = new SerialConfig();
		try {
			config.device(SerialPort.getDefaultPort()).baud(Baud._9600).dataBits(DataBits._8).parity(Parity.NONE)
					.stopBits(StopBits._1).flowControl(FlowControl.NONE);
			serial.open(config);
		} catch (UnsupportedBoardType | IOException | InterruptedException e) {
			e.printStackTrace();
		}
		if (homestart == 1) {
			home();
		}
	}

	public static void println(String msg) {
		console.println(msg);
	}

	public static void home() {
		console.println(serial);
		
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
			Serial.write("#5P1500S750<cr>");

		console.println(home);
//			serial.write(("#29 P1600 #30 P750 T2500 <cr>"));
//			serial.write(home);
//			serial.flush();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

	}

}
