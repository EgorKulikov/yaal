package net.egork;

import net.egork.collections.intervaltree.ReadOnlyIntervalTree;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.max;
import static java.util.Arrays.copyOfRange;
import static net.egork.io.InputReader.readIntArrays;
import static net.egork.misc.ArrayUtils.*;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class CutTheEdges {
    Graph graph;
    int[] onStack;
    int[] depth;
    int[] cycle;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = new int[n];
        int[] b = new int[n];
        in.readIntArrays(a, b);
        decreaseByOne(a, b);
        graph = BidirectionalGraph.createGraph(n, a, b);
        int[] answer = createArray(n, -1);
        onStack = new int[n];
        depth = createArray(n, -1);
        go(0, -1, 0);
        int minDiameter = 0;
        for (int i = 0; i < cycle.length; i++) {
            graph.removeEdge(cycle[i]);
            graph.removeEdge(cycle[i] ^ 1);
        }
        for (int i = 0; i < cycle.length; i++) {
            minDiameter = max(minDiameter, getDiameter(graph.source(cycle[i])));
        }
        int[] side = new int[cycle.length];
        for (int i = 0; i < cycle.length; i++) {
            side[i] = getSide(graph.source(cycle[i]), -1).first;
        }
        side = concatenate(side, side);
        int[] lArr = new int[side.length];
        for (int i = 0; i < side.length; i++) {
            lArr[i] = side[i] - i;
        }
        int[] rArr = new int[side.length];
        for (int i = 0; i < side.length; i++) {
            rArr[i] = side[i] + i;
        }
        ReadOnlyIntervalTree left = new ReadOnlyIntervalTree(asLong(lArr)) {
            @Override
            protected long neutralValue() {
                return Integer.MIN_VALUE;
            }

            @Override
            protected long joinValue(long left, long right) {
                return max(left, right);
            }
        };
        ReadOnlyIntervalTree right = new ReadOnlyIntervalTree(asLong(rArr)) {
            @Override
            protected long neutralValue() {
                return Integer.MIN_VALUE;
            }

            @Override
            protected long joinValue(long left, long right) {
                return max(left, right);
            }
        };
        for (int i = 0; i < cycle.length; i++) {
            int lResult = (int) left.query(i + 1, i + cycle.length);
            int rResult = (int) right.query(i + 1, i + cycle.length);
            int at = rResult - lResult;
            if ((at & 1) == 0) {
                at >>= 1;
                if (at >= i + 1 && at <= i + cycle.length && lArr[at] == lResult && rArr[at] == rResult) {
                    int nrResult = (int) max(right.query(i + 1, at - 1), right.query(at + 1, i + cycle.length));
                    int nlResult = (int) max(left.query(i + 1, at - 1), left.query(at + 1, i + cycle.length));
                    if (lResult + nrResult > nlResult + rResult) {
                        rResult = nrResult;
                    } else {
                        lResult = nlResult;
                    }
                }
            }
            answer[cycle[i] >> 1] = max(minDiameter, lResult + rResult);
        }
        for (int i : answer) {
            out.printLine(i);
        }
    }

    private int getDiameter(int vertex) {
        IntIntPair call = getSide(vertex, -1);
        return getSide(call.second, -1).first;
    }

    private IntIntPair getSide(int vertex, int last) {
        int result = 0;
        int at = vertex;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            IntIntPair call = getSide(next, vertex);
            if (call.first >= result) {
                result = call.first + 1;
                at = call.second;
            }
        }
        return new IntIntPair(result, at);
    }

    private boolean go(int vertex, int last, int cDepth) {
        if (depth[vertex] != -1) {
            cycle = copyOfRange(onStack, depth[vertex], cDepth);
            return true;
        }
        depth[vertex] = cDepth;
        for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
            int next = graph.destination(i);
            if (next == last) {
                continue;
            }
            onStack[cDepth] = i;
            if (go(next, vertex, cDepth + 1)) {
                return true;
            }
        }
        return false;
    }
}
