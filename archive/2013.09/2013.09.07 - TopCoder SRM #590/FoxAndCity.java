package net.egork;

import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class FoxAndCity {
    public int minimalCost(String[] linked, int[] want) {
		int count = want.length;
		int[][] distance = new int[count][count];
		ArrayUtils.fill(distance, count);
		for (int i = 0; i < count; i++) {
			distance[i][i] = 0;
			for (int j = 0; j < count; j++) {
				if (linked[i].charAt(j) == 'Y')
					distance[i][j] = 1;
			}
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++)
					distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[i][k]);
			}
		}
		int[] current = distance[0].clone();
		int[] benefit = new int[count];
		while (true) {
			for (int i = 0; i < count; i++) {
				if (current[i] <= 1)
					benefit[i] = -1000000;
				else
					benefit[i] = 2 * (current[i] - want[i]) - 1;
			}
			int[] good = new int[count];
			int[] bad = new int[count];
			int goodCount = 0;
			int badCount = 0;
			for (int i = 0; i < count; i++) {
				if (benefit[i] > 0)
					good[goodCount++] = i;
				else
					bad[badCount++] = i;
			}
			good = Arrays.copyOf(good, goodCount);
			bad = Arrays.copyOf(bad, badCount);
			int[] goodBenefit = new int[goodCount];
			int[] badBenefit = new int[badCount];
			for (int i = 0; i < goodCount; i++)
				goodBenefit[i] = benefit[good[i]];
			for (int i = 0; i < badCount; i++)
				badBenefit[i] = benefit[bad[i]];
			long[] required = new long[goodCount];
			for (int i = 0; i < goodCount; i++) {
				for (int j = 0; j < badCount; j++) {
					if (current[bad[j]] - current[good[i]] == distance[good[i]][bad[j]])
						required[i] += 1L << j;
				}
			}
			long best = 0;
			long bestBad = 0;
			int bestBenefit = 0;
			if (goodCount <= badCount) {
				for (int i = 0; i < (1 << goodCount); i++) {
					long allBad = 0;
					int curBenefit = 0;
					for (int j = 0; j < goodCount; j++) {
						if ((i >> j & 1) == 1) {
							allBad |= required[j];
							curBenefit += goodBenefit[j];
						}
					}
					for (int j = 0; j < badCount; j++) {
						if ((allBad >> j & 1) == 1)
							curBenefit += badBenefit[j];
					}
					if (curBenefit > bestBenefit) {
						best = i;
						bestBad = allBad;
						bestBenefit = curBenefit;
					}
				}
			} else {
				for (int i = 0; i < (1 << badCount); i++) {
					long allGood = 0;
					int curBenefit = 0;
					for (int j = 0; j < goodCount; j++) {
						if ((required[j] & i) == required[j]) {
							allGood += 1L << j;
							curBenefit += goodBenefit[j];
						}
					}
					for (int j = 0; j < badCount; j++) {
						if ((i >> j & 1) == 1)
							curBenefit += badBenefit[j];
					}
					if (curBenefit > bestBenefit) {
						best = allGood;
						bestBad = i;
						bestBenefit = curBenefit;
					}
				}
			}
			if (best == 0)
				break;
			for (int i = 0; i < goodCount; i++) {
				if ((best >> i & 1) == 1)
					current[good[i]]--;
			}
			for (int i = 0; i < badCount; i++) {
				if ((bestBad >> i & 1) == 1)
					current[bad[i]]--;
			}
		}
		int answer = 0;
		for (int i = 0; i < count; i++)
			answer += (current[i] - want[i]) * (current[i] - want[i]);
		return answer;
    }
}
