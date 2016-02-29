package on2016_02.on2016_02_07_IndiaHacks__Algorithms.B___Bear_and_Snowstorm;



import net.egork.collections.intcollection.Range;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class BBearAndSnowstorm {
    private static final long MOD = (long) (1e9 + 7);
    Graph graph;
    int[] ways;
    long answer;
    volatile boolean finished;

    public void solve(int testNumber, final InputReader in, final OutputWriter out) {
        finished = false;
        new Thread(null, new Runnable() {
            @Override
            public void run() {
                answer = 0;
                int n = in.readInt();
                int k = in.readInt();
                ways = new int[2 * n - 2];
                int[] p = readIntArray(in, n - 1);
                decreaseByOne(p);
                int[] order = new int[n - 1];
                for (int i = 0; i < order.length; i++) {
                    order[i] = i + 1;
                }
                graph = BidirectionalGraph.createGraph(n, p, order);
                dfs(0, -1);
                for (int i = 0; i < n; i++) {
                    for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                        int next = graph.destination(j);
                        solve(next, i, j, ways[j ^ 1], k - 1);
                    }
                }
                for (int i = 1; i <= k + 1; i++) {
                    answer *= i;
                    answer %= MOD;
                }
                answer *= IntegerUtils.reverse(4, MOD);
                answer %= MOD;
                out.printLine(answer);
                finished = true;
            }
        }, "Thr", 1 << 26).start();
        while (!finished) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void solve(int current, int last, int edge, long product, int remaining) {
        if (remaining == 0) {
            answer += product * ways[edge] % MOD;
            return;
        }
        int vert = graph.vertexCount() - ways[edge ^ 1];
        for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            solve(next, current, i, product * (vert - ways[i]) % MOD, remaining - 1);
        }
    }

    private int dfs(int vertex, int last) {
        int result = 1;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            ways[i] = dfs(next, vertex);
            ways[i ^ 1] = graph.vertexCount() - ways[i];
            result += ways[i];
        }
        return result;
    }
}
