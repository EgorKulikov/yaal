package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int radius = in.readInt();
		int[] values = IOUtils.readIntArray(in, count);
		long sum = ArrayUtils.sumArray(values);
		double totalSquare = Math.PI * radius * radius;
		for (int value : values)
			out.printf("%.8f\n", totalSquare * value / sum);
	}
}
