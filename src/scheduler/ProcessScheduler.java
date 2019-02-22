package scheduler;

import main.OutputClass;
import process.Process;

/**
 * ProcessScheduler class is an abstract class which manages the processes by a
 * given scheduler type. It has an array of processes, where the index
 * represents the PID of the process; an array of inputs and an array of
 * outputs; the current index of current input and the number of processes.
 * 
 * @author razvan
 *
 */
public abstract class ProcessScheduler {

	/**
	 * An array of running processes. The index represents the PID of the process.
	 */
	private Process[] process;

	/**
	 * An array of inputs. Every input is processed by a process chosen by the
	 * Scheduler
	 */
	private int[] inputs;

	/**
	 * An array of outputs. Is is used to store the output and details about process
	 * which computed the value, and for printing these data to file.
	 */
	private OutputClass[] outputs;

	/**
	 * The current input index form inputs array.
	 */
	private int crtInputIndex;

	/**
	 * The number of all running processes.
	 */
	private int numProcesses;

	/**
	 * The cache memory. It is used to store some values computed before, to
	 * increase performance.
	 */
	private Cache cache;

	/**
	 * Initialize the number of processes, the cache type and its size. Also it
	 * initializes the cache. This constructor may be used when the user wants to
	 * implement its own process scheduler.
	 * 
	 * @param numProcesses
	 *            number of processes
	 * @param cacheType
	 *            the type of cache
	 * @param cacheSize
	 *            the size of cache
	 */
	public ProcessScheduler(int numProcesses, String cacheType, int cacheSize) {
		this.process = new Process[numProcesses];
		numProcesses = 0;
		crtInputIndex = 0;

		initCache(cacheType, cacheSize);
	}

	/**
	 * Add a process in the processes list.
	 * 
	 * @param p
	 *            a process
	 */
	public void addProcess(Process p) {
		if (numProcesses >= process.length)
			return;

		process[numProcesses++] = p;
	}

	/**
	 * Getter for process
	 * 
	 * @return all running processes
	 */
	protected Process[] getProcess() {
		return process;
	}

	/**
	 * Run a process by its ID. This method will check if caching mechanism is
	 * running, if yes the process will be searched in cache, otherwise it will be
	 * run. Also after running/getting the value form cache, the output will be set.
	 * 
	 * @param id
	 *            the PID of process
	 */
	protected void runProcessAtID(int id) {
		boolean fromCache = false;

		process[id].setInput(inputs[this.crtInputIndex]);
		if (cache != null) {
			fromCache = cache.runProcessUsingCache(process[id]);
		}

		outputs[this.crtInputIndex].setInput(inputs[this.crtInputIndex]);
		outputs[this.crtInputIndex].setOutput(process[id].getOutput());
		outputs[this.crtInputIndex].setTypeProcess(process[id].toString());

		if (fromCache) {
			outputs[this.crtInputIndex].setFromCache("FromCache");
		} else {
			outputs[this.crtInputIndex].setFromCache("Computed");
		}
	}

	/**
	 * Getter for number of processes
	 * 
	 * @return the number of processes
	 */
	public int getNumProcesses() {
		return numProcesses;
	}

	/**
	 * Setter for inputs.
	 * 
	 * @param inputs an array of inputs
	 */
	public void setInputs(int[] inputs) {
		this.inputs = inputs;
		this.outputs = new OutputClass[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			this.outputs[i] = new OutputClass();
		}
	}

	/**
	 * Getter for inputs
	 * 
	 * @return an array of inputs
	 */
	public int[] getInputs() {
		return inputs;
	}

	/**
	 * Getter for outputs
	 * 
	 * @return an array of outputs
	 */
	public OutputClass[] getOutputs() {
		return outputs;
	}

	/**
	 * This method increments the index of current input, and return true if this
	 * input exists (is set). Otherwise, false is returned.
	 * 
	 * @return true if there are inputs, otherwise false.
	 */
	public boolean incrementCrtInputIndex() {
		if (inputs != null && crtInputIndex < inputs.length - 1) {
			crtInputIndex++;
			return true;
		}

		return false;
	}

	/**
	 * Getter for the index of current input
	 * 
	 * @return the index of current input
	 */
	protected int getCrtInputIndex() {
		return crtInputIndex;
	}

	/**
	 * This method checks if there are inputs to be processed or processes to
	 * process the inputs.
	 * 
	 * @return true if all is fine. Otherwise return false and print the error to
	 *         <code>System.out</code>
	 */
	protected boolean checkForErrors() {
		if (this.getInputs() == null) {
			System.out.println("Error: No input available to be precessed!");
			return false;
		}

		if (this.getProcess() == null) {
			System.out.println("Error: No processes running!");
			return false;
		}

		return true;
	}

	/**
	 * This method runs the scheduler. It checks for some errors and then it runs
	 * processes, governed by an implemented scheduler, for each input.
	 */
	public void runScheduler() {
		if (this.checkForErrors() == false) {
			return;
		}

		do {
			int PID = generatePID();
			this.runProcessAtID(PID);

		} while (this.incrementCrtInputIndex());
	}

	/**
	 * Generate a PID according to some scheduler policy. This method must be
	 * implemented in order to run the scheduler.
	 * 
	 * @return a PID generated.
	 */
	protected abstract int generatePID();

	/**
	 * This method initializes the cache according to its parameters.
	 * 
	 * @param cacheType
	 *            the type of cache. It may be "NoCache", "LruCache" or "LfuCache"
	 * @param cacheSize
	 *            the size of cache.
	 */
	private void initCache(String cacheType, int cacheSize) {
		switch (cacheType) {
		case "NoCache":
		default:
			cache = new NoCache();
			break;
		case "LruCache":
			cache = new LRUCache(cacheSize);
			break;
		case "LfuCache":
			cache = new LFUCache(cacheSize);
			break;
		}
	}

}
