package net.egork;

import net.egork.collections.Pair;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int passed = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		Graph<Pair<Integer, Integer>> graph = new Graph<Pair<Integer, Integer>>();
		@SuppressWarnings("unchecked")
		Pair<Integer, Integer>[] junctions = new Pair[27];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (Character.isDigit(map[i][j])) {
					int weight = map[i][j] - '0';
					for (int k = 0; k < 4; k++) {
						int row = i + MiscUtils.DX4[k];
						int column = j + MiscUtils.DY4[k];
						if (row >= 0 && row < rowCount && column >= 0 && column < columnCount && map[row][column] != '#')
							graph.addWeightedEdge(Pair.makePair(row, column), Pair.makePair(i, j), weight);
					}
					if (Character.isLetter(map[i][j]))
						junctions[map[i][j] - 'a'] = Pair.makePair(i, j);
				} else if (Character.isLetter(map[i][j]))
					junctions[map[i][j] - 'a'] = Pair.makePair(i, j);
			}
		}
		Pair<Integer, Integer> start = IOUtils.readIntPair(in);
		String path = in.readString();
		Pair<Integer, Integer> finish = IOUtils.readIntPair(in);
		junctions[26] = Pair.makePair(finish.first - 1, finish.second - 1);
		start = Pair.makePair(start.first - 1, start.second - 1);
		@SuppressWarnings("unchecked")
		Pair<Map<Pair<Integer, Integer>, Long>, Map<Pair<Integer, Integer>, Edge<Pair<Integer, Integer>>>>[] dijkstraResults = new Pair[27];
		for (int i = 0; i < 27; i++) {
			if (junctions[i] != null)
				dijkstraResults[i] = ShortestDistance.dijkstraAlgorithm(graph, junctions[i]);
		}
		path = path + ((char)('z' + 1));
		for (int i = 0; i < path.length(); i++) {
			int junction = path.charAt(i) - 'a';
			if (i != 0) {
				if (passed == 0) {
					out.printLine(start.first + 1, start.second + 1);
					return;
				}
				passed--;
				Pair<Integer, Integer> next = junctions[junction];
				if (next.first > start.first)
					start = Pair.makePair(start.first + 1, start.second);
				else if (next.first < start.first)
					start = Pair.makePair(start.first - 1, start.second);
				else if (next.second < start.second)
					start = Pair.makePair(start.first, start.second - 1);
				else
					start = Pair.makePair(start.first, start.second + 1);
			}
			if (dijkstraResults[junction].first.get(start) > passed) {
				while (true) {
					Edge<Pair<Integer, Integer>> edge = dijkstraResults[junction].second.get(start);
					if (edge.getWeight() > passed) {
						out.printLine(start.first + 1, start.second + 1);
						return;
					}
					passed -= edge.getWeight();
					start = edge.getSource();
				}
			}
			passed -= dijkstraResults[junction].first.get(start);
			start = junctions[junction];
		}
		out.printLine(finish.first, finish.second);
	}
}
