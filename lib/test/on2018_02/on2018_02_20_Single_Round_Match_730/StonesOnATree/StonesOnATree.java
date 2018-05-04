package on2018_02.on2018_02_20_Single_Round_Match_730.StonesOnATree;



import net.egork.graph.Graph;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class StonesOnATree {
    public int minStones(int[] p, int[] w) {
        int n = w.length;
        Graph graph = new Graph(n);
        for (int i = 0; i < n - 1; i++) {
            graph.addSimpleEdge(p[i], i + 1);
        }
        return go(0, graph, w);
    }

    private int go(int vertex, Graph graph, int[] w) {
        int child = graph.firstOutbound(vertex);
        if (child == -1) {
            return w[vertex];
        }
        if (graph.nextOutbound(child) == -1) {
            child = graph.destination(child);
            return max(w[vertex] + w[child], go(child, graph, w));
        }
        int other = graph.destination(graph.nextOutbound(child));
        child = graph.destination(child);
        int answer = w[vertex] + w[other] + w[child];
        int chAns = go(child, graph, w);
        int oAns = go(other, graph, w);
        answer = Math.max(answer, min(max(chAns + w[other], oAns), max(oAns + w[child], chAns)));
        return answer;
    }
}
