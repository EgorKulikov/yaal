package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.decreaseByOne;

public class GrowingTrees {
    Graph graph;
    int[] c;
    int[] a;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int d = in.readInt();
        int[] s = new int[n - 1];
        int[] e = new int[n - 1];
        c = new int[n - 1];
        a = new int[n - 1];
        in.readIntArrays(s, e, c, a);
        decreaseByOne(s, e);
        graph = BidirectionalGraph.createGraph(n, s, e);
        int left = 0;
        int right = d;
        while (left < right) {
            int middle = (left + right) >> 1;
            long delta = calculate(middle + 1) - calculate(middle);
            if (delta >= 0) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        out.printLine(left);
        out.printLine(calculate(left));
    }

    long diameter;

    private long calculate(int d) {
        diameter = 0;
        go(0, -1, d);
        return diameter;
    }

    private long go(int vertex, int last, int d) {
        long best = 0;
        long second = 0;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int to = graph.destination(i);
            if (to == last) {
                continue;
            }
            long call = go(to, vertex, d) + c[i >> 1] + (long)d * a[i >> 1];
            if (call > best) {
                second = best;
                best = call;
            } else {
                second = Math.max(second, call);
            }
        }
        diameter = Math.max(diameter, best + second);
        return best;
    }
}
