package on2017_10.on2017_10_25_CSAcademy_Round__54.LateEdges;


import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.fill;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class LateEdges {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = new int[m];
        int[] b = new int[m];
        int[] c = new int[m];
        in.readIntArrays(a, b, c);
        decreaseByOne(a, b);
        Graph graph = BidirectionalGraph.createGraph(n, a, b);
        int[] result = new int[2 * n];
        fill(result, MAX_VALUE);
        result[0] = 0;
        boolean[] was = new boolean[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            int at = -1;
            int best = MAX_VALUE;
            for (int j = 0; j < 2 * n; j++) {
                if (!was[j] && result[j] < best) {
                    best = result[j];
                    at = j;
                }
            }
            if (at == -1) {
                break;
            }
            was[at] = true;
            for (int j = graph.firstOutbound(at % n); j != -1; j = graph.nextOutbound(j)) {
                int time = best;
                if (c[j >> 1] > time) {
                    time = c[j >> 1];
                    if ((time & 1) != (best & 1)) {
                        time++;
                    }
                }
                time++;
                int destination = at < n ? graph.destination(j) + n : graph.destination(j);
                result[destination] = min(result[destination], time);
            }
        }
        int answer = min(result[n - 1], result[2 * n - 1]);
        if (answer == MAX_VALUE) {
            out.printLine(-1);
        } else {
            out.printLine(answer);
        }
    }
}
