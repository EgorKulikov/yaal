package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CorrectChange {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		int q1 = in.readInt();
		int q11 = in.readInt();
		int q111 = in.readInt();
		int target = in.readInt();
		for (int i = 0; i <= q111 && 111 * i <= target; i++) {
			int current = target - 111 * i;
			int take11 = Math.min(q11, current / 11);
			current -= take11 * 11;
			if (current <= q1) {
				out.printLine(true);
				return;
			}
		}
		out.printLine(false);
    }
}
