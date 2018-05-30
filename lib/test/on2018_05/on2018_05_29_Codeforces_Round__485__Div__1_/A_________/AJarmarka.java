package on2018_05.on2018_05_29_Codeforces_Round__485__Div__1_.A_________;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Random;

import static java.util.Arrays.fill;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class AJarmarka {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();
        int s = in.readInt();
        int[] a = in.readIntArray(n);
        int[] u = new int[m];
        int[] v = new int[m];
        in.readIntArrays(u, v);
        decreaseByOne(a, u, v);
        Graph graph = BidirectionalGraph.createGraph(n, u, v);
        int[][] closest = new int[n][k];
        boolean[] done = new boolean[n];
        int[] queue = new int[n];
        for (int i = 0; i < k; i++) {
            fill(done, false);
            int size = 0;
            for (int j = 0; j < n; j++) {
                if (a[j] == i) {
                    done[j] = true;
                    queue[size++] = j;
                }
            }
            for (int j = 0; j < n; j++) {
                int current = queue[j];
                for (int l = graph.firstOutbound(current); l != -1; l = graph.nextOutbound(l)) {
                    int next = graph.destination(l);
                    if (!done[next]) {
                        done[next] = true;
                        queue[size++] = next;
                        closest[next][i] = closest[current][i] + 1;
                    }
                }
            }
        }
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            sort(closest[i], 0, k, s);
            for (int j = 0; j < s; j++) {
                answer[i] += closest[i][j];
            }
        }
        out.printLine(answer);
    }

    Random random = new Random();

    void sort(int[] array, int from, int to, int id) {
        int by = array[random.nextInt(to - from) + from];
        int less = from;
        int more = to;
        int same = 0;
        int at = from;
        while (less + same < more) {
            if (array[at] > by) {
                int temp = array[more - 1];
                array[more - 1] = array[at];
                array[at] = temp;
                more--;
            } else if (array[at] < by) {
                int temp = array[less];
                array[less] = array[at];
                array[at] = temp;
                less++;
                at++;
            } else {
                same++;
                at++;
            }
        }
        if (id > more) {
            sort(array, more, to, id);
        } else if (id < less) {
            sort(array, from, less, id);
        }
    }
}
