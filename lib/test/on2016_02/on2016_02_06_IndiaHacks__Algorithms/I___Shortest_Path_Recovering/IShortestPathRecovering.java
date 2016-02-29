package on2016_02.on2016_02_06_IndiaHacks__Algorithms.I___Shortest_Path_Recovering;



import net.egork.collections.Pair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

import static java.util.Arrays.copyOf;
import static net.egork.io.IOUtils.readIntArray;
import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.ArrayUtils.asLong;
import static net.egork.misc.ArrayUtils.createArray;
import static net.egork.misc.MiscUtils.decreaseByOne;

public class IShortestPathRecovering {
    int n;
    Graph graph;
    boolean[] reset;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        int m = in.readInt();
        int[] u = new int[m];
        int[] v = new int[m];
        int[] c = new int[m];
        readIntArrays(in, u, v, c);
        int sSize = in.readInt();
        int[] s = readIntArray(in, sSize);
        decreaseByOne(u, v, s);
        graph = BidirectionalGraph.createWeightedGraph(n, u, v, asLong(c));
        reset = new boolean[n];
        long[] distance = shPath(s[0]).first;
        int source = s[0];
        long dist = 0;
        for (int i = 1; i < sSize; i++) {
            if (distance[s[i]] > dist) {
                dist = distance[s[i]];
                source = s[i];
            }
        }
        for (int i : s) {
            reset[i] = true;
        }
        Pair<long[], int[]> pair = shPath(source);
        distance = pair.first;
        int[] last = pair.second;
        int start = s[0];
        dist = distance[start];
        for (int i = 1; i < sSize; i++) {
            if (distance[s[i]] > dist) {
                dist = distance[s[i]];
                start = s[i];
            }
        }
        int[] answer = new int[n];
        int at = 0;
        while (start != -1) {
            answer[at++] = start + 1;
            start = last[start];
        }
        answer = copyOf(answer, at);
        out.printLine(at);
        out.printLine(answer);
    }

    Pair<long[], int[]> shPath(int source) {
        final long[] result = createArray(n, Long.MAX_VALUE);
        int[] last = new int[n];
        NavigableSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return result[a] != result[b] ? Long.compare(result[a], result[b]) : a - b;
            }
        });
        result[source] = 0;
        set.add(source);
        last[source] = -1;
        while (!set.isEmpty()) {
            int current = set.pollFirst();
            if (reset[current]) {
                set.clear();
            }
            for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
                int next = graph.destination(i);
                if (result[current] + graph.weight(i) <= result[next]) {
                    set.remove(next);
                    result[next] = result[current] + graph.weight(i);
                    last[next] = current;
                    set.add(next);
                }
            }
        }
        return Pair.makePair(result, last);
    }
}
