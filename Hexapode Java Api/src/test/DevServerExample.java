package test;

import java.io.IOException;

import api.basic.Hexapode;
import api.basic.PINConstants;

/**
 * Showing how to connect to a {@link devServer.DevServer DevServer}
 * 
 * @author Creepler13 &amp; JustAnotherJavaProgrammer
 */
public class DevServerExample {

	public static void main(String[] args) {
		// First of all, you need a running DevServer reachable from your device
		// If you want to learn how to start a DevServer, look no further than our
		// repository-wiki at:
		// https://github.com/Creepler13/Hexapode-Lynxmotion-Roboter-Java-Api/wiki
		// After starting the DevServer, it will display something like:
		// Now waiting for incoming connections at 172.41.19.74 on port 4444
		// Now, you can connect to your Hexapod.
		// Note: Only one client at a time can connect to DevServer. The connection is
		// automatically closed when your programme finishes.

		// The first time we request an instance of Hexapode, we should use getClient
		// because it will always return an instance in client mode.
		// If you want to use client mode as a fallback in case the programme is not
		// running on a hexapod, you should use getInstance instead.
		Hexapode hexapode = Hexapode.getClient();

		// Before we can send any commands to our DevServer, we must establish a
		// connection.
		try {
			// Replace "172.41.19.74" with the IP-Address displayed by the DevServer (or an
			// external IP-Address shown by ifconfig)
			// Add the port displayed by the DevServer to the method if it does not display
			// 4444 (should happen only if the code has been changed manually)
			hexapode.connectToDevServer("172.41.19.74");
		} catch (IllegalStateException | IOException e) {
			// An IllegalStateException will only be thrown if this programme is running
			// directly on a hexapod and you used getInstance, so the instance is in local
			// mode.
			e.printStackTrace();
		}

		// After connecting, everything works as if the programme was running directly
		// on the hexapod:
		Hexapode.getInstance().moveServo(PINConstants.MIDDLE + PINConstants.RIGHT + PINConstants.KNEE, 40, 500);
	}

}
