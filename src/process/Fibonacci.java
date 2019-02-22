package process;

/**
 * Fibonacci class computes the nth number form this series. For negative index,
 * the result is -1; for 0, the result is 0. This is a process.
 * 
 * @author razvan
 *
 */
public class Fibonacci extends Process {
	/**
	 * The maximum value of numbers to avoid <code>int</code> overflow.
	 */
	private final int maxValue = 9973;

	/**
	 * Create a new process which computes nth number form Fibonacci series.
	 */
	public Fibonacci() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see process.Process#run()
	 */
	@Override
	public void run() {
		if (this.getInput() < 0) {
			this.setOutput(-1);
			return;
		}

		if (this.getInput() == 0) {
			this.setOutput(0);
			return;
		}

		int n1 = 0;
		int n2 = 1;

		for (int i = 1; i < this.getInput(); i++) {
			n1 += n2;
			n1 %= maxValue;

			int tmp = n2;
			n2 = n1;
			n1 = tmp;
		}

		this.setOutput(n2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new String("Fibonacci");
	}
}
