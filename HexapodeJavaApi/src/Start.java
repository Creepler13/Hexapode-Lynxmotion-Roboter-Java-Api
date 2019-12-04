import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import api.advanced.Bundle;
import api.advanced.BundleCreator;
import api.advanced.Collection;
import api.basic.Hexapode;
import api.basic.PINConfig;

import static api.basic.PINConstants.*;

public class Start {

	public static void main(String args[]) {

		try {
			Hexapode.getClient().connectToDevServer("172.16.33.215", 4444);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Bundle bundle = BundleCreator.moveAllLegs(750, 1000, 2300, 2000);
//		Bundle bundle2 = BundleCreator.moveAllLegs(750, 0, 750, 2000);
//		Bundle bundle2 = new Bundle(2000);
//		bundle2.add(302, 1500);

//		bundle.add(bundle2);
//		while (true) {
//			bundle.execBlocking();
//			bundle2.execBlocking();
//		}

//		System.out.println(Hexapode.getClient().applyPositionMapping("#210P1300T560"));

//		Bundle leftA = new Bundle(250);
//		Bundle leftB = new Bundle(250);
//		Bundle leftC = new Bundle(250);
//		Bundle leftD = new Bundle(250);
//		for (int i = 0; i < 3; i++) {
//			int leg = 0;
//			switch (i) {
//			case 0:
//				leg = FRONT;
//				break;
//			case 1:
//				leg = MIDDLE;
//				break;
//			default:
//				leg = BACK;
//				break;
//			}
//			if (i == 1) {
//				leftA.add(BundleCreator.moveLeg(leg + LEFT, 1500, 500, 1000, 250));
//				leftB.add(BundleCreator.moveLeg(leg + LEFT, 1500, 750, 1575, 250));
//				leftC.add(BundleCreator.moveLeg(leg + LEFT, 0, 750, 1575, 250));
//				leftD.add(BundleCreator.moveLeg(leg + LEFT, 0, 500, 1000, 250));
//			} else {
//				leftA.add(BundleCreator.moveLeg(leg + LEFT, 0, 750, 1575, 250));
//				leftB.add(BundleCreator.moveLeg(leg + LEFT, 0, 500, 1000, 250));
//				leftC.add(BundleCreator.moveLeg(leg + LEFT, 1500, 500, 1000, 250));
//				leftD.add(BundleCreator.moveLeg(leg + LEFT, 1500, 750, 1575, 250));
//			}
//
//		}
//
//		Bundle rightA = new Bundle(250);
//		Bundle rightB = new Bundle(250);
//		Bundle rightC = new Bundle(250);
//		Bundle rightD = new Bundle(250);
//		for (int i = 0; i < 3; i++) {
//			int leg = 0;
//			switch (i) {
//			case 0:
//				leg = FRONT;
//				break;
//			case 1:
//				leg = MIDDLE;
//				break;
//			default:
//				leg = BACK;
//				break;
//			}
//			if (i == 1) {
//				rightA.add(BundleCreator.moveLeg(leg + RIGHT, 1500, 500, 1000, 250));
//				rightB.add(BundleCreator.moveLeg(leg + RIGHT, 1500, 750, 1575, 250));
//				rightC.add(BundleCreator.moveLeg(leg + RIGHT, 0, 750, 1575, 250));
//				rightD.add(BundleCreator.moveLeg(leg + RIGHT, 0, 500, 1000, 250));
//			} else {
//				rightA.add(BundleCreator.moveLeg(leg + RIGHT, 0, 1000, 1575, 250));
//				rightB.add(BundleCreator.moveLeg(leg + RIGHT, 0, 1500, 1000, 250));
//				rightC.add(BundleCreator.moveLeg(leg + RIGHT, 1500, 1500, 1000, 250));
//				rightD.add(BundleCreator.moveLeg(leg + RIGHT, 1500, 1000, 1575, 250));
//			}
//		}
//		Collection a = new Collection();
//		a.add(leftA);
//		a.add(rightA);
//		Collection b = new Collection();
//		b.add(leftB);
//		b.add(rightB);
//		Collection c = new Collection();
//		c.add(leftC);
//		c.add(rightC);
//		Collection d = new Collection();
//		d.add(leftD);
//		d.add(rightD);
//		for (int i = 0; i < 20; i++) {
//			a.execBlocking();
//			b.execBlocking();
//			c.execBlocking();
//			d.execBlocking();
//		}

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		while (true)
			try {
				moveServo(FRONT + RIGHT + KNEE, Integer.parseInt(r.readLine()), 500);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		System.out.println("Done!");
	}

	public static void moveServo(int pin, int pos, int time) {
		Hexapode.getInstance().serialCommand(Hexapode.applyPINMapping("#" + pin + "P" + pos + "T" + time));
	}

}
