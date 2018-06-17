package on2018_06.on2018_06_14_2018_TopCoder_Open_Algorithm.SquareFreeSet;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.util.Arrays.binarySearch;

public class SquareFreeSet {
    private static final int BUBEN = 70;

    public int findCost(int[] x) {
        IntHashSet candidates = new IntHashSet();
        for (int i : x) {
            for (int j = max(i - x.length - BUBEN, 1); j <= i + x.length + BUBEN; j++) {
                candidates.add(squareFree(j));
            }
        }
        IntList list = new IntArrayList(candidates);
        list.sort();
        int[] arr = list.toArray();
        Graph graph = new Graph(x.length + list.size() + 2);
        int source = x.length + list.size();
        int sink = source + 1;
        for (int i = 0; i < x.length; i++) {
            graph.addFlowWeightedEdge(source, i, 0, 1);
            for (int j = max(x[i] - x.length - BUBEN, 1); j <= x[i] + x.length + BUBEN; j++) {
                graph.addFlowWeightedEdge(i, x.length + binarySearch(arr, squareFree(j)), abs(j - x[i]), 1);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            graph.addFlowWeightedEdge(x.length + i, sink, 0, 1);
        }
        return (int)(long)MinCostFlow.minCostMaxFlow(graph, source, sink, false).first;
    }

    private int squareFree(int j) {
        for (int i = 2; i * i <= j; i++) {
            while (j % (i * i) == 0) {
                j /= i * i;
            }
        }
        return j;
    }
}
