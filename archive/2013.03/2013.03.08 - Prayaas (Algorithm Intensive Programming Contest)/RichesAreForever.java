package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RichesAreForever {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int min = in.readInt();
		int[] value = IOUtils.readIntArray(in, count);
		long left = 0;
		long right = (long) 2e9;
		long[] sum = new long[count + 1];
		while (left < right) {
			long middle = (left + right + 1) >> 1;
			for (int i = 0; i < min; i++)
				sum[i + 1] = sum[i] + value[i] - middle;
			long best = sum[min];
			long total = best;
			for (int i = min; i < count; i++) {
				sum[i + 1] = sum[i] + value[i] - middle;
				best += value[i] - middle;
				best = Math.max(best, sum[i + 1] - sum[i - min + 1]);
				total = Math.max(total, best);
			}
			if (total >= 0)
				left = middle;
			else
				right = middle - 1;
		}
		out.printLine(left);
    }
}
