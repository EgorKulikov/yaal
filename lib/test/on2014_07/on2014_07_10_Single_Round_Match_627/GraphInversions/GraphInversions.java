package on2014_07.on2014_07_10_Single_Round_Match_627.GraphInversions;



import net.egork.collections.set.TreapSet;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;

public class GraphInversions {
	Graph graph;
	NavigableSet<Integer> set;
	int[] weights;

    public int getMinimumInversions(int[] A, int[] B, int[] V, int K) {
		graph = BidirectionalGraph.createGraph(A.length, A, B);
		int result = Integer.MAX_VALUE;
		weights = Arrays.copyOf(V, V.length + 1);
		set = new TreapSet<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (weights[o1] != weights[o2]) {
					return weights[o1] - weights[o2];
				}
				return o1 - o2;
			}
		});
		for (int i = 0; i < A.length; i++) {
			result = Math.min(result, go(i, K));
		}
		if (result == Integer.MAX_VALUE) {
			result = -1;
		}
		return result;
    }

	private int go(int vertex, int remaining) {
		if (remaining == 0) {
			return 0;
		}
		if (set.contains(vertex)) {
			return Integer.MAX_VALUE;
		}
		int result = Integer.MAX_VALUE;
		set.add(vertex);
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			result = Math.min(result, go(graph.destination(i), remaining - 1));
		}
		if (result != Integer.MAX_VALUE) {
			weights[graph.vertexCount()] = weights[vertex];
			result += set.tailSet(graph.vertexCount(), false).size();
		}
		set.remove(vertex);
		return result;
	}
}
