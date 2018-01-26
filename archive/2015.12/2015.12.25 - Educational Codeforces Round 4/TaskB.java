package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] f = IOUtils.readIntArray(in, n);
		MiscUtils.decreaseByOne(f);
		f = ArrayUtils.reversePermutation(f);
		long answer = 0;
		for (int i = 1; i < n; i++) {
			answer += Math.abs(f[i] - f[i - 1]);
		}
		out.printLine(answer);
	}
}
