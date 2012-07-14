package net.egork;

import java.util.HashSet;
import java.util.Set;

public class GreedyTravelingSalesman {
	public int worstDistance(String[] thousands, String[] hundreds, String[] tens, String[] ones) {
		int count = thousands.length;
		int[][] distance = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				distance[i][j] = 1000 * (thousands[i].charAt(j) - '0') + 100 * (hundreds[i].charAt(j) - '0') + 10 * (tens[i].charAt(j) - '0') + ones[i].charAt(j) - '0';
		}
		int answer = 0;
		for (int i = 0; i < count; i++) {
			Set<Integer> interestingValues = new HashSet<Integer>();
			interestingValues.add(9999);
			for (int j : distance[i]) {
				if (j == 0)
					continue;
				interestingValues.add(j);
				if (j > 1)
					interestingValues.add(j - 1);
				if (j < 9999)
					interestingValues.add(j + 1);
			}
			for (int j = 0; j < count; j++) {
				for (int k : interestingValues) {
					int old = distance[i][j];
					distance[i][j] = k;
					int current = 0;
					int visited = 1;
					int result = 0;
					for (int l = 1; l < count; l++) {
						int minDistance = Integer.MAX_VALUE;
						int to = -1;
						for (int m = 0; m < count; m++) {
							if ((visited >> m & 1) == 0 && distance[current][m] < minDistance) {
								minDistance = distance[current][m];
								to = m;
							}
						}
						result += minDistance;
						current = to;
						visited += 1 << to;
					}
					answer = Math.max(answer, result);
					distance[i][j] = old;
				}
			}
		}
		return answer;
	}


}

