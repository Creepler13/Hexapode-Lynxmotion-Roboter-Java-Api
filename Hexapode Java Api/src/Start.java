import com.pi4j.io.serial.Baud;


public class Start {

	public static void main(String args[]) {
		Hexapode hexa = new Hexapode(Baud._9600);
		Hexapode.moveServo(13, 1500, 1500, false);
	}

}
