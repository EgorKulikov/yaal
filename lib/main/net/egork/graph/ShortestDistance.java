package net.egork.graph;

import net.egork.collections.Pair;
import net.egork.collections.intcollection.Heap;
import net.egork.generated.collections.comparator.IntComparator;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class ShortestDistance {
    public static Pair<long[], int[]> dijkstraAlgorithm(Graph graph, int source) {
        int vertexCount = graph.vertexCount();
        final long[] distance = new long[vertexCount];
        int[] last = new int[vertexCount];
        Arrays.fill(distance, Long.MAX_VALUE);
        Arrays.fill(last, -1);
        distance[source] = 0;
        if (graph.isSparse()) {
            Heap heap = new Heap(vertexCount, new IntComparator() {
                public int compare(int first, int second) {
                    return IntegerUtils.longCompare(distance[first], distance[second]);
                }
            }, vertexCount);
            heap.add(source);
            while (!heap.isEmpty()) {
                int current = heap.poll();
                int id = graph.firstOutbound(current);
                while (id != -1) {
                    int next = graph.destination(id);
                    long total = graph.weight(id) + distance[current];
                    if (distance[next] > total) {
                        distance[next] = total;
                        if (heap.getIndex(next) == -1) {
                            heap.add(next);
                        } else {
                            heap.shiftUp(heap.getIndex(next));
                        }
                        last[next] = id;
                    }
                    id = graph.nextOutbound(id);
                }
            }
        } else {
            boolean[] visited = new boolean[vertexCount];
            for (int i = 0; i < vertexCount; i++) {
                int index = -1;
                long length = Long.MAX_VALUE;
                for (int j = 0; j < vertexCount; j++) {
                    if (!visited[j] && distance[j] < length) {
                        length = distance[j];
                        index = j;
                    }
                }
                if (index == -1) {
                    break;
                }
                visited[index] = true;
                int id = graph.firstOutbound(index);
                while (id != -1) {
                    int next = graph.destination(id);
                    if (!visited[next]) {
                        long total = graph.weight(id) + length;
                        if (distance[next] > total) {
                            distance[next] = total;
                            last[next] = id;
                        }
                    }
                    id = graph.nextOutbound(id);
                }
            }
        }
        return Pair.makePair(distance, last);
    }

    public static Pair<Long, IntList> dijkstraAlgorithm(Graph graph, int source, int destination) {
        if (source == destination) {
            return Pair.makePair(0L, new IntArrayList());
        }
        Pair<long[], int[]> result = dijkstraAlgorithm(graph, source);
        if (result.second[destination] == -1) {
            return null;
        }
        IntList path = new IntArrayList();
        int id = destination;
        while (id != source) {
            path.add(result.second[id]);
            id = graph.source(result.second[id]);
        }
        path.inPlaceReverse();
        return Pair.makePair(result.first[destination], path);
    }
}
