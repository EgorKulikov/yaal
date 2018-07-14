package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;

import static java.lang.System.arraycopy;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.ArrayUtils.minPosition;

public class ColoringEdgesDiv1 {
    public int[] findColoring(int n, int[] x, int[] y) {
        Graph graph = BidirectionalGraph.createGraph(n, x, y);
        IndependentSetSystem white = new RecursiveIndependentSetSystem(n);
        IndependentSetSystem black = new RecursiveIndependentSetSystem(n);
        int[] order = new int[n];
        int[] answer = createArray(x.length, -1);
        boolean[] removed = new boolean[n];
        int[] degree = createArray(n, 3);
        for (int i = 0; i < n; i++) {
            int at = minPosition(degree);
            degree[at] = 4;
            removed[at] = true;
            order[i] = at;
            for (int j = graph.firstOutbound(at); j != -1; j = graph.nextOutbound(j)) {
                if (!removed[graph.destination(j)]) {
                    degree[graph.destination(j)]--;
                }
            }
        }
        int[] uncolored = new int[3];
        int[] still = new int[3];
        for (int i = n - 1; i >= 0; i--) {
            int at = order[i];
            removed[at] = false;
            degree[at] = 0;
            int qty = 0;
            for (int j = graph.firstOutbound(at); j != -1; j = graph.nextOutbound(j)) {
                int to = graph.destination(j);
                if (removed[to]) {
                    continue;
                }
                degree[at]++;
                if (degree[to] == 2) {
                    int e1 = -1;
                    int e2 = -1;
                    for (int k = graph.firstOutbound(to); k != -1; k = graph.nextOutbound(k)) {
                        if (graph.destination(k) != at) {
                            if (e1 == -1) {
                                e1 = k >> 1;
                            } else {
                                e2 = k >> 1;
                            }
                        }
                    }
                    if (answer[e1] == answer[e2]) {
                        answer[j >> 1] = 1 - answer[e1];
                        if (answer[j >> 1] == 0) {
                            white.join(at, to);
                        } else {
                            black.join(at, to);
                        }
                    } else {
                        uncolored[qty++] = j;
                    }
                } else {
                    uncolored[qty++] = j;
                }
                degree[to]++;
            }
            while (true) {
                int stillQty = 0;
                boolean updated = false;
                for (int j = 0; j < qty; j++) {
                    int to = graph.destination(uncolored[j]);
                    if (white.get(at) == white.get(to)) {
                        answer[uncolored[j] >> 1] = 1;
                        black.join(at, to);
                        updated = true;
                    } else if (black.get(at) == black.get(to)) {
                        answer[uncolored[j] >> 1] = 0;
                        white.join(at, to);
                        updated = true;
                    } else {
                        still[stillQty++] = uncolored[j];
                    }
                }
                if (!updated) {
                    break;
                }
                qty = stillQty;
                arraycopy(still, 0, uncolored, 0, qty);
            }
            if (qty <= 2) {
                for (int j = 0; j < qty; j++) {
                    int to = graph.destination(uncolored[j]);
                    answer[uncolored[j] >> 1] = j;
                    if (j == 0) {
                        white.join(at, to);
                    } else {
                        black.join(at, to);
                    }
                }
            } else {
                boolean ok = false;
                for (int j = 0; j < 3 && !ok; j++) {
                    int a = graph.destination(uncolored[j]);
                    for (int k = 0; k < j && !ok; k++) {
                        int b = graph.destination(uncolored[k]);
                        if (white.get(a) != white.get(b)) {
                            answer[uncolored[j] >> 1] = 0;
                            answer[uncolored[k] >> 1] = 0;
                            white.join(a, at);
                            white.join(b, at);
                            answer[uncolored[3 - j - k] >> 1] = 1;
                            black.join(at, graph.destination(uncolored[3 - j - k]));
                            ok = true;
                        } else if (black.get(a) != black.get(b)) {
                            answer[uncolored[j] >> 1] = 1;
                            answer[uncolored[k] >> 1] = 1;
                            black.join(a, at);
                            black.join(b, at);
                            answer[uncolored[3 - j - k] >> 1] = 0;
                            white.join(at, graph.destination(uncolored[3 - j - k]));
                            ok = true;
                        }
                    }
                }
            }
        }
        return answer;
    }
}
