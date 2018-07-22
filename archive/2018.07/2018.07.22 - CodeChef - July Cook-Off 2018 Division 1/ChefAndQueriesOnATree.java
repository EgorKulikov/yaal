package net.egork;

import net.egork.collections.intervaltree.LCA;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.graph.BidirectionalGraph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.copyOfRange;
import static net.egork.misc.ArrayUtils.*;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class ChefAndQueriesOnATree {
    BidirectionalGraph graph;
    int[] level;
    int[] size;
    int[] parent;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int q = in.readInt();
        int[] a = in.readIntArray(n);
        int[] x = new int[n - 1];
        int[] y = new int[n - 1];
        in.readIntArrays(x, y);
        decreaseByOne(x, y);
        graph = BidirectionalGraph.createGraph(n, x, y);
        level = new int[n];
        size = new int[n];
        parent = new int[n];
        go(0, -1, 0);
        boolean[] heavy = new boolean[n];
        for (int i = 1; i < n; i++) {
            if (size[i] * 2 >= size[parent[i]]) {
                heavy[i] = true;
            }
        }
        int[] order = order(level);
        reverse(order);
        Tree[] tree = new Tree[n];
        int[] heavyParent = new int[n];
        for (int i : order) {
            if (tree[i] != null) {
                continue;
            }
            IntList list = new IntArrayList();
            int current = i;
            while (true) {
                list.add(a[current]);
                if (!heavy[current]) {
                    break;
                }
                current = parent[current];
            }
            int hp = parent[current];
            list.inPlaceReverse();
            Tree cTree = new Tree(list.toArray(), level[current]);
            current = i;
            while (true) {
                tree[current] = cTree;
                heavyParent[current] = hp;
                if (!heavy[current]) {
                    break;
                }
                current = parent[current];
            }
        }
        LCA lca = new LCA(graph);
        int answer = 0;
        for (int i = 0; i < q; i++) {
            int r = in.readInt() ^ answer;
            int k = in.readInt();
            int[] u = in.readIntArray(k);
            for (int j = 0; j < k; j++) {
                u[j] ^= answer;
            }
            decreaseByOne(u);
            answer = MAX_VALUE;
            int root = u[0];
            for (int j = 1; j < k; j++) {
                root = lca.getLCA(root, u[j]);
            }
            int startLevel = level[root];
            for (int j : u) {
                while (true) {
                    if (tree[j].startLevel <= startLevel) {
                        answer = Math.min(answer, tree[j].query(startLevel - tree[j].startLevel, level[j] - tree[j]
                                .startLevel, r));
                        break;
                    } else {
                        answer = Math.min(answer, tree[j].query(0, level[j] - tree[j].startLevel, r));
                        j = heavyParent[j];
                    }
                }
            }
            out.printLine(answer);
        }
    }

    private int go(int vertex, int last, int cLevel) {
        level[vertex] = cLevel;
        parent[vertex] = last;
        size[vertex] = 1;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            size[vertex] += go(next, vertex, cLevel + 1);
        }
        return size[vertex];
    }

    static class Tree {
        int[][] values;
        int n;
        int startLevel;

        Tree(int[] a, int startLevel) {
            n = a.length;
            values = new int[4 * n][];
            init(0, 0, n - 1, a);
            this.startLevel = startLevel;
        }

        private void init(int root, int left, int right, int[] a) {
            values[root] = copyOfRange(a, left, right + 1);
            sort(values[root]);
            if (left != right) {
                int middle = (left + right) >> 1;
                init(2 * root + 1, left, middle, a);
                init(2 * root + 2, middle + 1, right, a);
            }
        }

        int query(int left, int right, int x) {
            return query(0, 0, n - 1, left, right, x);
        }

        private int query(int root, int from, int to, int left, int right, int x) {
            if (to < left || from > right) {
                return MAX_VALUE;
            }
            if (from >= left && to <= right) {
                int at = binarySearch(values[root], x);
                if (at >= 0) {
                    return 0;
                }
                at = -at - 1;
                int result = MAX_VALUE;
                if (at < values[root].length) {
                    result = min(result, abs(values[root][at] - x));
                }
                if (at > 0) {
                    result = min(result, abs(values[root][at - 1] - x));
                }
                return result;
            }
            int middle = (from + to) >> 1;
            return min(query(2 * root + 1, from, middle, left, right, x), query(2 * root + 2, middle + 1, to, left,
                    right, x));
        }
    }
}
