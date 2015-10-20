package on2015_10.on2015_10_04_Grand_Prix_of_Eurasia_2015._10___Civilization;


import net.egork.collections.Pair;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class Task10 {
    int[] dx = {1, 0, -1, -1, 0, 1};
    int[] dy = {0, 1, 1, 0, -1, -1};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int w = in.readInt();
        int h = in.readInt();
        int n = in.readInt();
        int[] xn = new int[n];
        int[] yn = new int[n];
        int[] p = new int[n];
        IOUtils.readIntArrays(in, xn, yn, p);
        int c = in.readInt();
        int[] xc = new int[c];
        int[] yc = new int[c];
        int[] v = new int[c];
        IOUtils.readIntArrays(in, xc, yc, v);
        int m = in.readInt();
        int[] xm = new int[m];
        int[] ym = new int[m];
        IOUtils.readIntArrays(in, xm, ym);
        Set<IntIntPair> forbidden = new HashSet<>();
        for (int i = 0; i < n; i++) {
            forbidden.add(new IntIntPair(xn[i], yn[i]));
        }
        for (int i = 0; i < m; i++) {
            forbidden.add(new IntIntPair(xm[i], ym[i]));
        }
        Set<IntIntPair>[] reachable = new Set[c];
        for (int i = 0; i < c; i++) {
            reachable[i] = new HashSet<>();
            go(v[i] - 1, xc[i], yc[i], w, h, forbidden, reachable[i]);
        }
        int killed = -1;
        IntIntPair[] destination = new IntIntPair[c];
        int[] shoot = new int[c];
        for (int i = 0; i < (1 << n); i++) {
            Set<IntIntPair> targets = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    for (int k = xn[j] - 2; k <= xn[j] + 2; k++) {
                        for (int l = yn[j] - 2; l <= yn[j] + 2; l++) {
                            if (!MiscUtils.isValidCell(k, l, w, h)) {
                                continue;
                            }
                            IntIntPair cell = new IntIntPair(k, l);
                            if (distance(cell.first, cell.second, xn[j], yn[j]) > 2) {
                                continue;
                            }
                            targets.add(cell);
                        }
                    }
                }
            }
            IntIntPair[] targArr = targets.toArray(new IntIntPair[0]);
            Graph graph = new Graph(c + 2 * targets.size() + n + 2);
            int source = c + 2 * targets.size() + n;
            int sink = source + 1;
            for (int j = 0; j < c; j++) {
                graph.addFlowEdge(source, j, 1);
                for (int k = 0; k < targArr.length; k++) {
                    if (reachable[j].contains(targArr[k])) {
                        graph.addFlowWeightedEdge(j, c + k, targArr[k].first == xc[j] && targArr[k].second == yc[j] ? 0 : 1, 1);
                    }
                }
            }
            for (int j = 0; j < targArr.length; j++) {
                graph.addFlowEdge(c + j, c + targArr.length + j, 1);
                for (int k = 0; k < n; k++) {
                    if ((i >> k & 1) == 1 && distance(targArr[j].first, targArr[j].second, xn[k], yn[k]) <= 2) {
                        graph.addFlowEdge(c + targArr.length + j, c + targArr.length * 2 + k, 1);
                    }
                }
            }
            int need = 0;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    graph.addFlowEdge(c + targArr.length * 2 + j, sink, p[j]);
                    need += p[j];
                }
            }
            Pair<Long, Long> result = MinCostFlow.minCostMaxFlow(graph, source, sink, false);
            if (result.second != need) {
                continue;
            }
            int current = Integer.bitCount(i);
            if (current > killed) {
                killed = current;
                for (int j = 0; j < c; j++) {
                    destination[j] = new IntIntPair(xc[j], yc[j]);
                    shoot[j] = 0;
                    for (int k = graph.firstOutbound(j); k != -1; k = graph.nextOutbound(k)) {
                        if (graph.destination(k) != source && graph.flow(k) > 0) {
                            int id = graph.destination(k) - c;
                            destination[j] = targArr[id];
                            for (int l = graph.firstOutbound(id + c + targArr.length); l != -1; l = graph.nextOutbound(l)) {
                                if (graph.flow(l) > 0) {
                                    shoot[j] = 1 + graph.destination(l) - c - 2 * targArr.length;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        out.printLine(killed);
        for (int i = 0; i < c; i++) {
            out.printLine(destination[i].first, destination[i].second, shoot[i]);
        }
    }

    int distance(int x1, int y1, int x2, int y2) {
        if (x1 <= x2 && y1 <= y2) {
            return x2 - x1 + y2 - y1;
        }
        if (x1 >= x2 && y1 >= y2) {
            return x1 - x2 + y1 - y2;
        }
        return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    private void go(int moves, int x, int y, int w, int h, Set<IntIntPair> forbidden, Set<IntIntPair> reachable) {
        IntIntPair key = new IntIntPair(x, y);
        if (forbidden.contains(key)) {
            return;
        }
        reachable.add(key);
        if (moves == 0) {
            return;
        }
        for (int i = 0; i < 6; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (MiscUtils.isValidCell(nx, ny, w, h)) {
                go(moves - 1, nx, ny, w, h, forbidden, reachable);
            }
        }
    }
}
