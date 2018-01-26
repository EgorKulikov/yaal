package net.egork;



import net.egork.collections.map.Indexer;
import net.egork.concurrency.Scheduler;
import net.egork.concurrency.Task;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArrays;

public class FoxMoles {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int n;
            int[] p;
            int[] r;

            @Override
            public void read(InputReader in) {
                n = in.readInt();
                p = new int[n];
                r = new int[n];
                readIntArrays(in, p, r);
            }

            int answer;

            @Override
            public void solve() {
                IntSet forbidden = new IntHashSet();
                for (int i = 0; i < n; i++) {
                    forbidden.add(p[i]);
                }
                Indexer<Integer> vertices = new Indexer<>();
                IntSet sureIs = new IntHashSet();
                IntSet forbiddenId = new IntHashSet();
                for (int i = 0; i < n; i++) {
                    vertices.get(p[i] - r[i]);
                    vertices.get(p[i] + r[i]);
                    if (forbidden.contains(p[i] - r[i])) {
                        sureIs.add(vertices.get(p[i] + r[i]));
                        forbiddenId.add(vertices.get(p[i] - r[i]));
                    }
                    if (forbidden.contains(p[i] + r[i])) {
                        sureIs.add(vertices.get(p[i] - r[i]));
                        forbiddenId.add(vertices.get(p[i] + r[i]));
                    }
                }
                Graph graph = new BidirectionalGraph(vertices.size());
                for (int i = 0; i < n; i++) {
                    graph.addSimpleEdge(vertices.get(p[i] - r[i]), vertices.get(p[i] + r[i]));
                }
                int[] color = new int[graph.vertexCount()];
                int[] queue = new int[color.length];
                for (int i = 0; i < color.length; i++) {
                    if (color[i] == 0) {
                        int forbiddenColor = 0;
                        queue[0] = i;
                        int size = 1;
                        color[i] = 1;
                        for (int j = 0; j < size; j++) {
                            int current = queue[j];
                            if (forbiddenId.contains(current)) {
                                if (forbiddenColor == 0) {
                                    forbiddenColor = color[current];
                                } else if (forbiddenColor != color[current]) {
                                    answer = -1;
                                    return;
                                }
                            }
                            for (int k = graph.firstOutbound(current); k != -1; k = graph.nextOutbound(k)) {
                                int next = graph.destination(k);
                                if (color[next] == color[current]) {
                                    answer = -1;
                                    return;
                                }
                                if (color[next] == 0) {
                                    color[next] = -color[current];
                                    queue[size++] = next;
                                }
                            }
                        }
                    }
                }
                graph = new Graph(vertices.size() + 2);
                int source = vertices.size();
                int sink = source + 1;
                for (int i = 0; i < vertices.size(); i++) {
                    if (color[i] == 1) {
                        graph.addFlowEdge(source, i, 1);
                    } else {
                        graph.addFlowEdge(i, sink, 1);
                    }
                }
                int minNeeded = sureIs.size();
                for (int i = 0; i < n; i++) {
                    if (sureIs.contains(vertices.get(p[i] - r[i])) || sureIs.contains(vertices.get(p[i] + r[i]))) {
                    } else if (color[vertices.get(p[i] - r[i])] == 1) {
                        graph.addFlowEdge(vertices.get(p[i] - r[i]), vertices.get(p[i] + r[i]), 1);
                    } else {
                        graph.addFlowEdge(vertices.get(p[i] + r[i]), vertices.get(p[i] - r[i]), 1);
                    }
                }
                minNeeded += MaxFlow.dinic(graph, source, sink);
                answer = vertices.size() - forbiddenId.size() - minNeeded + 1;
            }

            @Override
            public void write(OutputWriter out, int testNumber) {
                out.printLine("Case #" + testNumber + ":", answer);
            }
        }, 4);
    }
}
