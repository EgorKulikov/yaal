package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

import static net.egork.misc.ArrayUtils.fill;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class HeapPirates {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] u = new int[m];
        int[] v = new int[m];
        in.readIntArrays(u, v);
        if (m + 1 != n) {
            out.printLine(-1);
            return;
        }
        decreaseByOne(u, v);
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
        for (int i = 0; i < m; i++) {
            setSystem.join(u[i], v[i]);
        }
        if (setSystem.getSetCount() != 1) {
            out.printLine(-1);
            return;
        }
        Graph graph = BidirectionalGraph.createGraph(n, u, v);
        char[][] result = go(0, -1, graph, false);
        out.printLine(result.length, result[0].length);
        out.printTable(result);
    }

    private char[][] go(int vertex, int last, Graph graph, boolean one) {
        List<char[][]> calls = new ArrayList<>();
        int height = 1;
        int width = 1;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int to = graph.destination(i);
            if (to == last) {
                continue;
            }
            char[][] call = go(to, vertex, graph, !one);
            calls.add(call);
            height = Math.max(height, call.length + 1);
            width += call[0].length + 1;
        }
        char[][] answer = new char[height][width];
        fill(answer, one ? '1' : '0');
        int at = 1;
        for (char[][] call : calls) {
            int delta = height - call.length;
            for (int i = 0; i < call.length; i++) {
                System.arraycopy(call[i], 0, answer[i + delta], at, call[i].length);
            }
            at += call[0].length + 1;
        }
        return answer;
    }
}
