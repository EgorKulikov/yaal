package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.filter.Filter;
import net.egork.collections.sequence.Array;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.SimpleEdge;

public class MagicBoard {
	public String ableToUnlock(String[] board) {
		int rowCount = board.length;
		int columnCount = board[0].length();
		Graph graph = new BidirectionalGraph(rowCount + columnCount);
		int[] rowDegree = new int[rowCount];
		int[] columnDegree = new int[columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (board[i].charAt(j) == '#') {
					rowDegree[i]++;
					columnDegree[j]++;
					graph.add(new SimpleEdge(i, rowCount + j));
				}
			}
		}
		int source = 0;
		while (source < graph.getSize() && graph.getIncident(source).isEmpty())
			source++;
		if (source != graph.getSize()) {
			long[] distances = GraphAlgorithms.dijkstraAlgorithm(graph, source).first;
			for (int i = 0, distancesLength = distances.length; i < distancesLength; i++) {
				long l = distances[i];
				if (l == Long.MAX_VALUE && !graph.getIncident(i).isEmpty()) {
					return "NO";
				}
			}
		}
		Filter<Integer> oddFilter = new Filter<Integer>() {
			public boolean accept(Integer value) {
				return value % 2 == 1;
			}
		};
		int oddRowCount = CollectionUtils.count(Array.wrap(rowDegree), oddFilter);
		int oddColumnCount = CollectionUtils.count(Array.wrap(columnDegree), oddFilter);
		if (oddRowCount + oddColumnCount > 2 || oddRowCount == 2)
			return "NO";
		return "YES";
	}


}

