package on2014_06.on2014_06_12_Single_Round_Match_624.DrivingPlans;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;

public class DrivingPlans {
	private static final long MOD = (long) (1e9 + 9);

	public int count(int N, int[] A, int[] B, int[] C) {
		MiscUtils.decreaseByOne(A, B);
		Graph graph = BidirectionalGraph.createWeightedGraph(N, A, B, ArrayUtils.asLong(C));
		long[] fromStart = ShortestDistance.dijkstraAlgorithm(graph, 0).first;
		long[] fromEnd = ShortestDistance.dijkstraAlgorithm(graph, N - 1).first;
		long distance = fromEnd[0];
		for (int i = 0; i < N; i++) {
			if (fromStart[i] + fromEnd[i] == distance) {
				for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
					if (graph.weight(j) == 0)
						return -1;
				}
			}
		}
		int[] order = ArrayUtils.order(fromStart);
		long[] ways = new long[N];
		ways[0] = 1;
		for (int i : order) {
			if (fromStart[i] + fromEnd[i] != distance)
				continue;
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				int next = graph.destination(j);
				if (fromStart[i] + graph.weight(j) + fromEnd[next] == distance) {
					ways[next] += ways[i];
					if (ways[next] >= MOD)
						ways[next] -= MOD;
				}
			}
		}
		return (int) ways[N - 1];
    }
}
