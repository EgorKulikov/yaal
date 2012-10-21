package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class KillingGs {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int cockroachCount = in.readInt();
		int insecticideCount = in.readInt();
		int[] cost = IOUtils.readIntArray(in, insecticideCount);
		int[][] probabilityToKill = IOUtils.readIntTable(in, cockroachCount, insecticideCount);
		double[][] logProbability = new double[cockroachCount][insecticideCount];
		for (int i = 0; i < cockroachCount; i++) {
			for (int j = 0; j < insecticideCount; j++)
				logProbability[i][j] = -Math.log((100 - probabilityToKill[i][j]) / 100d);
		}
		double[] rateAlive = new double[cockroachCount];
		Arrays.fill(rateAlive, -Math.log(0.1));
		int[] taken = new int[insecticideCount];
		int takenIndex = 1;
		double[] usefulness = new double[insecticideCount];
		for (int i = 0; i < insecticideCount; i++) {
			boolean allDead = true;
			for (double rate : rateAlive) {
				if (rate > 1e-13) {
					allDead = false;
					break;
				}
			}
			if (allDead)
				break;
			int killAllIndex = -1;
			int killAllCost = Integer.MAX_VALUE;
			for (int j = 0; j < insecticideCount; j++) {
				if (taken[j] % 2 == 1)
					usefulness[j] = Double.NEGATIVE_INFINITY;
				else {
					usefulness[j] = 0;
					boolean willKillAll = true;
					for (int k = 0; k < cockroachCount; k++) {
						usefulness[j] += Math.max(Math.min(logProbability[k][j], rateAlive[k]), 0);
						if (rateAlive[k] > logProbability[k][j] + 1e-13)
							willKillAll = false;
					}
					if (willKillAll && cost[j] < killAllCost) {
						killAllIndex = j;
						killAllCost = cost[j];
					}
					usefulness[j] /= cost[j];
				}
			}
			if (killAllIndex != -1 && taken[killAllIndex] == 0) {
				takenIndex++;
				taken[killAllIndex] = takenIndex;
				takenIndex++;
			}
			int current = ListUtils.maxIndex(Array.wrap(usefulness));
			if (taken[current] != 0)
				break;
			taken[current] = takenIndex;
			for (int j = 0; j < cockroachCount; j++)
				rateAlive[j] -= logProbability[j][current];
		}
		int[] answer = new int[0];
		int minCost = Integer.MAX_VALUE;
		for (int i = 2; ; i += 2) {
			if (Array.wrap(taken).indexOf(i) == -1)
				break;
			int curCost = 0;
			int curCount = 0;
			for (int k = 0; k < insecticideCount; k++) {
				if (i == taken[k] || i > taken[k] && taken[k] % 2 == 1) {
					curCost += cost[k];
					curCount++;
				}
			}
			if (curCost < minCost) {
				answer = new int[curCount];
				minCost = curCost;
				int index = 0;
				for (int k = 0; k < insecticideCount; k++) {
					if (i == taken[k] || i > taken[k] && taken[k] % 2 == 1)
						answer[index++] = k + 1;
				}
			}
		}
		out.printLine(answer.length);
		out.printLine(Array.wrap(answer).toArray());
	}
}
