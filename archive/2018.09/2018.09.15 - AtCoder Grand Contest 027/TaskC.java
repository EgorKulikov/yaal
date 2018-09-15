package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        char[] s = in.readCharArray(n);
        int[] a = new int[m];
        int[] b = new int[m];
        in.readIntArrays(a, b);
        decreaseByOne(a, b);
        Graph graph = BidirectionalGraph.createGraph(n, a, b);
        int[] degA = new int[n];
        int[] degB = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                int to = graph.destination(j);
                if (s[to] == 'A') {
                    degA[i]++;
                } else {
                    degB[i]++;
                }
            }
        }
        int[] queue = new int[n];
        boolean[] added = new boolean[n];
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (degA[i] == 0 || degB[i] == 0) {
                queue[size++] = i;
                added[i] = true;
            }
        }
        for (int i = 0; i < size; i++) {
            int current = queue[i];
            for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
                int to = graph.destination(j);
                if (s[current] == 'A') {
                    if (--degA[to] == 0 && !added[to]) {
                        queue[size++] = to;
                        added[to] = true;
                    }
                } else if (--degB[to] == 0 && !added[to]) {
                    queue[size++] = to;
                    added[to] = true;
                }
            }
        }
        out.printLine(size == n ? "No" : "Yes");
    }
}
