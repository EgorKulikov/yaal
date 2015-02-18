package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] left = new int[count];
		int[] right = new int[count];
		IOUtils.readIntArrays(in, left, right);
		double totalWays = 0;
		double totalValue = 0;
		for (int i = 1; i <= 10000; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					if (j == k) {
						continue;
					}
					if (right[k] < i || left[k] > i) {
						continue;
					}
					double currentWays = Math.max(0, right[j] - Math.max(i - (j < k ? 1 : 0), left[j] - 1));
					for (int l = 0; l < k; l++) {
						if (l != j) {
							currentWays *= Math.max(0, Math.min(right[l], i - 1) - (left[l] - 1));
						}
					}
					for (int l = k + 1; l < count; l++) {
						if (l != j) {
							currentWays *= Math.max(0, Math.min(right[l], i) - (left[l] - 1));
						}
					}
					totalWays += currentWays;
					totalValue += currentWays * i;
				}
			}
		}
		out.printLine(totalValue / totalWays);
    }
}
