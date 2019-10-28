import java.io.IOException;

import api.advanced.Bundle;
import api.basic.Hexapode;

public class Start {

	public static void main(String args[]) {


		Bundle bundle = new Bundle(10);
		bundle.add(1,2);
		bundle.add(5,2000);
		Bundle bundle2 = new Bundle(10);
		bundle2.add(10, 2);
		
		bundle.add(bundle2);
		System.out.println(bundle.getCommand());
		
		Hexapode.getInstance().exec("#210P1000T1000");
		Hexapode.getInstance();
		try {
			Hexapode.getClient().connectToDevServer("172.16.33.215", 4444);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
