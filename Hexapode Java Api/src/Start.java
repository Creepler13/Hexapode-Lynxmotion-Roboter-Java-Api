import java.io.IOException;

import api.basic.Hexapode;

public class Start {

	public static void main(String args[]) {


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
