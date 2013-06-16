package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int yearCount = in.readInt();
		double money = in.readDouble();
		int[] fee = IOUtils.readIntArray(in, count);
		int[][] profit = IOUtils.readIntTable(in, count, yearCount);
		double[] result = new double[count];
		Arrays.fill(result, money);
		for (int i = 0; i < count; i++)
			result[i] *= 1 + profit[i][0] / 100d;
		for (int i = 1; i < yearCount; i++) {
			double max = 0;
			for (int j = 0; j < count; j++)
				max = Math.max(max, result[j] - fee[j]);
			for (int j = 0; j < count; j++)
				result[j] = Math.max(result[j], max - fee[j]);
			for (int j = 0; j < count; j++)
				result[j] *= 1 + profit[j][i] / 100d;
		}
		double answer = 0;
		for (double i : result)
			answer = Math.max(answer, i);
		out.printLine(answer);
	}
}
