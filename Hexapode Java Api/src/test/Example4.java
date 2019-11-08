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

		// Bundles come in handy if you want to combine multiple instructions, which
		// should be executed simultaneously for the same amount of time, into one call.
		// But in more complex cases, you might need to combine instructions which
		// should be executed for different amounts of time.
		// This is why we also have created Collections. Collections combine Bundles or
		// other Collections (which also might contain even more Collections or Bundles)
		// into one call. Each ExecutableCommand (Bundles and Collections) contained in
		// a Collection is started simultaneously but can take different amounts of
		// time.

		// Create a new empty Collection
		Collection collection = new Collection();

		// Add some Bundles and Collections to the Collection (created via
		// BundleCreator; see Example3)
		Bundle bundle1 = new Bundle(1000);
		bundle1.add(100, 1500);
		Collection collection2 = new Collection();

		collection.add(bundle1);
		collection.add(collection2);

		// Execute the commands in your Collection
		// The commands will run with the Time Specified in their Bundles (bundle1:
		// 1000)
		collection.exec();

		// You can also run them all with the same time
		collection.exec(1500);

		// See whats in your Collection
		collection.printTree();

		// remove a Bundle or Collection from your Collection
		collection.remove(bundle1);

	}

}
