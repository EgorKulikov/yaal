package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CoinsOnTheMatrix {
	private static final double[][] c = new double[1001][1001];

	static {
		for (int i = 0; i <= 1000; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= i; j++)
				c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int stepCount = in.readInt();
		double[][] addPower = new double[6][stepCount + 1];
		double[][] power = new double[6][stepCount + 1];
		double[] count = new double[6];
		double all = (double)rowCount * columnCount;
		for (int i = 0; i < 6; i++) {
			addPower[i][0] = 1;
			power[i][0] = 1;
			for (int j = 1; j <= stepCount; j++) {
				addPower[i][j] = addPower[i][j - 1] * (all - i) / all;
				power[i][j] = power[i][j - 1] * i / all;
			}
		}
		if (rowCount == 1 && columnCount == 1)
			count[1] = 1;
		else if (rowCount == 1 || columnCount == 1) {
			count[2] = 2;
			count[3] = rowCount + columnCount - 3;
		} else {
			count[3] = 4;
			count[4] = 2 * (rowCount + columnCount) - 8;
			count[5] = all - count[3] - count[4];
		}
		double answer = 0;
		for (int i = 1; i <= stepCount; i += 2) {
			for (int j = 1; j <= 5; j++) {
				if (power[j][i] != 0)
					answer += count[j] * c[stepCount][i] * power[j][i] * addPower[j][stepCount - i];
			}
		}
		out.printLine(all - answer);
	}
}
