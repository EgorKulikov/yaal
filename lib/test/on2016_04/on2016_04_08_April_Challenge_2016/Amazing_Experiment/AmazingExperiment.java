package on2016_04.on2016_04_08_April_Challenge_2016.Amazing_Experiment;



import net.egork.collections.intervaltree.LCA;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.ArrayUtils.*;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class AmazingExperiment {
    Graph graph;
    int[] answer;
    int[] down;
    int[] diameter;
    IntList[] sums;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] u = new int[n - 1];
        int[] v = new int[n - 1];
        int[] w = new int[n - 1];
        readIntArrays(in, u, v, w);
        decreaseByOne(u, v);
        graph = BidirectionalGraph.createWeightedGraph(n, u, v, asLong(w));
        answer = new int[n];
        down = new int[n];
        diameter = new int[n];
        sums = new IntList[n];
        LCA lca = new LCA(graph);
        int[] order = createOrder(n);
        sort(order, (x, y) -> lca.getLevel(y) - lca.getLevel(x));
        for (int i : order) {
            if (i == 0) {
                solve(0, -1);
            } else {
                for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                    if (lca.getLevel(graph.destination(j)) < lca.getLevel(i)) {
                        solve(i, graph.destination(j));
                    }
                }
            }
        }
        out.separateLines(answer);
    }

    private void solve(int vertex, int last) {
        int maxDiameter = 0;
        int maxDown = 0;
        int maxDownAt = -1;
        int secondDown = 0;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
//            solve(next, vertex);
            if (diameter[next] > maxDiameter) {
                maxDiameter = diameter[next];
                answer[vertex] = answer[next];
            }
            int curDown = (int) (down[next] + graph.weight(i));
            if (curDown > maxDown) {
                secondDown = maxDown;
                maxDown = curDown;
                maxDownAt = next;
            } else {
                secondDown = max(secondDown, curDown);
            }
        }
        if (maxDownAt == -1) {
            sums[vertex] = new IntArrayList(1);
            sums[vertex].add(0);
        } else {
            sums[vertex] = sums[maxDownAt];
            sums[vertex].add(maxDown);
        }
        down[vertex] = maxDown;
        if (maxDown + secondDown > maxDiameter) {
            maxDiameter = maxDown + secondDown;
            int middle = maxDiameter / 2;
            int left = 0;
            int right = sums[vertex].size() - 1;
            while (left < right) {
                int m = (left + right) >> 1;
                if (sums[vertex].get(m) >= middle) {
                    right = m;
                } else {
                    left = m + 1;
                }
            }
            int val = sums[vertex].get(left);
            answer[vertex] = max(val, maxDiameter - val);
            if (left > 0) {
                val = sums[vertex].get(left - 1);
                answer[vertex] = Math.min(answer[vertex], max(val, maxDiameter - val));
            }
        }
        diameter[vertex] = maxDiameter;
    }
}
