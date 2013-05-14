package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Restack {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] a = new int[count];
		int[] b = new int[count];
		IOUtils.readIntArrays(in, a, b);
		long[] delta = new long[count];
		long curDelta = 0;
		for (int i = 0; i < count; i++) {
			curDelta += a[i] - b[i];
			delta[i] = curDelta;
		}
		Arrays.sort(delta);
		long median = delta[count / 2];
		for (int i = 0; i < count; i++)
			delta[i] -= median;
		long result = 0;
		for (int i = 0; i < count; i++)
			result += Math.abs(delta[i]);
		out.printLine(result);
	}
}
