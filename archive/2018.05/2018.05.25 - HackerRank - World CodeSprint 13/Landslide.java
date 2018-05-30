package net.egork;

import net.egork.collections.intervaltree.LCA;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.DFSOrder;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import static net.egork.misc.MiscUtils.decreaseByOne;

public class Landslide {
    LCA lca;
    NavigableSet<Integer>[] blocks;
    int[] best;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] x = new int[n - 1];
        int[] y = new int[n - 1];
        in.readIntArrays(x, y);
        decreaseByOne(x, y);
        BidirectionalGraph graph = BidirectionalGraph.createGraph(n, x, y);
        lca = new LCA(graph);
        DFSOrder order = new DFSOrder(graph);
        blocks = new NavigableSet[4 * n];
        best = new int[4 * n];
        init(0, 0, n - 1);
        int q = in.readInt();
        Map<IntIntPair, Integer> edges = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            edges.put(new IntIntPair(x[i], y[i]), i);
            edges.put(new IntIntPair(y[i], x[i]), i);
        }
        boolean[] destroyed = new boolean[n - 1];
        for (int i = 0; i < q; i++) {
            char type = in.readCharacter();
            int xx = in.readInt() - 1;
            int yy = in.readInt() - 1;
            if (type == 'd') {
                IntIntPair key = new IntIntPair(xx, yy);
                if (!edges.containsKey(key)) {
                    continue;
                }
                int id = edges.get(key);
                if (destroyed[id]) {
                    continue;
                }
                if (lca.getLevel(xx) < lca.getLevel(yy)) {
                    xx = yy;
                }
                add(0, 0, n - 1, order.position[xx], order.end[xx], xx);
                destroyed[id] = true;
            } else if (type == 'c') {
                IntIntPair key = new IntIntPair(xx, yy);
                if (!edges.containsKey(key)) {
                    continue;
                }
                int id = edges.get(key);
                if (!destroyed[id]) {
                    continue;
                }
                if (lca.getLevel(xx) < lca.getLevel(yy)) {
                    xx = yy;
                }
                remove(0, 0, n - 1, order.position[xx], order.end[xx], xx);
                destroyed[id] = false;
            } else {
                int pxx = query(0, 0, n - 1, order.position[xx]);
                int pyy = query(0, 0, n - 1, order.position[yy]);
                if (pxx == pyy) {
                    out.printLine(lca.getPathLength(xx, yy));
                } else {
                    out.printLine("Impossible");
                }
            }
        }
    }

    private int query(int root, int left, int right, int position) {
        int here = best[root];//blocks[root].first();
        if (left == right) {
            return here;
        }
        int middle = (left + right) >> 1;
        int call;
        if (position <= middle) {
            call = query(2 * root + 1, left, middle, position);
        } else {
            call = query(2 * root + 2, middle + 1, right, position);
        }
        if (lca.getLevel(call) > lca.getLevel(here)) {
            here = call;
        }
        return here;
    }

    private void add(int root, int left, int right, int from, int to, int value) {
        if (left > to || right < from) {
            return;
        }
        if (left >= from && right <= to) {
            blocks[root].add(value);
            if (lca.getLevel(value) > lca.getLevel(best[root])) {
                best[root] = value;
            }
            return;
        }
        int middle = (left + right) >> 1;
        add(2 * root + 1, left, middle, from, to, value);
        add(2 * root + 2, middle + 1, right, from, to, value);
    }

    private void remove(int root, int left, int right, int from, int to, int value) {
        if (left > to || right < from) {
            return;
        }
        if (left >= from && right <= to) {
            blocks[root].remove(value);
            if (best[root] == value) {
                if (blocks[root].isEmpty()) {
                    best[root] = 0;
                } else {
                    best[root] = blocks[root].first();
                }
            }
            return;
        }
        int middle = (left + right) >> 1;
        remove(2 * root + 1, left, middle, from, to, value);
        remove(2 * root + 2, middle + 1, right, from, to, value);
    }

    private void init(int root, int left, int right) {
        blocks[root] = new TreeSet<>((a, b) -> lca.getLevel(b) - lca.getLevel(a));
        if (left != right) {
            int middle = (left + right) >> 1;
            init(2 * root + 1, left, middle);
            init(2 * root + 2, middle + 1, right);
        }
    }
}
