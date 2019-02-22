package scheduler;

import process.Process;

/**
 * LRUCache class implements a cache policy where the last recently used process
 * will be replaced when the cache is full.
 * 
 * @author razvan
 *
 */
public class LRUCache extends Cache {

	/**
	 * Initialize a less recently used cache policy
	 * 
	 * @param size
	 *            the size of cache
	 */
	public LRUCache(int size) {
		super(size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see scheduler.Cache#updateCache(process.Process)
	 */
	@Override
	protected void updateCache(Process p) {
		ProcessCached[] cache = this.getCache();
		ProcessCached lastProcess = null;

		int cacheSize = this.getCrtLen();
		int minTime = 0;

		if (cacheSize > 0) {
			minTime = cache[0].getTime();
			lastProcess = cache[0];
		}

		for (int i = 1; i < cacheSize; i++) {
			if (cache[i].getTime() < minTime) {
				minTime = cache[i].getTime();
				lastProcess = cache[i];
			}
		}

		if (lastProcess != null) {
			lastProcess.newCachedProcess(p);
		}
	}

}
