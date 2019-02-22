package scheduler;

import process.Process;

/**
 * WeightedScheduler class implements a weighted scheduler where any process is
 * run by a given weight.
 * 
 * @author razvan
 *
 */
public class WeightedScheduler extends ProcessScheduler {

	/**
	 * an array of current weights. It is used to store current weights, and when
	 * all weights are 0, the array will be reinitialized to maximum weights.
	 */
	private int[] crtWeights;

	/**
	 * Create a new instance of weighted scheduler. Also initialize all current
	 * weights to 0.
	 * 
	 * @param numProcesses
	 *            the number of processes
	 * @param cacheType
	 *            the type of cache
	 * @param cacheSize
	 *            the size of cache
	 */
	public WeightedScheduler(int numProcesses, String cacheType, int cacheSize) {
		super(numProcesses, cacheType, cacheSize);

		crtWeights = new int[numProcesses];

		for (int i = 0; i < numProcesses; i++) {
			crtWeights[i] = 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see scheduler.ProcessScheduler#generatePID()
	 */
	@Override
	protected int generatePID() {
		Process[] processes = this.getProcess();

		if (allWeightsAreZero()) {
			initAllWeights(processes);
		}

		int[] availablesPIDs = getAvailablesPIDs(processes.length);
		int PID = chooseRndPID(availablesPIDs);
		crtWeights[PID]--;

		return PID;
	}

	/**
	 * Initialize all weights to maximum values and reduce them by their greatest
	 * common divisor.
	 * 
	 * @param processes
	 *            an array of all processes
	 */
	private void initAllWeights(Process[] processes) {
		int gcd = gcdWeights(processes);

		for (int i = 0; i < crtWeights.length; i++) {
			crtWeights[i] = processes[i].getWeight() / gcd;
		}

	}

	/**
	 * Computes the greatest common divisor between two numbers.
	 * 
	 * @param a
	 *            first number
	 * @param b
	 *            second number
	 * @return the greatest common divisor between a and b
	 */
	private int gcdNumbers(int a, int b) {
		while (b > 0) {
			int tmp = b;
			b = a % b;
			a = tmp;
		}

		return a;
	}

	/**
	 * Find the gcd of all weights
	 * 
	 * @param processes
	 *            all processes
	 * @return the gcd
	 */
	private int gcdWeights(Process[] processes) {
		int gcd = processes[0].getWeight();

		for (int i = 0; i < processes.length; i++) {
			gcd = gcdNumbers(gcd, processes[i].getWeight());
		}

		return gcd;
	}

	/**
	 * Go though all current weights and store the PID where the weight is not 0.
	 * When the array has been finished, fill remaining space by -1.
	 * 
	 * @param numProcesses
	 *            the number of processes
	 * @return an array all PIDs where weights is not 0
	 */
	private int[] getAvailablesPIDs(int numProcesses) {
		int[] PID = new int[numProcesses];
		int crtIndex = 0;

		for (int i = 0; i < numProcesses; i++) {
			if (crtWeights[i] != 0) {
				PID[crtIndex++] = i;
			}
		}

		while (crtIndex < numProcesses) {
			PID[crtIndex++] = -1;
		}

		return PID;
	}

	/**
	 * This method computes a randomly weighted value between the PIDs where weights
	 * are different form 0. Firstly count the number of available PIDs, then choose
	 * a random PID in that range.
	 * 
	 * @param availablePIDs
	 *            an array of available PIDs. It the array is not full, the empty
	 *            values will be set to -1 by the user.
	 * @return a randomly weighted PID.
	 */
	private int chooseRndPID(int[] availablePIDs) {
		int PID = 0;
		int maxIndex = 0;

		for (int i = 0; i < availablePIDs.length; i++) {
			if (availablePIDs[i] != -1) {
				maxIndex++;
			} else
				break;
		}

		int rndIndex = (int) Math.floor(Math.random() * maxIndex);
		PID = availablePIDs[rndIndex];

		return PID;
	}

	/**
	 * Checks is all weights are set to 0.
	 * 
	 * @return true if all weights are 0, otherwise false
	 */
	private boolean allWeightsAreZero() {
		for (int i = 0; i < crtWeights.length; i++) {
			if (crtWeights[i] != 0)
				return false;
		}

		return true;
	}
}
