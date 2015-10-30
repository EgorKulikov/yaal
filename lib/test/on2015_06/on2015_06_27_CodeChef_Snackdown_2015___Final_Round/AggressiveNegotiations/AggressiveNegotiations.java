package on2015_06.on2015_06_27_CodeChef_Snackdown_2015___Final_Round.AggressiveNegotiations;



import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AggressiveNegotiations {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        long[][] rank = IOUtils.readLongTable(in, count, count);
        long answer = 0;
        for (int i = 59; i >= 0; i--) {
            Graph graph = new Graph(2 * count + 2);
            long current = answer + (1L << i);
            for (int j = 0; j < count; j++) {
                graph.addFlowEdge(2 * count, j, 1);
                graph.addFlowEdge(count + j, 2 * count + 1, 1);
                for (int k = 0; k < count; k++) {
                    if ((rank[j][k] & current) == current) {
                        graph.addFlowEdge(j, count + k, 1);
                    }
                }
            }
            if (MaxFlow.dinic(graph, 2 * count, 2 * count + 1) == count) {
                answer = current;
            }
        }
        out.printLine(answer);
    }
}
