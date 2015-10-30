package on2015_10.on2015_10_04_Grand_Prix_of_Eurasia_2015.Task3;


import net.egork.collections.Pair;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.graph.Graph;
import net.egork.graph.StronglyConnectedComponents;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Task3 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] type = new int[n];
        int[] lType = new int[n];
        int[] lValue = new int[n];
        int[] rType = new int[n];
        int[] rValue = new int[n];
        IOUtils.readIntArrays(in, type, lType, lValue, rType, rValue);
        Graph graph = new Graph(k);
        int[] edge = new int[n];
        int at = 0;
        for (int i = 0; i < n; i++) {
            if (lType[i] == 0 && rType[i] == 0) {
                edge[at++] = i;
                graph.addSimpleEdge(lValue[i] - 1, rValue[i] - 1);
            }
            if (lType[i] == 1 && rType[i] == 1 && lValue[i] + type[i] > rValue[i]) {
                out.printLine("NO");
                return;
            }
        }
        Pair<int[], Graph> pair = StronglyConnectedComponents.kosaraju(graph);
        Set<IntIntPair> strict = new HashSet<>();
        for (int i = 0; i < at; i++) {
            if (type[edge[i]] == 1) {
                if (pair.first[lValue[edge[i]] - 1] == pair.first[rValue[edge[i]] - 1]) {
                    out.printLine("NO");
                    return;
                }
                strict.add(new IntIntPair(pair.first[lValue[edge[i]] - 1], pair.first[rValue[edge[i]] - 1]));
            }
        }
        int[] minVal = new int[pair.second.vertexCount()];
        Arrays.fill(minVal, (int) -2e9);
        for (int i = 0; i < n; i++) {
            if (lType[i] == 1 && rType[i] == 0) {
                minVal[pair.first[rValue[i] - 1]] = Math.max(minVal[pair.first[rValue[i] - 1]], lValue[i] + type[i]);
            }
        }
//        int[] order = GraphAlgorithms.topologicalSort(pair.second);
        int[] order = ArrayUtils.createOrder(pair.second.vertexCount());
//        ArrayUtils.reverse(order);
        for (int i : order) {
            for (int j = pair.second.firstInbound(i); j != -1; j = pair.second.nextInbound(j)) {
                int from = pair.second.source(j);
                int delta = 0;
                if (strict.contains(new IntIntPair(from, i))) {
                    delta = 1;
                }
                minVal[i] = Math.max(minVal[i], minVal[from] + delta);
            }
        }
        int[] answer = new int[k];
        for (int i = 0; i < k; i++) {
            answer[i] = minVal[pair.first[i]];
        }
        for (int i = 0; i < n; i++) {
            if (lType[i] == 0 && rType[i] == 1 && answer[lValue[i] - 1] + type[i] > rValue[i]) {
                out.printLine("NO");
                return;
            }
        }
        out.printLine("YES");
        for (int i = 0; i < k; i++) {
            out.printLine(answer[i]);
        }
    }
}
