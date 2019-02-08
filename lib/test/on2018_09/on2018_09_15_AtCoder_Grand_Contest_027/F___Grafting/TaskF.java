package on2018_09.on2018_09_15_AtCoder_Grand_Contest_027.F___Grafting;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.count;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = new int[n - 1];
        int[] b = new int[n - 1];
        int[] c = new int[n - 1];
        int[] d = new int[n - 1];
        in.readIntArrays(a, b);
        in.readIntArrays(c, d);
        decreaseByOne(a, b, c, d);
        Graph start = BidirectionalGraph.createGraph(n, a, b);
        Graph end = BidirectionalGraph.createGraph(n, c, d);
        int[] pStart = new int[n];
        int[] pEnd = new int[n];
        boolean[] move = new boolean[n];
        int answer = n + 1;
        for (int i = 0; i < n; i++) {
            buildParent(start, i, -1, pStart);
            buildParent(end, i, -1, pEnd);
            for (int j = 0; j < n; j++) {
                move[j] = pStart[j] != pEnd[j];
            }
            boolean ok = true;
            for (int j = 0; j < n; j++) {
                if (j != i && !move[j] && move[pStart[j]]) {
                    ok = false;
                    break;
                }
            }
            if (!ok) {
                continue;
            }
            Graph top = new Graph(n);
            for (int j = 0; j < n; j++) {
                if (move[j] && move[pStart[j]]) {
                    top.addSimpleEdge(j, pStart[j]);
                }
                if (move[j] && move[pEnd[j]]) {
                    top.addSimpleEdge(pEnd[j], j);
                }
            }
            if (GraphAlgorithms.topologicalSort(top) == null) {
                continue;
            }
            answer = Math.min(answer, count(move, true));
        }
        Graph wasStart = start;
        for (int i = 0; i < n; i++) {
            if (wasStart.nextOutbound(wasStart.firstOutbound(i)) == -1) {
                int k = wasStart.destination(wasStart.firstOutbound(i));
                int edge = -1;
                for (int j = 0; j < n - 1; j++) {
                    if (a[j] == i || b[j] == i) {
                        edge = j;
                    }
                }
                for (int l = 0; l < n; l++) {
                    if (l != k && l != i) {
                        if (a[edge] == i) {
                            b[edge] = l;
                        } else {
                            a[edge] = l;
                        }
                        start = BidirectionalGraph.createGraph(n, a, b);
                        buildParent(start, i, -1, pStart);
                        buildParent(end, i, -1, pEnd);
                        for (int j = 0; j < n; j++) {
                            move[j] = pStart[j] != pEnd[j];
                        }
                        boolean ok = true;
                        for (int j = 0; j < n; j++) {
                            if (j != i && !move[j] && move[pStart[j]]) {
                                ok = false;
                                break;
                            }
                        }
                        if (!ok) {
                            continue;
                        }
                        Graph top = new Graph(n);
                        for (int j = 0; j < n; j++) {
                            if (move[j] && move[pStart[j]]) {
                                top.addSimpleEdge(j, pStart[j]);
                            }
                            if (move[j] && move[pEnd[j]]) {
                                top.addSimpleEdge(pEnd[j], j);
                            }
                        }
                        if (GraphAlgorithms.topologicalSort(top) == null) {
                            continue;
                        }
                        answer = Math.min(answer, n);
                    }
                }
                if (a[edge] == i) {
                    b[edge] = k;
                } else {
                    a[edge] = k;
                }
            }
        }
        if (answer == n + 1) {
            answer = -1;
        }
        out.printLine(answer);
    }

    private void buildParent(Graph graph, int vertex, int last, int[] parent) {
        parent[vertex] = last;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int to = graph.destination(i);
            if (to == last) {
                continue;
            }
            buildParent(graph, to, vertex, parent);
        }
    }
}
