package on2014_03.on2014_03_12_Single_Round_Match_612.SpecialCells;



import net.egork.collections.Pair;
import net.egork.collections.intcollection.IntPair;
import net.egork.collections.set.EHashSet;
import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;
import java.util.Set;

public class SpecialCells {
    public int guess(int[] x, int[] y) {
		int[] xs = x.clone();
		Arrays.sort(xs);
		xs = ArrayUtils.unique(xs);
		int[] ys = y.clone();
		Arrays.sort(ys);
		ys = ArrayUtils.unique(ys);
		Graph graph = new Graph(xs.length + ys.length + 2);
		int source = xs.length + ys.length;
		int sink = source + 1;
		Set<IntPair> present = new EHashSet<IntPair>();
		for (int i = 0; i < x.length; i++)
			present.add(new IntPair(x[i], y[i]));
		for (int i : x)
			graph.addFlowEdge(source, Arrays.binarySearch(xs, i), 1);
		for (int i : y)
			graph.addFlowEdge(Arrays.binarySearch(ys, i) + xs.length, sink, 1);
		for (int i : xs) {
			for (int j : ys) {
				if (present.contains(new IntPair(i, j)))
					graph.addFlowWeightedEdge(Arrays.binarySearch(xs, i), Arrays.binarySearch(ys, j) + xs.length, 1, 1);
				else
					graph.addFlowWeightedEdge(Arrays.binarySearch(xs, i), Arrays.binarySearch(ys, j) + xs.length, 0, 1);
			}
		}
		Pair<Long,Long> result = new MinCostFlow(graph, source, sink, false).minCostMaxFlow();
		return (int)(long) result.first;
    }
}
