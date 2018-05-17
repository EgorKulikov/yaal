package on2018_05.on2018_05_15_Codeforces_Round__483__Div__1____________Botan_Investments___________________.E_________NN;



import net.egork.collections.intervaltree.LCA;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.DFSOrder;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.System.arraycopy;
import static java.util.Arrays.binarySearch;
import static net.egork.misc.ArrayUtils.createOrder;
import static net.egork.misc.ArrayUtils.reversePermutation;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class EStranaNN {
    int[][] edges;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] p = in.readIntArray(n - 1);
        BidirectionalGraph graph = new BidirectionalGraph(n);
        for (int i = 0; i < n - 1; i++) {
            graph.addSimpleEdge(i + 1, p[i] - 1);
        }
        int m = in.readInt();
        int[] a = new int[m];
        int[] b = new int[m];
        in.readIntArrays(a, b);
        decreaseByOne(a, b);
        LCA lca = new LCA(graph);
        DFSOrder order = new DFSOrder(graph);
        int[] topmost = createOrder(n);
        for (int i = 0; i < m; i++) {
            int parent = lca.getLCA(a[i], b[i]);
            if (lca.getLevel(topmost[a[i]]) > lca.getLevel(parent)) {
                topmost[a[i]] = parent;
            }
            if (lca.getLevel(topmost[b[i]]) > lca.getLevel(parent)) {
                topmost[b[i]] = parent;
            }
        }
        int[] ltr = reversePermutation(order.position);
        for (int i = n - 1; i >= 0; i--) {
            int id = ltr[i];
            for (int j = graph.firstOutbound(id); j != -1; j = graph.nextOutbound(j)) {
                int to = graph.destination(j);
                if (order.position[to] > i && lca.getLevel(topmost[to]) < lca.getLevel(topmost[id])) {
                    topmost[id] = topmost[to];
                }
            }
        }
        int[][] steps = new int[20][n];
        arraycopy(topmost, 0, steps[0], 0, n);
        for (int i = 1; i < 20; i++) {
            for (int j = 0; j < n; j++) {
                steps[i][j] = steps[i - 1][steps[i - 1][j]];
            }
        }
        edges = new int[4 * n][];
        Graph buses = new BidirectionalGraph(n);
        for (int i = 0; i < m; i++) {
            buses.addSimpleEdge(order.position[a[i]], order.position[b[i]]);
        }
        init(0, 0, n - 1, buses);
        int q = in.readInt();
        for (int i = 0; i < q; i++) {
            int u = in.readInt() - 1;
            int v = in.readInt() - 1;
            int answer = 0;
            int parent = lca.getLCA(u, v);
            for (int j = 19; j >= 0; j--) {
                if (lca.getLevel(steps[j][u]) > lca.getLevel(parent)) {
                    answer += 1 << j;
                    u = steps[j][u];
                }
                if (lca.getLevel(steps[j][v]) > lca.getLevel(parent)) {
                    answer += 1 << j;
                    v = steps[j][v];
                }
            }
            if (u != parent && topmost[u] == u || v != parent && topmost[v] == v) {
                out.printLine(-1);
                continue;
            }
            if (u == parent || v == parent) {
                out.printLine(answer + 1);
                continue;
            }
            if (query(0, 0, n - 1, order.position[u], order.end[u], order.position[v], order.end[v])) {
                answer++;
            } else {
                answer += 2;
            }
            out.printLine(answer);
        }
    }

    private boolean query(int root, int left, int right, int lFrom, int lTo, int rFrom, int rTo) {
        if (right < lFrom || left > lTo) {
            return false;
        }
        if (left >= lFrom && right <= lTo) {
            int bs = binarySearch(edges[root], rFrom);
            return  (bs >= 0 || bs != binarySearch(edges[root], rTo));
        }
        int middle = (left + right) >> 1;
        return query(2 * root + 1, left, middle, lFrom, lTo, rFrom, rTo) || query(2 * root + 2, middle + 1, right, lFrom, lTo, rFrom, rTo);
    }

    private IntList init(int root, int left, int right, Graph buses) {
        if (left == right) {
            IntList result = new IntArrayList();
            for (int i = buses.firstOutbound(left); i != -1; i = buses.nextOutbound(i)) {
                result.add(buses.destination(i));
            }
            result.sort();
            result = result.unique();
            edges[root] = result.toArray();
            return result;
        }
        int middle = (left + right) >> 1;
        IntList l = init(2 * root + 1, left, middle, buses);
        IntList r = init(2 * root + 2, middle + 1, right, buses);
        int i = 0;
        int j = 0;
        IntList result = new IntArrayList();
        while (i < l.size() && j < r.size()) {
            if (l.get(i) == r.get(j)) {
                result.add(l.get(i));
                i++;
                j++;
            } else if (l.get(i) < r.get(j)) {
                result.add(l.get(i));
                i++;
            } else {
                result.add(r.get(j));
                j++;
            }
        }
        for (; i < l.size(); i++) {
            result.add(l.get(i));
        }
        for (; j < r.size(); j++) {
            result.add(r.get(j));
        }
        edges[root] = result.toArray();
        return result;
    }
}
