package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    int[] visited;
    int[][] graph;
    int minDegree;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        minDegree = in.readInt();
        int[] from = new int[edgeCount];
        int[] to = new int[edgeCount];
        IOUtils.readIntArrays(in, from, to);
        MiscUtils.decreaseByOne(from, to);
        graph = GraphUtils.buildSimpleGraph(count, from, to);
        visited = new int[count];
        Arrays.fill(visited, -1);
        IntList answer = new IntArrayList();
        go(0, 0, answer);
        out.printLine(answer.size());
        out.printLine(answer);
    }

    private int go(int vertex, int index, IntList answer) {
        visited[vertex] = index;
        for (int i : graph[vertex]) {
            if (visited[i] == -1) {
                int value = go(i, index + 1, answer);
                if (value <= index)
                    answer.add(vertex + 1);
                return value;
            }
            if (index - visited[i] >= minDegree) {
                answer.add(vertex + 1);
                return visited[i];
            }
        }
        throw new RuntimeException();
    }
}
