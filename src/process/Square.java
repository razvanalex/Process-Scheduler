package process;

/**
 * Square class computes the square of a given number. This is a process. It
 * uses <code>Math.pow()</code>.
 * 
 * @author razvan
 *
 */
public class Square extends Process {

	/**
	 * Create a new process which computes the square of a number.
	 */
	public Square() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see process.Process#run()
	 */
	@Override
	public void run() {
		this.setOutput((int) Math.pow(this.getInput(), 2));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new String("Square");
	}
}
