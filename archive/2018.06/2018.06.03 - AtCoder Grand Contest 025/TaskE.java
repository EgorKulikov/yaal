package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.intervaltree.LCA;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.StronglyConnectedComponents;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = new int[n - 1];
        int[] b = new int[n - 1];
        in.readIntArrays(a, b);
        int[] u = new int[m];
        int[] v = new int[m];
        in.readIntArrays(u, v);
        decreaseByOne(a, b, u, v);
        BidirectionalGraph graph = BidirectionalGraph.createGraph(n, a, b);
        LCA lca = new LCA(graph);
        int[] parent = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                if (lca.getLevel(graph.destination(j)) < lca.getLevel(i)) {
                    parent[i] = graph.destination(j);
                    break;
                }
            }
        }
        int[] first = createArray(n, -1);
        int[] second = createArray(n, -1);
        int[] not = new int[2 * m];
        for (int i = 0; i < m; i++) {
            not[i] = i + m;
            not[i + m] = i;
        }
        for (int i = 0; i < m; i++) {
            int root = lca.getLCA(u[i], v[i]);
            int lastFirst = -1;
            for (int j = u[i]; j != root; j = parent[j]) {
                if (first[j] == -1) {
                    first[j] = i;
                } else if (second[j] == -1) {
                    second[j] = i;
                    lastFirst = first[j];
                } else if (first[j] == lastFirst) {
                    second[j] = i;
                } else if (second[j] == lastFirst) {
                    first[j] = i;
                }
            }
            lastFirst = -1;
            for (int j = v[i]; j != root; j = parent[j]) {
                if (first[j] == -1) {
                    first[j] = not[i];
                } else if (second[j] == -1) {
                    second[j] = not[i];
                    lastFirst = first[j];
                } else if (first[j] == lastFirst) {
                    second[j] = not[i];
                } else if (second[j] == lastFirst) {
                    first[j] = not[i];
                }
            }
        }
        Graph sat = new Graph(2 * m);
        int t = 0;
        for (int i = 1; i < n; i++) {
            if (second[i] != -1) {
                sat.addSimpleEdge(first[i], not[second[i]]);
                sat.addSimpleEdge(second[i], not[first[i]]);
                sat.addSimpleEdge(not[first[i]], second[i]);
                sat.addSimpleEdge(not[second[i]], first[i]);
                t += 2;
            } else if (first[i] != -1) {
                t++;
            }
        }
        Pair<int[], Graph> result = StronglyConnectedComponents.kosaraju(sat);
        int[] value = new int[m];
        boolean[] taken = new boolean[result.second.vertexCount()];
        for (int i = 0; i < m; i++) {
            if (result.first[i] == result.first[not[i]]) {
                throw new RuntimeException();
            }
            if (taken[result.first[i]]) {
                value[i] = 1;
                continue;
            }
            if (taken[result.first[not[i]]]) {
                value[i] = -1;
                continue;
            }
            if (result.first[i] < result.first[not[i]]) {
                value[i] = -1;
            } else {
                value[i] = 1;
            }
            go(result.second, value[i] == 1 ? result.first[i] : result.first[not[i]], taken);
        }
        for (int i = 0; i < m; i++) {
            if (value[i] == -1) {
                int temp = u[i];
                u[i] = v[i];
                v[i] = temp;
            }
        }
        out.printLine(t);
        for (int i = 0; i < m; i++) {
            out.printLine(u[i] + 1, v[i] + 1);
        }
    }

    private void go(Graph graph, int vertex, boolean[] taken) {
        if (taken[vertex]) {
            return;
        }
        taken[vertex] = true;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            go(graph, graph.destination(i), taken);
        }
    }
}
