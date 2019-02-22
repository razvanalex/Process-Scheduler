package scheduler;

import process.Process;

/**
 * Cache class is used for caching system. It has a vector where data is stored
 * using <code>ProcessCached</code> class. If a value is stored, the it will not
 * be computed next time. This increases performance for big numbers.
 * 
 * @author razvan
 *
 */
public abstract class Cache {

	/**
	 * Cache memory as an array
	 */
	private ProcessCached[] cache;

	/**
	 * Current number of elements from cache
	 */
	private int crtLen = 0;

	/**
	 * Create a new empty Cache
	 */
	public Cache() {
		this(0);
	}

	/**
	 * Init Cache size
	 * 
	 * @param size
	 *            max size of cache
	 */
	public Cache(int size) {
		cache = new ProcessCached[size];
	}

	/**
	 * Run a process and check it was called before. If yes, find the value from
	 * cache, otherwise run the process and store the value in cache.
	 * 
	 * @param p
	 *            the process which is going to be running
	 * @return whether or not the value was calculated before (cached)
	 */
	public boolean runProcessUsingCache(Process p) {
		Integer value;
		if ((value = getCachedValue(p)) != null) {
			p.setOutput(value);
			return true;
		} else {
			p.run();
			cacheValue(p);
			return false;
		}
	}

	/**
	 * Find the value in cache and return it if it's found. Otherwise, return null.
	 * 
	 * @param p
	 *            the process which is going to be running
	 * @return the value from cache or null if is not there
	 */
	private Integer getCachedValue(Process p) {
		for (int i = 0; i < crtLen; i++) {
			if (cache[i].equals(p)) {
				cache[i].updateTime();
				cache[i].updateFrequency();
				return cache[i].getOutput();
			}
		}

		return null;
	}

	/**
	 * Cache a process with its value. If the cache is not full, will place the
	 * process there, otherwise will rewrite an old one.
	 * 
	 * @param p
	 *            the process which will be cached
	 */
	private void cacheValue(Process p) {
		if (crtLen < cache.length) {
			cache[crtLen++] = new ProcessCached(p);
		} else {
			updateCache(p);
		}
	}

	/**
	 * Update the cache when it is full. The caching policy is given by type of
	 * cache.
	 * 
	 * @param p
	 *            a process which will be added in cache
	 */
	protected abstract void updateCache(Process p);

	/**
	 * @return the cache of processes and values
	 */
	protected ProcessCached[] getCache() {
		return cache;
	}

	/**
	 * Getter for crtLen.
	 * 
	 * @return return the current number of values in cache
	 */
	protected int getCrtLen() {
		return crtLen;
	}

}

/**
 * ProcessCached is a class which encapsulates the process and its value in one
 * variable. It also stores the frequency and the time spawned. It is used to
 * implement caching policy.
 * 
 * @author razvan
 *
 */
class ProcessCached {
	/**
	 * The input of the process
	 */
	private int input;

	/**
	 * The output of the process
	 */
	private int output;

	/**
	 * Time when the process was spawned. It only counts how many processes was
	 * spawned before current process.
	 */
	private int time;

	/**
	 * Stores the frequency of the current process with its value. The frequency is
	 * computed according to the current state of cache
	 */
	private int frequency;

	/**
	 * Type of the process stored as string. E.g. Square, Cube, NexPrime etc.
	 */
	private String type;

	/**
	 * Its is used as time. It is unique per process.
	 */
	private static int cacheID = 0;

	/**
	 * Initialize a new cached process: input, output, type, frequency set to 0 and
	 * spawn time.
	 * 
	 * @param p
	 *            a process which will be cached
	 */
	public ProcessCached(Process p) {
		this.input = p.getInput();
		this.output = p.getOutput();
		this.type = p.toString();
		this.frequency = 0;

		updateTime();
		updateFrequency();
	}

	/**
	 * Update a cache value, without creating a new instance
	 * 
	 * @param p
	 *            a process which will be cached
	 */
	public void newCachedProcess(Process p) {
		this.input = p.getInput();
		this.output = p.getOutput();
		this.type = p.toString();
		this.frequency = 0;

		updateTime();
		updateFrequency();
	}

	/**
	 * Update the time when a new process of the same type and input is launched.
	 */
	public void updateTime() {
		cacheID++;
		this.time = cacheID;
	}

	/**
	 * Update the frequency when a new process of the same type and input is
	 * launched.
	 */
	public void updateFrequency() {
		this.frequency++;
	}

	/**
	 * Getter for input
	 * 
	 * @return the value of input
	 */
	public int getInput() {
		return this.input;
	}

	/**
	 * Getter for output
	 * 
	 * @return the value of output
	 */
	public int getOutput() {
		return this.output;
	}

	/**
	 * Getter for type
	 * 
	 * @return the value of type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Getter for time
	 * 
	 * @return the value of time
	 */
	public int getTime() {
		return this.time;
	}

	/**
	 * Getter for frequency
	 * 
	 * @return the value of frequency
	 */
	public int getFrequency() {
		return this.frequency;
	}

	/**
	 * Checks if a process is equal to the current instance
	 * 
	 * @param toCheck
	 *            the process to be checked
	 * @return true if both processes (this and toCheck) are the same type and have
	 *         the same input. Otherwise return false.
	 */
	public boolean equals(Process toCheck) {
		if (toCheck.toString().equals(this.type) && toCheck.getInput().equals(this.input)) {
			return true;
		}

		return false;
	}
}
