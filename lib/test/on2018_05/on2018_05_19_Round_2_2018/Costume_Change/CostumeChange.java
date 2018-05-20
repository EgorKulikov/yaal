package on2018_05.on2018_05_19_Round_2_2018.Costume_Change;



import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class CostumeChange {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[][] a = in.readIntTable(n, n);
        int answer = n * n;
        for (int i = -n; i <= n; i++) {
            Graph graph = new Graph(2 * n + 2);
            for (int j = 0; j < n; j++) {
                graph.addFlowEdge(2 * n, j, 1);
                graph.addFlowEdge(n + j, 2 * n + 1, 1);
                for (int k = 0; k < n; k++) {
                    if (a[j][k] == i) {
                        graph.addFlowEdge(j, n + k, 1);
                    }
                }
            }
            answer -= MaxFlow.dinic(graph, 2 * n, 2 * n + 1);
        }
        out.printLine("Case #" + testNumber + ":", answer);
    }
}
