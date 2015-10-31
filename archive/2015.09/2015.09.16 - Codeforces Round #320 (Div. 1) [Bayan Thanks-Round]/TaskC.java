package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		double left = -10000;
		double right = 10000;
		for (int i = 0; i < 100; i++) {
			double x = (left + right) / 2;
			double max = 0;
			double min = 0;
			double maxPrefix = 0;
			double minPrefix = 0;
			double sum = 0;
			for (int j = 0; j < n; j++) {
				sum += a[j] - x;
				maxPrefix = Math.max(maxPrefix, sum);
				minPrefix = Math.min(minPrefix, sum);
				max = Math.max(max, sum - minPrefix);
				min = Math.min(min, sum - maxPrefix);
			}
			if (max > -min) {
				left = x;
			} else {
				right = x;
			}
		}
		double x = (left + right) / 2;
		double max = 0;
		double min = 0;
		double maxPrefix = 0;
		double minPrefix = 0;
		double sum = 0;
		for (int j = 0; j < n; j++) {
			sum += a[j] - x;
			maxPrefix = Math.max(maxPrefix, sum);
			minPrefix = Math.min(minPrefix, sum);
			max = Math.max(max, sum - minPrefix);
			min = Math.min(min, sum - maxPrefix);
		}
		out.printLine(max);
	}
}
