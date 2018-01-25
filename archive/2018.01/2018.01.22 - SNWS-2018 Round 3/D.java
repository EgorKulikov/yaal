package net.egork;

import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.StronglyConnectedComponents;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.copyOf;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int r = in.readInt();
        int o = in.readInt();
        int l = in.readInt();
        Graph graph = new Graph(r, l);
        for (int i = 0; i < l; i++) {
            int a = in.readInt();
            int b = in.readInt();
            graph.addSimpleEdge(a, b);
        }
        int[] bad = in.readIntArray(o);
        boolean[] compromised = new boolean[r];
        int[] queue = copyOf(bad, r);
        int size = o;
        for (int i : bad) {
            compromised[i] = true;
        }
        for (int i = 0; i < size; i++) {
            for (int j = graph.firstInbound(queue[i]); j != -1; j = graph.nextInbound(j)) {
                int current = graph.source(j);
                if (!compromised[current]) {
                    compromised[current] = true;
                    queue[size++] = current;
                }
            }
        }
        int answer = size - o;
        Pair<int[], Graph> result = StronglyConnectedComponents.kosaraju(graph);
        int[] color = result.first;
        Graph condensed = result.second;
        boolean[] badColors = new boolean[condensed.vertexCount()];
        for (int i = 0; i < r; i++) {
            if (compromised[i]) {
                badColors[color[i]] = true;
            }
        }
        for (int i = 0; i < condensed.vertexCount(); i++) {
            if (badColors[i]) {
                continue;
            }
            boolean hasIncoming = false;
            for (int j = condensed.firstInbound(i); j != -1; j = condensed.nextInbound(j)) {
                if (!badColors[condensed.source(j)]) {
                    hasIncoming = true;
                    break;
                }
            }
            if (!hasIncoming) {
                answer++;
            }
        }
        out.printLine(answer);
    }
}
