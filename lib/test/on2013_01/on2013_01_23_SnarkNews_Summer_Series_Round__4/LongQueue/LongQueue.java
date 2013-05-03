package on2013_01.on2013_01_23_SnarkNews_Summer_Series_Round__4.LongQueue;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class LongQueue {
    int[][] graph;
    boolean[] visited;
    int sink, vertexCount;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int taxiCount = in.readInt();
        long[] can = new long[count];
        for (int i = 0; i < count; i++) {
            int variantCount = in.readInt();
            for (int j = 0; j < variantCount; j++)
                can[i] += 1L << (in.readInt() - 1);
        }
        vertexCount = count + taxiCount + 2;
        int source = vertexCount - 2;
        sink = source + 1;
        graph = new int[vertexCount][vertexCount];
        int queryCount = in.readInt();
        visited = new boolean[vertexCount];
        for (int i = 0; i < queryCount; i++) {
            ArrayUtils.fill(graph, 0);
            int length = in.readInt();
            long bad = 0;
            for (int j = 0; j < length; j++)
                bad += 1L << (in.readInt() - 1);
            long takenCare = 0;
            for (int j = 0; j < count; j++) {
                if ((bad >> j & 1) == 0) {
                    graph[source][j] = 1;
                    for (int k = 0; k < taxiCount; k++) {
                        if ((can[j] >> k & 1) == 1 && (takenCare >> k & 1) == 0)
                            graph[j][k + count] = 1;
                    }
                } else {
                    long delta = can[j] & (~takenCare);
                    graph[j][sink] = Long.bitCount(delta);
                    takenCare |= delta;
                    for (int k = 0; k < taxiCount; k++) {
                        if ((delta >> k & 1) == 1)
                            graph[k + count][j] = 1;
                    }
                }
            }
            int required = Long.bitCount(takenCare);
            while (required != 0) {
                Arrays.fill(visited, false);
                if (!flow(source)) {
                    out.printLine("No");
                    break;
                }
                required--;
            }
            if (required == 0)
                out.printLine("Yes");
        }
    }

    private boolean flow(int vertex) {
        if (visited[vertex])
            return false;
        visited[vertex] = true;
        if (vertex == sink)
            return true;
        for (int i = 0; i < vertexCount; i++) {
            if (graph[vertex][i] != 0 && flow(i)) {
                graph[vertex][i]--;
                graph[i][vertex]++;
                return true;
            }
        }
        return false;
    }
}
