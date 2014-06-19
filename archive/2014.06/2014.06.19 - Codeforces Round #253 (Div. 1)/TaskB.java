package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		double[] probabilities = IOUtils.readDoubleArray(in, count);
		double sum = 0;
		double product = 1;
		Arrays.sort(probabilities);
		if (probabilities[count - 1] > 0.5) {
			out.printLine(probabilities[count - 1]);
			return;
		}
		for (int i = count - 1; i >= 0 && sum < 1; i--) {
			sum += probabilities[i] / (1 - probabilities[i]);
			product *= 1 - probabilities[i];
		}
		out.printLine(sum * product);
	}
}
