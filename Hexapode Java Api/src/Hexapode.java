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

	final static Serial serial = SerialFactory.createInstance();
	final static Console console = new Console();

	public Hexapode(Baud baud) {
		try {
			SerialConfig config = new SerialConfig();
			config.device(SerialPort.getDefaultPort()).baud(baud).dataBits(DataBits._8).parity(Parity.NONE)
					.stopBits(StopBits._1).flowControl(FlowControl.NONE);
			serial.addListener(new SerialDataEventListener() {
				@Override
				public void dataReceived(SerialDataEvent event) {
				}
			});
			serial.open(config);
		} catch (UnsupportedBoardType | InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * jkjkljk.
	 * 
	 * @param servo      = Servo Controller Motor pin Nummer
	 *
	 * @param state      = gewünschte Servo position bsp: 1500
	 * 
	 * @param time       = Zeit bis der Servo state erreicht in ms
	 * 
	 * @param stopThread = true = stoppt den Main Thread für die zeit die der Servo
	 *                   bis [state] braucht([time]), false = motor bewegt sich
	 *                   thread läuft weiter.
	 * @return null
	 */

	public static void moveServo(int servo, int state, int time, boolean stopThread) {
		try {
			if (stopThread) {
				Thread.sleep(time);
			}
			serial.write("#" + servo + " P" + state + " T" + time + " <cr>");
		} catch (IllegalStateException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param msg String der in der Console aus
	 */
	public static void println(String msg) {
		console.println(msg);
	}
}
