package dev.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

import api.basic.Hexapode;

/**
 * This class allows one client at a time to connect and control the hexapod.
 * 
 * @author JusAnotherJavaProgrammer
 * @see api.basic.Hexapode#connectToDevServer(String, int)
 * @see api.basic.Hexapode#exec(String)
 * @see api.basic.Hexapode#serialCommand(String)
 */
public class DevServer {
	Hexapode hexapode = Hexapode.getInstance();
	protected static int PORT = 4444;
	ServerSocket serverSocket;

	public static void main(String[] args) {
		try {
			new DevServer();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("The server crashed! Exit programme...");
		}
	}

	private DevServer() throws IOException {
		serverSocket = new ServerSocket(PORT);
		while (true) {
			System.out.println("Now waiting for incoming connections at " + getIPAddr().getHostAddress() + " on port "
					+ serverSocket.getLocalPort());
			Socket s = null;
			try {
				s = serverSocket.accept();
				System.out.println("Now connected to: " + s.getInetAddress().toString());
				BufferedReader r = null;
				try {
					r = new BufferedReader(new InputStreamReader(s.getInputStream()));
				} catch (IOException e) {
					s.close();
				}
				String line = r.readLine();
				while (s.isConnected() && !s.isClosed() && line != null) {
					if (line.startsWith("e")) {
						line = line.substring(1);
						System.out.println("Executing command (pin-mapping enabled): " + line);
						hexapode.exec(line);
					} else if (line.startsWith("s")) {
						line = line.substring(1);
						System.out.println("Executing command (pin-mapping disabled): " + line);
						hexapode.serialCommand(line);
					}
					line = r.readLine();
				}
			} catch (IOException e) {
				if (s != null) {
					s.close();
				}
				e.printStackTrace();
				System.out.println("An exception occured! Connection closed");
			}
			if (!s.isClosed()) {
				s.close();
				System.out.println("Connection closed");
			}
		}
	}

	/**
	 * Get the public IP-address in the local network
	 * 
	 * @return The device's {@link InetAddress} in the local network
	 * @throws SocketException Thrown by
	 *                         {@link NetworkInterface#getNetworkInterfaces()}
	 */
	public InetAddress getIPAddr() throws SocketException {
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

}