package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int start = in.readInt() - 1;
		int end = in.readInt() - 1;
		int fee = in.readInt();
		int price = in.readInt();
		int fine = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] probability = new int[edgeCount];
		int[] distance = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, probability, distance);
		MiscUtils.decreaseByOne(from, to);
		int[][] dst = new int[count][count];
		ArrayUtils.fill(dst, Integer.MAX_VALUE / 2);
		for (int i = 0; i < count; i++)
			dst[i][i] = 0;
		for (int i = 0; i < edgeCount; i++) {
			dst[from[i]][to[i]] = Math.min(dst[from[i]][to[i]], distance[i]);
			dst[to[i]][from[i]] = Math.min(dst[to[i]][from[i]], distance[i]);
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++)
					dst[j][k] = Math.min(dst[j][k], dst[j][i] + dst[i][k]);
			}
		}
		Graph graph = new BidirectionalGraph(count);
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++)
				graph.addWeightedEdge(i, j, dst[i][j] * 100L * price + fee * 100);
		}
		for (int i = 0; i < edgeCount; i++)
			graph.addWeightedEdge(from[i], to[i], (distance[i] * price + fine) * probability[i]);
		long answer = ShortestDistance.dijkstraAlgorithm(graph, start, end).first;
		out.printLine(answer / 100 + "." + (answer / 10 % 10) + "" + (answer % 10));
    }
}
