package test;

import java.io.IOException;

import api.basic.Hexapode;

public class DevServer_Example {

	public static void main(String[] args) {
		
		
		//This is the example to explain how to use the DevServer
		//
		//TODO explain how to start the devserver
		
		//get the Hexpode Client instance (not Hexapode.getInstace()) 
		Hexapode hexapode = Hexapode.getClient();
		
		//Connect to the DevServer 
		//The Hostname is show after starting the Server
		try {
			hexapode.connectToDevServer("hostname", 4444);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		//After connecting you can use every like you would normally
		
		
		

	}

}
