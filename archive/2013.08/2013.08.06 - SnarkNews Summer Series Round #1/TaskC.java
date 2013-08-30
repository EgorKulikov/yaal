package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int maxWeight = in.readInt();
		int count = in.readInt();
		int[] weights = IOUtils.readIntArray(in, count);
		for (int i = 0; i < count; i++) {
			int sum = 0;
			for (int j = i; j > i - 4 && j >= 0; j--)
				sum += weights[j];
			if (sum > maxWeight) {
				out.printLine(Math.max(i - 1, 0));
				return;
			}
		}
		out.printLine(count - 1);
    }
}
