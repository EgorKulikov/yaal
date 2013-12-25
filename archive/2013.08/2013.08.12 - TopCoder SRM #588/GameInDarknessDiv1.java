package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.misc.ArrayUtils;

public class GameInDarknessDiv1 {
    public String check(String[] field) {
		int rowCount = field.length;
		int columnCount = field[0].length();
		char[][] map = new char[rowCount][];
		for (int i = 0; i < rowCount; i++)
			map[i] = field[i].toCharArray();
		int[][] index = new int[rowCount][columnCount];
		int curIndex = 0;
		ArrayUtils.fill(index, -1);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] != '#')
					index[i][j] = curIndex++;
			}
		}
		Graph graph = new BidirectionalGraph(curIndex);
		int aStart = -1;
		int bStart = -1;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == '#')
					continue;
				if (map[i][j] == 'A')
					aStart = index[i][j];
				if (map[i][j] == 'B')
					bStart = index[i][j];
				if (i + 1 < rowCount && map[i + 1][j] != '#')
					graph.addWeightedEdge(index[i][j], index[i + 1][j], 1);
				if (j + 1 < columnCount && map[i][j + 1] != '#')
					graph.addWeightedEdge(index[i][j], index[i][j + 1], 1);
			}
		}
		long[] aDistances = ShortestDistance.dijkstraAlgorithm(graph, aStart).first;
		long[] bDistances = ShortestDistance.dijkstraAlgorithm(graph, bStart).first;
		for (int i = 0; i < curIndex; i++) {
			if (aDistances[i] - 1 <= bDistances[i])
				continue;
			int good = 0;
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				if (calculate(graph.destination(j), i, graph) >= 3)
					good++;
			}
			if (good >= 3)
				return "Bob wins";
		}
		return "Alice wins";
	}

	private int calculate(int vertex, int last, Graph graph) {
		int result = 1;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				result = Math.max(result, calculate(next, vertex, graph) + 1);
		}
		return result;
	}
}
