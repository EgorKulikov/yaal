package on2016_02.on2016_02_26_Manthan__Codefest_16.F___The_Chocolate_Spree;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskF {
    int n;
    long[] a;
    Graph graph;
    long[] best;
    long[] cRes;
    long answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        a = readLongArray(in, n);
        int[] u = new int[n - 1];
        int[] v = new int[n - 1];
        readIntArrays(in, u, v);
        decreaseByOne(u, v);
        graph = BidirectionalGraph.createGraph(n, u, v);
        best = new long[n];
        cRes = new long[2 * n - 2];
        findBest(0, -1);
        findAnswer(0, -1, 0);
        out.printLine(answer);
    }

    private void findAnswer(int vertex, int last, long up) {
        long max = up;
        int maxAt = -1;
        long second = 0;
        int secondAt = -1;
        long third = 0;
        long bs = 0;
        long secBs = 0;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            long call = cRes[i];
            if (call > max) {
                third = second;
                second = max;
                secondAt = maxAt;
                max = call;
                maxAt = i;
            } else if (call > second) {
                third = second;
                second = call;
                secondAt = i;
            } else {
                third = Math.max(third, call);
            }
            if (best[next] > bs) {
                secBs = bs;
                bs = best[next];
            } else {
                secBs = Math.max(secBs, best[next]);
            }
        }
        answer = Math.max(answer, bs + secBs);
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            if (i == maxAt) {
                answer = Math.max(answer, best[next] + a[vertex] + second + third);
            } else if (i == secondAt) {
                answer = Math.max(answer, best[next] + a[vertex] + max + third);
            } else {
                answer = Math.max(answer, best[next] + a[vertex] + second + max);
            }
            findAnswer(next, vertex, (i == maxAt ? second : max) + a[vertex]);
        }
    }

    private long findBest(int vertex, int last) {
        long max = 0;
        long second = 0;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            long call = findBest(next, vertex);
            cRes[i] = call;
            if (call > max) {
                second = max;
                max = call;
            } else {
                second = Math.max(second, call);
            }
            best[vertex] = Math.max(best[vertex], best[next]);
        }
        best[vertex] = Math.max(best[vertex], max + second + a[vertex]);
        return max + a[vertex];
    }
}
