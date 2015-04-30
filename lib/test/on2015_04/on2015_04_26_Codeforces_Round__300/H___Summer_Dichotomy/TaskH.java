package on2015_04.on2015_04_26_Codeforces_Round__300.H___Summer_Dichotomy;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int minAccepted = in.readInt();
        int maxAccepted = in.readInt();
        int count = in.readInt();
        int edgeCount = in.readInt();
        int[] min = new int[count];
        int[] max = new int[count];
        IOUtils.readIntArrays(in, min, max);
        int[] from = new int[edgeCount];
        int[] to = new int[edgeCount];
        IOUtils.readIntArrays(in, from, to);
        MiscUtils.decreaseByOne(from, to);
        Graph graph = BidirectionalGraph.createGraph(count, from, to);
        int first = ArrayUtils.minElement(max);
        int second = ArrayUtils.maxElement(min);
        if (first + second < minAccepted) {
            second += minAccepted - first - second;
        } else if (first + second > maxAccepted) {
            first -= first + second - maxAccepted;
        }
        if (first < 0) {
            second += first;
            first = 0;
        }
        int[] queue = new int[count];
        int size = 0;
        int[] color = new int[count];
        for (int i = 0; i < count; i++) {
            if (min[i] > first || max[i] < first) {
                if (min[i] > second || max[i] < second) {
                    out.printLine("IMPOSSIBLE");
                    return;
                }
                color[i] = 2;
                queue[size++] = i;
            } else {
                if (min[i] > second || max[i] < second) {
                    color[i] = 1;
                    queue[size++] = i;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            size = process(queue[i], queue, color, graph, size);
            if (size == -1) {
                out.printLine("IMPOSSIBLE");
                return;
            }
        }
        for (int i = 0; i < count; i++) {
            if (color[i] == 0) {
                color[i] = 1;
                queue[size++] = i;
                for (int j = size - 1; j < size; j++) {
                    size = process(queue[j], queue, color, graph, size);
                    if (size == -1) {
                        out.printLine("IMPOSSIBLE");
                        return;
                    }
                }
            }
        }
        out.printLine("POSSIBLE");
        out.printLine(first, second);
        for (int i : color) {
            out.print(i);
        }
        out.printLine();
    }

    private int process(int current, int[] queue, int[] color, Graph graph, int size) {
        for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (color[next] == color[current]) {
                return -1;
            }
            if (color[next] == 0) {
                color[next] = 3 - color[current];
                queue[size++] = next;
            }
        }
        return size;
    }
}
