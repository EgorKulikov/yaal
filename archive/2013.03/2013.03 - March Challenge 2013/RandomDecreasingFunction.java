package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RandomDecreasingFunction {
	private double[][] result;
	private int max = 35;

	{
		result = new double[max][100001];
		for (int i = 0; i <= 100000; i++)
			result[0][i] = i;
		for (int j = 1; j < max; j++) {
			double sum = 0;
			for (int i = 1; i <= 100000; i++) {
				result[j][i] = sum / i;
				sum += result[j - 1][i];
			}
		}
		System.err.println(result[max - 1][100000]);
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		if (k >= max)
			out.printLine(0);
		else
			out.printLine(result[k][n]);
    }
}
