package net.egork;

import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.StronglyConnectedComponents;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] from = new int[m];
		int[] to = new int[m];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = Graph.createGraph(n, from, to);
		Graph result = new Graph(n);
		Pair<int[], Graph> kosaraju = StronglyConnectedComponents.kosaraju(graph);
		int[] first = ArrayUtils.createArray(kosaraju.second.vertexCount(), -1);
		int[] current = ArrayUtils.createArray(first.length, -1);
		for (int i = 0; i < n; i++) {
			int color = kosaraju.first[i];
			if (first[color] == -1) {
				first[color] = i;
				current[color] = i;
			} else {
				result.addSimpleEdge(current[color], i);
				current[color] = i;
			}
		}
		for (int i = 0; i < first.length; i++) {
			if (first[i] != current[i]) {
				result.addSimpleEdge(current[i], first[i]);
			}
		}
		boolean[][] hasEdge = new boolean[first.length][first.length];
		for (int i = 0; i < m; i++) {
			int c1 = kosaraju.first[from[i]];
			int c2 = kosaraju.first[to[i]];
			if (c1 != c2) {
				hasEdge[c1][c2] = true;
			}
		}
		for (int i = 0; i < first.length; i++) {
			for (int j = 0; j < first.length; j++) {
				for (int k = 0; k < first.length; k++) {
					hasEdge[j][k] |= hasEdge[j][i] && hasEdge[i][k];
				}
			}
		}
		for (int i = 0; i < first.length; i++) {
			for (int j = 0; j < first.length; j++) {
				if (!hasEdge[i][j]) {
					continue;
				}
				for (int k = 0; k < first.length; k++) {
					if (hasEdge[i][k] && hasEdge[k][j]) {
						hasEdge[i][j] = false;
						break;
					}
				}
				if (hasEdge[i][j]) {
					result.addSimpleEdge(first[i], first[j]);
				}
			}
		}
		out.printLine(n, result.edgeCount());
		for (int i = 0; i < result.edgeCount(); i++) {
			out.printLine(result.source(i) + 1, result.destination(i) + 1);
		}
	}
}
