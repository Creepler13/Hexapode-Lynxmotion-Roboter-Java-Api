import java.io.IOException;

import api.advanced.Bundle;
import api.basic.Hexapode;

public class Start {

	public static void main(String args[]) {

		try {
			Hexapode.getClient().connectToDevServer("172.16.33.215", 4444);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bundle bundle = new Bundle(2000);
		bundle.add(302, 0);
		bundle.add(312, 0);
//		Bundle bundle2 = new Bundle(2000);
//		bundle2.add(302, 1500);

//		bundle.add(bundle2);
		
		System.out.println(bundle.getCommand());
		bundle.exec();

		Hexapode.getInstance().exec("#210P1000T1000");
		Hexapode.getInstance();

	}

}
