package net.egork;

import net.egork.generated.collections.comparator.IntComparator;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Random;

import static java.lang.System.arraycopy;
import static net.egork.misc.ArrayUtils.*;

public class NamePreservingNetwork {
    Random random = new Random(239);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int u = in.readInt();
        if (u == -1) {
            System.exit(0);
        }
        int l = in.readInt();
        out.printLine(u);
        Graph original = new BidirectionalGraph(u);
        int start = u - 3;
        start -= start % 2;
        int half = start / 2;
        if (u == 10 || u == 11 || u == 13) {
            int seed;
            if (u == 10) {
                seed = 239;
            } else if (u == 11) {
                seed = 144;
            } else {
                seed = 239;
            }
            Random r = new Random(seed);
            long[] g = new long[u];
            int[] degree = createArray(u, 4);
            for (int i = 0; i < u * 2; i++) {
                int f;
                int t;
                do {
                    f = r.nextInt(u);
                    t = r.nextInt(u);
                } while (f == t || degree[f] == 0 || degree[t] == 0 || (g[f] >> t & 1) == 1);
                g[f] += 1L << t;
                g[t] += 1L << f;
                degree[f]--;
                degree[t]--;
            }
            for (int i = 0; i < u; i++) {
                for (int j = 0; j < i; j++) {
                    if ((g[i] >> j & 1) == 1) {
                        original.addSimpleEdge(i, j);
                    }
                }
            }
        } else {
            for (int i = 0; i < half; i++) {
                if (i >= 3 || half == 3) {
                    original.addSimpleEdge(i, i + half);
                }
                original.addSimpleEdge(i, (i + 1) % half + half);
                if (half > 3) {
                    original.addSimpleEdge(i, (i + 2) % half + half);
                }
                original.addSimpleEdge(i, (i + half - 1) % half + half);
            }
            if (u % 2 == 1) {
                original.addSimpleEdge(start, start + 1);
                original.addSimpleEdge(start, start + 2);
                original.addSimpleEdge(start + 1, start + 2);
                original.addSimpleEdge(start, 0);
                original.addSimpleEdge(start, 1);
                original.addSimpleEdge(start + 1, half);
                original.addSimpleEdge(start + 1, half + 2);
                original.addSimpleEdge(start + 2, 2);
                original.addSimpleEdge(start + 2, half + 1);
            } else {
                original.addSimpleEdge(start, start + 1);
                original.addSimpleEdge(start + 1, start + 2);
                original.addSimpleEdge(start + 2, start + 3);
                original.addSimpleEdge(start + 3, start);
                original.addSimpleEdge(start, start + 2);
                original.addSimpleEdge(start, 0);
                original.addSimpleEdge(start + 2, 1);
                original.addSimpleEdge(start + 1, half);
                original.addSimpleEdge(start + 1, half + 2);
                original.addSimpleEdge(start + 3, 2);
                original.addSimpleEdge(start + 3, half + 1);
            }
        }
        for (int i = 0; i < original.edgeCount(); i += 2) {
            out.printLine(original.source(i) + 1, original.destination(i) + 1);
        }
        out.flush();
        in.readInt();
        Graph result = new BidirectionalGraph(u);
        int[] p = new int[u];
        for (int i = 0; i < u; i++) {
            int pos = random.nextInt(i + 1);
            p[i] = p[pos];
            p[pos] = i;
        }
        int[] degree = new int[u];
        for (int i = 0; i < 2 * u; i++) {
            int a = in.readInt() - 1;
            int b = in.readInt() - 1;
//            int a = p[original.source(2 * i)];
//            int b = p[original.destination(2 * i)];
            degree[a]++;
            degree[b]++;
            result.addSimpleEdge(a, b);
        }
        int[] typeOriginal = getTypes(original);
        int[] typeResult = getTypes(result);
        int[] answer = new int[u];
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < u; j++) {
                if (typeOriginal[i] == typeResult[j]) {
                    answer[i] = j + 1;
                    break;
                }
            }
        }
        out.printLine(answer);
        out.flush();
//        for (int i = 0; i < u; i++) {
//            if (p[i] + 1 != answer[i]) {
//                throw new RuntimeException();
//            }
//        }
    }

    private int[] getTypes(Graph graph) {
        int n = graph.vertexCount();
        long[] mask = new long[n];
        for (int i = 0; i < graph.edgeCount(); i++) {
            mask[graph.source(i)] += 1L << graph.destination(i);
        }
        int[] result = new int[n];
        int[][] vertices = new int[n][4];
        int[][] connected = new int[n][4];
        int[][] degree = new int[n][4];
        int nextType = -1;
        int[] order = createOrder(4);
        while (nextType != n - 1) {
            int was = nextType;
            boolean updated = false;
            for (int i = 0; i < n; i++) {
                int at = 0;
                for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                    vertices[i][at++] = graph.destination(j);
                }
                for (int j = 0; j < 4; j++) {
                    connected[i][j] = result[vertices[i][j]];
                    degree[i][j] = 0;
                    for (int k = 0; k < 4; k++) {
                        if ((mask[vertices[i][j]] >> vertices[i][k] & 1) == 1) {
                            degree[i][j]++;
                        }
                    }
                }
                int finalI = i;
                sort(order, (a, b) -> connected[finalI][a] != connected[finalI][b] ?
                        connected[finalI][a] - connected[finalI][b] :
                        degree[finalI][a] - degree[finalI][b]);
                orderBy(reversePermutation(order), connected[i], degree[i]);
            }
            int[] oo = createOrder(n);
            IntComparator comparator = (a, b) -> {
                if (result[a] != result[b]) {
                    return result[a] - result[b];
                }
                for (int i = 0; i < 4; i++) {
                    if (connected[a][i] != connected[b][i]) {
                        return connected[a][i] - connected[b][i];
                    }
                }
                for (int i = 0; i < 4; i++) {
                    if (degree[a][i] != degree[b][i]) {
                        return degree[a][i] - degree[b][i];
                    }
                }
                return 0;
            };
            sort(oo, comparator);
            nextType = -1;
            int[] newType = new int[n];
            for (int i = 0; i < n; i++) {
                if (i == 0 || comparator.compare(oo[i - 1], oo[i]) != 0) {
                    nextType++;
                }
                newType[oo[i]] = nextType;
            }

            arraycopy(newType, 0, result, 0, n);

            if (was == nextType) {
                throw new RuntimeException();
            }
        }
        return result;
    }
}
