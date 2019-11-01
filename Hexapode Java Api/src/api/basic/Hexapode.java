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

	/**
	 * <b>WARNING:</b> The PINS must not have IDs below 0!<br>
	 * The array containing a list of mapped pins<br>
	 * <p>
	 * <code>PIN_MAPPING[index][0]</code> contains the original pin on the board and
	 * <code>PIN_MAPPING[index][1]</code> contains an alternative pin that can be
	 * used instead.<br>
	 * <i>Tip:</i> You can still use the original "hardware" pin number if no other
	 * pin is mapped to it.
	 *
	 * @see PINConfig
	 * @see PINConstants
	 */
	public static final int[][] PIN_MAPPING = new int[18][2];

	private static Hexapode instance = null;

	private boolean clientMode = false;
	private Socket sock = null;
	private BufferedWriter w = null;

	/**
	 * Get the shared instance of this Singleton
	 *
	 * @return An instance of this class
	 * @see Hexapode#getClient()
	 */
	public static Hexapode getInstance() {
		if (instance == null)
			instance = new Hexapode(false);
		return instance;
	}

	/**
	 * Like {@link Hexapode#getInstance() getInstance}, but forces the returned
	 * instance to be in client-mode<br>
	 * <b>Note:</b> This method must be called before calling
	 * {@link Hexapode#getInstance() getInstance()}
	 *
	 * @throws IllegalStateException If getInstance has been called before and
	 *                               already created a local (non-client) instance
	 *                               of the Singleton
	 * @return An instance of this class using the DevServer
	 * @see Hexapode#getInstance()
	 */
	public static Hexapode getClient() throws IllegalStateException {
		if (instance == null) {
			instance = new Hexapode(true);
			return instance;
		}
		if (!instance.clientMode)
			throw new IllegalStateException("getInstance has already be called!");
		return instance;
	}

	private Hexapode(boolean forceClient) {
		PINConfig.initPINConfig();
		if (!forceClient) {
			try {
				serial = SerialFactory.createInstance();
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
			System.out.println("This host can now only be used as a client!");
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
	public void serialCommand(String command) {
		if (clientMode) {
			sendToServer(command);
		}
		try {
			serial.write(command + "\r");
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
			sendToServer(command);
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
	 * @throws UnknownHostException  Thrown by the {@link Socket#Socket(String, int)
	 *                               Socket contructor}
	 * @throws IOException           Thrown by the {@link Socket#Socket(String, int)
	 *                               Socket contructor}
	 * @throws IllegalStateException If this method is called on a local
	 *                               (non-client) instance<br>
	 *                               <i>Tip:</i> You can force the instance to be a
	 *                               client instance by calling
	 *                               {@link Hexapode#getClient() getClient()} the
	 *                               first time you want to get an instance of this
	 *                               class.
	 * @see Hexapode#getClient()
	 */
	public void connectToDevServer(String hostname, int port)
			throws UnknownHostException, IOException, IllegalStateException {
		if (!clientMode)
			throw new IllegalStateException("You are calling this method on a local (non-client) instance!");
		if (w != null)
			w.close();
		if (sock != null)
			sock.close();
		sock = new Socket(hostname, port);
		w = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
	}

	/**
	 * Get a boolean whether this instance is a client and must be connected to a
	 * DevServer to work<br>
	 * <b>Note:</b> You can not change the mode (client-mode or local-mode) of an
	 * instance once it has been created.
	 *
	 * @return Whether this instance is in client-mode
	 * @see Hexapode#getInstance()
	 * @see Hexapode#getClient()
	 */

	private void sendToServer(String command) {
		if (w != null)
			try {
				w.write("e" + command);
				w.newLine();
				w.flush();
				System.out.println("Send command : " + command + " to the Server.");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Your command could not be sent to the dev server. Are you connected?");
			}
		else
			Console.box("Your device seemingly is not a hexapod.",
					"To control a remote hexapod, please connect to a DevServer running on a hexapod.", "",
					"If you are running this program on a hexapod, please make sure",
					"that you have added the local Pi4J installation to the classpath (See documentation).");
		return;
	}

	public boolean isClient() {
		return clientMode;
	}
}
