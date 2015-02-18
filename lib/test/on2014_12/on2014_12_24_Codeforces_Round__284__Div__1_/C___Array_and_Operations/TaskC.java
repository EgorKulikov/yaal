package on2014_12.on2014_12_24_Codeforces_Round__284__Div__1_.C___Array_and_Operations;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    int[] leftToRight;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        int[] array = IOUtils.readIntArray(in, count);
        int[] from = new int[edgeCount];
        int[] to = new int[edgeCount];
        IOUtils.readIntArrays(in, from, to);
        MiscUtils.decreaseByOne(from, to);
        Graph graph = new Graph(count + 2);
        leftToRight = new int[edgeCount];
        for (int i = 0; i < edgeCount; i++) {
            int source = (from[i] & 1) == 0 ? from[i] : to[i];
            leftToRight[i] = graph.addFlowEdge(source, from[i] + to[i] - source, Long.MAX_VALUE);
        }
        for (int i = 0; i < count; i += 2) {
            graph.addFlowEdge(count, i, 1);
        }
        for (int i = 1; i < count; i += 2) {
            graph.addFlowEdge(i, count + 1, 1);
        }
        long answer = 0;
        for (int i = 2; i * i <= 1e9; i++) {
            answer += solve(array, graph, i);
        }
        for (int i : array) {
            if (i != 1) {
                answer += solve(array, graph, i);
            }
        }
        out.printLine(answer);
    }

    private long solve(int[] array, Graph graph, int divisor) {
        for (int i : leftToRight) {
            graph.capacity[i] = Long.MAX_VALUE;
            graph.capacity[graph.reverse(i)] = 0;
        }
        int total = 0;
        for (int i = graph.firstOutbound(array.length); i != -1; i = graph.nextOutbound(i)) {
            total++;
            int capacity = 0;
            int id = graph.destination(i);
            while (array[id] % divisor == 0) {
                array[id] /= divisor;
                capacity++;
            }
            graph.capacity[i] = capacity;
            graph.capacity[graph.reverse(i)] = 0;
        }
        for (int i = graph.firstInbound(array.length + 1); i != -1; i = graph.nextInbound(i)) {
            total++;
            int capacity = 0;
            int id = graph.source(i);
            while (array[id] % divisor == 0) {
                array[id] /= divisor;
                capacity++;
            }
            graph.capacity[i] = capacity;
            graph.capacity[graph.reverse(i)] = 0;
        }
        if (total != array.length) {
            throw new RuntimeException();
        }
        return MaxFlow.dinic(graph, array.length, array.length + 1);
    }
}
