/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import process.Process;
import process.*;
import scheduler.ProcessScheduler;
import scheduler.*;

/**
 *
 * @author alexm
 * @author razvan
 * 
 */
public class ProblemData {

	private String cacheType;
	private int cacheCapacity;
	private String schedulerType;
	private ProcessStructure[] processes;
	private int[] numbersToBeProcessed;

	public ProblemData(String cacheType, int cacheCapacity, String schedulerType, ProcessStructure[] processes,
			int[] numbersToBeProcessed) {
		this.cacheType = cacheType;
		this.cacheCapacity = cacheCapacity;
		this.schedulerType = schedulerType;
		this.processes = processes;
		this.numbersToBeProcessed = numbersToBeProcessed;
	}

	/**
	 * @return the cacheType
	 */
	public String getCacheType() {
		return cacheType;
	}

	/**
	 * @return the cacheCapacity
	 */
	public int getCacheCapacity() {
		return cacheCapacity;
	}

	/**
	 * @return the schedulerType
	 */
	public String getSchedulerType() {
		return schedulerType;
	}

	/**
	 * @return the processes
	 */
	public ProcessStructure[] getProcesses() {
		return processes;
	}

	/**
	 * @return the numbersToBeProcessed
	 */
	public int[] getNumbersToBeProcessed() {
		return numbersToBeProcessed;
	}

	/**
	 * Create all processes and the scheduler form data from a given file, run all
	 * processes governed by the scheduler and returns the values given by the
	 * processes.
	 * 
	 * @return a vector of outputs from processes
	 */
	public OutputClass[] createOutput() {
		ProcessScheduler scheduler;
		int numOfProcesses = processes.length;

		switch (schedulerType) {
		case "RandomScheduler":
			scheduler = new RandomScheduler(numOfProcesses, this.cacheType, this.cacheCapacity);
			break;
		case "RoundRobinScheduler":
			scheduler = new RoundRobinScheduler(numOfProcesses, this.cacheType, this.cacheCapacity);
			break;
		case "WeightedScheduler":
			scheduler = new WeightedScheduler(numOfProcesses, this.cacheType, this.cacheCapacity);
			break;
		default:
			scheduler = null;
		}

		for (int i = 0; i < numOfProcesses; i++) {
			Process process;

			switch (processes[i].getType()) {
			case "CheckPrime":
				process = new CheckPrime();
				break;
			case "NextPrime":
				process = new NextPrime();
				break;
			case "Cube":
				process = new Cube();
				break;
			case "Square":
				process = new Square();
				break;
			case "Sqrt":
				process = new Sqrt();
				break;
			case "Factorial":
				process = new Factorial();
				break;
			case "Fibonacci":
				process = new Fibonacci();
				break;
			default:
				process = null;
			}

			process.setWeight(processes[i].getWeight());
			scheduler.addProcess(process);
		}

		scheduler.setInputs(numbersToBeProcessed);
		scheduler.runScheduler();

		return scheduler.getOutputs();
	}
}
