package devServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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
			System.out.println("The server crashed! Exit programm...");
		}
	}

	private DevServer() throws IOException {
		serverSocket = new ServerSocket(PORT);
		while (true) {
			System.out.println("Now waiting for incoming connections");
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
				while (s.isConnected() && !s.isClosed()) {
					String line = r.readLine();
					if (line.startsWith("e")) {
						hexapode.exec(line.substring(1));
					} else if (line.startsWith("s")) {
						hexapode.serialCommand(line.substring(1));
					}
				}
			} catch (IOException e) {
				if (s != null) {
					s.close();
				}
				e.printStackTrace();
				System.out.println("Connection closed");
			}
		}
	}

}
