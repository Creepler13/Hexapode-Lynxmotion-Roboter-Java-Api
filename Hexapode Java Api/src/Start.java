import java.io.IOException;

import api.advanced.Bundle;
import api.advanced.Collection;
import api.advanced.ExecutableCommands;
import api.basic.Hexapode;

public class Start {

	public static void main(String args[]) {

//		Hexapode.getInstance().serialCommand("#6 P1000 T1000 \r");
//		try {
//			System.out.println("Sent command! Sleeping for 3 seconds");
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		try {
			Hexapode.getInstance().connectToDevServer("172.16.33.215", 4444);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hexapode.getInstance().exec("#210P1000T1000");

	}

}
