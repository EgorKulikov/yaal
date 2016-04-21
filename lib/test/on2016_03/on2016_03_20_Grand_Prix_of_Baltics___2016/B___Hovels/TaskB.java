package on2016_03.on2016_03_20_Grand_Prix_of_Baltics___2016.B___Hovels;



import net.egork.collections.intcollection.Range;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.fill;
import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.MiscUtils.DX4;
import static net.egork.misc.MiscUtils.DY4;

public class TaskB {
    public static final int SIZE = 40;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] hx = new int[3];
        int[] hy = new int[3];
        readIntArrays(in, hx, hy);
        int[] sx = new int[3];
        int[] sy = new int[3];
        readIntArrays(in, sx, sy);
        for (int i = 0; i < 3; i++) {
            hx[i] *= 5;
            hy[i] *= 5;
            sx[i] *= 5;
            sy[i] *= 5;
        }
        List<IntIntPair>[] paths = new List[9];
        int[] order = Range.range(4).toArray();
        Random random = new Random(239);
        visited = new boolean[SIZE * SIZE * 2 + 2];
        while (true) {
            fill(paths, null);
            Graph graph = new Graph(SIZE * SIZE * 2 + 2);
            int source = graph.vertexCount() - 2;
            int sink = source + 1;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    boolean good = true;
                    int inVert = i * SIZE + j;
                    int outVert = inVert + SIZE * SIZE;
                    for (int k = 0; k < 3; k++) {
                        if (i == hx[k] && j == hy[k]) {
                            good = false;
                            graph.addFlowEdge(source, outVert, 3);
                        }
                    }
                    for (int k = 0; k < 3; k++) {
                        if (i == sx[k] && j == sy[k]) {
                            good = false;
                            graph.addFlowEdge(inVert, sink, 3);
                        }
                    }
                    if (good) {
                        graph.addFlowEdge(inVert, outVert, 1);
                    }
                    shuffle(order, random);
                    for (int k : order) {
                        int ni = i + DX4[k];
                        int nj = j + DY4[k];
                        if (ni < 0) {
                            ni += SIZE;
                        }
                        if (ni >= SIZE) {
                            ni -= SIZE;
                        }
                        if (nj < 0) {
                            nj += SIZE;
                        }
                        if (nj >= SIZE) {
                            nj -= SIZE;
                        }
                        graph.addFlowEdge(outVert, ni * SIZE + nj, 1);
                    }
                }
            }
            if (flow(graph, source, sink) != 9) {
                throw new RuntimeException();
            }
            for (int i = 0; i < 3; i++) {
                for (int j = graph.firstOutbound(hx[i] * SIZE + hy[i] + SIZE * SIZE); j != -1;
                     j = graph.nextOutbound(j)) {
                    if ((j & 1) == 0) {
                        continue;
                    }
                    if (graph.flow(j) > 0) {
                        List<IntIntPair> path = new ArrayList<>();
//                    paths[at++] = path;
                        path.add(new IntIntPair(hx[i], hy[i]));
                        int to = graph.destination(j);
                        int cx = to / SIZE;
                        int cy = to % SIZE;
                        path.add(new IntIntPair(cx, cy));
                        while (true) {
                            boolean good = false;
                            for (int k = 0; k < 3; k++) {
                                if (cx == sx[k] && cy == sy[k]) {
                                    paths[3 * i + k] = path;
                                    good = true;
                                    break;
                                }
                            }
                            if (good) {
                                break;
                            }
                            for (int k = graph.firstOutbound(cx * SIZE + cy + SIZE * SIZE); k != -1;
                                 k = graph.nextOutbound(k)) {
                                if ((k & 1) == 0) {
                                    continue;
                                }
                                if (graph.flow(k) > 0) {
                                    to = graph.destination(k);
                                    cx = to / SIZE;
                                    cy = to % SIZE;
                                    break;
                                }
                            }
                            path.add(new IntIntPair(cx, cy));
                        }
                    }
                }
            }
            boolean good = true;
            for (int i = 0; i < 9; i++) {
                if (paths[i] == null) {
                    good = false;
                    break;
                }
            }
            if (good) {
                break;
            }
        }
        out.printLine(5);
        for (List<IntIntPair> path : paths) {
            out.print(path.size());
            for (IntIntPair pair : path) {
                out.print("", pair.first, pair.second);
            }
            out.printLine();
        }
    }

    boolean[] visited;

    private int flow(Graph graph, int source, int sink) {
        for (int i = 0; i < 9; i++) {
            fill(visited, false);
            if (!dfs(graph, source, sink)) {
                return i;
            }
        }
        return 9;
    }

    private boolean dfs(Graph graph, int source, int sink) {
        if (visited[source]) {
            return false;
        }
        visited[source] = true;
        if (source == sink) {
            return true;
        }
        for (int i = graph.firstOutbound(source); i != -1; i = graph.nextOutbound(i)) {
            if (graph.capacity(i) != 0 && dfs(graph, graph.destination(i), sink)) {
                graph.pushFlow(i, 1);
                return true;
            }
        }
        return false;
    }

    private void shuffle(int[] order, Random random) {
        for (int i = 1; i < 4; i++) {
            int id = random.nextInt(i + 1);
            int temp = order[i];
            order[i] = order[id];
            order[id] = temp;
        }
    }
}
