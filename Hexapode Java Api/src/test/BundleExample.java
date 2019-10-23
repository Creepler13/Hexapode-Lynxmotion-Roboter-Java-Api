package test;

import api.advanced.Bundle;

public class BundleExample {

	public static void main(String[] args) {

		Bundle bundle = new Bundle(1000);

		bundle.add(100, 1500);

		bundle.exec();

		bundle.exec(500);

		bundle.print();

		bundle.getCommand();

		bundle.getRawCommand();

		bundle.getTime();

		bundle.getTree();

		bundle.printTree();

	}

}
