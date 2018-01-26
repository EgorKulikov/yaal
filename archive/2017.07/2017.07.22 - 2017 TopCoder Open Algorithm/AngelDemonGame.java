package net.egork;

import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;

public class AngelDemonGame {
    public String winner(String[] G, int A, int D) {
        int n = G.length;
        if (D >= n - 1) {
            return "Demon";
        }
        Graph g = new Graph(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (G[i].charAt(j) == 'Y') {
                    g.addFlowWeightedEdge(i, j, 0, 1);
                } else {
                    g.addFlowWeightedEdge(i, j, 1, 1);
                }
            }
        }
        Pair<Long, Long> result = MinCostFlow.minCostMaxFlow(g, 0, n - 1, false, D + 1);
        if (result.second <= D || result.first > A) {
            return "Unknown";
        }
        return "Angel";
    }
}
