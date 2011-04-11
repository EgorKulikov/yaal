package net.egork.timus;

import net.egork.arrays.ArrayWrapper;
import net.egork.collections.CollectionUtils;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.SimpleEdge;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1072 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int computerCount = in.readInt();
		int[][] networks = new int[computerCount][];
		for (int i = 0; i < computerCount; i++) {
			networks[i] = new int[in.readInt()];
			for (int j = 0; j < networks[i].length; j++)
				networks[i][j] = MiscUtils.parseIP(in.readString()) & MiscUtils.parseIP(in.readString());
		}
		Graph graph = new BidirectionalGraph(computerCount);
		for (int i = 0; i < computerCount; i++) {
			for (int j = i + 1; j < computerCount; j++) {
				boolean connected = false;
				for (int k : networks[i]) {
					if (ArrayWrapper.wrap(networks[j]).contains(k))
						connected = true;
				}
				if (connected)
					graph.add(new SimpleEdge(i, j));
			}
		}
		int source = in.readInt() - 1;
		int destination = in.readInt() - 1;
		GraphAlgorithms.DistanceResult result = GraphAlgorithms.leviteAlgorithm(graph, source);
		if (result.getDistances()[destination] == Long.MAX_VALUE)
			out.println("No");
		else {
			out.println("Yes");
			IOUtils.printCollection(CollectionUtils.increment(MiscUtils.getPath(result.getLast(), destination)), out);
		}
	}
}

