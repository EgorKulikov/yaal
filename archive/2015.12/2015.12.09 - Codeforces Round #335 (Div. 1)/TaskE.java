package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[][] p = IOUtils.readIntTable(in, n, n);
		double[] retain = new double[n];
		Arrays.fill(retain, 1);
		double[] answer = new double[n];
		double[] accumulated = new double[n];
		Arrays.fill(answer, Double.POSITIVE_INFINITY);
		answer[n - 1] = 0;
		boolean[] processed = new boolean[n];
		for (int i = 0; i < n; i++) {
			double best = Double.POSITIVE_INFINITY;
			int at = -1;
			for (int j = 0; j < n; j++) {
				if (!processed[j] && best > answer[j]) {
					best = answer[j];
					at = j;
				}
			}
			if (at == -1) {
				break;
			}
			processed[at] = true;
			for (int j = 0; j < n; j++) {
				if (processed[j] || p[j][at] == 0 || retain[j] == 0) {
					continue;
				}
				double nAccumulated = accumulated[j] + retain[j] * p[j][at] / 100d * (answer[at]);
				double nRetain = retain[j] * (1 - p[j][at] / 100d);
				double nAnswer = (1 + nAccumulated) / (1 - nRetain);
				if (nAnswer < answer[j]) {
					accumulated[j] = nAccumulated;
					retain[j] = nRetain;
					answer[j] = nAnswer;
				}
			}
		}
		out.printLine(answer[0]);
	}
}
