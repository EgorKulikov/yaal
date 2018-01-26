package on2017_08.on2017_08_15_Single_Round_Match_719.OwaskiAndTree;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;

import static java.lang.Math.max;

public class OwaskiAndTree {
    int n;
    Graph graph;
    int[] pleasure;
    int[] wasBefore;
    int[] firstVisit;

    public int maximalScore(int[] parent, int[] pleasure) {
        n = pleasure.length;
        this.pleasure = pleasure;
        graph = new BidirectionalGraph(n, n - 1);
        for (int i = 1; i < n; i++) {
            graph.addSimpleEdge(i, parent[i - 1]);
        }
        wasBefore = new int[n];
        firstVisit = new int[n];
        go(0, -1);
        return wasBefore[0];
    }

    private void go(int vertex, int last) {
        firstVisit[vertex] = pleasure[vertex];
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            go(next, vertex);
            firstVisit[vertex] += max(firstVisit[next], 0);
            wasBefore[vertex] += wasBefore[next];
        }
        wasBefore[vertex] = Math.max(wasBefore[vertex], firstVisit[vertex]);
    }
}
