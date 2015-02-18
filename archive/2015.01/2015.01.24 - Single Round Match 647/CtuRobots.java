package net.egork;

import net.egork.misc.ArrayUtils;

public class CtuRobots {
    public double maxDist(int B, int[] cost, int[] cap) {
		double[] answer = new double[B + 1];
		ArrayUtils.orderBy(cap, cost);
		for (int i = 0; i < cap.length; i++) {
			for (int k = B - cost[i]; k >= 0; k--) {
				answer[k + cost[i]] = Math.max(answer[k + cost[i]], cap[i] + answer[k] / 3);
			}
		}
		return answer[B] / 2;
    }
}
