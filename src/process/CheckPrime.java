package process;

/**
 * This class is a process which checks if a number is prime or not. The
 * algorithm used is straightforward: check all numbers until square root of it,
 * if there is a divisor. For negative numbers, the answer is no.
 * 
 * @author razvan
 *
 */
public class CheckPrime extends Process {

	/**
	 * Create a new process which checks if a number is prime or not.
	 */
	public CheckPrime() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see process.Process#run()
	 */
	@Override
	public void run() {
		int half = (int) Math.ceil(Math.sqrt(this.getInput()));

		if (this.getInput() < 2) {
			this.setOutput(0);
			return;
		}

		if (this.getInput() == 2) {
			this.setOutput(1);
			return;
		}

		this.setOutput(1);
		for (int i = 2; i <= half; i++) {
			if (this.getInput() % i == 0) {
				this.setOutput(0);
				return;
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
		return new String("CheckPrime");
	}

}
