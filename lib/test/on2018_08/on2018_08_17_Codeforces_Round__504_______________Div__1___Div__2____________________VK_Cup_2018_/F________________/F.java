package on2018_08.on2018_08_17_Codeforces_Round__504_______________Div__1___Div__2____________________VK_Cup_2018_.F________________;



import net.egork.collections.intervaltree.LCA;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.generated.collections.list.LongArray;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.DFSOrder;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Long.MAX_VALUE;
import static java.lang.Math.min;
import static net.egork.misc.ArrayUtils.count;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class F {
    Graph graph;
    LCA lca;
    DFSOrder order;
    long[] tree;

    Graph special;
    long[] answer;
    int n;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        int k = in.readInt();
        int m = in.readInt();
        int[] ga = new int[k];
        int[] gb = new int[k];
        in.readIntArrays(ga, gb);
        int[] fa = new int[m];
        int[] fb = new int[m];
        int[] fw = new int[m];
        in.readIntArrays(fa, fb, fw);
        decreaseByOne(ga, gb, fa, fb);
        graph = new BidirectionalGraph(n);
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
        for (int i = 0; i < k; i++) {
            graph.addSimpleEdge(ga[i], gb[i]);
            setSystem.join(ga[i], gb[i]);
        }
        boolean[] taken = new boolean[m];
        for (int i = 0; i < m; i++) {
            if (setSystem.get(fa[i]) != setSystem.get(fb[i])) {
                graph.addSimpleEdge(fa[i], fb[i]);
                setSystem.join(fa[i], fb[i]);
                taken[i] = true;
            }
        }
        lca = new LCA(graph);
        order = new DFSOrder((BidirectionalGraph)graph);
        tree = createArray(4 * n, MAX_VALUE);
        special = new Graph(n);
        for (int i = 0; i < m; i++) {
            if (!taken[i]) {
                int parent = lca.getLCA(fa[i], fb[i]);
                special.addWeightedEdge(parent, fa[i], fw[i]);
                special.addWeightedEdge(parent, fb[i], fw[i]);
            }
        }
        answer = new long[k];
        go(0, -1);
        if (count(answer, MAX_VALUE) != 0) {
            out.printLine(-1);
        } else {
            out.printLine(new LongArray(answer).sum());
        }
    }

    private void go(int vertex, int last) {
        for (int i = special.firstOutbound(vertex); i != -1; i = special.nextOutbound(i)) {
            int to = order.position[special.destination(i)];
            update(0, 0, n - 1, to, special.weight(i));
        }
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int to = graph.destination(i);
            if (to == last) {
                continue;
            }
            int edge = i >> 1;
            if (edge < answer.length) {
                answer[edge] = query(0, 0, n - 1, order.position[to], order.end[to]);
            }
            go(to, vertex);
        }
    }

    private long query(int root, int left, int right, int from, int to) {
        if (left > to || right < from) {
            return MAX_VALUE;
        }
        if (left >= from && to >= right) {
            return tree[root];
        }
        int middle = (left + right) >> 1;
        return min(query(2 * root + 1, left, middle, from, to), query(2 * root + 2, middle + 1, right, from, to));
    }

    private void update(int root, int left, int right, int at, long value) {
        if (at > right || at < left) {
            return;
        }
        tree[root] = Math.min(tree[root], value);
        if (left != right) {
            int middle = (left + right) >> 1;
            update(2 * root + 1, left, middle, at, value);
            update(2 * root + 2, middle + 1, right, at, value);
        }
    }
}
