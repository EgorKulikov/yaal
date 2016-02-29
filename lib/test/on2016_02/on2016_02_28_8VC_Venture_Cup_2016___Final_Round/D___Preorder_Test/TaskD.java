package on2016_02.on2016_02_28_8VC_Venture_Cup_2016___Final_Round.D___Preorder_Test;



import net.egork.generated.collections.queue.IntArrayQueue;
import net.egork.generated.collections.queue.IntQueue;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskD {
    Graph graph;
    int[] weight;
    int[] path;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = readIntArray(in, n);
        int[] u = new int[n - 1];
        int[] v = new int[n - 1];
        readIntArrays(in, u, v);
        decreaseByOne(u, v);
        graph = BidirectionalGraph.createGraph(n, u, v);
        int[] order = order(a);
        reverse(order);
        int left = k;
        int right = n;
        weight = new int[n];
        int[] degree = new int[n];
        for (int i = 0; i < n - 1; i++) {
            degree[u[i]]++;
            degree[v[i]]++;
        }
        int[] remaining = new int[n];
        int[] queue = new int[n];
        path = new int[n];
        while (left < right) {
            int middle = (left + right) >> 1;
            int threshold = a[order[middle - 1]];
            fill(weight, 0);
            for (int i = 0; i < n; i++) {
                weight[i] = a[i] >= threshold ? 1 : 0;
            }
            System.arraycopy(degree, 0, remaining, 0, n);
            int size = 0;
            for (int i = 0; i < n; i++) {
                if (degree[i] == 1 && weight[i] > 0) {
                    queue[size++] = i;
                }
            }
            for (int i = 0; i < size; i++) {
                int current = queue[i];
                for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
                    int next = graph.destination(j);
                    if (weight[next] != 0) {
                        weight[next] += weight[current];
                        weight[current] = 0;
                        remaining[next]--;
                        if (remaining[next] == 1) {
                            queue[size++] = next;
                        }
                    }
                }
            }
            int result = dfs(0, -1);
            if (result >= k) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        out.printLine(a[order[left - 1]]);
    }

    private int dfs(int current, int last) {
        int result = 0;
        int max = 0;
        int second = 0;
        for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            result = Math.max(result, dfs(next, current));
            if (path[next] > max) {
                second = max;
                max = path[next];
            } else {
                second = Math.max(second, path[next]);
            }
        }
        if (weight[current] != 0) {
            path[current] = weight[current] + max;
            result = Math.max(result, max + second + weight[current]);
        } else {
            path[current] = 0;
        }
        return result;
    }
}
