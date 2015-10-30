package on2015_08.on2015_08_05_SNSS_2015_R1.E____________________________;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.readInt();
        int n = in.readInt();
        int m = in.readInt();
        int[] c1 = new int[m];
        int[] c2 = new int[m];
        int[] t = new int[m];
        int[] d = new int[m];
        IOUtils.readIntArrays(in, c1, c2, t, d);
        int a = in.readInt() - 1;
        int b = in.readInt() - 1;
        MiscUtils.decreaseByOne(c1, c2);
        Graph graph = BidirectionalGraph.createGraph(n, c1, c2);
        long[][] result = new long[n][k];
        ArrayUtils.fill(result, Long.MAX_VALUE / 2);
        Arrays.fill(result[a], 0);
        for (int i = k - 1; i > 0; i--) {
            for (int j = 0; j < n; j++) {
                if (result[j][i] != Long.MAX_VALUE / 2) {
                    for (int l = graph.firstOutbound(j); l != -1; l = graph.nextOutbound(l)) {
                        int edge = l >> 1;
                        if (d[edge] == 0 || d[edge] > i) {
                            continue;
                        }
                        int to = graph.destination(l);
                        result[to][i - d[edge]] = Math.min(result[to][i - d[edge]], result[j][i] + t[edge]);
                    }
                }
            }
        }
        Graph zeroWeight = new BidirectionalGraph(n);
        for (int i = 0; i < m; i++) {
            if (d[i] == 0) {
                zeroWeight.addWeightedEdge(c1[i], c2[i], t[i]);
            }
        }
        long[] distances = ShortestDistance.dijkstraAlgorithm(zeroWeight, b).first;
        long answer = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (result[i][0] != Long.MAX_VALUE / 2 && distances[i] != Long.MAX_VALUE) {
                answer = Math.min(answer, result[i][0] + distances[i]);
            }
        }
        if (answer != Long.MAX_VALUE) {
            out.printLine(answer);
        } else {
            out.printLine(-1);
        }
    }
}
