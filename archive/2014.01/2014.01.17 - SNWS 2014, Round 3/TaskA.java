package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int required = in.readInt();
		int[] current = IOUtils.readIntArray(in, count);
		int sum = (int) ArrayUtils.sumArray(current);
		boolean[] can = new boolean[sum + 1];
		can[0] = true;
		sum = 0;
		for (int i : current) {
			for (int j = sum; j >= 0; j--) {
				if (can[j])
					can[j + i] = true;
			}
			sum += i;
		}
		for (int i = (required + 1) / 2; i <= sum; i++) {
			if (can[i]) {
				out.printLine(i);
				return;
			}
		}
		out.printLine(-1);
    }
}
