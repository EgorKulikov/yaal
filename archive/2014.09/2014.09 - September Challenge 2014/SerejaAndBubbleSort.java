package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SerejaAndBubbleSort {
	double[][] qty = new double[101][];
	double[][] sum = new double[101][];
	double[][] weightedSum = new double[101][];

	{
		qty[1] = new double[1];
		sum[1] = new double[2];
		weightedSum[1] = new double[2];
		qty[1][0] = 1;
		sum[1][1] = 1;
		for (int i = 2; i <= 100; i++) {
			qty[i] = new double[i * (i - 1) / 2 + 1];
			sum[i] = new double[qty[i].length + 1];
			weightedSum[i] = new double[sum[i].length];
			for (int j = 0; j < qty[i].length; j++) {
				for (int k = 0; k < i; k++) {
					if (j - k >= 0 && j - k < qty[i - 1].length) {
						qty[i][j] += qty[i - 1][j - k];
					}
				}
				sum[i][j + 1] = sum[i][j] + qty[i][j];
				weightedSum[i][j + 1] = weightedSum[i][j] + j * qty[i][j];
			}
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		long operations = in.readLong();
		int[] array = IOUtils.readIntArray(in, size);
		if (operations >= size * (size - 1) / 2) {
			out.printLine(0);
			return;
		}
		double total = 0;
		double last = 0;
		for (int i = 0; i < qty[size].length; i++) {
			total += qty[size][i];
			last += qty[size][i] * i;
		}
		last /= total;
		for (int i = 1; i < operations && last > 1e-7; i++) {
			double current;
			int down = (int) Math.round(Math.floor(last));
			if (i + down < qty[size].length) {
				current = weightedSum[size][i + down + 1] - weightedSum[size][i] - i * (sum[size][i + down + 1] - sum[size][i]) +
					last * (sum[size][qty[size].length] - sum[size][i + down + 1]);
			} else {
				current = weightedSum[size][qty[size].length] - weightedSum[size][i] - i * (sum[size][qty[size].length] - sum[size][i]);
			}
			last = current / total;
		}
		int inversions = 0;
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (array[i] > array[j]) {
					inversions++;
				}
			}
		}
		double answer = Math.max(0, inversions - operations);
		if (operations > 0) {
			answer = Math.min(answer, last);
		}
		out.printLine(answer);
	}
}
