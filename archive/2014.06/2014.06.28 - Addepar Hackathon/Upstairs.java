package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Upstairs {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] floor = IOUtils.readIntArray(in, count);
		int base = 0;
		for (int i = 1; i < count; i++) {
			base += Math.max(0, floor[i] - floor[i - 1]);
		}
		int best = base;
		int answer = -1;
		for (int i = 1; i < count; i++) {
			int candidate = base;
			if (i != 1) {
				candidate += Math.max(0, floor[i] - floor[i - 2]) - Math.max(0, floor[i - 1] - floor[i - 2]);
			}
			if (i != count - 1) {
				candidate += Math.max(0, floor[i + 1] - floor[i - 1]) - Math.max(0, floor[i + 1] - floor[i]);
			}
			candidate += Math.max(floor[i - 1] - floor[i], 0) - Math.max(floor[i] - floor[i - 1], 0);
			if (candidate < best) {
				best = candidate;
				answer = i;
			}
		}
		out.printLine(answer);
    }
}
