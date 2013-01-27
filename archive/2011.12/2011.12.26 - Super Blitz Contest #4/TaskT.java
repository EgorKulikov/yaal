package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskT {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt();
		long[] fibonacci = IntegerUtils.generateFibonacci(index, -1);
		StringBuilder result = new StringBuilder();
		for (long number : fibonacci) {
			result.append(number);
			if (result.length() >= index)
				break;
		}
		out.printLine(result.charAt(index - 1));
	}
}
