import api.advanced.Bundle;
import api.advanced.Collection;
import api.advanced.ExecutableCommands;

public class Start {

	public static void main(String args[]) {

		Collection colect = new Collection();
		Bundle bund = new Bundle(10);
		colect.add(new Collection(new ExecutableCommands[] {bund}));
		colect.printTree();

	}

}
