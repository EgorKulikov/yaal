package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class ExplosiveGame {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int q = in.readInt();
		int[] a = new int[n];
		int[] b = new int[n];
		IOUtils.readIntArrays(in, a, b);
		int[] delta = new int[n];
		for (int i = 0; i < n; i++) {
			delta[i] = Math.abs(a[i] - b[i]);
		}
		Arrays.sort(delta);
		boolean good = true;
		for (int i = 0; i < n; i += 2) {
			if (delta[i] != delta[i + 1]) {
				good = false;
				break;
			}
		}
		long answer;
		if (good) {
			answer = 0;
			for (int i = 0; i < n; i += 2) {
				answer += delta[i];
			}
			for (int i = 0; i < n; i++) {
				answer += Math.min(a[i], b[i]);
			}
		} else {
			answer = -1;
		}
		for (int i = 0; i < q; i++) {
			if (in.readLong() == answer) {
				out.printLine("YES");
			} else {
				out.printLine("NO");
			}
		}
	}
}
