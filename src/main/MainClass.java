package main;

/**
 * MainClass is the entry point class of the program. It contains only
 * <code>main()</code> function.
 * 
 * @author razvan
 *
 */
public class MainClass {

	/**
	 * This is the entry point of the program. It will try to open the two files
	 * given as parameter and will process the data. The output will be written into
	 * the output file. If no parameters are given, an exception will be triggered.
	 * 
	 * @param args
	 *            an array of strings witch contains input file name and output file
	 *            name
	 * 
	 */
	public static void main(String[] args) {
		try {
			HomeworkReader hr = new HomeworkReader(args[0]);
			HomeworkWriter hw = new HomeworkWriter(args[1]);

			OutputClass[] output = hr.readData().createOutput();
			for (int i = 0; i < output.length; i++) {
				hw.println(output[i].toString());
			}

			hr.close();
			hw.close();
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("Error: No arguments given!");
		}
	}
}
