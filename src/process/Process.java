package process;

/**
 * Process class. It has an input, and output and a way of running.
 * 
 * @author razvan
 *
 */
public abstract class Process {
	/**
	 * The input of a process
	 */
	private Integer input = null;
	/**
	 * The output of a process
	 */
	private Integer output = null;
	/**
	 * The weight of a process to be called
	 */
	private int weight = 1;

	/**
	 * Create a new generic process.
	 */
	public Process() {
	}

	/**
	 * Run the process. It will get the input, compute and then store the result in
	 * output. If there is no class that extends Process, the user has to define its
	 * way to run the process. Use <code>getInput()</code>, <code>setInput()</code>,
	 * <code>getOutput()</code> and <code>setOutput()</code> to access the input and
	 * output.
	 */
	public abstract void run();

	/**
	 * Getter for input
	 * 
	 * @return the input
	 */
	public Integer getInput() {
		return input;
	}

	/**
	 * Setter for input
	 * 
	 * @param input new input
	 */
	public void setInput(Integer input) {
		this.input = input;
	}

	/**
	 * Getter for output
	 * @return the output
	 */
	public int getOutput() {
		return this.output;
	}

	/**
	 * Setter for output
	 * @param output new output
	 */
	public void setOutput(Integer output) {
		this.output = output;
	}

	/**
	 * Getter for weight
	 * @return the weight of the process
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Setter for weight
	 * @param weight a new weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

}
