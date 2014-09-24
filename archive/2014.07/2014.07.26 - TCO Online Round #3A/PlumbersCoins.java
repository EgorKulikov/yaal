package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class PlumbersCoins {
    public int minTime(int[] tubes, int[] coins) {
		int[] points = new int[tubes.length + coins.length + 1];
		System.arraycopy(tubes, 0, points, 0, tubes.length);
		System.arraycopy(coins, 0, points, tubes.length, coins.length);
		Arrays.sort(points);
		points = ArrayUtils.unique(points);
		int[][] distance = new int[points.length][points.length];
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				distance[i][j] = Math.abs(points[i] - points[j]);
			}
		}
		for (int i = 0; i < tubes.length; i++) {
			int from = Arrays.binarySearch(points, tubes[i]);
			int to = Arrays.binarySearch(points, tubes[i ^ 1]);
			distance[from][to] = Math.min(distance[from][to], 1);
		}
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				for (int k = 0; k < points.length; k++) {
					distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[i][k]);
				}
			}
		}
		long answer = Long.MAX_VALUE;
		for (int i = 0; i < coins.length; i++) {
			for (int j = i; j < coins.length; j++) {
				long base = 0;
				int at = 0;
				for (int k = 0; k < i; k++) {
					int to = Arrays.binarySearch(points, coins[k]);
					base += distance[at][to];
					at = to;
				}
				for (int k = j + 1; k < coins.length; k++) {
					int to = Arrays.binarySearch(points, coins[k]);
					base += distance[at][to];
					at = to;
				}
				for (int k = i; k <= j; k++) {
					int to = Arrays.binarySearch(points, coins[k]);
					answer = Math.min(answer, base + distance[at][to] + Math.min(coins[j] - coins[k], coins[k] - coins[i]) + coins[j] - coins[i]);
				}
			}
		}
		return (int)answer;
    }
}
