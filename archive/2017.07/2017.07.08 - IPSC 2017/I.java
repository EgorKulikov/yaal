package net.egork;



import net.egork.collections.Pair;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.graph.StronglyConnectedComponents;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.fill;
import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class I {
    int[] pathId;
    int[] path;
    int length;
    boolean[] isAnswer;
    int maxId;
    boolean[] visited;
    Graph graph;
    int[] max;
    int start;
    int destination;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        System.err.println(testNumber);
        int n = in.readInt();
        int m = in.readInt();
        int orn = n;
        int[] a = new int[m];
        int[] b = new int[m];
        readIntArrays(in, a, b);
        decreaseByOne(a, b);
        graph = Graph.createGraph(n, a, b);
        Pair<int[], Graph> kosaraju = StronglyConnectedComponents.kosaraju(graph);
//        graph = kosaraju.second;
        graph = new Graph(kosaraju.first.length);
        Set<IntIntPair> was = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int from = kosaraju.first[a[i]];
            int to = kosaraju.first[b[i]];
            if (from != to) {
                IntIntPair key = new IntIntPair(from, to);
                if (!was.contains(key)) {
                    was.add(key);
                    graph.addSimpleEdge(from, to);
                }
            }
        }
        int[] representative = new int[graph.vertexCount()];
        int[] qty = new int[graph.vertexCount()];
        for (int i = 0; i < n; i++) {
            qty[kosaraju.first[i]]++;
            representative[kosaraju.first[i]] = i + 1;
        }
        for (int i = 0; i < m; i++) {
            if (a[i] == b[i]) {
                qty[kosaraju.first[a[i]]]++;
            }
        }
        destination = kosaraju.first[n - 1];
        int origin = kosaraju.first[0];
        n = graph.vertexCount();
        pathId = createArray(n, -1);
        isAnswer = new boolean[n];
        path = new int[n];
        visited = new boolean[n];
        length = 0;
        if (!dfs(origin)) {
            out.printLine(0);
            out.printLine();
            return;
        }
        fill(isAnswer, true);
        max = createArray(n, -1);
        for (int j = 0; j < length; j++) {
            maxId = j;
            for (int i = graph.firstOutbound(path[j]); i != -1; i = graph.nextOutbound(i)) {
                dfs2(graph.destination(i));
            }
            if (maxId != j) {
                fill(isAnswer, j + 1, maxId, false);
            }
        }
        IntList answer = new IntArrayList();
        for (int i = 0; i < length; i++) {
            if (isAnswer[i]) {
                if (qty[path[i]] == 1) {
                    answer.add(representative[path[i]]);
                }
            }
        }
        if (orn <= 10000) {
            IntList altAnswer = new IntArrayList();
            for (int i = 0; i < orn; i++) {
                Graph graph = new Graph(orn + 1);
                for (int j = 0; j < m; j++) {
                    if (b[j] == i) {
                        graph.addWeightedEdge(a[j], orn, 1);
                    } else {
                        graph.addWeightedEdge(a[j], b[j], 1);
                    }
                }
                if (ShortestDistance.dijkstraAlgorithm(graph, i, orn) != null) {
                    continue;
                }
                Graph g = new Graph(orn);
                for (int j = 0; j < m; j++) {
                    if (a[j] != i && b[j] != i) {
                        g.addWeightedEdge(a[j], b[j], 1);
                    }
                }
                if (ShortestDistance.dijkstraAlgorithm(g, 0, orn - 1) == null) {
//                throw new RuntimeException();
                    altAnswer.add(i + 1);
                }
            }
            Graph graph = new Graph(orn);
            for (int i = 0; i < m; i++) {
                graph.addWeightedEdge(a[i], b[i], 1);
            }
            long[] dst = ShortestDistance.dijkstraAlgorithm(graph, 0).first;
            altAnswer.sort((x, y) -> Long.compare(dst[x - 1], dst[y - 1]));
            if (!answer.equals(altAnswer)) {
                System.err.println("SAD");
                throw new RuntimeException();
            }
        }
        out.printLine(answer.size());
        out.printLine(answer);
    }

    private void dfs2(int current) {
        if (max[current] == -1) {
            if (pathId[current] != -1) {
                max[current] = pathId[current];
            } else {
                max[current] = -2;
                for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
                    int to = graph.destination(i);
                    dfs2(to);
                    if (max[to] != -2) {
                        if (max[current] == -2) {
                            max[current] = max[to];
                        } else {
                            max[current] = Math.max(max[current], max[to]);
                        }
                    }
                }
            }
        }
        if (max[current] == -2) {
            return;
        }
        maxId = Math.max(maxId, max[current]);
    }

    private boolean dfs(int current) {
        if (visited[current]) {
            return false;
        }
        visited[current] = true;
        path[length] = current;
        pathId[current] = length++;
        if (current == destination) {
            return true;
        }
        for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
            if (dfs(graph.destination(i))) {
                return true;
            }
        }
        pathId[current] = -1;
        length--;
        return false;
    }
}
