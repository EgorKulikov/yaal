package net.egork.io;

import java.io.PrintWriter;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class IOUtils {
	public static void printArray(int[] array, PrintWriter out) {
		if (array.length == 0) {
			out.println();
			return;
		}
		out.print(array[0]);
		for (int i = 1; i < array.length; i++)
			out.print(" " + array[i]);
		out.println();
	}
}
