package net.egork.io;

import java.io.PrintWriter;
import java.util.Collection;

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

	public static<T> void printList(Collection<T> collection, PrintWriter out) {
		boolean isFirst = true;
		for (T element : collection) {
			if (isFirst)
				isFirst = false;
			else
				out.print(" ");
			out.print(element);
		}
		out.println();
	}
}
