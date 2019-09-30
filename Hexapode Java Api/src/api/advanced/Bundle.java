package api.advanced;

import api.basic.Hexapode;

public class Bundle extends Hexapode {

	String command;
	int time;

	public Bundle(int time) {
		this.time = time;
	}

	public void add(int servo, int position) {
		this.command = this.command + "#" + servo + "P" + position;
	}

	public void exec() {
		serialcommand(command + " T" + time);
	}

	public void exec(int customtime) {
		this.time = customtime;
		exec();
	}

	public void add(Bundle bundle) {
		this.time = bundle.time;
		this.command = this.command + bundle.command;
	}

	public void settime(int newtime) {
		this.time = newtime;
	}

}
