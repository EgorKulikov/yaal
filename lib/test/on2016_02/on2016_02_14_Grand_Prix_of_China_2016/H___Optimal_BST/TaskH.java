package on2016_02.on2016_02_14_Grand_Prix_of_China_2016.H___Optimal_BST;



import net.egork.collections.intcollection.Range;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskH {
    int[] a;
    Graph graph;
    int[] v;
    long[][] opt;
    long[] answer;
    int[][] ind;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        a = readIntArray(in, n);
        int[] p = readIntArray(in, n - 1);
        decreaseByOne(p);
        int[] range = new int[n - 1];
        for (int i = 0; i < range.length; i++) {
            range[i] = i + 1;
        }
        graph = BidirectionalGraph.createGraph(n, p, range);
        opt = new long[n][n];
        ind = new int[n][n];
        v = new int[n];
        answer = new long[n];
        go(0, -1, 0, true);
        for (int i = 0; i < n; i++) {
            out.printLine(answer[i]);
        }
    }

    private void go(int vertex, int last, int position, boolean first) {
        v[position] = a[vertex];
        opt[position][position] = v[position];
        ind[position][position] = position;
        long sum = v[position];
        for (int i = position - 1; i >= 0; i--) {
            sum += v[i];
            opt[i][position] = Long.MAX_VALUE;
            int limit = ind[position][i + 1];
//            if (!first) {
//                limit = Math.min(limit, ind[position][i]);
//            }
            int start = max(ind[position - 1][i], i + 1);
            if (!first) {
                start = Math.max(start, ind[position][i]);
            }
            for (int j = start; j <= limit; j++) {
                long candidate = opt[i][j - 1] + opt[j][position] + sum;
                if (candidate < opt[i][position]) {
                    opt[i][position] = candidate;
                    ind[position][i] = j;
                }
            }
        }
        answer[vertex] = opt[0][position];
        IntList nxt = new IntArrayList();
        IntList nVal = new IntArrayList();
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next != last) {
                nxt.add(next);
                nVal.add(a[next]);
            }
        }
        int[] nArr = nxt.toArray();
        int[] vArr = nVal.toArray();
        orderBy(vArr, nArr);
        for (int i = 0; i < nArr.length; i++) {
            go(nArr[i], vertex, position + 1, i == 0);
        }
    }
}
