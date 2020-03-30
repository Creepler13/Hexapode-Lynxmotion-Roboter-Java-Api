package api.advanced;

/**
 * A tool to combine multiple {@link ExecutableCommands} into one object, which
 * runs one command after another.
 * 
 * @author JustAnotherJavaProgrammer
 * @see test.Example5
 * @see ExecutableCommands
 * @see Bundle
 * @see Collection
 */
public class Sequence extends Collection {

	/**
	 * Create a new, empty {@linkplain Sequence}
	 */
	public Sequence() {
		super();
	}

	/**
	 * Create a sequence and initialize it with the {@link ExecutableCommands}
	 * contained in the array
	 * 
	 * @param commands An array of {@linkplain ExecutableCommands}
	 */
	public Sequence(ExecutableCommands[] commands) {
		super(commands);
	}

	/**
	 * Creates a new sequence by taking the {@linkplain ExecutableCommands} from a {@linkplain Collection}
	 * and initializing a new {@linkplain Sequence} using them
	 * 
	 * @param c The {@linkplain Collection} to be converted
	 * @return The newly generated {@linkplain Collection}
	 */
	public static Sequence fromCollection(Collection c) {
		return new Sequence(c.getCommands().toArray(new ExecutableCommands[0]));
	}

	/**
	 * A version of {@link Sequence#execBlocking() execBlocking()} running in a separate {@linkplain Thread}
	 * 
	 * @see Sequence#execBlocking()
	 */
	@Override
	public void exec() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				execBlocking();
			}
		}).start();
	}

	/**
	 * A version of {@link Sequence#execBlocking(int) execBlocking(int)} running in a separate {@linkplain Thread}
	 * 
	 * @see Sequence#execBlocking(int)
	 */
	@Override
	public void exec(int time) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				execBlocking(time);
			}
		}).start();
	}

	/**
	 * Call the {@link ExecutableCommands#execBlocking() execBlocking()} methods of all contained
	 * commands
	 * 
	 * @see ExecutableCommand#execBlocking()
	 */
	@Override
	public void execBlocking() {
		for (ExecutableCommands command : getCommands()) {
			command.execBlocking();
		}
	}

	
	/**
	 * Call the {@link ExecutableCommands#execBlocking(int) execBlocking(int)} methods of all
	 * contained commands with the provided time as the argument
	 * 
	 * @param time Argument for {@link ExecutableCommands#execBlocking(int)}
	 * @see ExecutableCommands#execBlocking(int)
	 */
	@Override
	public void execBlocking(int time) {
		for (ExecutableCommands command : getCommands()) {
			command.execBlocking(time);
		}
	}
}
