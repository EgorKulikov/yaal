package net.egork.graph;

/**
 * @author egorku@yandex-team.ru
 */
public class DFSOrder {
    public final int[] position;
    public final int[] end;

    public DFSOrder(Graph graph) {
        this(graph, 0);
    }

    public DFSOrder(Graph graph, int root) {
        int count = graph.vertexCount();
        position = new int[count];
        end = new int[count];
        int[] edge = new int[count];
        int[] stack = new int[count];
        for (int i = 0; i < count; i++) {
            edge[i] = graph.firstOutbound(i);
        }
        stack[0] = root;
        int size = 1;
        position[root] = 0;
        int index = 0;
        while (size > 0) {
            int current = stack[size - 1];
            if (edge[current] == -1) {
                end[current] = index;
                size--;
            } else {
                int next = graph.destination(edge[current]);
                edge[current] = graph.nextOutbound(edge[current]);
                position[next] = ++index;
                stack[size++] = next;
            }
        }
    }

    public DFSOrder(BidirectionalGraph graph) {
        this(graph, 0);
    }

    public DFSOrder(BidirectionalGraph graph, int root) {
        int count = graph.vertexCount();
        position = new int[count];
        end = new int[count];
        int[] edge = new int[count];
        int[] stack = new int[count];
        int[] last = new int[count];
        for (int i = 0; i < count; i++) {
            edge[i] = graph.firstOutbound(i);
        }
        stack[0] = root;
        last[root] = -1;
        int size = 1;
        position[root] = 0;
        int index = 0;
        while (size > 0) {
            int current = stack[size - 1];
            if (edge[current] == -1) {
                end[current] = index;
                size--;
            } else {
                int next = graph.destination(edge[current]);
                if (next == last[current]) {
                    edge[current] = graph.nextOutbound(edge[current]);
                    continue;
                }
                edge[current] = graph.nextOutbound(edge[current]);
                position[next] = ++index;
                last[next] = current;
                stack[size++] = next;
            }
        }
    }
}
