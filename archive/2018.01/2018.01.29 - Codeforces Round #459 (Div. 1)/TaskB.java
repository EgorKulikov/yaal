package net.egork;

import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
    Graph graph;
    char[] c;
    int[][][] answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] v = new int[m];
        int[] u = new int[m];
        c = new char[m];
        for (int i = 0; i < m; i++) {
            v[i] = in.readInt() - 1;
            u[i] = in.readInt() - 1;
            c[i] = (char) (in.readCharacter() - 'a');
        }
        graph = Graph.createGraph(n, v, u);
        answer = new int[n][n][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (go(i, j, 0) > 0) {
                    out.print('A');
                } else {
                    out.print('B');
                }
            }
            out.printLine();
        }
    }

    private int go(int v1, int v2, int minMove) {
        if (answer[v1][v2][minMove] != 0) {
            return answer[v1][v2][minMove];
        }
        for (int i = graph.firstOutbound(v1); i != -1; i = graph.nextOutbound(i)) {
            if (c[i] >= minMove && go(v2, graph.destination(i), c[i]) < 0) {
                return answer[v1][v2][minMove] = 1;
            }
        }
        return answer[v1][v2][minMove] = -1;
    }
}
