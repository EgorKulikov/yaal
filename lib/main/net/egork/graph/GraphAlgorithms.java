package net.egork.graph;

/**
 * @author egor@egork.net
 */
public class GraphAlgorithms {
    public static int[] topologicalSort(Graph graph) {
        int count = graph.vertexCount();
        int[] order = new int[count];
        int[] degree = new int[count];
        int size = 0;
        for (int i = 0; i < graph.edgeCount(); i++) {
            if (!graph.isRemoved(i)) {
                degree[graph.destination(i)]++;
            }
        }
        for (int i = 0; i < count; i++) {
            if (degree[i] == 0) {
                order[size++] = i;
            }
        }
        for (int i = 0; i < size; i++) {
            int current = order[i];
            for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
                int next = graph.destination(j);
                if (--degree[next] == 0) {
                    order[size++] = next;
                }
            }
        }
        if (size != count) {
            return null;
        }
        return order;
    }
}
