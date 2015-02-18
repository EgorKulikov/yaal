package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class StartWithCounting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] qty = new int[10001];
		for (int i = 0; i < count; i++) {
			qty[in.readInt()]++;
		}
		int answer = -1;
		int max = 0;
		for (int i = 0; i <= 10000; i++) {
			if (qty[i] > max) {
				max = qty[i];
				answer = i;
			}
		}
		out.printLine(answer, max);
	}
}
