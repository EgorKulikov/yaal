package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] count = IOUtils.readIntArray(in, 3);
		Graph[] graphs = new Graph[3];
		for (int i = 0; i < 3; i++) {
			int[] from = new int[count[i] - 1];
			int[] to = new int[count[i] - 1];
			IOUtils.readIntArrays(in, from, to);
			MiscUtils.decreaseByOne(from, to);
			graphs[i] = BidirectionalGraph.createGraph(count[i], from, to);
		}
		long[][] sum = new long[3][];
		for (int i = 0; i < 3; i++) {
			sum[i] = new long[count[i]];
			int[] vertices = new int[count[i]];
			dfs(0, 0, -1, graphs[i], sum[i], vertices);
			dfs2(0, 0, -1, graphs[i], sum[i], 0, vertices, count[i]);
		}
		long answer = 0;
		long internal = 0;
		for (int i = 0; i < 3; i++) {
//			LCA lca = new LCA(graphs[i]);
			int first = (i + 1) % 3;
			int second = (i + 2) % 3;
			long total = ArrayUtils.maxElement(sum[first]) * (count[i] + count[second]) + ArrayUtils.maxElement(sum[second]) * (count[i] + count[first]) + 2L * count[first] * count[second] + (long)(count[first] + count[second]) * count[i];
			long[] bestFirst = new long[count[i]];
			long[] bestSecond = new long[count[i]];
			long max = dfs3(0, -1, count[first], count[second], sum[i], graphs[i], bestFirst, bestSecond);
			for (int j = 0; j < count[i]; j++) {
				internal += sum[i][j];
//				for (int k = 0; k < count[i]; k++) {
//					long distance = lca.getPathLength(j, k);
//					answer = Math.max(answer, total + sum[i][j] * count[first] + sum[i][k] * count[second] + distance * count[first] * count[second]);
//				}
			}
			answer = Math.max(answer, max + total);
		}
		answer += internal / 2;
		out.printLine(answer);

    }

	private long dfs3(int vertex, int last, int fc, int sc, long[] sum, Graph graph, long[] bestFirst, long[] bestSecond) {
		bestFirst[vertex] = sum[vertex] * fc;
		bestSecond[vertex] = sum[vertex] * sc;
		long result = bestFirst[vertex] + bestSecond[vertex];
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next == last)
				continue;
			result = Math.max(result, dfs3(next, vertex, fc, sc, sum, graph, bestFirst, bestSecond));
			result = Math.max(result, bestFirst[next] + (long)fc * sc + bestSecond[vertex]);
			result = Math.max(result, bestSecond[next] + (long)fc * sc + bestFirst[vertex]);
			bestFirst[vertex] = Math.max(bestFirst[vertex], bestFirst[next] + (long)fc * sc);
			bestSecond[vertex] = Math.max(bestSecond[vertex], bestSecond[next] + (long)fc * sc);
		}
		return result;
	}

	private long dfs(int level, int vertex, int last, Graph graph, long[] sum, int[] vertices) {
		long result = 0;
		vertices[vertex] = 1;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last) {
				result += dfs(level + 1, next, vertex, graph, sum, vertices);
				result += vertices[next];
				vertices[vertex] += vertices[next];
			}
		}
		sum[vertex] = result;
		return result;
	}

	private void dfs2(int level, int vertex, int last, Graph graph, long[] sum, long toAdd, int[] vertices, int count) {
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				dfs2(level + 1, next, vertex, graph, sum, toAdd + sum[vertex] - sum[next] + count - 2 * vertices[next], vertices, count);
		}
		sum[vertex] += toAdd;
	}
}
