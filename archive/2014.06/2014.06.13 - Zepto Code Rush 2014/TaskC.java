package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int count = in.readInt();
		int cost = in.readInt();
		char[][][] maps = new char[count][][];
		for (int i = 0; i < count; i++) {
			maps[i] = IOUtils.readTable(in, rowCount, columnCount);
		}
		Graph graph = new BidirectionalGraph(count, count * (count - 1) / 2);
		int[] order = new int[count * (count - 1) / 2];
		int k = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < i; j++) {
				order[k++] = graph.edgeCount();
				int distance = 0;
				for (int l = 0; l < rowCount; l++) {
					for (int m = 0; m < columnCount; m++) {
						distance += maps[i][l][m] != maps[j][l][m] ? 1 : 0;
					}
				}
				graph.addWeightedEdge(i, j, Math.min(distance * cost, rowCount * columnCount));
			}
		}
		Graph tree = new BidirectionalGraph(count, count - 1);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				return Long.compare(graph.weight(first), graph.weight(second));
			}
		});
		long answer = 0;
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i : order) {
			if (setSystem.join(graph.source(i), graph.destination(i))) {
				tree.addWeightedEdge(graph.source(i), graph.destination(i), graph.weight(i) == rowCount * columnCount ? 1 : 0);
				answer += graph.weight(i);
			}
		}
		out.printLine(answer + rowCount * columnCount);
		out.printLine(1, 0);
		dfs(0, -1, tree, out);
	}

	private void dfs(int current, int last, Graph tree, OutputWriter out) {
		for (int i = tree.firstOutbound(current); i != -1; i = tree.nextOutbound(i)) {
			int next = tree.destination(i);
			if (next == last)
				continue;
			out.printLine(next + 1, tree.weight(i) == 1 ? 0 : current + 1);
			dfs(next, current, tree, out);
		}
	}
}
