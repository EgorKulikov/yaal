package on2016_06.on2016_06_25_World_CodeSprint__4.Roads_in_HackerLand;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.ArrayUtils.orderBy;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class RoadsInHackerLand {
    int n;
    int[] id;
    long[] answer;
    Graph graph;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        int m = in.readInt();
        int[] a = new int[m];
        int[] b = new int[m];
        int[] c = new int[m];
        readIntArrays(in, a, b, c);
        decreaseByOne(a, b);
        orderBy(c, a, b);
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n);
        graph = new BidirectionalGraph(n, n - 1);
        id = new int[n - 1];
        for (int i = 0; i < m; i++) {
            if (setSystem.join(a[i], b[i])) {
                id[graph.edgeCount() >> 1] = i;
                graph.addSimpleEdge(a[i], b[i]);
            }
        }
        answer = new long[m + 60];
        dfs(0, -1);
        for (int i = 0; i < answer.length - 1; i++) {
            answer[i + 1] += answer[i] >> 1;
            answer[i] &= 1;
        }
        boolean was = false;
        for (int i = answer.length - 1; i >= 0; i--) {
            if (answer[i] == 1) {
                was = true;
            }
            if (was) {
                out.print(answer[i]);
            }
        }
        out.printLine();
    }

    private int dfs(int vertex, int edge) {
        int size = 1;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            if (i != (edge ^ 1)) {
                int current = dfs(graph.destination(i), i);
                answer[id[i >> 1]] = (long)current * (n - current);
                size += current;
            }
        }
        return size;
    }
}
