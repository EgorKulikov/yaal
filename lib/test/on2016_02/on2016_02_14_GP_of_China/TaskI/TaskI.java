package on2016_02.on2016_02_14_GP_of_China.TaskI;



import net.egork.collections.intervaltree.LCA;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskI {
    LCA lca;

    int dist(int a, int b) {
        return lca.getLevel(a) + lca.getLevel(b) - 2 * lca.getLevel(lca.getLCA(a, b));
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] p = readIntArray(in, n);
        int[] a = new int[n - 1];
        int[] b = new int[n - 1];
        readIntArrays(in, a, b);
        decreaseByOne(p, a, b);
        Graph graph = BidirectionalGraph.createGraph(n, a, b);
        lca = new LCA(graph);
        int answer = 0;
        int[] parent = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                int next = graph.destination(j);
                if (lca.getLevel(next) < lca.getLevel(i)) {
                    parent[i] = j;
                }
            }
        }
//        Graph nGraph = new Graph(n);
//        for (int i = 0; i < n - 1; i++) {
//
//            nGraph.addWeightedEdge(a[i], b[i], 1);
//            nGraph.addWeightedEdge(b[i], a[i], 1);
//        }
        int[][] distances = new int[n][n];
        fill(distances, INF);
        for (int i = 0; i < n; i++) {
            if (i != 0 && i == p[i]) {
                distances[0][i] = 0;
                continue;
            }
            int top = lca.getLCA(i, p[i]);
            int current = i;
            while (current != top) {
                for (int j = 0; j < n; j++) {
                    distances[i][j] = Math.min(distances[i][j], dist(current, j));
                }
//                if (i != current) {
//                    nGraph.addWeightedEdge(i, current, 0);
//                }
                answer++;
                current = graph.destination(parent[current]);
            }
            current = p[i];
            while (current != top) {
                for (int j = 0; j < n; j++) {
                    distances[i][j] = Math.min(distances[i][j], dist(current, j));
                }
                answer++;
                current = graph.destination(parent[current]);
            }
            for (int j = 0; j < n; j++) {
                distances[i][j] = Math.min(distances[i][j], dist(current, j));
            }
        }
        ng = new int[n][n];
        mg = new int[n][n];
        mgvia = new int[n][n];
        Branching branching = minRooted(n, distances, 0);
        answer += branching.cost * 2;
        out.printLine(answer);
    }

    static class Branching {
            int root;
            int cost;
            int[] parent;
        }

    int INF = Integer.MAX_VALUE / 5;
    int[][] ng;
    int[][] mg;
    int[][] mgvia;

    private Branching minRooted(int n, int[][] g, int root) {
//            int[][] ng = new int[n][n];
//        fill(ng, 0);
        for (int i = 0; i < n; i++) {
            fill(ng[i], 0, n, 0);
        }
            int delta = 0;
            int[] parent = new int[n];
            parent[root] = -1;
            for (int j = 0; j < n; ++j) if (j != root) {
                int min = INF;
                for (int i = 0; i < n; ++i) if (g[i][j] < min) {
                    min = g[i][j];
                    parent[j] = i;
                }
                for (int i = 0; i < n; ++i) ng[i][j] = g[i][j] - min;
                delta += min;
            }
            int[] cycle = findCycle(parent);
            if (cycle == null) {
                Branching res = new Branching();
                res.cost = delta;
                res.parent = parent;
                res.root = root;
                return res;
            }
            boolean[] incycle = new boolean[n];
            for (int x : cycle) incycle[x] = true;
            int[] mapped = new int[n];
            int mcnt = 1;
            for (int i = 0; i < n; ++i) {
                if (!incycle[i]) {
                    mapped[i] = mcnt++;
                } else {
                    mapped[i] = 0;
                }
            }
//            int[][] mg = new int[mcnt][mcnt];
//            int[][] mgvia = new int[mcnt][mcnt];
        for (int i = 0; i < mcnt; i++) {
            int[] x = mg[i];
            Arrays.fill(x, 0, mcnt, INF);
        }
        for (int i = 0; i < mcnt; i++) {
            int[] x = mgvia[i];
            Arrays.fill(x, 0, mcnt, -1);
        }
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j) {
                    int mi = mapped[i];
                    int mj = mapped[j];
                    if (mi == mj) continue;
                    if (ng[i][j] < mg[mi][mj]) {
                        mg[mi][mj] = ng[i][j];
                        if (mi == 0) mgvia[mi][mj] = i; else if (mj == 0) mgvia[mi][mj] = j;
                    }
                }
        for (int i = 0; i < mcnt; i++) {
            for (int j = 0; j < mcnt; j++) {
                g[i][j] = mg[i][j];
            }
        }
            Branching small = minRooted(mcnt, g, mapped[root]);
/*            for (int i = 0; i < n; ++i) if (i != root && !incycle[i]) {
                int sp = small.parent[mapped[i]];
                if (sp == 0) {
                    parent[i] = mgvia[0][mapped[i]];
                } else {
                    for (int j = 0; j < n; ++j) if (mapped[j] == sp)
                        parent[i] = j;
                }
            }
            {
                int sp = small.parent[0];
                int j;
                for (j = 0; j < n; ++j) if (mapped[j] == sp) break;
                int cycleRoot = mgvia[sp][0];
                parent[cycleRoot] = j;
            }*/
            Branching res = new Branching();
            res.cost = small.cost + delta;
            res.root = root;
            res.parent = parent;
            return res;
        }

        private int[] findCycle(int[] parent) {
            int n = parent.length;
            int[] mark = new int[n];
            Arrays.fill(mark, 0);
            int stage = 0;
            for (int i = 0; i < n; ++i) if (mark[i] == 0) {
                ++stage;
                int j = i;
                while (j >= 0 && mark[j] == 0) {
                    mark[j] = stage;
                    j = parent[j];
                }
                if (j >= 0 && mark[j] == stage) {
                    int[] res = new int[n];
                    int rp = 0;
                    int k = j;
                    do {
                        res[rp++] = k;
                        k = parent[k];
                    } while (k != j);
                    res = Arrays.copyOf(res, rp);
                    return res;
                }
            }
            return null;
        }



    private int[] buildMST(Graph graph, boolean[] ignore) {
        int vertexCount = graph.vertexCount();
        int[] parent = new int[vertexCount];
        parent[0] = -1;
        for (int i = 1; i < vertexCount; i++) {
            if (ignore[i]) {
                continue;
            }
            parent[i] = -1;
            long curWeight = Long.MAX_VALUE;
            for (int j = graph.firstInbound(i); j != -1; j = graph.nextInbound(j)) {
                if (graph.weight(j) < curWeight) {
                    parent[i] = j;
                    curWeight = graph.weight(j);
//                    break;
                }
            }
//            if (parent[i] == -1) {
//                parent[i] = graph.firstInbound(i);
//            }
        }
        int[] processed = new int[vertexCount];
        processed[0] = 2;
        for (int i = 1; i < vertexCount; i++) {
            if (processed[i] != 0 || ignore[i]) {
                continue;
            }
            int current = i;
            do {
                processed[current] = 1;
                current = graph.source(parent[current]);
            } while (processed[current] == 0);
            if (processed[current] == 2) {
                current = i;
                do {
                    processed[current] = 2;
                    current = graph.source(parent[current]);
                } while (processed[current] == 1);
                continue;
            }
            long minEdge = Long.MAX_VALUE;
            int start = current;
            do {
                minEdge = Math.min(minEdge, graph.weight(parent[current]));
//                if (graph.weight(parent[current]) == 0) {
//                    minEdge = 0;
//                }
                current = graph.source(parent[current]);
                processed[current] = 3;
            } while (current != start);
            Graph newGraph = new Graph(vertexCount, graph.edgeCount());
            for (int j = 0; j < graph.edgeCount(); j++) {
                int from = graph.source(j);
                int to = graph.destination(j);
                if (processed[from] == 3 && processed[to] == 3) {
                    if (from == start) {
                        newGraph.addSimpleEdge(to, to);
                    } else {
                        newGraph.addSimpleEdge(from, from);
                    }
                } else if (processed[from] == 3) {
                    newGraph.addWeightedEdge(start, to, graph.weight(j));
                } else if (processed[to] == 3) {
                    newGraph.addWeightedEdge(from, start, graph.weight(j) - (graph.weight(parent[to]) - minEdge));
                } else {
                    newGraph.addWeightedEdge(from, to, graph.weight(j));
                }
            }
            //			boolean[] wasIgnore = ignore.clone();
            for (int j = 0; j < vertexCount; j++) {
                if (processed[j] == 3 && j != start) {
                    ignore[j] = true;
                }
            }
            int[] newParent = buildMST(newGraph, ignore.clone());
            //			int[] realParent = new int[parent.length];
            //			realParent[0] = -1;
            //			for (int j = 1; j < parent.length; j++) {
            //				if (wasIgnore[j])
            //					realParent[j] = -2;
            //				else
            //					realParent[j] = graph.source(parent[j]);
            //			}
            //			System.err.println(Arrays.toString(ArrayUtils.createOrder(parent.length)));
            //			System.err.println(Arrays.toString(realParent));
            for (int j = 1; j < newParent.length; j++) {
                if (!ignore[j]) {
                    parent[graph.destination(newParent[j])] = newParent[j];
                }
            }
            //			for (int j = 1; j < parent.length; j++) {
            //				if (wasIgnore[j])
            //					realParent[j] = -2;
            //				else
            //					realParent[j] = graph.source(parent[j]);
            //			}
            //			System.err.println(Arrays.toString(realParent));
            break;
        }
        return parent;
    }


    private int go(int current, int last, int[] up, int[] down, Graph graph) {
        int result = 0;
        for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            int call = go(next, current, up, down, graph);
            result += call;
            int max = max(up[i >> 1], down[i >> 1]) * 2;
            result += max;
            if (max == 0 && call != 0) {
                result += 2;
            }
        }
        return result;
    }
}
