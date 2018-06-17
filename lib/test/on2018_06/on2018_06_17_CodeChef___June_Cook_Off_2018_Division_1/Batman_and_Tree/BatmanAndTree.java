package on2018_06.on2018_06_17_CodeChef___June_Cook_Off_2018_Division_1.Batman_and_Tree;



import net.egork.collections.intervaltree.LCA;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.DFSOrder;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class BatmanAndTree {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int si = in.readInt() - 1;
        int p = in.readInt();
        int[] u = new int[n - 1];
        int[] v = new int[n - 1];
        in.readIntArrays(u, v);
        decreaseByOne(u, v);
        BidirectionalGraph graph = BidirectionalGraph.createGraph(n, u, v);
        DFSOrder order = new DFSOrder(graph);
        LCA lca = new LCA(graph);
        LongIntervalTree tree = new LongIntervalTree(n) {
            @Override
            protected long joinValue(long left, long right) {
                return max(left, right);
            }

            @Override
            protected long joinDelta(long was, long delta) {
                return max(was, delta);
            }

            @Override
            protected long accumulate(long value, long delta, int length) {
                return max(value, delta);
            }

            @Override
            protected long neutralValue() {
                return 0;
            }

            @Override
            protected long neutralDelta() {
                return 0;
            }
        };
        tree.update(order.position[si], order.position[si], p);
        int[][] up = new int[20][n];
        for (int i = 0; i < n; i++) {
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                if (lca.getLevel(graph.destination(j)) < lca.getLevel(i)) {
                    up[0][i] = graph.destination(j);
                }
            }
        }
        for (int i = 1; i < 20; i++) {
            for (int j = 0; j < n; j++) {
                up[i][j] = up[i - 1][up[i - 1][j]];
            }
        }
        int m = in.readInt();
        for (int i = 0; i < m; i++) {
            int x = in.readInt() - 1;
            int y = in.readInt() - 1;
            int r = in.readInt();
            int t = in.readInt();
            int parent = lca.getLCA(x, y);
            int top = -1;
            if (x == parent) {
                int current = y;
                for (int j = 19; j >= 0; j--) {
                    if (lca.getLevel(up[j][current]) > lca.getLevel(x)) {
                        current = up[j][current];
                    }
                }
                top = current;
            } else if (y == parent) {
                int current = x;
                for (int j = 19; j >= 0; j--) {
                    if (lca.getLevel(up[j][current]) > lca.getLevel(y)) {
                        current = up[j][current];
                    }
                }
                top = current;
            }
            long bestX;
            if (x != parent) {
                bestX = tree.query(order.position[x], order.end[x]);
            } else {
                bestX = max(tree.query(0, order.position[top] - 1), tree.query(order.end[top] + 1, n - 1));
            }
            long bestY;
            if (y != parent) {
                bestY = tree.query(order.position[y], order.end[y]);
            } else {
                bestY = max(tree.query(0, order.position[top] - 1), tree.query(order.end[top] + 1, n - 1));
            }
            if (bestX > r) {
                if (y != parent) {
                    tree.update(order.position[y], order.end[y], bestX + t);
                } else {
                    tree.update(0, order.position[top] - 1, bestX + t);
                    tree.update(order.end[top] + 1, n - 1, bestX + t);
                }
            }
            if (bestY > r) {
                if (x != parent) {
                    tree.update(order.position[x], order.end[x], bestY + t);
                } else {
                    tree.update(0, order.position[top] - 1, bestY + t);
                    tree.update(order.end[top] + 1, n - 1, bestY + t);
                }
            }
        }
        out.printLine(tree.query(0, n - 1));
    }
}
