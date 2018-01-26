package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefVote {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] votes = IOUtils.readIntArray(in, count);
        if (ArrayUtils.sumArray(votes) != count || ArrayUtils.maxElement(votes) == count) {
            out.printLine(-1);
            return;
        }
        Graph graph = new Graph(2 * count + 2);
        int source = 2 * count;
        int sink = 2 * count + 1;
        for (int i = 0; i < count; i++) {
            graph.addFlowEdge(source, i, 1);
            for (int j = 0; j < count; j++) {
                if (i != j) {
                    graph.addFlowEdge(i, j + count, 1);
                }
            }
            graph.addFlowEdge(i + count, sink, votes[i]);
        }
        MaxFlow.dinic(graph, source, sink);
        int[] answer = new int[count];
        for (int i = 0; i < count; i++) {
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                if (graph.flow(j) == 1) {
                    answer[i] = graph.destination(j) - count + 1;
                }
            }
        }
        out.printLine(answer);
    }
}
