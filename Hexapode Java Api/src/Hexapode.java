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
			config.device(SerialPort.getDefaultPort()).baud(Baud._38400).dataBits(DataBits._8).parity(Parity.NONE)
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

		try {
			serial.write("#5 P1600 #10 P750 T2500 <cr>");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

	}

}
