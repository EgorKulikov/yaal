package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.graph.Graph;

import static net.egork.graph.MaxFlow.dinic;

public class DengklekGaneshAndChains {
    public String getBestChains(String[] chains, int[] lengths) {
        int n = chains.length;
        int m = lengths.length;
        boolean[][] graph = new boolean[m][n];
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            chains[i] += chains[i];
        }
        for (int i = 0; i < lengths.length; i++) {
            IntList ids = new IntArrayList();
            String best = "";
            for (int j = 0; j < n; j++) {
                Graph g = new Graph(i + n + 2);
                for (int k = 0; k < i; k++) {
                    g.addFlowEdge(i + n, k, 1);
                    for (int l = 0; l < n; l++) {
                        if (graph[k][l]) {
                            g.addFlowEdge(k, i + l, 1);
                        }
                    }
                }
                for (int k = 0; k < n; k++) {
                    if (j != k) {
                        g.addFlowEdge(i + k, i + n + 1, 1);
                    }
                }
                if (dinic(g, i + n, i + n + 1) != i) {
                    continue;
                }
                for (int k = 0; k < (chains[j].length() >> 1); k++) {
                    String candidate = chains[j].substring(k, k + lengths[i]);
                    if (ids.isEmpty() || candidate.compareTo(best) > 0) {
                        ids = new IntArrayList();
                        ids.add(j);
                        best = candidate;
                    } else if (candidate.equals(best)) {
                        ids.add(j);
                    }
                }
            }
            answer.append(best);
            for (int j : ids) {
                graph[i][j] = true;
            }
        }
        return answer.toString();
    }
}
