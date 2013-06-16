package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	int N = 120;
	double[][][] answer = new double[N + 5][N + 5][N + 5];
	double[] probability = new double[N + 5];

	{
		ArrayUtils.fill(answer, -1);
		probability[0] = 1;
		for (int i = 1; i < probability.length; i++)
			probability[i] = probability[i - 1] / 2;
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
//		for (int i = 0; i <= N; i++) {
//			double precise = solve(i);
//			double[][] c = new double[i + 1][i + 1];
//			for (int j = 0; j <= i; j++) {
//				c[j][0] = 1;
//				for (int k = 1; k <= j; k++)
//					c[j][k] = c[j - 1][k - 1] + c[j - 1][k];
//			}
//			double approximate = 1 + i / 2d;
//			out.printLine(precise, approximate, Math.abs(precise - approximate));
//		}
		int count = in.readInt();
		if (count <= N)
			out.printLine(solve(count));
		else
			out.printLine(1 + count / 2d);
    }

	private double solve(int count) {
		double answer = probability[count] * Math.max(count, 1);
		if (count >= 1)
			answer += Math.max(count - 1, 2) * probability[count] * count;
		for (int j = 2; j <= count; j++) {
			for (int k = 0; k + j <= count; k++) {
				for (int l = 0; k + j + l <= count; l++) {
					answer += calculate(count - l - k - 2, j - 2, Math.max(j + 1, k + l)) * probability[k + l + 2];
				}
			}
		}
		return answer;
	}

	private double calculate(int remaining, int remainingFirst, int max) {
		if (answer[remaining][remainingFirst][max] != -1)
			return answer[remaining][remainingFirst][max];
		if (remainingFirst == 0)
			return answer[remaining][remainingFirst][max] = Math.max(max, remaining) * probability[remaining];
		if (remainingFirst == remaining)
			return answer[remaining][remainingFirst][max] = max * probability[remaining];
		answer[remaining][remainingFirst][max] = 0;
		for (int i = 0; i <= remaining - remainingFirst; i++)
			answer[remaining][remainingFirst][max] += calculate(remaining - i - 1, remainingFirst - 1, Math.max(i, max)) * probability[i + 1];
		return answer[remaining][remainingFirst][max];
	}
}
