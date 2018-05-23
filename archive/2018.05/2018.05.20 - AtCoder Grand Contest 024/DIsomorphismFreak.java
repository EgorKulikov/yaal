package net.egork;

import net.egork.collections.Pair;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntLongPair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.misc.MiscUtils;

import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.maxPosition;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class DIsomorphismFreak {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = new int[n - 1];
        int[] b = new int[n - 1];
        in.readIntArrays(a, b);
        decreaseByOne(a, b);
        Graph g = BidirectionalGraph.createWeightedGraph(n, a, b, createArray(n - 1, 1L));
        long[] distance = ShortestDistance.dijkstraAlgorithm(g, 0).first;
        int end = maxPosition(distance);
        Pair<long[], int[]> result = ShortestDistance.dijkstraAlgorithm(g, end);
        end = maxPosition(result.first);
        long diameter = result.first[end];
        int times = (int) (diameter / 2);
        for (int i = 0; i < times; i++) {
            end = g.source(result.second[end]);
        }
        IntLongPair best = process(g, end);
        for (int i = g.firstOutbound(end); i != -1; i = g.nextOutbound(i)) {
            best = MiscUtils.min(best, process(g, end, g.destination(i)));
        }
        out.printLine(best.first, best.second);
    }

    IntLongPair process(Graph g, int...start) {
        int n = g.vertexCount();
        boolean[] processed = new boolean[n];
        IntList level = new IntArrayList();
        for (int i : start) {
            processed[i] = true;
            level.add(i);
        }
        int colors = 0;
        long endVertices = level.size();
        while (!level.isEmpty()) {
            colors++;
            IntList nextLevel = new IntArrayList();
            int max = 0;
            for (int i : level) {
                int current = 0;
                for (int j = g.firstOutbound(i); j != -1; j = g.nextOutbound(j)) {
                    int vertex = g.destination(j);
                    if (!processed[vertex]) {
                        current++;
                        nextLevel.add(vertex);
                        processed[vertex] = true;
                    }
                }
                max = Math.max(max, current);
            }
            if (max != 0) {
                endVertices *= max;
            }
            level = nextLevel;
        }
        return new IntLongPair(colors, endVertices);
    }
}
