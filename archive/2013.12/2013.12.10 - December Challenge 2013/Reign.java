package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Reign {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int delta = in.readInt();
		int[] prosperity = IOUtils.readIntArray(in, count);
		long minSum = 0;
		long sum = 0;
		long[] max = new long[count];
		Arrays.fill(max, Long.MIN_VALUE);
		for (int i = 0; i < count; i++) {
			if (i != 0)
				max[i] = max[i - 1];
			sum += prosperity[i];
			max[i] = Math.max(max[i], sum - minSum);
			minSum = Math.min(minSum, sum);
		}
		minSum = 0;
		sum = 0;
		long answer = Long.MIN_VALUE;
		for (int i = count - 1; i > delta; i--) {
			sum += prosperity[i];
			answer = Math.max(answer, max[i - delta - 1] + sum - minSum);
			minSum = Math.min(minSum, sum);
		}
		out.printLine(answer);
    }
}
