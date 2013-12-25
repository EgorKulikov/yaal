package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		long[] result = new long[count];
		Arrays.fill(result, (long) 1e9);
		long[] delta = new long[count];
		int[] type = new int[queryCount];
		int[] from = new int[queryCount];
		int[] to = new int[queryCount];
		int[] variable = new int[queryCount];
		IOUtils.readIntArrays(in, type, from, to, variable);
		MiscUtils.decreaseByOne(from);
		for (int i = 0; i < queryCount; i++) {
			if (type[i] == 1) {
				for (int j = from[i]; j < to[i]; j++)
					delta[j] += variable[i];
			} else {
				for (int j = from[i]; j < to[i]; j++)
					result[j] = Math.min(result[j], variable[i] - delta[j]);
			}
		}
		for (int i = 0; i < count; i++) {
			if (Math.abs(result[i]) > 1e9) {
				out.printLine("NO");
				return;
			}
		}
		System.arraycopy(result, 0, delta, 0, count);
		for (int i = 0; i < queryCount; i++) {
			if (type[i] == 1) {
				for (int j = from[i]; j < to[i]; j++)
					delta[j] += variable[i];
			} else {
				long max = Long.MIN_VALUE;
				for (int j = from[i]; j < to[i]; j++)
					max = Math.max(max, delta[j]);
				if (max != variable[i]) {
					out.printLine("NO");
					return;
				}
			}
		}
		out.printLine("YES");
		out.printLine(result);
    }
}
