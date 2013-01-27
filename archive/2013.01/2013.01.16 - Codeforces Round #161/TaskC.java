package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        if (count == 5) {
            out.printLine("1 2 3 4 5");
            return;
        }
        int[] from = new int[count * 2];
        int[] to = new int[count * 2];
        IOUtils.readIntArrays(in, from, to);
        MiscUtils.decreaseByOne(from, to);
        int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
        for (int i = 0; i < count; i++) {
            if (graph[i].length != 4) {
                out.printLine(-1);
                return;
            }
        }
        if (count == 6) {
            int[] reverse = new int[6];
            for (int i = 0; i < 6; i++) {
                reverse[i] = 15 - i;
                for (int j : graph[i])
                    reverse[i] -= j;
            }
            int[] answer = new int[6];
            int j = 0;
            for (int i = 0; i < 6; i++) {
                if (reverse[i] > i)
                    answer[j++] = i + 1;
            }
            for (int i = 0; i < 3; i++)
                answer[i + 3] = reverse[answer[i] - 1] + 1;
            out.printLine(answer);
            return;
        }
        int[] newFrom = new int[count];
        int[] newTo = new int[count];
        int j = 0;
        for (int i = 0; i < count; i++) {
            for (int k : graph[i]) {
                if (k > i)
                    continue;
                int same = 0;
                for (int l : graph[i]) {
                    for (int m : graph[k]) {
                        if (l == m)
                            same++;
                    }
                }
//                if (same > 2) {
//                    out.printLine(-1);
//                    return;
//                }
                if (same == 2) {
                    if (j == count) {
                        out.printLine(-1);
                        return;
                    }
                    newFrom[j] = i;
                    newTo[j++] = k;
                }
            }
        }
        if (j != count) {
            out.printLine(-1);
            return;
        }
        int[][] original = graph;
        graph = GraphUtils.buildSimpleGraph(count, newFrom, newTo);
        for (int i = 0; i < count; i++) {
            if (graph[i].length != 2) {
                out.printLine(-1);
                return;
            }
        }
        boolean[] visited = new boolean[count];
        int current = 0;
        int[] answer = new int[count];
        for (int i = 0; i < count; i++) {
            answer[i] = current + 1;
            visited[current] = true;
            if (i == count - 1)
                break;
            int next;
            if (visited[graph[current][0]])
                next = graph[current][1];
            else
                next = graph[current][0];
            if (visited[next]) {
                out.printLine(-1);
                return;
            }
            current = next;
        }
        for (int i = 0; i < count; i++) {
            int next = i + 2;
            int next1 = i + 1;
            if (next >= count)
                next -= count;
            next = answer[next] - 1;
            if (next1 >= count)
                next1 -= count;
            next1 = answer[next1] - 1;
            boolean found = false;
            boolean found1 = false;
            for (int k : original[answer[i] - 1]) {
                if (k == next)
                    found = true;
                if (k == next1)
                    found1 = true;
            }
            if (!found || !found1) {
                out.printLine(-1);
                return;
            }
        }
        out.printLine(answer);
    }
}
