package api.advanced;

import java.util.ArrayList;

public class Collection implements ExecutableCommands {

	ArrayList<ExecutableCommands> commands = new ArrayList<ExecutableCommands>();

	public Collection() {

	}

	public Collection(ExecutableCommands[] commands) {
		for (ExecutableCommands command : commands) {
			this.commands.add(command);
		}
	}

	public void exec() {
		for (ExecutableCommands command : commands) {
			System.out.println(command);
			// command.exec();
		}
	}

	@Override
	public void exec(int time) {
		for (ExecutableCommands command : commands) {
			command.exec(time);
		}
	}

	public void add(ExecutableCommands commands) {
		this.commands.add(commands);
	};

	@Override
	public void printTree() {
		System.out.println(getTree());
	}

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

}
