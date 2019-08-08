
//import com.pi4j.io.serial.*;
//import com.pi4j.util.CommandArgumentParser;
//
//import java.io.IOException;
//
//public class Start {
//
//	public static void main(String args[]) throws InterruptedException, IOException {
//		
//		
//		final Serial serial = SerialFactory.createInstance();
//		try {
//			
//			SerialConfig config = new SerialConfig();
//			
//			config.device(SerialPort.getDefaultPort())
//			.baud(Baud._9600)
//			.dataBits(DataBits._8)
//			.parity(Parity.NONE)
//			.stopBits(StopBits._1)
//			.flowControl(FlowControl.NONE);
//
//			if (args.length > 0) {
//				config = CommandArgumentParser.getSerialConfig(config, args);
//			}
//
//			serial.open(config);
//			serial.write("#5 P1000 #7 P2000 T2500 <cr>");
//		} catch ( IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		Hexapode.start(1,args);
//		Hexapode.println("Test");
//	}
//
//}
