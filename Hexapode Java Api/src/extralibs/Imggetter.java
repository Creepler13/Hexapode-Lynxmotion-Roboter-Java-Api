package extralibs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imggetter {

	private static BufferedImage black = loadimage("hole");
	private static BufferedImage ground = loadimage("ground");
	private static BufferedImage wall = loadimage("wall");

	public BufferedImage getimg(int a) {
		switch (a) {
		case 1:
			return wall;
		case 0:
			return ground;
		default:
			return black;
		}

	}

	public static BufferedImage loadimage(String b) {
		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		try {
			image = ImageIO.read(new File("src/textures/" + b + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;

	}

}