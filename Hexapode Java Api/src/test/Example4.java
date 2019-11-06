package test;

import api.advanced.Bundle;
import api.advanced.Collection;

/**
 * Showcasing {@link api.advanced.Collection Collections}
 * 
 * @author JustAnotherJavaProgrammer &amp; Creepler13
 * @see api.advanced.Collection
 */
public class Example4 {

	public static void main(String[] args) {
		// TODO write an example, showcasing Collections

		// We already know how to work with Bundles ...

		// Create a Colection
		Collection collection = new Collection();

		// Create and add Bundles or Collections to the Collection
		Bundle bundle1 = new Bundle(1000);
		bundle1.add(100, 1500);
		Collection collection2 = new Collection();

		collection.add(bundle1);
		collection.add(collection2);
		
		//Execute the commands in your Collection
		//The commands will run with the Time Specified in their Bundles (bundle1: 1000)
		collection.exec();

		//You can also run them all with the same time
		collection.exec(1500);
		
	}

}
