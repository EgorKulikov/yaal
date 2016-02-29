package on2016_02.on2016_02_07_Grand_Prix_of_Saratov.TaskK;



import net.egork.collections.Pair;
import net.egork.collections.intervaltree.ArrayBasedIntervalTree;
import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.ReadOnlyIntervalTree;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import sun.security.provider.certpath.Vertex;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskK {
    Graph graph;
//    int[] sides;
//    int[] secSides;
//    int[] mx;
    int left;
    int right;
    int best;
    int dist;
    int k;
//    IntervalTree tree;
    Vertex[] vertices;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        k = in.readInt();
        int[] u = new int[n - 1];
        int[] v = new int[n - 1];
        readIntArrays(in, u, v);
        graph = BidirectionalGraph.createWeightedGraph(n, u, v, createArray(n - 1, 1L));
        vertices = new Vertex[n];
        int diameter = 0;
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex();
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                int next = graph.destination(j);
                IntIntPair res = dfs(next, i);
                vertices[i].addMax(res.second, next);
                vertices[i].addPath(res.first + 1, next);
            }
            diameter = Math.max(diameter, vertices[i].path[0] + vertices[i].path[1]);
        }
        best = diameter;
        for (int i = 0; i < n; i++) {
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                int next = graph.destination(j);
                solve(next, i, i, 1, vertices[i].getPath(next), vertices[i].getMax(next));
            }
        }
//        int oneEnd = maxPosition(ShortestDistance.dijkstraAlgorithm(graph, 0).first);
//        Pair<long[], int[]> shortest = ShortestDistance.dijkstraAlgorithm(graph, oneEnd);
//        long[] dist = shortest.first;
//        int[] last = shortest.second;
//        int twoEnd = maxPosition(dist);
//        long diameter = dist[twoEnd];
//        IntList path = new IntArrayList();
//        int current = twoEnd;
//        forbidden = new boolean[2 * n - 2];
//        while (current != oneEnd) {
//            path.add(current);
//            forbidden[last[current]] = true;
//            forbidden[last[current] ^ 1] = true;
//            current = graph.source(last[current]);
//        }
//        path.add(oneEnd);
//        sides = new int[path.size()];
//        secSides = new int[sides.length];
//        mx = new int[sides.length];
//        for (int i = 0; i < sides.length; i++) {
//            for (int j = graph.firstOutbound(path.get(i)); j != -1; j = graph.nextOutbound(j)) {
//                if (!forbidden[j]) {
//                    IntIntPair dfs = dfs(graph.destination(j), path.get(i));
//                    int cur = 1 + dfs.first;
//                    mx[i] = Math.max(mx[i], dfs.second);
//                    if (cur > sides[i]) {
//                        sides[i] = cur;
//                    } else {
//                        secSides[i] = Math.max(secSides[i], cur);
//                    }
//                }
//            }
//        }
//        int[] special = new int[sides.length];
//        for (int i = special.length - 1; i >= 0; i--) {
//            special[i] = sides[i] + special.length - 1 - i;
//            special[i] = Math.max(special[i], mx[i]);
//        }
//        tree = new ReadOnlyIntervalTree(asLong(special)) {
//            @Override
//            protected long joinValue(long left, long right) {
//                return Math.max(left, right);
//            }
//            @Override
//            protected long neutralValue() {
//                return 0;
//            }
//        };
//        left = path.size() / 2;
//        right = path.size() / 2;
//        best = (int) diameter;
//        for (int i = 0; i < path.size(); i++) {
//            check(i);
//        }
        out.printLine(best);
        out.printLine(dist);
        if (left != right) {
            out.printLine(left, right);
        }
    }

    private void solve(int current, int last, int end, int length, int maxSingle, int maxPath) {
        if (length > k) {
            return;
        }
        int diameter = max(maxPath, max(vertices[current].getMax(last), vertices[current].getPath(last) + maxSingle));
        if (diameter < best || diameter == best && dist > length) {
            best = diameter;
            dist = length;
            left = end;
            right = current;
        }
        for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
            int next = graph.destination(j);
            if (next == last) {
                continue;
            }
            solve(next, current, end, length + 1, max(maxSingle, vertices[current].getPath(last, next)),
                    max(maxPath, max(vertices[current].getMax(last, next), vertices[current].getPath(last, next) +
                            maxSingle)));
        }
    }

    /*private void check(int start) {
        int toLeft = start;
        int current = 0;
        for (int i = 0; i < start; i++) {
            current = Math.max(current, i + sides[i]);
            current = Math.max(current, mx[i]);
        }
        int max = 0;
        int secondMax = 0;
        for (int j = start; j <= start + k && j < sides.length; j++) {
            if (sides[j] > max) {
                max = sides[j];
            } else {
                secondMax = Math.max(secondMax, sides[j]);
            }
            secondMax = Math.max(secondMax, secSides[j]);
            current = Math.max(current, max + secondMax);
            current = Math.max(current, max + toLeft);
            current = Math.max(current, mx[j]);
            int toRight = sides.length - 1 - j;
            int maxRight = (int) tree.query(j + 1, sides.length - 1);
            int candidate = max(current, max(maxRight, toRight + max(max, toLeft)));
            if (candidate < best || candidate == best && right - left > j - start) {
                best = candidate;
                left = start;
                right = j;
            }
        }
    }*/

    static class Vertex {
        int[] path = new int[4];
        int[] pathBy = createArray(4, -1);
        int[] max = new int[3];
        int[] maxBy = createArray(3, -1);

        void addPath(int length, int to) {
            for (int i = 0; i < 4; i++) {
                if (length > path[i]) {
                    for (int j = 3; j > i; j--) {
                        path[j] = path[j - 1];
                        pathBy[j] = pathBy[j - 1];
                    }
                    path[i] = length;
                    pathBy[i] = to;
                    return;
                }
            }
        }

        void addMax(int length, int to) {
            for (int i = 0; i < 3; i++) {
                if (length > max[i]) {
                    for (int j = 2; j > i; j--) {
                        max[j] = max[j - 1];
                        maxBy[j] = maxBy[j - 1];
                    }
                    max[i] = length;
                    maxBy[i] = to;
                    return;
                }
            }
        }

        int getPath(int f) {
            for (int i = 0; i < 4; i++) {
                if (pathBy[i] != f) {
                    return path[i];
                }
            }
            throw new RuntimeException();
        }

        int getPath(int f0, int f1) {
            for (int i = 0; i < 4; i++) {
                if (pathBy[i] != f0 && pathBy[i] != f1) {
                    return path[i];
                }
            }
            throw new RuntimeException();
        }

        int getMax(int f) {
            for (int i = 0; i < 3; i++) {
                if (maxBy[i] != f) {
                    return max(max[i], getSpecial(f));
                }
            }
            throw new RuntimeException();
        }

        int getSpecial(int f) {
            int taken = 0;
            int total = 0;
            for (int i = 0; i < 4 && taken < 2; i++) {
                if (pathBy[i] != f) {
                    total += path[i];
                    taken++;
                }
            }
            return total;
        }

        int getSpecial(int f, int f1) {
            int taken = 0;
            int total = 0;
            for (int i = 0; i < 4 && taken < 2; i++) {
                if (pathBy[i] != f && pathBy[i] != f1) {
                    total += path[i];
                    taken++;
                }
            }
            return total;
        }

        int getMax(int f0, int f1) {
            for (int i = 0; i < 3; i++) {
                if (maxBy[i] != f0 && maxBy[i] != f1) {
                    return max(max[i], getSpecial(f0, f1));
                }
            }
            throw new RuntimeException();
        }
    }

    private IntIntPair dfs(int current, int last) {
        int max = 0;
        int second = 0;
        int path = 0;
        for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (last == next) {
                continue;
            }
            IntIntPair dfs = dfs(next, current);
            int candidate = dfs.first + 1;
            if (max < candidate) {
                second = max;
                max = candidate;
            } else {
                second = Math.max(second, candidate);
            }
            path = Math.max(path, dfs.second);
        }
        return new IntIntPair(max, max(max + second, path));
    }
}
