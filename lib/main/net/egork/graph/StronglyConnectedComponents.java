package net.egork.graph;

import net.egork.collections.Pair;

import java.util.Arrays;

import static net.egork.misc.ArrayUtils.createArray;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public class StronglyConnectedComponents {
    private final Graph graph;
    private int[] order;
    private boolean[] visited;
    private int index = 0;
    private int vertexCount;
    private int[] condensed;
    private int[] next;
    private int key;
    private int[] queue;
    private int queueSize;

    private StronglyConnectedComponents(Graph graph) {
        this.graph = graph;
        vertexCount = graph.vertexCount();
        order = new int[vertexCount];
        visited = new boolean[vertexCount];
        condensed = new int[vertexCount];
    }

    public static Pair<int[], Graph> kosaraju(Graph graph) {
        return new StronglyConnectedComponents(graph).kosaraju();
    }

    private Pair<int[], Graph> kosaraju() {
        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                firstDFS(i);
            }
        }
        Arrays.fill(visited, false);
        Graph result = new Graph(0);
        index = 0;
        next = createArray(graph.vertexCount(), -1);
        queue = new int[graph.vertexCount()];
        for (int i = vertexCount - 1; i >= 0; i--) {
            if (!visited[order[i]]) {
                key = i;
                queueSize = 0;
                secondDFS(order[i]);
                result.addVertices(1);
                for (int j = 0; j < queueSize; j++) {
                    result.addSimpleEdge(queue[j], index);
                }
                index++;
            }
        }
        return Pair.makePair(condensed, result);
    }

    private void secondDFS(int vertexID) {
        if (visited[vertexID]) {
            if (condensed[vertexID] != index) {
                if (next[condensed[vertexID]] != key) {
                    next[condensed[vertexID]] = key;
                    queue[queueSize++] = condensed[vertexID];
                }
            }
            return;
        }
        condensed[vertexID] = index;
        visited[vertexID] = true;
        int edgeID = graph.firstInbound(vertexID);
        while (edgeID != -1) {
            secondDFS(graph.source(edgeID));
            edgeID = graph.nextInbound(edgeID);
        }
    }

    private void firstDFS(int vertexID) {
        if (visited[vertexID]) {
            return;
        }
        visited[vertexID] = true;
        int edgeID = graph.firstOutbound(vertexID);
        while (edgeID != -1) {
            firstDFS(graph.destination(edgeID));
            edgeID = graph.nextOutbound(edgeID);
        }
        order[index++] = vertexID;
    }
}
