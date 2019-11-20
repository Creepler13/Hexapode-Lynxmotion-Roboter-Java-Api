import java.io.IOException;

import api.advanced.Bundle;
import api.advanced.BundleCreator;
import api.basic.Hexapode;
import static api.basic.PINConstants.*;

public class Start {

	public static void main(String args[]) {

		try {
			Hexapode.getClient().connectToDevServer("172.16.33.215", 4444);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bundle bundle = BundleCreator.moveAllLegs(1500, 1500, 1500, 1000);
//		Bundle bundle2 = new Bundle(2000);
//		bundle2.add(302, 1500);

//		bundle.add(bundle2);
		bundle.exec();

		
//		System.out.println(Hexapode.getClient().applyPositionMapping("#210P1300T560"));
	}

}
