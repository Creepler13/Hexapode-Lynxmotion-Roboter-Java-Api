package api.advanced;

import java.util.ArrayList;

import api.basic.Hexapode;

/**
 * A powerful tool to combine instructions to multiple servos and execute them
 * in parallel
 * <p>
 * All instructions inside a Bundle have the same execution time. To combine
 * instructions with multiple execution times, you can combine multiple Bundles
 * into a {@link Collection}.
 * </p>
 * <p>
 * Tip: There also is a convenience class to easily create Bundles for the most
 * common use cases. It's named {@link BundleCreator}.
 * </p>
 * 
 * @author JustAnotherJavaProgrammer &amp; Creepler13
 * @see test.Example2
 * @see BundleCreator
 * @see ExecutableCommands
 * @see Collection
 */
public class Bundle implements ExecutableCommands {

	private ArrayList<Integer> servos = new ArrayList<>();
	private String command = "";
	private int time;
	private Hexapode hexapod;

	/**
	 * Create a new {@linkplain api.advanced.Bundle} with the specified default
	 * execution time
	 * 
	 * @param time The default execution time, used when invoking
	 *             {@link api.advanced.Bundle#exec() exec()}
	 */
	public Bundle(int time) {
		hexapod = Hexapode.getInstance();
		this.time = time;
	}

	/**
	 * Add an instruction to a servo to the Bundle <br>
	 * <b>Note:</b> This method does not throw or warn you if this Bundle already
	 * contains an instruction to this servo.<br>
	 * <b>Note:</b> This method does not throw or warn you if the targeted position
	 * is out of range.
	 * 
	 * @param servo    The pin of the servo which should be added to the Bundle
	 * @param position The targeted position of the servo ranging from 0 to 1500
	 */
	public void add(int servo, int position) {
		this.command = this.command + "#" + servo + "P" + position;
		this.servos.add(servo);
	}

	/**
	 * 
	 * add a bundle to another bundle
	 * 
	 * @param bundle
	 */
	public void add(Bundle bundle) {
		ArrayList<Integer> tmp = new ArrayList<>();
		boolean test = true;
		String[] servos = bundle.command.split("#");
		System.out.println(servos[0]);
		for (int i = 1; i < servos.length; i++) {
			for (int j = 0; j < this.servos.size(); j++) {
				if (servos[i].split("P")[0] != "" + this.servos.get(j)) {
				} else {
					test = false;
					// ist schon in der basis benutzt worden
				}
			}
			if (test) {
				tmp.add(Integer.parseInt(servos[i].split("P")[0]));
				tmp.add(Integer.parseInt(servos[i].split("P")[1]));
			} else {
				test = true;
			}
		}
		for (int i = 0; i < tmp.size(); i = i + 2) {
			this.add(tmp.get(i), tmp.get(i + 1));
		}
	}

	/**
	 * Remove all instructions to the specified servo from the
	 * {@linkplain api.advanced.Bundle Bundle}
	 * 
	 * @param servo The pin of the servo which should be removed
	 */
	public void remove(int servo) {
		command.replaceAll("#" + servo + "P/\\d+/", "");
	}

	/**
	 * {@inheritDoc }
	 */
	public void exec(int customTime) {
//		System.out.println("Now executing command: " + getCommand());
		hexapod.exec(getCommand());
	}

	/**
	 * Execute the Bundle for the default time specified in the constructor <br>
	 * This method does not block the {@linkplain Thread} when invoked.
	 * 
	 * @see Bundle#exec(int)
	 * @see Bundle#setTime(int)
	 */
	public void exec() {
		exec(time);
	}

	/**
	 * Set a new default execution time
	 * 
	 * @param newTime The new default execution time in milliseconds
	 * @see Bundle#getTime()
	 */
	public void setTime(int newTime) {
		this.time = newTime;
	}

	/**
	 * Get the default execution time specified in the constructor or in
	 * {@link Bundle#setTime(int) setTime}
	 * 
	 * @return The default execution time of this Bundle
	 * @see Bundle#setTime(int)
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Get the command currently represented by this bundle (without time).
	 * 
	 * @return The raw command
	 */
	public String getRawCommand() {
		return command;
	}

	/**
	 * Get the command represented by this Bundle.
	 * 
	 * @return The command that will be executed by exec().
	 */
	public String getCommand() {
		return command + " T" + time;
	}

	/**
	 * Prints the result of {@link Bundle#getTree() getTree}
	 * 
	 * @see Bundle#getCommand()
	 */
	@Override
	public void printTree() {
		System.out.println(getTree());
	}

	/**
	 * Alias for {@link Bundle#getCommand() getCommand}
	 * 
	 * @see Bundle#getCommand()
	 */
	@Override
	public String getTree() {
		return getCommand();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Bundle ? getCommand().equals(((Bundle) obj).getCommand()) : false;
	}

}
