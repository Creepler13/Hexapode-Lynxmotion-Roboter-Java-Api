package api.advanced;

import api.basic.Hexapode;

public class Bundle implements ExecutableCommands {

	private String command = "";
	private int time;
	Hexapode hexapod;

	public Bundle(int time) {
		hexapod = Hexapode.getInstance();
		this.time = time;
	}

	public void add(int servo, int position) {
		this.command = this.command + "#" + servo + "P" + position;
	}

	public void exec(int customTime) {
		hexapod.serialcommand(getCommand());
	}

	public void exec() {
		exec(time);
	}

	public void setTime(int newtime) {
		this.time = newtime;
	}

	public int getTime() {
		return time;
	}

	public void print() {
		System.out.println(command + "time: " + time);
	}

	/**
	 * Get the command currently represented by this bundle (witout time).
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

	@Override
	public void printTree() {
		System.out.println(getTree());
	}

	@Override
	public String getTree() {
		return getCommand();
	}

}
