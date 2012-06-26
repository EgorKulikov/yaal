package on2012_1_18.magicboard;



import net.egork.collections.CollectionUtils;
import net.egork.collections.filter.Filter;
import net.egork.collections.sequence.Array;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.SimpleEdge;

import java.util.Map;

public class MagicBoard {
	public String ableToUnlock(String[] board) {
		int rowCount = board.length;
		int columnCount = board[0].length();
		Graph<Integer> graph = new BidirectionalGraph<Integer>();
		int[] rowDegree = new int[rowCount];
		int[] columnDegree = new int[columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (board[i].charAt(j) == '#') {
					rowDegree[i]++;
					columnDegree[j]++;
					graph.add(new SimpleEdge<Integer>(i, rowCount + j));
				}
			}
		}
		int source = 0;
		while (source < rowCount + columnCount && !graph.getIncident(source).iterator().hasNext())
			source++;
		if (source != rowCount + columnCount) {
			Map<Integer, Long> distances = GraphAlgorithms.dijkstraAlgorithm(graph, source).first;
			for (int i = 0, distancesLength = rowCount + columnCount; i < distancesLength; i++) {
				Long l = distances.get(i);
				if (l == null && graph.getIncident(i).iterator().hasNext()) {
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

