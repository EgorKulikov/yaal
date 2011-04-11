package net.egork.timus;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1506 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int length = in.readInt();
		int columnCount = in.readInt();
		int[] numbers = in.readIntArray(length);
		int rowCount = (length + columnCount - 1) / columnCount;
		for (int i = 0; i < rowCount; i++) {
			for (int j = i; j < length; j += rowCount)
				out.printf("%4d", numbers[j]);
			out.println();
		}
	}
}

