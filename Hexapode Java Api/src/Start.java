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

public class Start {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		
		final Serial serial = SerialFactory.createInstance();
		try {
			
			SerialConfig config = new SerialConfig();
			
			config.device(SerialPort.getDefaultPort())
			.baud(Baud._9600)
			.dataBits(DataBits._8)
			.parity(Parity.NONE)
			.stopBits(StopBits._1)
			.flowControl(FlowControl.NONE);

			if (args.length > 0) {
				config = CommandArgumentParser.getSerialConfig(config, args);
			}

			serial.open(config);
			serial.write("#5 P1600 #7 P2000 T2500 <cr>");
		} catch (UnsupportedBoardType | IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		
		Hexapode.start(1,args);
		Hexapode.println("Test");
	}

}
