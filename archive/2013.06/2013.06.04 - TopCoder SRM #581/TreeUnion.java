package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.misc.MiscUtils;
import net.egork.string.StringUtils;

public class TreeUnion {
    public double expectedCycles(String[] tree1, String[] tree2, int K) {
		Graph first = buildGraph(tree1);
		Graph second = buildGraph(tree2);
		double[] firstQty = buildQty(first);
		double[] secondQty = buildQty(second);
		int count = first.vertexCount();
		double answer = 0;
		for (int i = 2; i + 2 <= K; i++)
			answer += firstQty[i] * secondQty[K - i] * 2 / (count * (count - 1));
		return answer;
    }

	private double[] buildQty(Graph graph) {
		double[] qty = new double[6];
		for (int i = 0; i < graph.vertexCount(); i++)
			calculate(i, -1, 1, graph, qty);
		for (int i = 2; i < 6; i++)
			qty[i] /= 2;
		return qty;
	}

	private void calculate(int vertex, int last, int depth, Graph graph, double[] qty) {
		qty[depth]++;
		if (++depth >= qty.length)
			return;
		for (Edge edge : graph.outbound(vertex)) {
			int next = edge.getDestination();
			if (next == last)
				continue;
			calculate(next, vertex, depth, graph, qty);
		}
	}

	private Graph buildGraph(String[] tree) {
		int[] from = MiscUtils.getIntArray(StringUtils.unite(tree));
		int[] to = new int[from.length];
		for (int i = 0; i < to.length; i++)
			to[i] = i + 1;
		return BidirectionalGraph.createGraph(from.length + 1, from, to);
	}
}
