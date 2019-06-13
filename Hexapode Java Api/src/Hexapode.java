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

public class Hexapode {
	/**
	 * @param args
	 */
	final static Serial serial = SerialFactory.createInstance();

	public static void start(int homestart) {
		serial.addListener(new SerialDataEventListener() {
			public void dataReceived(SerialDataEvent event) {

			}
		});
		SerialConfig config = new SerialConfig();
		try {
			config.device(SerialPort.getDefaultPort()).baud(Baud._38400).dataBits(DataBits._8).parity(Parity.NONE)
					.stopBits(StopBits._1).flowControl(FlowControl.NONE);
		} catch (UnsupportedBoardType | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			serial.open(config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(homestart == 1) {
			home();
		}

	}

	public static void home() {

		try {
			serial.write("#5 P1600 #10 P750 T2500 <cr>");
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
