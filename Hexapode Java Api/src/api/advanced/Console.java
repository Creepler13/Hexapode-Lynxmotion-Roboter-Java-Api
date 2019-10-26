package api.advanced;

public class Console {

	private static com.pi4j.util.Console console = new com.pi4j.util.Console();

	public Console() {

	}

	public com.pi4j.util.Console box(int padding, String... lines) {
		return console.box(padding, lines);
	}

	public com.pi4j.util.Console box(String... lines) {
		return console.box(lines);
	}

	public com.pi4j.util.Console clearScreen() {
		return console.clearScreen();
	}

	public com.pi4j.util.Console emptyLine() {
		return console.emptyLine();
	}

	public com.pi4j.util.Console emptyLine(int number) {
		return console.emptyLine(number);
	}

	public com.pi4j.util.Console eraseLine() {
		return console.eraseLine();
	}

	public com.pi4j.util.Console print(Object data) {
		return console.print(data);
	}

	public com.pi4j.util.Console print(String data) {
		return console.print(data);
	}

	public com.pi4j.util.Console print(String format, Object... args) {
		return console.print(format, args);
	}

	public com.pi4j.util.Console println() {
		return console.println();
	}

	public com.pi4j.util.Console println(char character, int repeat) {
		return console.println(character, repeat);
	}

	public com.pi4j.util.Console println(Object line) {
		return console.println(line);
	}

	public com.pi4j.util.Console println(String line) {
		return console.println(line);
	}

	public com.pi4j.util.Console println(String format, Object... args) {
		return console.println(format, args);
	}

	public com.pi4j.util.Console separatorLine() {
		return console.separatorLine();
	}

	public com.pi4j.util.Console separatorLine(char character) {
		return console.separatorLine(character);
	}

	public com.pi4j.util.Console separatorLine(char character, int length) {
		return console.separatorLine(character, length);
	}

	public com.pi4j.util.Console title(String... title) {
		return console.title(title);
	}
}
