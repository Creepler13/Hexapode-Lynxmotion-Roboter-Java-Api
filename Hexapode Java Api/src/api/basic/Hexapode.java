package api.basic;

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

/**
 * 
 * Lowest-level class to control the hexapod directly by sending commands.
 * <p>
 * This is the base class used by all classes of this API to access the
 * hardware. <br>
 * There may only be one shared instance of this class. You can get it by
 * calling {@link api.basic.Hexapode#getInstance() getInstance()} on this class.
 *
 * @author Creepler13 & JustAnotherJavaProgrammer
 */
public class Hexapode {

	private final static Serial serial = SerialFactory.createInstance();

	// For future use
	private final static Console console = new Console();

	// WARNING: The PINS must not have IDs below 0!
	public static final int[][] PIN_MAPPING = new int[18][2];

	private static final Hexapode instance = new Hexapode();

	/**
	 * Get the shared instance of this Singleton
	 * 
	 * @return An instance of this class
	 */
	public static Hexapode getInstance() {
		return instance;
	}

	private Hexapode() {
		PINConfig.initPINConfig();
		try {
			SerialConfig config = new SerialConfig();
			config.device(SerialPort.getDefaultPort()).baud(Baud._9600).dataBits(DataBits._8).parity(Parity.NONE)
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
	 * Directly executes a command.
	 * 
	 * <p>
	 * <b>Note</b>: This method does <i>not</i> use the PIN-mapping {@link}
	 * 
	 * @param cmd The command to be executed
	 * @see api.basic.Hexapode#exec Execute a command with applied PIN-mapping
	 */
	public void serialCommand(String cmd) {
		try {
			serial.write(cmd + "\r");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute a command and apply PIN-mapping
	 *
	 * @param command The command to be executed
	 * @see api.basic.Hexapode#applyPINMapping(String)
	 */
	public void exec(String command) {
//		command = applyPINMapping(command);
//		System.out.println("Executing command:\n" + command);
		serialCommand(applyPINMapping(command));
	}

	/**
	 * Apply PIN-mapping to a given String
	 * 
	 * @param command The command which PIN-mapping should be applied to
	 * @return The command after PIN-mapping has been applied
	 */
	public String applyPINMapping(String command) {
//		for (int i = 0; i < PIN_MAPPING.length; i++) {
//			System.out.println("[ " + PIN_MAPPING[i][0] + ", " + PIN_MAPPING[i][1] + " ]");
//		}
		command = command.replaceAll(" ", "");
		for (int i = 0; i < PIN_MAPPING.length; i++) {
			command = command.replaceAll("#" + PIN_MAPPING[i][1], "#" + (-PIN_MAPPING[i][0]));
		}
		for (int i = 0; i < PIN_MAPPING.length; i++) {
			command = command.replaceAll("#" + (-PIN_MAPPING[i][0]), "#" + PIN_MAPPING[i][0]);
		}
		return command;
	}

	// TODO Write to console
}
