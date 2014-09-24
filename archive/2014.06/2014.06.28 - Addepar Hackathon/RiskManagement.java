package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Random;

public class RiskManagement {

	public static final int TIME_LIMIT = 3800;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long startTime = System.currentTimeMillis();
		int count = in.readInt();
		double[][] values = IOUtils.readDoubleTable(in, count, count);
		int[] result = new int[count];
		int[] answer = new int[count];
		double[] sums = new double[count];
		Random random = new Random(23942);
		double totalBest = Double.NEGATIVE_INFINITY;
		boolean first = true;
		Arrays.fill(answer, 1);
		while (true) {
			for (int i = 0; i < count; i++) {
				sums[i] = 0;
				answer[i] = (first || random.nextBoolean()) ? 1 : -1;
			}
			first = false;
			for (int i = 0; i < count; i++) {
				for (int j = 0; j < count; j++) {
					if (i != j) {
						sums[i] += values[i][j] * answer[i] * answer[j];
						sums[j] += values[i][j] * answer[i] * answer[j];
					}
				}
			}
			double current = 0;
			for (double d : sums) {
				current += d;
			}
			current /= 2;
			int iter = 0;
			while (true) {
				iter++;
				if ((iter & 16535) == 0 && System.currentTimeMillis() - startTime > TIME_LIMIT) {
					break;
				}
				double best = 0;
				int at = -1;
				for (int i = 0; i < count; i++) {
					if (sums[i] < best) {
						best = sums[i];
						at = i;
					}
				}
				if (at == -1) {
					break;
				}
				answer[at] *= -1;
				for (int i = 0; i < count; i++) {
					if (i != at) {
						sums[i] += 2 * answer[at] * answer[i] * (values[i][at] + values[at][i]);
					}
				}
				sums[at] *= -1;
				current += 2 * sums[at];
			}
			if (current > totalBest) {
				totalBest = current;
				System.arraycopy(answer, 0, result, 0, count);
			}
			if (System.currentTimeMillis() - startTime > TIME_LIMIT) {
				break;
			}
		}
		out.printLine(result);
    }
}
