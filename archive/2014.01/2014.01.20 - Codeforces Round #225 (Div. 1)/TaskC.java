package net.egork;

import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.DFSOrder;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] initial = IOUtils.readIntArray(in, count);
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		BidirectionalGraph graph = BidirectionalGraph.createGraph(count, from, to);
		DFSOrder order = new DFSOrder(graph);
		int[] level = new int[count];
		dfs(0, -1, 0, graph, level);
		for (int i = 0; i < count; i++) {
			if (level[i] == 1)
				initial[i] = -initial[i];
		}
		SumIntervalTree tree = new SumIntervalTree(count);
		for (int i = 0; i < count; i++)
			tree.update(order.position[i], order.position[i], initial[i]);
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			int vertex = in.readInt() - 1;
			if (type == 1) {
				int value = in.readInt();
				if (level[vertex] == 1)
					value = -value;
				tree.update(order.position[vertex], order.end[vertex], value);
			} else {
				long result = tree.query(order.position[vertex], order.position[vertex]);
				if (level[vertex] == 1)
					result = -result;
				out.printLine(result);
			}
		}
    }

	private void dfs(int vertex, int last, int curLevel, Graph graph, int[] level) {
		level[vertex] = curLevel;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				dfs(next, vertex, 1 - curLevel, graph, level);
		}
	}
}
