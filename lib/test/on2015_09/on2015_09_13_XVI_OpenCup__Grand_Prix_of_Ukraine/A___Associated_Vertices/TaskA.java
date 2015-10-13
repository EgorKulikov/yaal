package on2015_09.on2015_09_13_XVI_OpenCup__Grand_Prix_of_Ukraine.A___Associated_Vertices;



import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.StronglyConnectedComponents;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] x = new int[m];
        int[] y = new int[m];
        IOUtils.readIntArrays(in, x, y);
        MiscUtils.decreaseByOne(x, y);
        Graph graph = Graph.createGraph(n, x, y);
        Pair<int[], Graph> kosaraju = StronglyConnectedComponents.kosaraju(graph);
        graph = kosaraju.second;
        n = graph.vertexCount();
        int[] sizes = new int[n];
        for (int i : kosaraju.first) {
            sizes[i]++;
        }
        int[] order = GraphAlgorithms.topologicalSort(graph);
        boolean[][] amiable = new boolean[n][n];
        boolean[][] subTree = new boolean[n][n];
        ArrayUtils.reverse(order);
        for (int i : order) {
            subTree[i][i] = true;
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                int to = graph.destination(j);
                for (int k = 0; k < n; k++) {
                    subTree[i][k] |= subTree[to][k];
                }
            }
        }
        ArrayUtils.reverse(order);
        for (int i : order) {
            amiable[i][i] = true;
            for (int k = 0; k < n; k++) {
                amiable[i][k] = subTree[i][k];
            }
            for (int j = graph.firstInbound(i); j != -1; j = graph.nextInbound(j)) {
                int to = graph.source(j);
                for (int k = 0; k < n; k++) {
                    amiable[i][k] |= amiable[to][k];
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (amiable[i][j]) {
                    answer += sizes[i] * sizes[j];
                }
            }
        }
        out.printLine(answer);
    }
}
