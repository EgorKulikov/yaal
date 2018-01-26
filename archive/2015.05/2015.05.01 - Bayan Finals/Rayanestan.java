package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

public class Rayanestan {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int count;
            int edgeCount;
            int start;
            int end;
            int[] from;
            int[] to;
            long[] cost;

            long answer;

            @Override
            public void read(InputReader in) {
                count = in.readInt();
                edgeCount = in.readInt();
                start = in.readInt() - 1;
                end = in.readInt() - 1;
                from = new int[edgeCount];
                to = new int[edgeCount];
                cost = new long[edgeCount];
                for (int i = 0; i < edgeCount; i++) {
                    from[i] = in.readInt() - 1;
                    to[i] = in.readInt() - 1;
                    cost[i] = in.readLong();
                }
            }

            @Override
            public void solve() {
                Graph graph = new BidirectionalGraph(count);
                boolean[] in = new boolean[edgeCount];
                IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
                for (int i = 0; i < edgeCount; i++) {
                    if (setSystem.join(from[i], to[i])) {
                        graph.addWeightedEdge(from[i], to[i], cost[i]);
                        in[i] = true;
                    }
                }
                long[] xor = new long[count];
                process(0, -1, 0L, graph, xor);
                long[] cycles = new long[edgeCount - count + 1];
                int at = 0;
                for (int i = 0; i < edgeCount; i++) {
                    if (!in[i]) {
                        cycles[at++] = xor[from[i]] ^ xor[to[i]] ^ cost[i];
                    }
                }
                int start = 0;
                for (int i = 59; i >= 0; i--) {
                    int index = -1;
                    for (int j = start; j < cycles.length; j++) {
                        if (cycles[j] >= (1L << i)) {
                            index = j;
                            break;
                        }
                    }
                    if (index == -1) {
                        continue;
                    }
                    long temp = cycles[start];
                    cycles[start] = cycles[index];
                    cycles[index] = temp;
                    for (int j = index + 1; j < cycles.length; j++) {
                        if (cycles[j] >= (1L << i)) {
                            cycles[j] ^= cycles[start];
                        }
                    }
                    start++;
                }
                long base = xor[this.start] ^ xor[end];
                for (long i : cycles) {
                    if ((base ^ i) < base) {
                        base ^= i;
                    }
                }
                answer = base;
            }

            private void process(int vertex, int last, long value, Graph graph, long[] xor) {
                xor[vertex] = value;
                for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
                    int next = graph.destination(i);
                    if (next != last) {
                        process(next, vertex, value ^ graph.weight(i), graph, xor);
                    }
                }
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":");
                out.printLine(answer);
            }
        }, 4);
    }
}
