import api.advanced.Bundle;
import api.advanced.Collection;
import api.advanced.ExecutableCommands;
import api.basic.Hexapode;

public class Start {

	public static void main(String args[]) {

//		Hexapode.getInstance().serialCommand("#6 P1000 T1000 \r");
//		try {
//			System.out.println("Sent command! Sleeping for 3 seconds");
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		Collection colect = new Collection();
		Bundle bund = new Bundle(2000);
		bund.add(5, 1500);
		colect.add(new Collection(new ExecutableCommands[] {bund}));
		colect.printTree();
		
		System.out.println("Executing collection...");
		colect.exec();

	}

}
