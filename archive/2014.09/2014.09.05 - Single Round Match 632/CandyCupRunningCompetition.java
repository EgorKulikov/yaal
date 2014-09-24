package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;

public class CandyCupRunningCompetition {
    public int findMaximum(int N, int[] A, int[] B) {
		Graph graph = new BidirectionalGraph(N);
		long answer = 0;
		for (int i = A.length - 1; i >= 0; i--) {
			answer *= 3;
			graph.addFlowEdge(A[i], B[i], 1);
			answer += MaxFlow.dinic(graph, 0, N - 1);
			for (int j = 0; j < graph.edgeCount(); j++) {
				graph.capacity[j] = Math.min(Integer.MAX_VALUE, graph.capacity[j] * 3);
			}
			answer %= (int)(1e9 + 7);
		}
		return (int)answer;
    }
}
