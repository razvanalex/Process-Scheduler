package process;

/**
 * NextPrime class computes the next prime of a given number. This is a process.
 * It take all odd numbers greater than the given one, and checks if it's prime.
 * If it is prime, return it as output. Otherwise keep seeking.
 * 
 * @author razvan
 *
 */
public class NextPrime extends Process {

	/**
	 * Create a new process witch find the next prime number greater than the given
	 * number.
	 */
	public NextPrime() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see process.Process#run()
	 */
	@Override
	public void run() {
		if (this.getInput() < 2) {
			this.setOutput(2);
			return;
		}

		this.setOutput(this.getInput() + 1);

		while (!isPrime(this.getOutput())) {
			if (this.getOutput() % 2 == 0)
				this.setOutput(this.getOutput() + 1);
			else
				this.setOutput(this.getOutput() + 2);
		}

	}

	/**
	 * It takes all numbers less than square root of n and checks if the number is
	 * divisor of n. If yes, return false. Otherwise return keep searching until
	 * no number is found, then return true.
	 * 
	 * @param n
	 *            the given number to be checked
	 * @return if number is prime or not
	 */
	private boolean isPrime(int n) {
		if (n == 2)
			return true;

		int half = (int) Math.ceil(Math.sqrt(n));

		for (int i = 2; i <= half; i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new String("NextPrime");
	}
}
