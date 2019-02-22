package process;

/**
 * Factorial class which computes the factorial of a number. It is a process. If
 * the result is greater than <code>maxValue</code>, then it will be applied
 * modulo to the result. For negative numbers, the result will be 0.
 * 
 * @author razvan
 *
 */
public class Factorial extends Process {
	/**
	 * The maximum value to avoid <code>int</code> overflow.
	 */
	private final int maxValue = 9973;

	/**
	 * Create a new process which computes the factorial.
	 */
	public Factorial() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see process.Process#run()
	 */
	@Override
	public void run() {
		if (this.getInput() < 0)
			this.setOutput(0);
		else {
			this.setOutput(1);
			for (int i = 2; i <= this.getInput(); i++) {
				this.setOutput(this.getOutput() * i);
				this.setOutput(this.getOutput() % maxValue);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new String("Factorial");
	}

}
