package net.egork;

import java.util.Arrays;

public class KingdomAndDice {
	public double newFairness(int[] firstDie, int[] secondDie, int X) {
		int free = 0;
		int curWins = 0;
		for (int i : firstDie) {
			if (i == 0)
				free++;
			else {
				for (int j : secondDie) {
					if (i > j)
						curWins++;
				}
			}
		}
		Arrays.sort(secondDie);
		int totalWins = firstDie.length * secondDie.length;
		boolean[][] possible = new boolean[free + 1][totalWins + 1];
		for (int i = 0; i <= free; i++)
			possible[i][curWins] = true;
		for (int i = 0; i < secondDie.length; i++) {
			int from = secondDie[i] + 1;
			int to = i == secondDie.length - 1 ? X : secondDie[i + 1] - 1;
			int count = to - from + 1;
			for (int j : firstDie) {
				if (j >= from && j <= to)
					count--;
			}
			for (int j = 0; j < count && j <= free; j++) {
				for (int k = free - 1; k >= 0; k--) {
					for (int l = 0; l < possible[k].length; l++) {
						if (possible[k][l])
							possible[k + 1][l + i + 1] = true;
					}
				}
			}
		}
		int mid = possible[free].length - 1;
		int minMid = Integer.MAX_VALUE;
		double answer = -1;
		for (int i = 0; i < possible[free].length; i++) {
			if (possible[free][i]) {
				int cur = Math.abs(2 * i - mid);
				if (cur < minMid) {
					minMid = cur;
					answer = (double)i / mid;
				}
			}
		}
		return answer;
	}


}

