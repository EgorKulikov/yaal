package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.map.Indexer;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.misc.MiscUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlockTheBlockPuzzle {
    public int minimumHoles(String[] board) {
		int count = board.length;
		char[][] map = new char[count][];
		for (int i = 0; i < count; i++) {
			map[i] = board[i].toCharArray();
		}
		int lastRow = -1;
		int lastColumn = -1;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (map[i][j] == '$') {
					lastRow = i;
					lastColumn = j;
				}
			}
		}
		Indexer<Position> indexer = new Indexer<>();
		List<Pair<Position, Position>> edges = new ArrayList<>();
		for (int i = lastRow % 3; i < count; i += 3) {
			for (int j = lastColumn % 3; j < count; j += 3) {
				Position position = new Position(i, j, i, j);
				indexer.get(position);
				if (i + 3 < count)
					indexer.get(new Position(i + 1, j, i + 2, j));
				if (j + 3 < count)
					indexer.get(new Position(i, j + 1, i, j + 2));
				for (int k = 0; k < 4; k++) {
					Position newPosition = new Position(i + Math.min(MiscUtils.DX4[k], 2 * MiscUtils.DX4[k]),
														j + Math.min(MiscUtils.DY4[k], 2 * MiscUtils.DY4[k]),
														i + Math.max(MiscUtils.DX4[k], 2 * MiscUtils.DX4[k]),
														j + Math.max(MiscUtils.DY4[k], 2 * MiscUtils.DY4[k]));
					if (indexer.containsKey(newPosition)) {
						edges.add(Pair.makePair(position, newPosition));
					}
				}
			}
		}
		Graph graph = new Graph(2 * indexer.size() + 2);
		int source = 2 * indexer.size();
		int sink = source + 1;
		for (Map.Entry<Position, Integer> entry : indexer.entrySet()) {
			int empty = 0;
			Position position = entry.getKey();
			boolean infty = false;
			for (int i = position.row0; i <= position.row1; i++) {
				for (int j = position.column0; j <= position.column1; j++) {
					if (map[i][j] != 'H')
						empty++;
					if (map[i][j] == 'b')
						infty = true;
				}
			}
			if (infty) {
				empty = Integer.MAX_VALUE;
			}
			int index = entry.getValue();
			graph.addFlowEdge(index, index + indexer.size(), empty);
			if (position.row0 == position.row1 && position.column0 == position.column1 && map[position.row0][position.column0] == 'b') {
				graph.addFlowEdge(index, sink, Integer.MAX_VALUE);
			}
		}
		graph.addFlowEdge(source, indexer.get(new Position(lastRow, lastColumn, lastRow, lastColumn)) + indexer.size(), Integer.MAX_VALUE);
		for (Pair<Position, Position> edge : edges) {
			graph.addFlowEdge(indexer.get(edge.first) + indexer.size(), indexer.get(edge.second), Integer.MAX_VALUE);
			graph.addFlowEdge(indexer.get(edge.second) + indexer.size(), indexer.get(edge.first), Integer.MAX_VALUE);
		}
		int result = (int) MaxFlow.dinic(graph, source, sink);
		return result == Integer.MAX_VALUE ? -1 : result;
    }

	static class Position {
		int row0;
		int column0;
		int row1;
		int column1;

		Position(int row0, int column0, int row1, int column1) {
			this.row0 = row0;
			this.column0 = column0;
			this.row1 = row1;
			this.column1 = column1;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Position position = (Position) o;

			if (column0 != position.column0) return false;
			if (column1 != position.column1) return false;
			if (row0 != position.row0) return false;
			if (row1 != position.row1) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = row0;
			result = 31 * result + column0;
			result = 31 * result + row1;
			result = 31 * result + column1;
			return result;
		}
	}
}
