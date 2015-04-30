package on2015_04.on2015_04_26_Codeforces_Round__300.E___Demiurges_Play_Again;



import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    int[] sum;
    int[] min;
    Graph graph;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] from = new int[count - 1];
        int[] to = new int[count - 1];
        IOUtils.readIntArrays(in, from, to);
        MiscUtils.decreaseByOne(from, to);
        graph = Graph.createGraph(count, from, to);
        min = ArrayUtils.createArray(count, -1);
        sum = ArrayUtils.createArray(count, -1);
        int[] notLeaf = new int[count];
        for (int i : from) {
            notLeaf[i]++;
        }
        out.printLine(ArrayUtils.count(notLeaf, 0) + 1 - getMin(0), getSum(0));
    }

    private int getSum(int vertex) {
        if (sum[vertex] != -1) {
            return sum[vertex] = -1;
        }
        if (graph.firstOutbound(vertex) == -1) {
            return sum[vertex] = 1;
        }
        sum[vertex] = 0;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            sum[vertex] += getMin(graph.destination(i));
        }
        return sum[vertex];
    }

    private int getMin(int vertex) {
        if (min[vertex] != -1) {
            return min[vertex];
        }
        if (graph.firstOutbound(vertex) == -1) {
            return min[vertex] = 1;
        }
        min[vertex] = Integer.MAX_VALUE;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            min[vertex] = Math.min(min[vertex], getSum(graph.destination(i)));
        }
        return min[vertex];
    }
}
