package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	double[] probHeight;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String type = in.readString();
		int value = in.readInt();
		int maxHeight = in.readInt();
		probHeight = new double[maxHeight + 1];
		double curProb = 1;
		for (int i = 0; i < maxHeight; i++)
			probHeight[i] = curProb /= 2;
		probHeight[maxHeight] = curProb;
		if ("Alice".equals(type)) {
			if (maxHeight == 0) {
				out.printLine(value);
				return;
			}
			double[][] expectedOnHeight = new double[value + 1][maxHeight + 1];
			double[][] probOnHeight = new double[value + 1][maxHeight + 1];
			double[][] expectedStopOnHeight = new double[value + 1][maxHeight + 1];
			double[][] probStopOnHeight = new double[value + 1][maxHeight + 1];
			probOnHeight[0][0] = 1;
			for (int i = 1; i <= value; i++) {
				double sumLess = 0;
				double sumProb = 0;
				double sumExpected = 0;
				for (int j = 0; j <= maxHeight; j++) {
					expectedOnHeight[i][j] += probHeight[j] * ((1 << j) * probOnHeight[i - 1][j] + expectedOnHeight[i - 1][j]);
					probOnHeight[i][j] += probOnHeight[i - 1][j] * probHeight[j];
					expectedOnHeight[i][j] += sumExpected * probHeight[j];
					probOnHeight[i][j] += sumProb * probHeight[j];
					expectedStopOnHeight[i][j] = expectedOnHeight[i][j];
					probStopOnHeight[i][j] = probOnHeight[i][j];
					expectedOnHeight[i][j] += expectedOnHeight[i - 1][j] * sumLess;
					probOnHeight[i][j] += probOnHeight[i - 1][j] * sumLess;
					sumLess += probHeight[j];
					sumProb += probOnHeight[i - 1][j];
					sumExpected += (expectedOnHeight[i - 1][j] + (1 << j) * probOnHeight[i - 1][j]);
				}
			}
			double answer = 0;
			double totalProb = 0;
			for (int i = 1; i <= value; i++) {
				double sumProb = 0;
				double sumExpected = 0;
				for (int j = 0; j <= maxHeight; j++) {
					double expected = expectedStopOnHeight[i][j] * sumProb + sumExpected * probStopOnHeight[i][j];
					double probability = probStopOnHeight[i][j] * sumProb;
					answer += expected;
					totalProb += probability;
					sumProb += probOnHeight[value - i][j];
					sumExpected += expectedOnHeight[value - i][j] + (1 << j) * probOnHeight[value - i][j];
				}
			}
			double allOneProb = 1;
			for (int i = 0; i < value; i++)
				allOneProb /= 2;
			totalProb += allOneProb;
			answer += allOneProb * (value + 1);
			out.printLine(answer / totalProb - 1);
			return;
		}
		out.printLine(value);
    }
}
