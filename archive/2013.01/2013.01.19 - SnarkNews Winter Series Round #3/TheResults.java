package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TheResults {

	public static final double LOG2 = Math.log(2);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int types = in.readInt();
		double[] delta = new double[count + 1];
		for (int i = 1; i <= count; i++)
			delta[i] = -(double) i / count * Math.log((double) i / count) / LOG2;
		int[] points = IOUtils.readIntArray(in, count);
		Arrays.sort(points);
		double[][] answer = new double[count + 1][types + 2];
		ArrayUtils.fill(answer, Double.NEGATIVE_INFINITY);
		answer[0][0] = 0;
		for (int i = 1; i <= count; i++) {
			for (int j = 1; j <= types + 1; j++) {
				for (int k = 0; k < i; k++) {
					if (k == 0 || points[k] != points[k - 1])
						answer[i][j] = Math.max(answer[i][j], answer[k][j - 1] + delta[i - k]);
				}
			}
		}
		double result = 0;
		for (int i = 0; i <= types + 1; i++)
			result = Math.max(result, answer[count][i]);
		out.printLine(result);
	}
}
