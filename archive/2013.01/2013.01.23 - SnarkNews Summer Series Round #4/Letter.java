package net.egork;

import net.egork.collections.set.EHashSet;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.lang.Long;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class Letter {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        int[] from = new int[edgeCount];
        int[] to = new int[edgeCount];
        int[] sum = new int[edgeCount];
        IOUtils.readIntArrays(in, from, to, sum);
        MiscUtils.decreaseByOne(from, to);
        int[][] graph = GraphUtils.buildGraph(count, from, to);
        int[] type = new int[count];
        long[] delta = new long[count];
        int[] queue = new int[count];
        boolean[] visited = new boolean[count];
        visited[0] = true;
        int size = 1;
        type[0] = 1;
        for (int i = 0; i < count; i++) {
            int current = queue[i];
            for (int j : graph[current]) {
                int other = GraphUtils.otherVertex(current, from[j], to[j]);
                if (!visited[other]) {
                    visited[other] = true;
                    type[other] = -type[current];
                    delta[other] = sum[j] - delta[current];
                    queue[size++] = other;
                    visited[other] = true;
                }
            }
        }
        long singleAnswer = -1;
        for (int i = 0; i < edgeCount; i++) {
            if (type[from[i]] != type[to[i]] && sum[i] != delta[from[i]] + delta[to[i]]) {
                out.printLine(0);
                return;
            }
            if (type[from[i]] == 1 && type[to[i]] == 1) {
                long totalDelta = delta[from[i]] + delta[to[i]];
                if (totalDelta >= sum[i] || ((sum[i] - totalDelta) & 1) != 0) {
                    out.printLine(0);
                    return;
                }
                long current = (sum[i] - totalDelta) >> 1;
                if (current > count || singleAnswer != -1 && singleAnswer != current) {
                    out.printLine(0);
                    return;
                }
                singleAnswer = current;
            } else if (type[from[i]] == -1 && type[to[i]] == -1) {
                long totalDelta = delta[from[i]] + delta[to[i]];
                if (totalDelta <= sum[i] || ((totalDelta - sum[i]) & 1) != 0) {
                    out.printLine(0);
                    return;
                }
                long current = (totalDelta - sum[i]) >> 1;
                if (current > count || singleAnswer != -1 && singleAnswer != current) {
                    out.printLine(0);
                    return;
                }
                singleAnswer = current;
            }
        }
        if (singleAnswer != -1) {
            long[] result = new long[count];
            Set<Long> all = new EHashSet<Long>();
            for (int i = 0; i < count; i++) {
                result[i] = type[i] * singleAnswer + delta[i];
                if (all.contains(result[i])) {
                    out.printLine(0);
                    return;
                }
                all.add(result[i]);
                if (result[i] < 1 || result[i] > count) {
                    out.printLine(0);
                    return;
                }
            }
            out.printLine(1);
            out.printLine(result);
            return;
        }
        NavigableSet<Long> plus = new TreeSet<Long>();
        NavigableSet<Long> minus = new TreeSet<Long>();
        for (int i = 0; i < count; i++) {
            if (type[i] == 1) {
                if (plus.contains(delta[i])) {
                    out.printLine(0);
                    return;
                }
                plus.add(delta[i]);
            } else {
                if (minus.contains(delta[i])) {
                    out.printLine(0);
                    return;
                }
                minus.add(delta[i]);
            }
        }
        int total = 0;
        long[] sample = null;
        if (!plus.isEmpty()) {
            long answer = 1 - plus.first();
            Set<Long> all = new EHashSet<Long>();
            long[] result = new long[count];
            boolean good = true;
            for (int i = 0; i < count; i++) {
                result[i] = type[i] * answer + delta[i];
                if (all.contains(result[i])) {
                    good = false;
                }
                all.add(result[i]);
                if (result[i] < 1 || result[i] > count) {
                    good = false;
                }
            }
            if (good) {
                total++;
                sample = result;
            }
        }
        if (!minus.isEmpty()) {
            long answer = minus.first() - 1;
            Set<Long> all = new EHashSet<Long>();
            long[] result = new long[count];
            boolean good = true;
            for (int i = 0; i < count; i++) {
                result[i] = type[i] * answer + delta[i];
                if (all.contains(result[i])) {
                    good = false;
                }
                all.add(result[i]);
                if (result[i] < 1 || result[i] > count) {
                    good = false;
                }
            }
            if (good) {
                total++;
                sample = result;
            }
        }
        out.printLine(total);
        if (sample != null)
            out.printLine(sample);
    }
}
