package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;
import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class BusCompany {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        IOUtils.readIntArrays(in, a, b, c);
        Graph graph = new Graph(m + 2);
        int source = 0;
        int sink = m + 1;
        for (int i = 0; i <= m; i++) {
            graph.addFlowWeightedEdge(i, i + 1, 0, k);
        }
        for (int i = 0; i < n; i++) {
            graph.addFlowWeightedEdge(a[i], b[i], -c[i], 1);
        }
        out.printLine(-new MinCostFlow(graph, source, sink, true).minCostMaxFlow().first);
    }
}
