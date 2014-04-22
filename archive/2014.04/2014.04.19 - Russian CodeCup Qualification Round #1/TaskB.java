package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long start = in.readLong();
		long mod = in.readLong();
		long tries = in.readLong();
		long time = in.readLong();
		long answer = start + time;
		if (start % mod != 0) {
			long end = (start / mod + Math.min(time + 1, tries)) * mod + Math.max(0, time + 1 - tries);
			answer = Math.max(answer, end);
		}
		long next = (start + mod - 1) / mod * mod;
		if (next - start < time) {
			long end = next + Math.min(time - (next - start), tries) * mod + Math.max(0, time - tries - (next - start));
			answer = Math.max(answer, end);
		}
		out.printLine(answer);
    }
}
