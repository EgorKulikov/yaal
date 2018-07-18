package net.egork;

import net.egork.collections.comparators.ReverseComparator;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static net.egork.misc.ArrayUtils.sumArray;

public class SplitTheSticks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] l = in.readIntArray(n);
        if (sumArray(l) % 2 != 0) {
            out.printLine(-1);
            return;
        }
        List<IntIntPair> answer = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(new ReverseComparator<>());
        for (int i : l) {
            queue.add(i);
        }
        while (queue.size() > 1) {
            int longest = queue.poll();
            int second = queue.poll();
            if (longest != second) {
                answer.add(new IntIntPair(longest, second));
                queue.add(longest - second);
            }
        }
        if (!queue.isEmpty()) {
            int stick = queue.poll();
            answer.add(new IntIntPair(stick, stick / 2));
        }
        out.printLine(answer.size());
        for (IntIntPair pair : answer) {
            out.printLine(pair.first, pair.second);
        }
    }
}
