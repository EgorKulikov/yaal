package net.egork;

import net.egork.collections.intcollection.IntArray;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Rake {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int jumpCount = in.readInt();
		final int[] required = new int[count];
		final int[] total = new int[count];
		IOUtils.readIntArrays(in, required, total);
		int[] result = new int[jumpCount + 1];
		for (int i = 0; i < count; i++) {
			result[0] += required[i];
			for (int j = 1; j <= jumpCount; j++) {
				int k = Math.max(0, j - total[i]);
				result[k] = Math.min(result[k], result[j] + Math.min(required[i], total[i] - (j - k)));
				result[j] += required[i];
			}
		}
		out.printLine(new IntArray(result).min());
    }
}
