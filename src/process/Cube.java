package process;

/**
 * Cube class computes the cube of a number. It uses <code>Math.pow()</code> to
 * compute it. This class is a process.
 * 
 * @author razvan
 *
 */
public class Cube extends Process {

	/**
	 * Create a new process for computing the cube.
	 */
	public Cube() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see process.Process#run()
	 */
	@Override
	public void run() {
		this.setOutput((int) Math.pow(this.getInput(), 3));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new String("Cube");
	}

}
