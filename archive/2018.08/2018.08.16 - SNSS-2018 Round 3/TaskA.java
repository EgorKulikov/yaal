package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskA {
    List<IntIntPair> answer = new ArrayList<>();
    Graph graph;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        answer = new ArrayList<>();
        int n = in.readInt();
        int m = in.readInt();
        int[] x = new int[m];
        int[] y = new int[m];
        in.readIntArrays(x, y);
        decreaseByOne(x, y);
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
        graph = new BidirectionalGraph(n);
        for (int i = 0; i < m; i++) {
            if (setSystem.join(x[i], y[i])) {
                graph.addSimpleEdge(x[i], y[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (setSystem.get(i) == i) {
                if (go(i, -1)) {
                    out.printLine(-1);
                    return;
                }
            }
        }
        out.printLine(answer.size());
        out.printPairList(answer);
    }

    private boolean go(int vertex, int last) {
        boolean add = true;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int to = graph.destination(i);
            if (to == last) {
                continue;
            }
            add ^= go(to, vertex);
        }
        if (add) {
            answer.add(new IntIntPair(vertex + 1, last + 1));
        }
        return add;
    }
}
