package on2016_07.on2016_07_22_Codeforces_Round__364__Div__1_.B___Connecting_Universities;



import net.egork.generated.collections.pair.LongIntPair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.io.IOUtils.readIntArray;
import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskB {
    Graph graph;
    int remaining;
    boolean[] isUniversity;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] u = readIntArray(in, 2 * k);
        int[] x = new int[n - 1];
        int[] y = new int[n - 1];
        readIntArrays(in, x, y);
        decreaseByOne(u, x, y);
        graph = BidirectionalGraph.createGraph(n, x, y);
        remaining = k;
        isUniversity = new boolean[n];
        for (int i : u) {
            isUniversity[i] = true;
        }
        long answer = solve(0, -1).first;
        out.printLine(answer);
    }

    private LongIntPair solve(int vertex, int last) {
        int qty = 0;
        long answer = 0;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            LongIntPair call = solve(next, vertex);
            qty += call.second;
            answer += call.first;
        }
        answer += qty;
        if (isUniversity[vertex]) {
            qty++;
        }
        int here = max(0, qty - remaining);
        qty -= 2 * here;
        remaining -= here;
        return new LongIntPair(answer, qty);
    }
}
