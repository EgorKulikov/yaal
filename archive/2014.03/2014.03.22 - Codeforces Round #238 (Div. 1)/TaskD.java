package net.egork;

import net.egork.collections.intervaltree.LCA;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		long[] y = new long[count];
		for (int i = 0; i < count; i++) {
			x[i] = in.readInt();
			y[i] = in.readLong();
		}
		int[] parent = new int[count];
		int[] vertex = new int[count];
		Segment[] segment = new Segment[count - 1];
		int size = 0;
		vertex[0] = count - 1;
		for (int i = count - 2; i >= 0; i--) {
			while (size > 0 && segment[size - 1].over(x[i], y[i]))
				size--;
			parent[i] = vertex[size];
			segment[size] = new Segment(x[i], y[i], x[parent[i]], y[parent[i]]);
			vertex[++size] = i;
		}
		Graph graph = new BidirectionalGraph(count, count - 1);
		for (int i = 0; i < count - 1; i++)
			graph.addSimpleEdge(i, parent[i]);
		LCA lca = new LCA(graph, count - 1);
		int queryCount = in.readInt();
		int[] answer = new int[queryCount];
		for (int i = 0; i < queryCount; i++) {
			int first = in.readInt() - 1;
			int second = in.readInt() - 1;
			answer[i] = lca.getLCA(first, second) + 1;
		}
		out.printLine(answer);
    }

	static class Segment {
		private final int x0;
		private final long y0;
		private final int x1;
		private final long y1;

		Segment(int x0, long y0, int x1, long y1) {
			this.x0 = x0;
			this.y0 = y0;
			this.x1 = x1;
			this.y1 = y1;
		}

		boolean over(int x, long y) {
	    	return x0 * (y - y1) + x * (y1 - y0) + x1 * (y0 - y) < 0;
		}
	}
}
