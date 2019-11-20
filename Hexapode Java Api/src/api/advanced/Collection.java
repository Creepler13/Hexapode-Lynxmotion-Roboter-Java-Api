package api.advanced;

import java.util.ArrayList;

import javax.swing.DebugGraphics;

import api.basic.Hexapode;

/**
 * A tool to combine multiple {@link ExecutableCommands} into one object, which
 * runs all contained commands in parallel.
 * 
 * @author JustAnotherJavaProgrammer &amp; Creepler13
 * @see test.Example4
 * @see ExecutableCommands
 * @see Bundle
 */
public class Collection implements ExecutableCommands {

	ArrayList<ExecutableCommands> commands = new ArrayList<ExecutableCommands>();

	/**
	 * Create a new, empty {@linkplain Collection}.
	 */
	public Collection() {

	}

	/**
	 * Create a collection and initialize it with the {@link ExecutableCommands}
	 * contained in the array
	 * 
	 * @param commands An array of {@linkplain ExecutableCommands}
	 */
	public Collection(ExecutableCommands[] commands) {
		for (ExecutableCommands command : commands) {
			this.commands.add(command);
		}
	}

	/**
	 * Call the {@link ExecutableCommands#exec() exec()} methods of all contained
	 * commands
	 * 
	 * @see Collection#exec(int)
	 */
	public void exec() {
		for (ExecutableCommands command : commands) {
			command.exec();
		}
	}

	/**
	 * Call the {@link ExecutableCommands#exec(int) exec(int)} methods of all
	 * contained commands with the provided time as the argument
	 * 
	 * @param time Argument for {@link ExecutableCommands#exec(int)}
	 * @see Collection#exec()
	 */
	@Override
	public void exec(int time) {
		for (ExecutableCommands command : commands) {
			command.exec(time);
		}
	}

	/**
	 * Add an {@link ExecutableCommands ExecutableCommand} to the commands contained
	 * in this {@link Collection}
	 * 
	 * @param command The {@linkplain ExecutableCommands ExecutableCommand} to be
	 *                added
	 */
	public void add(ExecutableCommands command) {
		this.commands.add(command);
	};

	/**
	 * Remove the first occurrence of a command equal to the one provided as an
	 * argument from the collection <br>
	 * This method is an alias of <code>commands.remove(command)</code>
	 * 
	 * @param command The {@linkplain ExecutableCommands command} to be removed
	 */
	public void remove(ExecutableCommands command) {
		commands.remove(command);
	}

	/**
	 * Print the result of {@link Collection#getTree() getTree}
	 */
	@Override
	public void printTree() {
		System.out.println(getTree());
	}

	/**
	 * Returns a {@link String} containing a tree showing the hierarchy of
	 * {@link ExecutableCommands} in this Collection
	 */
	@Override
	public String getTree() {
		String res = "Collection";
		for (ExecutableCommands command : commands) {
			String temp = command.getTree();
			temp.replaceAll("\n", "\n|--");
			if (!temp.contains("\n"))
				temp = "|--" + temp;
			res += ((temp.startsWith("Collection")) ? "\n|--" : "\n|  ") + temp;
		}
		return res;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Collection ? getTree().equals(((Collection) obj).getTree()) : false;
	}

	@Override
	public int getTime() {
		int largestTime = 0;
		for (ExecutableCommands c : commands) {
			if (c.getTime() > largestTime)
				largestTime = c.getTime();
		}
		return largestTime;
	}

	@Override
	public void execBlocking() {
		exec();
		try {
			Thread.sleep(getTime());
		} catch (InterruptedException e) {
			if (Hexapode.DEBUGGING)
				e.printStackTrace();
			System.out.println("The thread was interrupted whilst being blocked by a command being executed.");
		}
	}

	@Override
	public void execBlocking(int time) {
		exec(time);
		try {
			Thread.sleep(time + 50);
		} catch (InterruptedException e) {
			if (Hexapode.DEBUGGING)
				e.printStackTrace();
			System.out.println("The thread was interrupted whilst being blocked by a command being executed.");
		}
	}

}
