package on2015_06.on2015_06_20_IPSC.TaskI;



import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskI {

    public static final long MAX = 1000000000000L;

    long[] fromStart;
    long[] fromEnd;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        System.err.println(testNumber);
        int count = in.readInt();
        int edgeCount = in.readInt();
        int[] from = new int[edgeCount];
        int[] to = new int[edgeCount];
        int[] distance = new int[edgeCount];
        int[] ascent = new int[edgeCount];
        IOUtils.readIntArrays(in, from, to, distance, ascent);
        MiscUtils.decreaseByOne(from, to);
        fromStart = new long[count];
        fromEnd = new long[count];
        boolean[] current = new boolean[edgeCount];
        generate(0, count, from, to, distance, ascent, current);
        long start = 0;
        boolean[] other = new boolean[edgeCount];
        List<Long> answer = new ArrayList<>();
        while (true) {
            System.err.println(start);
            generate(MAX, count, from, to, distance, ascent, other);
            if (Arrays.equals(current, other)) {
                break;
            }
            long left = start + 1;
            long right = MAX;
            while (left < right) {
                long middle = (left + right) >> 1;
                generate(middle, count, from, to, distance, ascent, other);
                if (Arrays.equals(current, other)) {
                    left = middle + 1;
                } else {
                    right = middle;
                }
            }
            answer.add(Math.min(left, MAX - 1));
            start = left;
            generate(start, count, from, to, distance, ascent, current);
        }
        out.print(answer.size());
        long last = -MAX;
        for (long l : answer) {
            if (l - last > 1 && l != MAX - 1) {
                out.printFormat(" 0.%012d", l);
            }
            last = l;
        }
        out.printLine();
    }

    private void generate(long current, int count, int[] from, int[] to, int[] distance, int[] ascent, boolean[] result) {
        Graph graph = new Graph(count, from.length);
        for (int i = 0; i < from.length; i++) {
            graph.addWeightedEdge(from[i], to[i], distance[i] * current + ascent[i] * (MAX - current));
        }
        Arrays.fill(result, false);
        Arrays.fill(fromStart, Long.MAX_VALUE);
        fromStart[0] = 0;
        for (int i = 0; i < count; i++) {
            if (fromStart[i] == Long.MAX_VALUE) {
                continue;
            }
            for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
                int target = graph.destination(j);
                fromStart[target] = Math.min(fromStart[target], fromStart[i] + graph.weight(j));
            }
        }
        if (fromStart[count - 1] == Long.MAX_VALUE) {
            return;
        }
        Arrays.fill(fromEnd, Long.MAX_VALUE);
        fromEnd[count - 1] = 0;
        for (int i = count - 1; i >= 0; i--) {
            if (fromEnd[i] == Long.MAX_VALUE) {
                continue;
            }
            for (int j = graph.firstInbound(i); j != -1; j = graph.nextInbound(j)) {
                int target = graph.source(j);
                fromEnd[target] = Math.min(fromEnd[target], fromEnd[i] + graph.weight(j));
            }
        }
        if (fromStart[count - 1] == Long.MAX_VALUE) {
            return;
        }
        for (int i = 0; i < from.length; i++) {
            result[i] = fromStart[from[i]] + distance[i] * current + ascent[i] * (MAX - current) + fromEnd[to[i]] == fromStart[count - 1];
        }
    }
}
