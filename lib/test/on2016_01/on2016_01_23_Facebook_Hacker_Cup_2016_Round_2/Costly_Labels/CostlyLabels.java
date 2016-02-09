package on2016_01.on2016_01_23_Facebook_Hacker_Cup_2016_Round_2.Costly_Labels;



import net.egork.collections.Pair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class CostlyLabels {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int k;
            int p;
            int[][] c;
            Graph graph;
            long answer;
            long[][] value;
            long[][] down;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                k = in.readInt();
                p = in.readInt();
                c = IOUtils.readIntTable(in, n, k);
                int[] a = new int[n - 1];
                int[] b = new int[n - 1];
                IOUtils.readIntArrays(in, a, b);
                MiscUtils.decreaseByOne(a, b);
                graph = BidirectionalGraph.createGraph(n, a, b);
            }

            @Override
            public void solve() {
                value = new long[n][k + 1];
                down = new long[n][k];
                ArrayUtils.fill(value, -1);
                ArrayUtils.fill(down, -1);
                answer = Long.MAX_VALUE;
                for (int i = 0; i < k; i++) {
                    long current = goDown(0, -1, i);
                    answer = Math.min(answer, current);
                }
                answer += calculate(0, -1, k);
                value = null;
                down = null;
            }

            private long goDown(int vertex, int last, int color) {
                if (down[vertex][color] != -1) {
                    return down[vertex][color];
                }
                long result = c[vertex][color];
                for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
                    int next = graph.destination(i);
                    if (next == last) {
                        continue;
                    }
                    result += calculate(next, vertex, color);
                }
                return down[vertex][color] = result;
            }

            private long calculate(int vertex, int last, int color) {
                if (value[vertex][color] != -1) {
                    return value[vertex][color];
                }
                value[vertex][color] = p;
                int degree = 0;
                for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
                    degree++;
                    int next = graph.destination(i);
                    if (next == last) {
                        continue;
                    }
                    long candidate = Long.MAX_VALUE;
                    for (int j = 0; j < k; j++) {
                        candidate = Math.min(candidate, goDown(next, vertex, j));
                    }
                    value[vertex][color] += candidate;
                }
                if (degree > k) {
                    return value[vertex][color];
                }
                if (last != -1) {
                    degree--;
                }
                Graph flow = new Graph(degree + k + 2);
                int at = 0;
                for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
                    int next = graph.destination(i);
                    if (next == last) {
                        continue;
                    }
                    flow.addFlowWeightedEdge(degree + k, at, 0, 1);
                    for (int j = 0; j < k; j++) {
                        flow.addFlowWeightedEdge(at, degree + j, goDown(next, vertex, j), 1);
                    }
                    at++;
                }
                for (int i = 0; i < k; i++) {
                    if (i != color) {
                        flow.addFlowWeightedEdge(degree + i, degree + k + 1, 0, 1);
                    }
                }
                Pair<Long, Long> result = MinCostFlow.minCostMaxFlow(flow, degree + k, degree + k + 1, false);
                if (result.second == degree) {
                    value[vertex][color] = Math.min(value[vertex][color], result.first);
                }
                return value[vertex][color];
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
