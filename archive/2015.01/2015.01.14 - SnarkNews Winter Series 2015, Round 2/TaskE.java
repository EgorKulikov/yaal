package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] qty = IOUtils.readIntArray(in, count);
		long[] sum = ArrayUtils.partialSums(qty);
		long[] weightedSum = new long[sum.length];
		for (int i = 1; i <= count; i++) weightedSum[i] = weightedSum[i - 1] + sum[i];
		long[] answer = new long[count];
		answer[0] = sum[count] - sum[1];
		for (int i = 1; i < count; i++) {
			long delta = sum[count] - sum[i + 1];
			answer[i] = answer[i - 1];
			for (int j = i - 2; j >= 0; j--) {
				answer[i] = Math.min(answer[i], answer[j] + weightedSum[i] - weightedSum[j + 1] - (i - j - 1) * sum[j + 1]);
			}
			answer[i] = Math.min(answer[i], weightedSum[i]);
			answer[i] += delta;
		}
		out.printLine(answer[count - 1]);
    }
}
