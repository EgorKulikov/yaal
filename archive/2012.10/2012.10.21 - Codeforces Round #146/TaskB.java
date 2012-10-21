package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		double[] probabilities = IOUtils.readDoubleArray(in, count);
		double sum = 0;
		double answer = 0;
		for (double p : probabilities) {
			answer += p * (1 + 2 * sum);
			sum = p * (1 + sum);
		}
		out.printLine(answer);
	}
}
