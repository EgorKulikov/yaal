package on2014_08.on2014_08_22_Single_Round_Match_630.Egalitarianism3;



import net.egork.collections.map.Counter;
import net.egork.collections.set.EHashSet;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;

import java.util.Set;

public class Egalitarianism3 {
    public int maxCities(int n, int[] a, int[] b, int[] len) {
		int answer = Math.min(n, 2);
		MiscUtils.decreaseByOne(a, b);
		Graph graph = BidirectionalGraph.createWeightedGraph(n, a, b, ArrayUtils.asLong(len));
		for (int i = 0; i < n; i++) {
			Counter<Long> counter = new Counter<>();
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				Set<Long> set = new EHashSet<>();
				dfs(graph.destination(j), i, graph.weight(j), graph, set);
				for (long k : set) {
					counter.add(k);
				}
			}
			for (long j : counter.values()) {
				answer = (int) Math.max(answer, j);
			}
		}
		return answer;
    }

	private void dfs(int vertex, int last, long weight, Graph graph, Set<Long> set) {
		set.add(weight);
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last) {
				dfs(next, vertex, weight + graph.weight(i), graph, set);
			}
		}
	}
}
