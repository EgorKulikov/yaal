package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int x = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		int[] fromStart = new int[n + 1];
		for (int i = 0; i < n; i++) {
			fromStart[i + 1] = fromStart[i] | a[i];
		}
		int[] fromEnd = new int[n + 1];
		for (int i = n - 1; i >= 0; i--) {
			fromEnd[i] = fromEnd[i + 1] | a[i];
		}
		long answer = 0;
		for (int i = 0; i < n; i++) {
			long current = fromStart[i] | fromEnd[i + 1];
			long val = a[i];
			for (int j = 0; j < k; j++) {
				val *= x;
			}
			answer = Math.max(answer, current | val);
		}
		out.printLine(answer);
	}
}
