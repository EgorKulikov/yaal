package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class GreatestSum {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		int p = in.readInt();
		int k = in.readInt();
		int[] f = new int[5 * p];
		f[0] = k;
		int[] position = new int[2 * p + 1];
		Arrays.fill(position, -1);
		int prePeriod;
		int period;
		for (int i = 1; ; i++) {
			f[i] = (1 - Integer.signum(f[i - 1]) * (1 + Integer.signum(f[i - 1]))) * ((a * Math.abs(f[i - 1]) + b) % p);
			if (position[f[i] + p] != -1) {
				prePeriod = position[f[i] + p];
				period = i - prePeriod;
				break;
			}
			position[f[i] + p] = i;
		}
		System.arraycopy(f, prePeriod, f, prePeriod + period, period);
		long periodSum = 0;
		for (int i = prePeriod; i < prePeriod + period; i++)
			periodSum += f[i];
		if (periodSum > 0) {
			out.printLine("Infinite");
			return;
		}
		long[] partialSums = new long[prePeriod + 2 * period + 1];
		for (int i = 1; i <= prePeriod + 2 * period; i++)
			partialSums[i] = partialSums[i - 1] + f[i - 1];
		long answer = 0;
		for (int i = 0; i < partialSums.length; i++) {
			for (int j = 0; j < i; j++)
				answer = Math.max(answer, partialSums[i] - partialSums[j]);
		}
		out.printLine(answer);
	}
}
