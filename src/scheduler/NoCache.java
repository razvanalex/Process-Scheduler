package scheduler;

import process.Process;

/**
 * NoCache class does not implement any caching mechanism. The cache array has 0
 * elements.
 * 
 * @author razvan
 *
 */
public class NoCache extends Cache {
	
	/**
	 * Initialize an empty cache and do not use cache at all.
	 */
	public NoCache() {
		super(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see scheduler.Cache#updateCache(process.Process)
	 */
	@Override
	protected void updateCache(Process p) {
		// Do nothing...
	}
}
