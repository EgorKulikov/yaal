package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.misc.MiscUtils;

public class CentaurCompany {
	int[][] graph;
	double[][][][] result;
	int count;

    public double getvalue(int[] a, int[] b) {
		MiscUtils.decreaseByOne(a, b);
		count = a.length + 1;
		graph = GraphUtils.buildSimpleGraph(count, a, b);
		result = new double[count][][][];
		for (int i = 0; i < count; i++)
			result[i] = new double[graph[i].length + 1][2 * count - 1][2 * count - 1];
		go(0, 0, -1);
		double answer = 0;
		for (int i = 0; i < 2 * count - 1; i++) {
			for (int j = 0; j < 2 * count - 1; j++)
				answer += result[0][0][i][j] * (Math.max(0, i - count) + Math.max(0, j - count));
		}
		return answer;
    }

	private int go(int vertex, int edge, int last) {
		if (edge == graph[vertex].length) {
			result[vertex][edge][count - 1][count - 2] = 1;
			return 1;
		}
		int leftSize = go(vertex, edge + 1, last);
		int next = graph[vertex][edge];
		if (next == last) {
			result[vertex][edge] = result[vertex][edge + 1];
			return leftSize;
		}
		int rightSize = go(next, 0, vertex);
		for (int i = Math.min(-leftSize, -2); i < leftSize - 1; i++) {
			for (int j = Math.min(-leftSize, -2); j < leftSize - 1; j++) {
				if (result[vertex][edge + 1][i + count][j + count] == 0)
					continue;
				for (int k = Math.min(-rightSize, -2); k < rightSize - 1; k++) {
					for (int l = Math.min(-rightSize, -2); l < rightSize - 1; l++) {
						if (result[next][0][k + count][l + count] == 0)
							continue;
						int a = i + k;
						int b = j + l + 2;
						result[vertex][edge][a + count][b + count] += result[vertex][edge + 1][i + count][j + count] * result[next][0][k + count][l + count] / 2;
						a = i + l + 2;
						b = j + k + 2;
						result[vertex][edge][a + count][b + count] += result[vertex][edge + 1][i + count][j + count] * result[next][0][k + count][l + count] / 2;

					}
				}
			}
		}
		return leftSize + rightSize;
	}
}
