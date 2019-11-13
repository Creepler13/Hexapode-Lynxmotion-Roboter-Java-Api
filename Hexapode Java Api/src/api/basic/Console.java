package api.basic;

/**
 * A static wrapper for com.pi4j.util.Console
 * 
 * @author Creepler13 &amp; JustAnotherJavaProgrammer
 */
public class Console {

	private static final com.pi4j.util.Console console = new com.pi4j.util.Console();

	public static com.pi4j.util.Console box(int padding, String... lines) {
		return console.box(padding, lines);
	}

	public static com.pi4j.util.Console box(String... lines) {
		return console.box(lines);
	}

	public static com.pi4j.util.Console clearScreen() {
		return console.clearScreen();
	}

	public static com.pi4j.util.Console emptyLine() {
		return console.emptyLine();
	}

	public static com.pi4j.util.Console emptyLine(int number) {
		return console.emptyLine(number);
	}

	public static com.pi4j.util.Console eraseLine() {
		return console.eraseLine();
	}

	public static com.pi4j.util.Console print(Object data) {
		return console.print(data);
	}

	public static com.pi4j.util.Console print(String data) {
		return console.print(data);
	}

	public static com.pi4j.util.Console print(String format, Object... args) {
		return console.print(format, args);
	}

	public static com.pi4j.util.Console println() {
		return console.println();
	}

	public static com.pi4j.util.Console println(char character, int repeat) {
		return console.println(character, repeat);
	}

	public static com.pi4j.util.Console println(Object line) {
		return console.println(line);
	}

	public static com.pi4j.util.Console println(String line) {
		return console.println(line);
	}

	public static com.pi4j.util.Console println(String format, Object... args) {
		return console.println(format, args);
	}

	public static com.pi4j.util.Console separatorLine() {
		return console.separatorLine();
	}

	public static com.pi4j.util.Console separatorLine(char character) {
		return console.separatorLine(character);
	}

	public static com.pi4j.util.Console separatorLine(char character, int length) {
		return console.separatorLine(character, length);
	}

	public static com.pi4j.util.Console title(String... title) {
		return console.title(title);
	}

	public static <T> void printArray(T[] arr) {
		System.out.print("[ ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1)
				System.out.print(", ");
		}
		System.out.println(" ]");
	}

	public static void printArray(double[] arr) {
		System.out.print("[ ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1)
				System.out.print(", ");
		}
		System.out.println(" ]");
	}
	
	public static void printArray(int[] arr) {
		System.out.print("[ ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1)
				System.out.print(", ");
		}
		System.out.println(" ]");
	}
}
