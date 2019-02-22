package process;

/**
 * Sqrt class computes the square root of a given number treated as a positive
 * one and then round it to the largest integer less than or equal to the given
 * number. This is a process. It uses <code>Math.floor()</code>,
 * <code>Math.sqrt()</code> and <code>Math.abs()</code>.
 * 
 * @author razvan
 *
 */
public class Sqrt extends Process {

	/**
	 * Create a nu process which computes the square root of a | number | and then
	 * round it.
	 */
	public Sqrt() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see process.Process#run()
	 */
	@Override
	public void run() {
		this.setOutput((int) Math.floor(Math.sqrt(Math.abs(this.getInput()))));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new String("Sqrt");
	}
}
