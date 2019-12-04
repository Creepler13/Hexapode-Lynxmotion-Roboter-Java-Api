package dev.server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import api.basic.Hexapode;

/**
 * This class allows one client at a time to connect and control the hexapod.
 * 
 * @author JusAnotherJavaProgrammer
 * @see api.basic.Hexapode#connectToDevServer(String, int)
 * @see api.basic.Hexapode#exec(String)
 * @see api.basic.Hexapode#serialCommand(String)
 */
public class DevServer extends WebSocketServer {
	Hexapode hexapode = Hexapode.getInstance();
	protected static int PORT = 4444;
	ServerSocket serverSocket;

	public static void main(String[] args) {
		Hexapode.getInstance();
		new DevServer();
	}

	private DevServer() {
		super(new InetSocketAddress(PORT));
		run();
	}

	/**
	 * Get the public IP-address in the local network
	 * 
	 * @return The device's {@link InetAddress} in the local network
	 * @throws SocketException Thrown by
	 *                         {@link NetworkInterface#getNetworkInterfaces()}
	 */
	public static InetAddress getIPAddr() throws SocketException {
		Enumeration<NetworkInterface> ifs = NetworkInterface.getNetworkInterfaces();
		for (NetworkInterface intf : Collections.list(ifs)) {
			Enumeration<InetAddress> addrs = intf.getInetAddresses();
			for (InetAddress addr : Collections.list(addrs)) {
//				System.out.println(addr.getHostAddress());
				if (!addr.getHostAddress().startsWith("0.") && !addr.getHostAddress().startsWith("127")
						&& !addr.getHostAddress().contains(":"))
					return addr;
			}
		}
		return null;
	}

	@Override
	public void onClose(WebSocket conn, int arg1, String arg2, boolean arg3) {
		System.out.println("Connection to " + conn.getRemoteSocketAddress().getAddress().toString() + " closed");
	}

	@Override
	public void onError(WebSocket conn, Exception e) {
		e.printStackTrace();
		System.out.println("An exception occured! Connection closed");
	}

	@Override
	public void onMessage(WebSocket conn, String msg) {
		for (String line : msg.split("[\r\n]+")) {
			if (line.startsWith("e")) {
				line = line.substring(1);
				System.out.println("Executing command (pin-mapping enabled): " + line);
				hexapode.exec(line);
			} else if (line.startsWith("s")) {
				line = line.substring(1);
				System.out.println("Executing command (pin-mapping disabled): " + line);
				hexapode.serialCommand(line);
			}
		}
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake arg1) {
		System.out.println("Now connected to: " + conn.getRemoteSocketAddress().getAddress().toString());
	}

	@Override
	public void onStart() {
		try {
			System.out.println("Now waiting for incoming connections at " + getIPAddr().getHostAddress() + " on port "
					+ getPort());
		} catch (SocketException e) {
			System.out.println("Now waiting for incoming connections at " + getAddress().getAddress().getHostAddress()
					+ " on port " + getPort());
		}
		setConnectionLostTimeout(100);
	}

}