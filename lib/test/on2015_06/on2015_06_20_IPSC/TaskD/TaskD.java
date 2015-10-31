package on2015_06.on2015_06_20_IPSC.TaskD;


import net.egork.collections.Pair;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskD {
    List<Pair<IntIntPair, Long>> graph;
    long delta;
    long subDelta;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int required = in.readInt();
        graph = new ArrayList<>();
        delta = 8;
        subDelta = 1;
        int current = build(required - 1);
//        graph.add(Pair.makePair(new IntIntPair(2, 1), 1L));
        graph.add(Pair.makePair(new IntIntPair(0, current), 1L));
        out.printLine(current + 1);
        if (current >= 60) {
            throw new RuntimeException(required + " " + (current + 1));
        }
        out.printLine(graph.size());
        for (Pair<IntIntPair, Long> pair : graph) {
            out.printLine(pair.first.first, pair.first.second, pair.second);
        }
//        out.printLine(Integer.toBinaryString(2097150));
    }

    int build(int required) {
        if (required <= 3) {
            for (int i = 1; i < required; i++) {
                graph.add(Pair.makePair(new IntIntPair(i + 1, i), subDelta));
                subDelta *= 2;
                delta *= 2;
            }
            return required;
        }
        if ((required & 1) == 1) {
            int current = build((required - 3) / 2);
            graph.add(Pair.makePair(new IntIntPair(current + 3, current), subDelta));
            graph.add(Pair.makePair(new IntIntPair(current + 3, current + 2), delta));
            graph.add(Pair.makePair(new IntIntPair(current + 2, current + 1), 0L));
            graph.add(Pair.makePair(new IntIntPair(current + 1, current), -delta));
            current += 3;
            delta *= 2;
            subDelta *= 2;
            return current;
        }
        int current = build((required - 2) / 2);
        graph.add(Pair.makePair(new IntIntPair(current + 2, current), subDelta));
        graph.add(Pair.makePair(new IntIntPair(current + 2, current + 1), delta));
        graph.add(Pair.makePair(new IntIntPair(current + 1, current), -delta));
        current += 2;
        delta *= 2;
        subDelta *= 2;
        return current;
    }
}
