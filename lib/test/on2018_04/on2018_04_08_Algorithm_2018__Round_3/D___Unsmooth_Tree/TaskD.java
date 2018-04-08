package on2018_04.on2018_04_08_Algorithm_2018__Round_3.D___Unsmooth_Tree;



import net.egork.generated.collections.pair.DoubleDoublePair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.parseInt;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskD {
    Graph graph;
    static final int INFTY = 1000000;
    int[] x;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        x = new int[n];
        for (int i = 0; i < n; i++) {
            String s = in.readString();
            if (s.equals("*")) {
                x[i] = INFTY + 1;
            } else {
                x[i] = parseInt(s);
            }
        }
        int[] u = new int[n - 1];
        int[] v = new int[n - 1];
        in.readIntArrays(u, v);
        decreaseByOne(u, v);
        graph = BidirectionalGraph.createGraph(n, u, v);
        double left = 0;
        double right = 2 * INFTY;
        for (int i = 0; i < 100; i++) {
            double middle = (left + right) / 2;
            DoubleDoublePair result = go(0, -1, middle);
            if (result != null) {
                right = middle;
            } else {
                left = middle;
            }
        }
        out.printLine((left + right) / 2);
    }

    private DoubleDoublePair go(int root, int last, double middle) {
        double from = -INFTY;
        double to = INFTY;
        if (x[root] != INFTY + 1) {
            from = to = x[root];
        }
        for (int i = graph.firstOutbound(root); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            DoubleDoublePair result = go(next, root, middle);
            if (result == null) {
                return null;
            }
            from = Math.max(from, result.first - middle);
            to = Math.min(to, result.second + middle);
        }
        if (from > to) {
            return null;
        }
        return new DoubleDoublePair(from, to);
    }
}
