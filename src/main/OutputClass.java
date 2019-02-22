package main;


/**
 * OutputClass is designed to store all data needed for
 * output. 
 * 
 * @author razvan
 *
 */
public class OutputClass {
	
	/**
	 * The input value for a process
	 */
	private int input;
	
	/**
	 * The output value for a process
	 */
	private int output;
	
	/**
	 * The type of the process
	 */
	private String typeProcess;
	
	/**
	 * Is cached or computed text
	 */
	private String fromCache;
	
	/**
	 * It is an empty constructor. You have to initialize 
	 * parameters using setters.
	 * 
	 */
	public OutputClass() {
	}
	
	/**
	 * This constructor initializes all data needed to display
	 * all information needed.
	 * 
	 * @param input			the input of the running process
	 * @param output		the output of the running process
	 * @param typeProcess	the type of the process (Cube, Sqrt, Square, 
	 * 						NextPrime, Fibonacci, Factoria, CheckPrime)
	 * @param fromCache		whether is from cache or computed
	 */
	public OutputClass(int input, int output, String typeProcess, String fromCache) {
		this.input = input;
		this.output = output;
		this.typeProcess = typeProcess;
		this.fromCache = fromCache;
	}

	/**	
	 * Getter for input value.
	 * @return input value stored
	 */
	public int getInput() {
		return input;
	}

	/**
	 * Setter for input value.
	 * @param input	new input value
	 */
	public void setInput(int input) {
		this.input = input;
	}

	/**
	 * Getter for output value.
	 * @return	output value stored
	 */
	public int getOutput() {
		return output;
	}

	/**
	 * Setter for output value.
	 * @param output	new output value
	 */
	public void setOutput(int output) {
		this.output = output;
	}

	/**
	 * Getter for type of process.
	 * @return	Cube, Sqrt, Square, NextPrime, 
	 * 			Fibonacci, Factoria, CheckPrime
	 * 			as a string
	 */
	public String getTypeProcess() {
		return typeProcess;
	}

	/**
	 * Setter for type of precess.
	 * @param typeProcess	Should be Cube, Sqrt, 
	 * 						Square, NextPrime, Fibonacci, 
	 * 						Factoria, CheckPrime
	 */
	public void setTypeProcess(String typeProcess) {
		this.typeProcess = typeProcess;
	}

	/**
	 * Getter for fromCache. 
	 * @return	"Computed" or "FromCache" as String
	 */
	public String isFromCache() {
		return fromCache;
	}

	/**
	 * Setter for fromCache
	 * @param fromCache	should be "Computed" or "FromCache" 
	 * 					as String
	 */
	public void setFromCache(String fromCache) {
		this.fromCache = fromCache;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new String(this.input + " " + this.typeProcess + " " + this.output + " " + this.fromCache);
	}

	
}
