package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AcrossTheRiver {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int discCount = in.readInt();
		int width = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		int[] radius = new int[discCount];
		int[] cost = new int[discCount];
		IOUtils.readIntArrays(in, radius, cost);
		ArrayUtils.orderBy(cost, radius);
		for (int i = 1; i < discCount; i++)
			radius[i] = Math.max(radius[i], radius[i - 1]);
		Graph graph = new Graph(count * discCount + 2, count * (discCount * count + discCount + 1));
		int source = count * discCount;
		int destination = source + 1;
		for (int i = 0; i < count; i++) {
			boolean foundFromSource = false;
			boolean foundToDestination = false;
			for (int j = 0; j < discCount; j++) {
				int vertex = i * discCount + j;
				if (!foundFromSource && y[i] <= radius[j]) {
					graph.addWeightedEdge(source, vertex, cost[j]);
					foundFromSource = true;
				}
				if (!foundToDestination && y[i] + radius[j] >= width) {
					graph.addWeightedEdge(vertex, destination, 0);
					foundToDestination = true;
				}
				if (j != 0)
					graph.addWeightedEdge(vertex - 1, vertex, cost[j] - cost[j - 1]);
				for (int k = 0; k < count; k++) {
					int left = 0;
					int right = discCount;
					long distance = (long)(x[i] - x[k]) * (x[i] - x[k]) + (long)(y[i] - y[k]) * (y[i] - y[k]);
					while (left < right) {
						int middle = (left + right) >> 1;
						long totalRadius = radius[j] + radius[middle];
						if (totalRadius * totalRadius >= distance)
							right = middle;
						else
							left = middle + 1;
					}
					if (left < discCount)
						graph.addWeightedEdge(vertex, k * discCount + left, cost[left]);
				}
			}
		}
		Pair<Long,IntList> result = ShortestDistance.dijkstraAlgorithm(graph, source, destination);
		if (result == null) {
			out.printLine("impossible");
			return;
		}
		out.printLine(result.first);
	}
}
