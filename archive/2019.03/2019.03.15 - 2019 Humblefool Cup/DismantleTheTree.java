package net.egork;

import net.egork.collections.map.Counter;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;

import java.util.Map;

import static net.egork.misc.ArrayUtils.asLong;
import static net.egork.misc.ArrayUtils.sumArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class DismantleTheTree {
    public int dismantle(int N, int[] X, int[] Y, int[] weight) {
        decreaseByOne(X, Y);
        Graph graph = BidirectionalGraph.createWeightedGraph(N, X, Y, asLong(weight));
        long answer = sumArray(weight);
        for (int i = 0; i < N; i++) {
            Counter<Long> counter = new Counter<>();
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                counter.add(graph.weight(j));
            }
            for (Map.Entry<Long, Long> entry : counter.entrySet()) {
                answer -= entry.getKey() * (entry.getValue() / 2);
            }
        }
        return (int) answer;
    }
}
