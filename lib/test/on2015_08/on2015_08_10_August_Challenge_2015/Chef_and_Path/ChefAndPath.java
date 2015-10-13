package on2015_08.on2015_08_10_August_Challenge_2015.Chef_and_Path;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ChefAndPath {
    Graph graph;
    int[][] delta;
    int[][] stack;
    int[] length;
    int l;
    int r;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int l = in.readInt();
        int r = in.readInt();
        this.l = l;
        this.r = r;
        int[] a = new int[n - 1];
        int[] b = new int[n - 1];
        int[] c = new int[n - 1];
        IOUtils.readIntArrays(in, a, b, c);
        MiscUtils.decreaseByOne(a, b);
        graph = BidirectionalGraph.createWeightedGraph(n, a, b, ArrayUtils.asLong(c));
        Arrays.sort(c);
        delta = new int[n][r + 1];
        stack = new int[n][r + 1];
        length = new int[n];
        int left = 0;
        int right = c.length;
        while (left < right) {
            int middle = (left + right) >> 1;
            if (can(0, -1, c[middle])) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        if (left == c.length) {
            out.printLine(-1);
        } else {
            out.printLine(c[left]);
        }
    }

    private boolean can(int vertex, int last, int threshold) {
        length[vertex] = 1;
        delta[vertex][0] = 0;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            if (can(next, vertex, threshold)) {
                return true;
            }
            int inDelta = graph.weight(i) <= threshold ? 1 : -1;
            int head = 0;
            int tail = 0;
            for (int j = r - 1; j >= l; j--) {
                if (j < length[vertex]) {
                    while (tail > head && delta[vertex][stack[vertex][tail - 1]] < delta[vertex][j]) {
                        tail--;
                    }
                    stack[vertex][tail++] = j;
                }
            }
            for (int j = 0; j < length[next]; j++) {
                if (head < tail && stack[vertex][head] == r - j) {
                    head++;
                }
                if (j < l) {
                    int jj = l - j - 1;
                    if (jj < length[vertex]) {
                        while (tail > head && delta[vertex][stack[vertex][tail - 1]] < delta[vertex][jj]) {
                            tail--;
                        }
                        stack[vertex][tail++] = jj;
                    }
                }
                if (head < tail && delta[vertex][stack[vertex][head]] + inDelta + delta[next][j] > 0) {
                    return true;
                }
            }
            for (int j = 0; j < length[next] && j < r; j++) {
                if (j + 1 >= length[vertex]) {
                    delta[vertex][j + 1] = inDelta + delta[next][j];
                    length[vertex]++;
                } else {
                    delta[vertex][j + 1] = Math.max(delta[vertex][j + 1], inDelta + delta[next][j]);
                }
            }
        }
        return false;
    }
}
