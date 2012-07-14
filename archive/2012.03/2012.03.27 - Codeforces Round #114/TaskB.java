package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int minWin = in.readInt();
		int capacity = in.readInt();
		double[][][] probability = new double[count + 1][minWin + 1][2 * count + 1];
		probability[0][minWin][count + Math.min(count, capacity)] = 1;
		int[] winProbability = IOUtils.readIntArray(in, count);
		int[] prizes = IOUtils.readIntArray(in, count);
		for (int i = 0; i < count; i++) {
			double curWinProbability = winProbability[i] / 100.;
			if (prizes[i] == -1) {
				for (int j = 0; j <= minWin; j++) {
					for (int k = 0; k <= 2 * count; k++) {
						if (probability[i][j][k] == 0)
							continue;
						probability[i + 1][Math.max(j - 1, 0)][k - 1] += curWinProbability * probability[i][j][k];
						probability[i + 1][j][k] += (1 - curWinProbability) * probability[i][j][k];
					}
				}
			} else {
				for (int j = 0; j <= minWin; j++) {
					for (int k = 0; k <= 2 * count; k++) {
						if (probability[i][j][k] == 0)
							continue;
						probability[i + 1][Math.max(j - 1, 0)][Math.min(2 * count, k + prizes[i])] += curWinProbability * probability[i][j][k];
						probability[i + 1][j][k] += (1 - curWinProbability) * probability[i][j][k];
					}
				}
			}
		}
		double answer = 0;
		for (int i = count; i <= 2 * count; i++)
			answer += probability[count][0][i];
		out.printLine(answer);
	}
}
