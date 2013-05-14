package net.egork;

import net.egork.collections.map.Indexer;
import net.egork.graph.GraphUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		Indexer<String> indexer = new Indexer<String>();
		for (int i = 0; i < edgeCount; i++) {
			from[i] = indexer.get(in.readString());
			to[i] = indexer.get(in.readString());
		}
		int count = indexer.size();
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		short[][] common = new short[count][];
		for (int i = 0; i < count; i++)
			common[i] = new short[i];
		for (int[] row : graph) {
			for (int i : row) {
				for (int j : row) {
					if (i == j)
						break;
					if (i > j)
						common[i][j]++;
					else
						common[j][i]++;
				}
			}
		}
		for (int i = 0; i < edgeCount; i++) {
			if (from[i] > to[i])
				common[from[i]][to[i]] = -1;
			else
				common[to[i]][from[i]] = -1;
		}
		String[] byIndex = new String[count];
		for (String s : indexer.keySet())
			byIndex[indexer.get(s)] = s;
		out.printLine(count);
		for (int i = 0; i < count; i++) {
			int max = 0;
			int maxQty = 0;
			for (int j = 0; j < i; j++) {
				if (common[i][j] > max) {
					max = common[i][j];
					maxQty = 1;
				} else if (common[i][j] == max)
					maxQty++;
			}
			for (int j = i + 1; j < count; j++) {
				if (common[j][i] > max) {
					max = common[j][i];
					maxQty = 1;
				} else if (common[j][i] == max)
					maxQty++;
			}
			out.printLine(byIndex[i], maxQty);
		}
	}
}
