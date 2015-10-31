package on2015_06.on2015_06_20_EpicCode_CodeSprint.Epic_Tree;



import net.egork.collections.intervaltree.LCA;
import net.egork.graph.BidirectionalGraph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class EpicTree {
    private static final long MOD = 10009;

    static class Tree {
        long[] plainValue;
        long[] plainDelta;
        long[] totalValue;
        long[] totalDeltaDirect;
        long[] qty;
        long[] cumulativeQty;
        int size;
        
        Tree(int size) {
            this.size = size;
            int length = 4 * size;
            plainValue = new long[length];
            plainDelta = new long[length];
            totalValue = new long[length];
            totalDeltaDirect = new long[length];
            qty = new long[size + 1];
//            cumulativeQty = new long[size + 1];
        }

        void setQty(int at, int value) {
            qty[at + 1] = value;
        }

        void finish() {
            for (int i = 1; i <= size; i++) {
//                cumulativeQty[i] = qty[i] * i;
                qty[i] += qty[i - 1];
//                cumulativeQty[i] += cumulativeQty[i - 1];
            }
        }

        void updateValue(int from, int to, long query, long fromDown) {
            updateValue(0, 0, size - 1, from, to, query, fromDown);
        }

//        long getCumulative(int from, int to) {
//            return cumulativeQty[to + 1] - cumulativeQty[from] - from * getQty(from, to);
//        }
        
        void updateValue(int root, int left, int right, int from, int to, long query, long fromDown) {
            if (left >= from && right <= to) {
                plainValue[root] += query * (right - left + 1);
                plainDelta[root] += query;
                if (left == right) {
                    totalValue[root] += query * (getQty(left, to)) + fromDown;
                }
                totalDeltaDirect[root] += fromDown + query * getQty(right + 1, to);
                return;
            }
            if (left > to || right < from) {
                return;
            }
            pushDown(root, left, right);
            int middle = (left + right) >> 1;
            updateValue(2 * root + 1, left, middle, from, to, query, fromDown);
            updateValue(2 * root + 2, middle + 1, right, from, to, query, fromDown);
            plainValue[root] = plainValue[2 * root + 1] + plainValue[2 * root + 2];
        }

        private void pushDown(int root, int left, int right) {
            int middle = (left + right) >> 1;
            plainDelta[2 * root + 1] += plainDelta[root];
            plainDelta[2 * root + 2] += plainDelta[root];
            plainValue[2 * root + 1] += (middle - left + 1) * plainDelta[root];
            plainValue[2 * root + 2] += (right - middle) * plainDelta[root];
            if (left == middle) {
                totalValue[2 * root + 1] += (getQty(left, right)) * plainDelta[root];
            }
            totalDeltaDirect[2 * root + 1] += plainDelta[root] * getQty(middle + 1, right);
            if (middle + 1 == right) {
                totalValue[2 * root + 2] += (getQty(middle + 1, right)) * plainDelta[root];
            }
            plainDelta[root] = 0;
            totalDeltaDirect[2 * root + 1] += totalDeltaDirect[root];
            totalDeltaDirect[2 * root + 2] += totalDeltaDirect[root];
            if (left == middle) {
                totalValue[2 * root + 1] += totalDeltaDirect[root];
            }
            if (middle + 1 == right) {
                totalValue[2 * root + 2] += totalDeltaDirect[root];
            }
            totalDeltaDirect[root] = 0;
        }

        long getPlain(int from, int to) {
            return getPlain(0, 0, size - 1, from, to);
        }

        long getPlain(int root, int left, int right, int from, int to) {
            if (left >= from && right <= to) {
                return plainValue[root];
            }
            if (left > to || right < from) {
                return 0;
            }
            pushDown(root, left, right);
            int middle = (left + right) >> 1;
            return getPlain(2 * root + 1, left, middle, from, to) + getPlain(2 * root + 2, middle + 1, right, from, to);
        }

        long getTotal(int at) {
            return getTotal(0, 0, size - 1, at);
        }

        long getTotal(int root, int left, int right, int at) {
            if (left > at || right < at) {
                return 0;
            }
            if (left == right) {
                return totalValue[root];
            }
            pushDown(root, left, right);
            int middle = (left + right) >> 1;
            return getTotal(2 * root + 1, left, middle, at) + getTotal(2 * root + 2, middle + 1, right, at);
        }

        long getQty(int from, int to) {
            return qty[to + 1] - qty[from];
        }
    }

    Tree[] trees;
    int[] inTree;
    LCA lca;
    int[] parent;
    int[] qty;
    int[] head;
    
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] from = new int[count - 1];
        int[] to = new int[count - 1];
        IOUtils.readIntArrays(in, from, to);
        MiscUtils.decreaseByOne(from, to);
        BidirectionalGraph graph = BidirectionalGraph.createGraph(count, from, to);
        lca = new LCA(graph);
        parent = new int[count];
        parent[0] = -1;
        for (int i = 1; i < count; i++) {
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                if (lca.getLevel(i) == 1 + lca.getLevel(graph.destination(j))) {
                    parent[i] = graph.destination(j);
                }
            }
        }
        int[] order = ArrayUtils.createOrder(count);
        ArrayUtils.sort(order, (f, s) -> lca.getLevel(f) - lca.getLevel(s));
        ArrayUtils.reverse(order);
        qty = new int[count];
        Arrays.fill(qty, 1);
        for (int i : order) {
            if (i != 0) {
                qty[parent[i]] += qty[i];
            }
        }
        trees = new Tree[count];
        inTree = new int[count];
        head = new int[count];
        for (int i : order) {
            if (trees[i] != null) {
                continue;
            }
            int size = 1;
            int current = i;
            while (current != 0 && qty[current] * 2 >= qty[parent[current]]) {
                size++;
                current = parent[current];
            }
            int last = current;
            Tree tree = new Tree(size);
            current = i;
            for (int j = size - 1; j >= 0; j--) {
                trees[current] = tree;
                inTree[current] = j;
                tree.setQty(j, qty[current]);
                head[current] = last;
                current = parent[current];
            }
            tree.finish();
        }
        int queryCount = in.readInt();
        for (int i = 0; i < queryCount; i++) {
            int type = in.readInt();
            if (type == 1) {
                int first = in.readInt() - 1;
                int second = in.readInt() - 1;
                int by = in.readInt();
                int lc = lca.getLCA(first, second);
                long up = update(first, lc, true, by, 0) + update(second, lc, false, by, 0);
                if (lc != 0) {
                    update(parent[lc], 0, true, 0, up);
                }
            } else {
                int vertex = in.readInt() - 1;
                long result = trees[vertex].getTotal(inTree[vertex]);
                result += qty[vertex] * get(vertex);
                out.printLine(result % MOD);
            }
        }
    }

    private long update(int from, int to, boolean includeTo, int update, long fromDown) {
        while (trees[from] != trees[to]) {
            trees[from].updateValue(0, inTree[from], update, fromDown);
            fromDown += trees[from].getQty(0, inTree[from]) * update;
            from = parent[head[from]];
        }
        to = includeTo ? inTree[to] : inTree[to] + 1;
        trees[from].updateValue(to, inTree[from], update, fromDown);
        fromDown += trees[from].getQty(to, inTree[from]) * update;
        if (!includeTo) {
            trees[from].updateValue(to - 1, to - 1, 0, fromDown);
        }
        return fromDown;
    }

    private long get(int vertex) {
        long result = 0;
        while (vertex != 0) {
            vertex = parent[vertex];
            result += trees[vertex].getPlain(0, inTree[vertex]);
            vertex = head[vertex];
        }
        return result;
    }
}
