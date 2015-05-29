package on2015_05.on2015_05_23_CodeChef_SnackDown_2015__Round_1A.Dining;



import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Dining {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int days = in.readInt();
        int perDay = in.readInt();
        double[][] probability = IOUtils.readDoubleTable(in, count, days);
        Graph graph = new Graph(count + days + 2);
        int source = graph.vertexCount() - 2;
        int sink = graph.vertexCount() - 1;
        long infty = Long.MAX_VALUE / (2 * days);
        for (int i = 0; i < days; i++) {
            graph.addFlowWeightedEdge(source, i, 0, perDay - 1);
            graph.addFlowWeightedEdge(source, i, -infty, 1);
            for (int j = 0; j < count; j++) {
                graph.addFlowWeightedEdge(i, days + j, Math.round(-1e9 * Math.log(probability[j][i])), 1);
            }
        }
        for (int i = 0; i < count; i++) {
            graph.addFlowWeightedEdge(days + i, sink, 0, 1);
        }
        long result = new MinCostFlow(graph, source, sink, false).minCostMaxFlow().first + infty * days;
        out.printLine(Math.exp(-result / 1e9));
        int[] answer = new int[count];
        for (int i = 0; i < count; i++) {
            for (int j = graph.firstInbound(i + days); j != -1; j = graph.nextInbound(j)) {
                if (graph.flow(j) == 1) {
                    answer[i] = graph.source(j) + 1;
                }
            }
        }
        out.printLine(answer);
    }
}
