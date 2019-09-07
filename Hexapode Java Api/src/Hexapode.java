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

	public static void start() {
		try {
			SerialConfig config = new SerialConfig();
			config.device(SerialPort.getDefaultPort()).baud(Baud._9600).dataBits(DataBits._8).parity(Parity.NONE)
					.stopBits(StopBits._1).flowControl(FlowControl.NONE);
			serial.open(config);
			serial.addListener(new SerialDataEventListener() {
				@Override
				public void dataReceived(SerialDataEvent event) {
				}
			});
		} catch (UnsupportedBoardType | InterruptedException | IOException e) {
			e.printStackTrace();
		}

	}

	public static void moveMotor(int servo, int state, int time) {
		try {
			serial.write("#" + servo + " P" + state + " T" + time + " <cr>");
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void println(String msg) {
		console.println(msg);
	}
}
