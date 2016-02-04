package net.egork.graph;

import net.egork.generated.collections.queue.IntArrayQueue;
import net.egork.generated.collections.queue.IntQueue;

import java.util.Arrays;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public class MaxFlow {
    private final Graph graph;
    private int source;
    private int destination;
    private IntQueue queue;
    private int[] distance;
    private int[] nextEdge;

    public MaxFlow(Graph graph, int source, int destination) {
        this.graph = graph;
        this.source = source;
        this.destination = destination;
        int vertexCount = graph.vertexCount();
        queue = new IntArrayQueue(vertexCount);
        distance = new int[vertexCount];
        nextEdge = new int[vertexCount];
    }

    public static long dinic(Graph graph, int source, int destination) {
        return new MaxFlow(graph, source, destination).dinic();
    }

    public long dinic() {
        long totalFlow = 0;
        while (true) {
            edgeDistances();
            if (distance[destination] == -1) {
                break;
            }
            Arrays.fill(nextEdge, -2);
            totalFlow += dinicImpl(source, Long.MAX_VALUE);
        }
        return totalFlow;
    }

    private void edgeDistances() {
        Arrays.fill(distance, -1);
        distance[source] = 0;
        queue.add(source);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            int id = graph.firstOutbound(current);
            while (id != -1) {
                if (graph.capacity(id) != 0) {
                    int next = graph.destination(id);
                    if (distance[next] == -1) {
                        distance[next] = distance[current] + 1;
                        queue.add(next);
                    }
                }
                id = graph.nextOutbound(id);
            }
        }
    }

    private long dinicImpl(int source, long flow) {
        if (source == destination) {
            return flow;
        }
        if (flow == 0 || distance[source] == distance[destination]) {
            return 0;
        }
        int id = nextEdge[source];
        if (id == -2) {
            nextEdge[source] = id = graph.firstOutbound(source);
        }
        long totalPushed = 0;
        while (id != -1) {
            int nextDestinationID = graph.destination(id);
            if (graph.capacity(id) != 0 && distance[nextDestinationID] == distance[source] + 1) {
                long pushed = dinicImpl(nextDestinationID, Math.min(flow, graph.capacity(id)));
                if (pushed != 0) {
                    graph.pushFlow(id, pushed);
                    flow -= pushed;
                    totalPushed += pushed;
                    if (flow == 0) {
                        return totalPushed;
                    }
                }
            }
            nextEdge[source] = id = graph.nextOutbound(id);
        }
        return totalPushed;
    }
}
