package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class StrangeFunction {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int c = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		Arrays.sort(a);
		for (int i = 0; i < n / 2; i++) {
			a[i] -= c;
		}
		for (int i = n / 2; i < n; i++) {
			a[i] += c;
		}
		long sum = 0;
		long answer = 0;
		for (int i = 0; i < n; i++) {
			answer += (long)i * a[i] - sum;
			sum += a[i];
		}
		out.printLine(answer);
	}
}
