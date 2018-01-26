package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int s = in.readInt();
		int[] dist = IOUtils.readIntArray(in, n);
		Arrays.sort(dist);
		int answer = 0;
		int quality = 2 * dist[0];
		for (int i = 1; i < n; i++) {
			int candidate = dist[i] - dist[i - 1];
			if (candidate > quality) {
				quality = candidate;
				answer = dist[i] + dist[i - 1];
			}
		}
		int candidate = 2 * (s - dist[n - 1]);
		if (candidate > quality) {
			quality = candidate;
			answer = 2 * s;
		}
		out.printLine(answer / 2d);
	}
}
