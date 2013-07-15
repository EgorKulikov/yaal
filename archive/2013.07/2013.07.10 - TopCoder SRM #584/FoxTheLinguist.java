package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.string.StringUtils;

public class FoxTheLinguist {
    public int minimalHours(int n, String[] courseInfo) {
		String all = StringUtils.unite(courseInfo);
		String[] tokens = all.split(" ");
		int count = tokens.length;
		int[] fromLanguage = new int[count];
		int[] fromFluency = new int[count];
		int[] toLanguage = new int[count];
		int[] toFluency = new int[count];
		int[] cost = new int[count];
		for (int i = 0; i < count; i++) {
			fromLanguage[i] = tokens[i].charAt(0) - 'A';
			fromFluency[i] = tokens[i].charAt(1) - '0';
			toLanguage[i] = tokens[i].charAt(4) - 'A';
			toFluency[i] = tokens[i].charAt(5) - '0';
			cost[i] = Integer.parseInt(tokens[i].substring(7));
		}
		Graph graph = new Graph(n * 10);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 10; j++) {
				if (j != 0)
					graph.addWeightedEdge(i * 10 + j, i * 10 + j - 1, 0);
				for (int k = 0; k < n; k++)
					graph.addWeightedEdge(i * 10 + j, k * 10, 0);
			}
		}
		for (int i = 0; i < count; i++)
			graph.addWeightedEdge(fromLanguage[i] * 10 + fromFluency[i], toLanguage[i] * 10 + toFluency[i], cost[i]);
		int[][] distance = new int[n * 10][n * 10];
		for (int i = 0; i < n * 10; i++) {
			long[] lDis = ShortestDistance.dijkstraAlgorithm(graph, i).first;
			for (int j = 0; j < n * 10; j++) {
				if (lDis[j] == Long.MAX_VALUE)
					return -1;
				distance[i][j] = (int) lDis[j];
			}
		}
		int[][] answer = new int[1 << n][n * 10];
		for (int i = 1; i < (1 << n); i++) {
			if (Integer.bitCount(i) == 1) {
				int where = Integer.bitCount(i - 1) * 10 + 9;
				for (int j = 0; j < n * 10; j++) {
					answer[i][j] = distance[j][where];
				}
			} else {
				for (int j = 0; j < n * 10; j++) {
					answer[i][j] = Integer.MAX_VALUE;
					for (int k = i & (i - 1); k != 0; k = (k - 1) & i)
						answer[i][j] = Math.min(answer[i][j], answer[k][j] + answer[i - k][j]);
				}
				for (int j = 0; j < n * 10; j++) {
					for (int k = 0; k < n * 10; k++)
						answer[i][j] = Math.min(answer[i][j], distance[j][k] + answer[i][k]);
				}
			}
		}
		return answer[(1 << n) - 1][0];
    }
}
