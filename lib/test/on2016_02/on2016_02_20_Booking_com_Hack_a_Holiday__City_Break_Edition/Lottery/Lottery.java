package on2016_02.on2016_02_20_Booking_com_Hack_a_Holiday__City_Break_Edition.Lottery;



import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Lottery {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.readInt();
        int h = in.readInt();
        Graph graph = new Graph(t + h + 2);
        int source = t + h;
        int sink = source + 1;
        for (int i = 0; i < t; i++) {
            graph.addFlowEdge(source, i, 1);
        }
        for (int i = 0; i < h; i++) {
            graph.addFlowEdge(i + t, sink, 1);
        }
        for (int i = 0; i < t; i++) {
            int ti = in.readInt();
            for (int j = 0; j < ti; j++) {
                graph.addFlowEdge(i, in.readInt() - 1 + t, 1);
            }
        }
        out.printLine(h - MaxFlow.dinic(graph, source, sink));
    }
}
