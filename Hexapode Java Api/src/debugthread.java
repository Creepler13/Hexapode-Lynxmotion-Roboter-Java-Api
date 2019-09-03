import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import extralibs.Imggetter;
import extralibs.JBackgroundPanel;

public class debugthread extends Thread {

	static double newX = 0;
	static double newY = 0;
	static double newRot = 0;

	private static String[] Map;

	private static JFrame frame;
	private static JBackgroundPanel contentPane;
	static Imggetter imggetter = new Imggetter();

	public void run() {
		frame = new JFrame("Debug Window!");
		frame.setBounds(500, 500, 500, 500);
		frame.setVisible(true);
		contentPane = new JBackgroundPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		File theDir = new File("src/hexadata");
		if (!theDir.exists()) {
			boolean result = false;
			try {
				theDir.mkdir();
				new File("src/hexadata/map.txt").createNewFile();
				File file = new File("src/hexadata/map.txt");
				FileWriter write = new FileWriter(file);
				write.write(
						"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
				write.flush();
				write.close();
				result = true;
			} catch (SecurityException | IOException se) {
				// handle it
			}
			if (result) {
				System.out.println("directory created");
			}
		}

		movecalc(10);

	}

	public static void movecalc(int moveLenght) {

		newX = Hexapode.hexaX + Math.sin(Hexapode.hexaRotation) * moveLenght;
		newY = Hexapode.hexaY + Math.cos(Hexapode.hexaRotation) * moveLenght;

		System.out.println(newX + " = X");
		System.out.println(newY + " = Y");

		contentPane.setBackground(render(500, 500));

	}

	public static void update() {
		contentPane.setBackground(render(500, 500));
	}

	private static BufferedImage render(int h, int w) {

		BufferedImage i = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = i.createGraphics();

		g.setColor(Color.BLACK);

		File file = new File("src/hexadata/map.txt");
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			Map = br.readLine().split(",");
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int mapX = 0;
		int mapY = 0;
		for (int j = 0; j < 100; j++) {
			g.drawImage(imggetter.getimg(Integer.parseInt(Map[j])), mapX, mapY, null);
			if (mapX != 450) {
				mapX = mapX + 50;
			} else {
				mapX = 0;
				mapY = mapY + 50;
			}
		}

		double rotationRequired = Math.toRadians(Hexapode.hexaRotation);
		double locationX = imggetter.getimg(1).getWidth() / 2;
		double locationY = imggetter.getimg(1).getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		g.drawImage(op.filter(imggetter.getimg(99), null), (int) newX, (int) newY, null);

		return i;

	}

}
