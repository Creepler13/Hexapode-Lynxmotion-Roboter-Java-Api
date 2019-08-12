import java.io.File;

import javax.swing.JFrame;

public class debugthread extends Thread {
	public void run() {
		JFrame frame = new JFrame("Debug Window!");
		frame.setBounds(500, 500, 500, 500);
		frame.setVisible(true);
		File theDir = new File("src/DebugMaps");
		if (!theDir.exists()) {
			System.out.println("creating directory: DebugMaps");
			boolean result = false;
			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("directory created");
			}
		}

		
	
		
		
	}
}
