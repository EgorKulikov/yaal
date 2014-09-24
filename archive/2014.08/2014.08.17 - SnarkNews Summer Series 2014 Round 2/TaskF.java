package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int changeTime = in.readInt();
		int count = in.readInt();
		int lineCount = in.readInt();
		int from = in.readInt() - 1;
		int to = in.readInt() - 1;
		int[][] id = new int[count][lineCount];
		ArrayUtils.fill(id, -1);
		int nextId = 0;
		Graph graph = new BidirectionalGraph(0);
		for (int i = 0; i < lineCount; i++) {
			int length = in.readInt();
			int[] indices = new int[length];
			int[] times = new int[length];
			IOUtils.readIntArrays(in, indices, times);
			MiscUtils.decreaseByOne(indices);
			graph.addVertices(length);
			for (int j = 1; j < length; j++) {
				graph.addWeightedEdge(nextId + j - 1, nextId + j, times[j] - times[j - 1]);
			}
			for (int j = 0; j < length; j++) {
				id[indices[j]][i] = nextId++;
			}
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < lineCount; j++) {
				if (id[i][j] != -1) {
					for (int k = j + 1; k < lineCount; k++) {
						if (id[i][k] != -1) {
							graph.addWeightedEdge(id[i][j], id[i][k], changeTime);
						}
					}
				}
			}
		}
		long answer = Long.MAX_VALUE;
		for (int i = 0; i < lineCount; i++) {
			if (id[from][i] != -1) {
				long[] distances = ShortestDistance.dijkstraAlgorithm(graph, id[from][i]).first;
				for (int j = 0; j < lineCount; j++) {
					if (id[to][j] != -1) {
						answer = Math.min(answer, distances[id[to][j]]);
					}
				}
			}
		}
		out.printLine(answer);
	}
}
