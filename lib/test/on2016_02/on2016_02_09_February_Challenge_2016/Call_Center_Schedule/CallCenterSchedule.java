package on2016_02.on2016_02_09_February_Challenge_2016.Call_Center_Schedule;



import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class CallCenterSchedule {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int p = in.readInt();
        int d = in.readInt();
        int h = in.readInt();
        int n = in.readInt();
        int[] l = readIntArray(in, p);
        int ltBegin = in.readInt() - 1;
        int ltEnd = in.readInt() - 1;
        int[][] r = readIntTable(in, d, h);
        int[][][] f = new int[p][][];
        for (int i = 0; i < p; i++) {
            f[i] = readIntTable(in, d, h);
        }
        Graph graph = new Graph(p * (2 * d + 1) + d * h + 2);
        int source = graph.vertexCount() - 2;
        int sink = source + 1;
        for (int i = 0; i < p; i++) {
            graph.addFlowEdge(source, i, l[i]);
        }
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < d; j++) {
                int remaining = n;
                for (int k : f[i][j]) {
                    remaining -= 1 - k;
                }
                if (remaining < 0) {
                    out.printLine("No");
                    return;
                }
                if (remaining > 0) {
                    graph.addFlowEdge(i, p + i * d + j, remaining);
                }
            }
        }
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < d; j++) {
                int remaining = ltEnd - ltBegin;
                for (int k = ltBegin; k <= ltEnd; k++) {
                    remaining -= 1 - f[i][j][k];
                }
                if (remaining < 0) {
                    out.printLine("No");
                    return;
                }
                if (remaining > 0) {
                    graph.addFlowEdge(p + i * d + j, p * (d + 1) + i * d + j, remaining);
                }
            }
        }
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < d; j++) {
                for (int k = 0; k < h; k++) {
                    if (k < ltBegin || k > ltEnd && f[i][j][k] == 1) {
                        graph.addFlowEdge(p + i * d + j, p * (2 * d + 1) + h * j + k, 1);
                    } else if (k >= ltBegin && k <= ltEnd && f[i][j][k] == 1) {
                        graph.addFlowEdge(p * (d + 1) + i * d + j, p * (2 * d + 1) + h * j + k, 1);
                    }
                }
            }
        }
        long required = 0;
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < h; j++) {
                graph.addFlowEdge(p * (2 * d + 1) + h * i + j, sink, r[i][j]);
                required += r[i][j];
            }
        }
        long flow = MaxFlow.dinic(graph, source, sink);
        if (flow == required) {
            out.printLine("Yes");
        } else {
            out.printLine("No");
        }
    }
}
