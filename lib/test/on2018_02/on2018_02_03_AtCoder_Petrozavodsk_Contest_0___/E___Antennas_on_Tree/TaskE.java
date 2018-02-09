package on2018_02.on2018_02_03_AtCoder_Petrozavodsk_Contest_0___.E___Antennas_on_Tree;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;

public class TaskE {
    Graph graph;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = new int[n - 1];
        int[] b = new int[n - 1];
        in.readIntArrays(a, b);
        graph = BidirectionalGraph.createGraph(n, a, b);
        for (int i = 0; i < n; i++) {
            int second = graph.nextOutbound(graph.firstOutbound(i));
            if (second != -1 && graph.nextOutbound(second) != -1) {
                out.printLine(go(i, -1));
                return;
            }
        }
        out.printLine(1);
    }

    private int go(int vertex, int last) {
        int result = 0;
        int numZero = 0;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next != last) {
                int call = go(next, vertex);
                result += call;
                if (call == 0) {
                    numZero++;
                }
            }
        }
        result += max(0, numZero - 1);
        return result;
    }
}
