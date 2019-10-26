package api.basic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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

import devServer.DevServer;

/**
 * 
 * Lowest-level class to control the hexapod directly by sending commands.
 * <p>
 * This is the base class used by all classes of this API to access the
 * hardware. <br>
 * There may only be one shared instance of this class. You can get it by
 * calling {@link api.basic.Hexapode#getInstance() getInstance()} on this class.
 *
 * @author Creepler13 &amp; JustAnotherJavaProgrammer
 */
public class Hexapode {

	private static Serial serial = null;

	// For future use
	private static Console console = null;

	// WARNING: The PINS must not have IDs below 0!
	public static final int[][] PIN_MAPPING = new int[18][2];
	private static boolean CreatedPiInstance = false;

	private static Hexapode instance = null;
	private static final Hexapode serverinstance = new Hexapode(true);

	private boolean clientMode = false;
	private BufferedWriter w = null;

	/**
	 * Get the shared instance of this Singleton
	 * 
	 * @return An instance of this class
	 */
	public static Hexapode getInstance() {
		if (!CreatedPiInstance) {
			instance = new Hexapode(false);
			CreatedPiInstance = true;
			return instance;
		} else {
			return instance;

		}
	}

	/**
	 * Get the shared ClientInstance of this Singleton id you want to use the Server
	 * 
	 * @return An instance of this class using the DevServer
	 */
	public static Hexapode getClient() {
		return serverinstance;
	}

	private Hexapode(boolean dev) {
		PINConfig.initPINConfig();
		if (!dev) {
			try {
				serial = SerialFactory.createInstance();
				console = new Console();
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
				System.out.println("This host can only be used as a client!");
				clientMode = true;
			}
		} else {
			clientMode = true;
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
		if (clientMode) {
			if (w != null)
				try {
					w.write("s" + cmd);
					w.newLine();
					w.flush();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Your command could not be sent to the dev server. Are you connected?");
				}

			return;
		}
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
		if (clientMode) {
			if (w != null)
				try {
					w.write("e" + command);
					w.newLine();
					w.flush();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Your command could not be sent to the dev server. Are you connected?");
				}
			return;
		} else {
			serialCommand(applyPINMapping(command));
		}
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

	/**
	 * Wrapper class to execute a simple command
	 * 
	 * <p>
	 * To execute more complex commands use {@link api.advanced.Bundle Bundles}.
	 * </p>
	 * 
	 * @param servo      The pin of the servo to move
	 * @param pos        The position the servo should move to
	 * @param timeMillis The time the servo will need to reach the targeted position
	 * @see test.Example1
	 */
	public void moveServo(int servo, int pos, int timeMillis) {
		exec("#" + servo + "P" + pos + "T" + timeMillis);
	}

	/**
	 * Connect to a running {@link DevServer}
	 * 
	 * @param hostname The hostname of the host to connect to
	 * @param port     The port to connect to
	 * @throws UnknownHostException Thrown by the {@link Socket#Socket(String, int)
	 *                              Socket contructor}
	 * @throws IOException          Thrown by the {@link Socket#Socket(String, int)
	 *                              Socket contructor}
	 */
	public void connectToDevServer(String hostname, int port) throws UnknownHostException, IOException {
		@SuppressWarnings("resource")
		Socket sock = new Socket(hostname, port);
		w = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
	}

	// TODO Write to console
}
