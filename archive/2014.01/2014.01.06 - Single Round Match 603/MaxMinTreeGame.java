package net.egork;

public class MaxMinTreeGame {
    public int findend(int[] edges, int[] costs) {
		int[] degree = new int[costs.length];
		for (int i = 0; i < edges.length; i++) {
			degree[i + 1]++;
			degree[edges[i]]++;
		}
		int answer = 0;
		for (int i = 0; i < costs.length; i++) {
			if (degree[i] == 1)
				answer = Math.max(answer, costs[i]);
		}
		return answer;
    }
}
