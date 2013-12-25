package net.egork;

import net.egork.collections.ArrayUtils;

public class CasketOfStar {
	private int[][] answer;
	private int[] weight;

	public int maxEnergy(int[] weight) {
		this.weight = weight;
		answer = new int[weight.length][weight.length];
		ArrayUtils.fill(answer, -1);
		return go(0, weight.length - 1);
	}

	private int go(int from, int to) {
		if (answer[from][to] != -1)
			return answer[from][to];
		if (from + 1 == to)
			return answer[from][to] = 0;
		answer[from][to] = 0;
		for (int i = from + 1; i < to; i++)
			answer[from][to] = Math.max(answer[from][to], go(from, i) + go(i, to));
		answer[from][to] += weight[from] * weight[to];
		return answer[from][to];
	}


}

