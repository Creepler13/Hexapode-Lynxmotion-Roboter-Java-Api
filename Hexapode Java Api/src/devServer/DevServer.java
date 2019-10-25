package devServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import api.basic.Hexapode;

public class DevServer {
	Hexapode hexapode = Hexapode.getInstance();
	protected static int PORT = 4444;
	ServerSocket serverSocket;

	public static void main(String[] args) {
		try {
			new DevServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private DevServer() throws IOException {
		serverSocket = new ServerSocket(PORT);
		while (true) {
			Socket s = serverSocket.accept();
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
		}
	}

}
