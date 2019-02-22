package scheduler;

import process.Process;

/**
 * LFUCache implements a less frequency used cache policy. When the cache is
 * full, the caching mechanism will find the process with was used less times
 * and it will be replaced with a new process. When two cached processes have
 * the same frequency, the older one is replaced.
 * 
 * @author razvan
 *
 */
public class LFUCache extends Cache {

	/**
	 * Initialize a LFU cache policy
	 * 
	 * @param size
	 *            the size of cache
	 */
	public LFUCache(int size) {
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
		ProcessCached lessUsedProcess = null;

		int cacheSize = this.getCrtLen();
		int minFreq = 0;
		int minTime = 0;

		if (cacheSize > 0) {
			minFreq = cache[0].getFrequency();
			lessUsedProcess = cache[0];
		}

		for (int i = 0; i < cacheSize; i++) {
			if (cache[i].getFrequency() < minFreq) {
				minFreq = cache[i].getFrequency();
			}
		}

		for (int i = 0; i < cacheSize; i++) {
			if (cache[i].getFrequency() == minFreq) {
				if (minTime == 0) {
					minTime = cache[i].getTime();
					lessUsedProcess = cache[i];
					continue;
				}

				if (cache[i].getTime() < minTime) {
					minFreq = cache[i].getFrequency();
					lessUsedProcess = cache[i];
				}
			}
		}

		if (lessUsedProcess != null) {
			lessUsedProcess.newCachedProcess(p);
		}
	}

}
