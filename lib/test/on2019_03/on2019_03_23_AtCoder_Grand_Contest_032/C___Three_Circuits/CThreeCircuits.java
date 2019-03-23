package on2019_03.on2019_03_23_AtCoder_Grand_Contest_032.C___Three_Circuits;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.find;
import static net.egork.misc.ArrayUtils.sort;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class CThreeCircuits {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = new int[m];
        int[] b = new int[m];
        in.readIntArrays(a, b);
        decreaseByOne(a, b);
        int[] degree = new int[n];
        for (int i : a) {
            degree[i]++;
        }
        for (int i : b) {
            degree[i]++;
        }
        int[] orDegree = degree.clone();
        sort(degree);
        for (int i = 0; i < n; i++) {
            if (degree[i] % 2 != 0) {
                out.printLine("No");
                return;
            }
        }
        if (degree[n - 1] >= 6) {
            out.printLine("Yes");
            return;
        }
        if (n >= 3 && degree[n - 3] >= 4) {
            out.printLine("Yes");
            return;
        }
        if (n >= 2 && degree[n - 2] >= 4) {
            Graph graph = BidirectionalGraph.createGraph(n, a, b);
            int x = find(orDegree, 4);
            orDegree[x] = -1;
            int y = find(orDegree, 4);
            for (int i = graph.firstOutbound(x); i != -1; i = graph.nextOutbound(i)) {
                int last = x;
                int current = graph.destination(i);
                while (true) {
                    int next = -1;
                    for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
                        int cand = graph.destination(j);
                        if (cand != last) {
                            next = cand;
                            break;
                        }
                    }
                    last = current;
                    current = next;
                    if (current == x || current == y) {
                        break;
                    }
                }
                if (current == x) {
                    out.printLine("Yes");
                    return;
                }
            }
        }
        out.printLine("No");
    }
}
