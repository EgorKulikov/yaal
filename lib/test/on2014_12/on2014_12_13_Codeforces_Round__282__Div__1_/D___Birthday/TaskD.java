package on2014_12.on2014_12_13_Codeforces_Round__282__Div__1_.D___Birthday;



import net.egork.collections.intervaltree.LCA;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    private static final long MOD = (long) (1e9 + 7);
    Graph graph;
    long[] qty;
    long[] sum;
    long[] sq;
    long[] totalSq;
    long[] totalSum;
    long[] level;
    long[] qUp;
    long[] qDown;
    long[] sumUp;
    long[] sumDown;
    long[] sqUp;
    long[] sqDown;
    int[] parent;
    long[] edgeUp;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] from = new int[count - 1];
        int[] to = new int[count - 1];
        int[] length = new int[count - 1];
        IOUtils.readIntArrays(in, from, to, length);
        MiscUtils.decreaseByOne(from, to);
        graph = BidirectionalGraph.createWeightedGraph(count, from, to, ArrayUtils.asLong(length));
        qty = new long[count];
        sum = new long[count];
        sq = new long[count];
        totalSq = new long[count];
        totalSum = new long[count];
        qUp = new long[count];
        qDown = new long[count];
        sumUp = new long[count];
        sumDown = new long[count];
        sqUp = new long[count];
        sqDown = new long[count];
        level = new long[count];
        parent = new int[count];
        edgeUp = new long[count];
        calculate(0, -1, 0L);
        calculateTotal(0, -1, 0L);
        LCA lca = new LCA(graph);
        int queryCount = in.readInt();
        for (int i = 0; i < queryCount; i++) {
            int first = in.readInt() - 1;
            int root = in.readInt() - 1;
            int base = lca.getLCA(first, root);
            long answer;
            if (base == root) {
                long distance = level[first] - level[root];
                if (parent[root] == -1) {
                    answer = totalSq[first];
                } else {
                    long otherQty = count - qty[root];
                    long otherSum = (totalSum[parent[root]] - sum[root] - edgeUp[root] * qty[root]) % MOD;
                    long otherSq = (totalSq[parent[root]] - sq[root] - 2 * sum[root] * edgeUp[root] - edgeUp[root] * edgeUp[root] % MOD * qty[root]) % MOD;
                    distance = (distance + edgeUp[root]) % MOD;
                    answer = (totalSq[first] - otherSq - 2 * otherSum * distance - distance * distance % MOD * otherQty) % MOD;
                }
            } else {
                long distance = (level[first] + level[root] - 2 * level[base]) % MOD;
                answer = (sq[root] + 2 * sum[root] * distance + distance * distance % MOD * qty[root]);
            }
//            long baseLength = level[first] - level[base];
//            answer += sqUp[base] + 2 * sumUp[base] * baseLength + baseLength * baseLength % MOD * qUp[base];
//            answer %= MOD;
//            long otherQty = qDown[root] - qDown[base];
//            long otherSum = (sumDown[root] - sumDown[base] - level[base] * otherQty) % MOD;
//            long otherSq = (sqDown[root] - sqDown[base] - 2 * (sumDown[root] - sumDown[base]) * level[base] + level[base] * level[base] % MOD * otherQty);
//            answer += otherSq + 2 * otherSum * baseLength + baseLength * baseLength % MOD * otherQty;
//            answer += baseLength * baseLength;
//            answer %= MOD;
            answer *= 2;
            answer %= MOD;
            answer -= totalSq[first];
            answer %= MOD;
            if (answer < 0) {
                answer += MOD;
            }
            out.printLine(answer);
        }
    }

    private void calculateTotal(int vertex, int last, long edge) {
        if (last != -1) {
            long otherQty = graph.vertexCount() - qty[vertex];
            long otherSum = (totalSum[last] - sum[vertex] - qty[vertex] * edge) % MOD;
            long otherSq = (totalSq[last] - sq[vertex] - 2 * sum[vertex] * edge - edge * edge % MOD * qty[vertex]) % MOD;
            totalSum[vertex] = (sum[vertex] + otherSum + otherQty * edge) % MOD;
            totalSq[vertex] = (sq[vertex] + otherSq + 2 * otherSum * edge + edge * edge % MOD * otherQty) % MOD;
        } else {
            totalSq[vertex] = sq[vertex];
            totalSum[vertex] = sum[vertex];
        }
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            long down = graph.weight(i);
            calculateTotal(next, vertex, down);
        }
    }

    private void calculate(int vertex, int last, long edge) {
        parent[vertex] = last;
        edgeUp[vertex] = edge;
        if (last != -1) {
            level[vertex] = (level[last] + edge) % MOD;
            qUp[vertex] = qUp[last] + 1;
            qDown[vertex] = qDown[last] + 1;
            sumUp[vertex] = (sumUp[last] + edge * qUp[vertex]) % MOD;
            sumDown[vertex] = (sumDown[last] + level[vertex]) % MOD;
            sqUp[vertex] = (sqUp[last] + 2 * sumUp[last] * edge + edge * edge % MOD * qUp[vertex]) % MOD;
            sqDown[vertex] = (sqDown[last] + level[vertex] * level[vertex]) % MOD;
        }
        qty[vertex] = 1;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            long down = graph.weight(i);
            calculate(next, vertex, down);
            qty[vertex] += qty[next];
            qty[vertex] %= MOD;
            sum[vertex] += sum[next] + qty[next] * down;
            sum[vertex] %= MOD;
            sq[vertex] += sq[next] + 2 * sum[next] * down + down * down % MOD * qty[next];
            sq[vertex] %= MOD;
        }
    }
}
