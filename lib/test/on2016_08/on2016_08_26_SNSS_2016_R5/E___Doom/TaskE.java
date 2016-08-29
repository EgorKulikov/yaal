package on2016_08.on2016_08_26_SNSS_2016_R5.E___Doom;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readDoubleArray;
import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.MiscUtils.decreaseByOne;
import static org.apache.commons.math3.util.MathArrays.copyOf;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int s = in.readInt() - 1;
        int f = in.readInt() - 1;
        double[] rooms = readDoubleArray(in, n);
        int[] p = new int[m];
        int[] q = new int[m];
        readIntArrays(in, p, q);
        decreaseByOne(p, q);
        Graph graph = BidirectionalGraph.createGraph(n, p, q);
        double[][] answer = new double[n][n + 1];
        rooms = copyOf(rooms, n + 1);
        boolean[][] done = new boolean[n][n + 1];
        answer[s][n] = 1 - Math.max(rooms[s], 0);
        for (int i = 0; i < n * (n + 1); i++) {
            int r = -1;
            int mask = -1;
            double prob = 0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= n; k++) {
                    if (!done[j][k] && answer[j][k] > prob) {
                        prob = answer[j][k];
                        r = j;
                        mask = k;
                    }
                }
            }
            if (r == f) {
                out.printLine(prob);
                return;
            }
            done[r][mask] = true;
            if (rooms[r] < rooms[mask]) {
                answer[r][r] = Math.max(answer[r][r], prob);
                continue;
            }
            for (int j = graph.firstOutbound(r); j != -1; j = graph.nextOutbound(j)) {
                int dst = graph.destination(j);
                answer[dst][mask] = Math.max(answer[dst][mask], prob * (1 - Math.max(0, rooms[dst] + rooms[mask])));
            }
        }
    }
}
