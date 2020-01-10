package demo;

import api.advanced.Bundle;
import api.advanced.BundleCreator;
import api.advanced.Collection;
import api.advanced.Sequence;
import api.basic.Hexapode;

import static api.basic.PINConstants.BACK;
import static api.basic.PINConstants.FRONT;
import static api.basic.PINConstants.LEFT;
import static api.basic.PINConstants.MIDDLE;
import static api.basic.PINConstants.RIGHT;

import java.io.IOException;

public class Start {

	public static void main(String args[]) {

		try {
			Hexapode.getClient().connectToDevServer("172.16.33.215", 4444);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			createSequence().execBlocking(5000);
		}

	}

	private static Sequence createSequence() {
		Bundle leftA = new Bundle(250);
		Bundle leftB = new Bundle(250);
		Bundle leftC = new Bundle(250);
		Bundle leftD = new Bundle(250);
		for (int i = 0; i < 3; i++) {
			int leg = 0;
			switch (i) {
			case 0:
				leg = FRONT;
				break;
			case 1:
				leg = MIDDLE;
				break;
			default:
				leg = BACK;
				break;
			}
			if (i == 1) {
				leftA.add(BundleCreator.moveLeg(leg + LEFT, 1500, 500, 1000, 250));
				leftB.add(BundleCreator.moveLeg(leg + LEFT, 1500, 750, 1575, 250));
				leftC.add(BundleCreator.moveLeg(leg + LEFT, 0, 750, 1575, 250));
				leftD.add(BundleCreator.moveLeg(leg + LEFT, 0, 500, 1000, 250));
			} else {
				leftA.add(BundleCreator.moveLeg(leg + LEFT, 0, 750, 1575, 250));
				leftB.add(BundleCreator.moveLeg(leg + LEFT, 0, 500, 1000, 250));
				leftC.add(BundleCreator.moveLeg(leg + LEFT, 1500, 500, 1000, 250));
				leftD.add(BundleCreator.moveLeg(leg + LEFT, 1500, 750, 1575, 250));
			}

		}

		Bundle rightA = new Bundle(250);
		Bundle rightB = new Bundle(250);
		Bundle rightC = new Bundle(250);
		Bundle rightD = new Bundle(250);
		for (int i = 0; i < 3; i++) {
			int leg = 0;
			switch (i) {
			case 0:
				leg = FRONT;
				break;
			case 1:
				leg = MIDDLE;
				break;
			default:
				leg = BACK;
				break;
			}
			if (i == 1) {
				rightA.add(BundleCreator.moveLeg(leg + RIGHT, 1500, 500, 1000, 250));
				rightB.add(BundleCreator.moveLeg(leg + RIGHT, 1500, 750, 1575, 250));
				rightC.add(BundleCreator.moveLeg(leg + RIGHT, 0, 750, 1575, 250));
				rightD.add(BundleCreator.moveLeg(leg + RIGHT, 0, 500, 1000, 250));
			} else {
				rightA.add(BundleCreator.moveLeg(leg + RIGHT, 0, 1000, 1575, 250));
				rightB.add(BundleCreator.moveLeg(leg + RIGHT, 0, 1500, 1000, 250));
				rightC.add(BundleCreator.moveLeg(leg + RIGHT, 1500, 1500, 1000, 250));
				rightD.add(BundleCreator.moveLeg(leg + RIGHT, 1500, 1000, 1575, 250));
			}
		}
		Collection a = new Collection();
		a.add(leftA);
		a.add(rightA);
		Collection b = new Collection();
		b.add(leftB);
		b.add(rightB);
		Collection c = new Collection();
		c.add(leftC);
		c.add(rightC);
		Collection d = new Collection();
		d.add(leftD);
		d.add(rightD);
		System.out.println("Done!");
		return new Sequence(new Collection[] { a, b, c, d });
	}

	public static void moveServo(int pin, int pos, int time) {
		Hexapode.getInstance().serialCommand(Hexapode.applyPINMapping("#" + pin + "P" + pos + "T" + time));
	}

}
