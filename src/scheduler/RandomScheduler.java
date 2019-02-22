package scheduler;

/**
 * RandomScheduler class implements a random scheduler where processes are
 * chosen randomly. The PID (the index in process list) is generated randomly,
 * being a number between 0 and number of processes.
 * 
 * @author razvan
 *
 */
public class RandomScheduler extends ProcessScheduler {

	/**
	 * Initialize a random scheduler.
	 * 
	 * @param numProcesses
	 *            number of processes
	 * @param cacheType
	 *            the type of cache
	 * @param cacheSize
	 *            the size of cache
	 */
	public RandomScheduler(int numProcesses, String cacheType, int cacheSize) {
		super(numProcesses, cacheType, cacheSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see scheduler.ProcessScheduler#generatePID()
	 */
	@Override
	protected int generatePID() {
		return (int) Math.floor(Math.random() * this.getNumProcesses());
	}
}
