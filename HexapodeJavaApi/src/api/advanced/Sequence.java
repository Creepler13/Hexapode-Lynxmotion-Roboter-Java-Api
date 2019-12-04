package api.advanced;

public class Sequence extends Collection {

	public Sequence() {
		super();
	}

	public Sequence(ExecutableCommands[] commands) {
		super(commands);
	}

	public static Sequence fromCollection(Collection c) {
		return new Sequence(c.getCommands().toArray(new ExecutableCommands[0]));
	}

	@Override
	public void exec() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				execBlocking();
			}
		}).start();
	}

	@Override
	public void exec(int time) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				execBlocking(time);
			}
		}).start();
	}

	@Override
	public void execBlocking() {
		for (ExecutableCommands command : getCommands()) {
			command.execBlocking();
		}
	}

	@Override
	public void execBlocking(int time) {
		for (ExecutableCommands command : getCommands()) {
			command.execBlocking(time);
		}
	}
}
