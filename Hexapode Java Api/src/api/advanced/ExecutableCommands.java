package api.advanced;

/**
 * The interface that can be implemented to add custom elements to Collections.
 * 
 * <p>
 * This class is implemented by {@link api.advanced.Bundle Bundles} and {@link api.advanced.Collection Collections}.
 * </p>
 * 
 * @author JustAnotherJavaProgrammer
 * @see api.advanced.Collection
 * @see api.advanced.Bundle
 */
public interface ExecutableCommands {
	/**
	 * Execute the Command(s) using the default time <br>
	 * This method does not block the {@linkplain Thread} when invoked.
	 */
	public void exec();

	/**
	 * Execute the Command(s) for a given time <br>
	 * This method does not block the {@linkplain Thread} when invoked.
	 * 
	 * @param time The execution time in milliseconds
	 */
	public void exec(int time);

	/**
	 * Print the tree of Command nodes executed by this Command
	 * 
	 * @see api.advanced.ExecutableCommands#getTree()
	 */
	public void printTree();

	/**
	 * Get the tree of Command nodes executed by this command
	 * 
	 * @return The command tree
	 */
	public String getTree();
}
