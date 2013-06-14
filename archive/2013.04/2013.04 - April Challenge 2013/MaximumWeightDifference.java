package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class MaximumWeightDifference {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int size = in.readInt();
		size = Math.min(size, count - size);
		int[] weights = IOUtils.readIntArray(in, count);
		Arrays.sort(weights);
		int answer = 0;
		for (int i = 0; i < size; i++)
			answer -= weights[i];
		for (int i = size; i < count; i++)
			answer += weights[i];
		out.printLine(answer);
	}
}
