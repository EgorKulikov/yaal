package on2016_03.on2016_03_18_CROC_2016___Elimination_Round.D___Robot_Rapping_Results_Report;



import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.util.Arrays.fill;
import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] u = new int[m];
        int[] v = new int[m];
        readIntArrays(in, u, v);
        decreaseByOne(u, v);
        int left = 1;
        int right = m + 1;
        queue = new int[n];
        degree = new int[n];
        while (left < right) {
            int middle = (left + right) >> 1;
            if (ok(n, u, v, middle)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        out.printLine(left == m + 1 ? -1 : left);
    }

    int[] queue;
    int[] degree;

    private boolean ok(int n, int[] u, int[] v, int m) {
        Graph graph = new Graph(n, m);
        for (int i = 0; i < m; i++) {
            graph.addSimpleEdge(u[i], v[i]);
        }
        fill(degree, 0);
        for (int i = 0; i < m; i++) {
            degree[v[i]]++;
        }
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue[size++] = i;
            }
        }
        if (size > 1) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            int wSize = size;
            for (int j = graph.firstOutbound(queue[i]); j != -1; j = graph.nextOutbound(j)) {
                int next = graph.destination(j);
                if (--degree[next] == 0) {
                    queue[size++] = next;
                }
            }
            if (size - wSize > 1) {
                return false;
            }
        }
        return true;
    }
}
