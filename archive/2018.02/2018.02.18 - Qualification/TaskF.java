package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.maxPosition;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] x = new int[n - 1];
        int[] y = new int[n - 1];
        in.readIntArrays(x, y);
        decreaseByOne(x, y);
        Graph graph = BidirectionalGraph.createWeightedGraph(n, x, y, createArray(n - 1, 1L));
        int end = maxPosition(ShortestDistance.dijkstraAlgorithm(graph, 0).first);
        int other = maxPosition(ShortestDistance.dijkstraAlgorithm(graph, end).first);
        int[] edges = ShortestDistance.dijkstraAlgorithm(graph, end, other).second.toArray();
        int[] path = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            path[i] = graph.source(edges[i]);
        }
        path[edges.length] = other;
        int left = 1;
        int right = (path.length - 1) >> 1;
        while (left < right) {
            int middle = (left + right) >> 1;
            long[] d1 = ShortestDistance.dijkstraAlgorithm(graph, path[middle]).first;
            long[] d2 = ShortestDistance.dijkstraAlgorithm(graph, path[path.length - middle - 1]).first;
            boolean good = true;
            for (int i = 0; i < n; i++) {
                if (d1[i] > middle && d2[i] > middle) {
                    good = false;
                    break;
                }
            }
            if (good) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        int first = path[left] + 1;
        int second = path[path.length - left - 1] + 1;
        if (first == second) {
            second = 1;
        }
        if (first == second) {
            second = 2;
        }
        out.printLine(first, second);
    }
}
