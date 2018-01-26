package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Feast {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int t = in.readInt();
		int a = in.readInt();
		int b = in.readInt();
		int[] previous = new int[t + 1];
		boolean[] achievable = new boolean[a];
		achievable[0] = true;
		for (int i = 1; i <= t; i++) {
			previous[i] = previous[i - 1];
			if (i % b == 0 || achievable[i % a]) {
				achievable[i % a] = true;
				previous[i] = i;
			}
		}
		int answer = 0;
		for (int i = 0; i <= t; i++) {
			if (previous[i] == i) {
				answer = Math.max(answer, i / 2 + previous[t - i / 2]);
			}
		}
		out.printLine(answer);
	}
}
