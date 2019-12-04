package dev.server;

import java.net.URI;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import api.basic.Hexapode;

public class DevClient extends WebSocketClient {

	public DevClient(URI serverUri) {
		super(serverUri);
		try {
			connectBlocking();
		} catch (InterruptedException e) {
			if (Hexapode.DEBUGGING)
				e.printStackTrace();
			System.out.println("The current Thread was interrupted whilst connectiong to a server!");
		}
	}

	@Override
	public void onClose(int arg0, String arg1, boolean arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(Exception arg0) {
		System.out.println("A connection error occured! You might be diconnected!");
	}

	@Override
	public void onMessage(String msg) {
		System.out.println("Data received from the server:\n" + msg);
	}

	@Override
	public void onOpen(ServerHandshake arg0) {
	}

}
