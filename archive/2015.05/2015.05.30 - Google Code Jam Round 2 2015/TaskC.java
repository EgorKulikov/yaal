package net.egork;

import net.egork.collections.map.Indexer;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int count;
            String[][] sentences;
            int answer = 0;

            @Override
            public void read(InputReader in) {
                count = in.readInt();
                sentences = new String[count][];
                for (int i = 0; i < count; i++) {
                    sentences[i] = in.readLine().split(" ");
                }
            }

            @Override
            public void solve() {
                int[][] id = new int[count][];
                Indexer<String> indexer = new Indexer<>();
                for (int i = 0; i < count; i++) {
                    id[i] = new int[sentences[i].length];
                    for (int j = 0; j < id[i].length; j++) {
                        id[i][j] = indexer.get(sentences[i][j]);
                    }
                }
//                answer = Integer.MAX_VALUE;
//                int[] type = new int[indexer.size()];
//                for (int i = 0; i < (1 << (count - 2)); i++) {
//                    int mask = 2 + 4 * i;
//                    Arrays.fill(type, 0);
//                    for (int j = 0; j < count; j++) {
//                        for (int k : id[j]) {
//                            type[k] |= 1 << ((mask >> j) & 1);
//                        }
//                    }
//                    answer = Math.min(answer, ArrayUtils.count(type, 3));
//                }
                Graph graph = new Graph(2 * indexer.size() + 2);
                int source = graph.vertexCount() - 2;
                int sink = graph.vertexCount() - 1;
                for (int i : id[0]) {
                    graph.addFlowEdge(source, 2 * i, Integer.MAX_VALUE);
                }
                for (int i : id[1]) {
                    graph.addFlowEdge(2 * i + 1, sink, Integer.MAX_VALUE);
                }
                for (int i = 0; i < indexer.size(); i++) {
                    graph.addFlowEdge(2 * i, 2 * i + 1, 1);
                }
                for (int i = 2; i < count; i++) {
                    for (int j : id[i]) {
                        for (int k : id[i]) {
                            if (j != k) {
                                graph.addFlowEdge(2 * j + 1, 2 * k, Integer.MAX_VALUE);
                            }
                        }
                    }
                }
                answer = (int) MaxFlow.dinic(graph, source, sink);
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
