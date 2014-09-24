package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	private Graph graph;
	private int[] oddity;
	private IntList answer;
	private boolean[] visited;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		graph = BidirectionalGraph.createGraph(count, from, to);
		oddity = IOUtils.readIntArray(in, count);
		answer = new IntArrayList();
		visited = new boolean[count];
		for (int i = 0; i < count; i++) {
			if (oddity[i] == 1) {
				dfs(i);
				if (oddity[i] == 1) {
					answer.popBack();
					oddity[i] = 0;
				}
				break;
			}
		}
		if (ArrayUtils.count(oddity, 1) != 0) {
			out.printLine(-1);
			return;
		}
		out.printLine(answer.size());
		out.printLine(answer);
    }

	private void dfs(int vertex) {
		answer.add(vertex + 1);
		oddity[vertex] ^= 1;
		visited[vertex] = true;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (!visited[next]) {
				dfs(next);
				answer.add(vertex + 1);
				oddity[vertex] ^= 1;
				if (oddity[next] != 0) {
					answer.add(next + 1);
					answer.add(vertex + 1);
					oddity[next] ^= 1;
					oddity[vertex] ^= 1;
				}
			}
		}
	}
}
