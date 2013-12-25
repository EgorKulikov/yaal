package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskBHard {
	int max = 10000000;

//	long[] full = new long[max + 1];
	long[] cheat = new long[max + 1];

	{
//		Arrays.fill(full, -1);
		Arrays.fill(cheat, -1);
		cheat[1] = 0;
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(goCheat(in.readInt()));
    }

	private long goCheat(int x) {
		if (x <= max && cheat[x] != -1)
			return cheat[x];
		long z = x / 2;
		long result = goCheat((int) z) + goCheat((int) (x - z)) + z * (x - z);
		if (x <= max)
			cheat[x] = result;
		return result;
	}

//	private long goFull(int x) {
//		if (full[x] != -1)
//			return full[x];
//		for (int k = 2; k <= x; k++) {
//			int z = x / k;
//			full[x] = Math.max(full[x], goFull(z)+ goFull(x - z) + z * (x - z));
//		}
//		return full[x];
//	}
}
