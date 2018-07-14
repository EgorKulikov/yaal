package net.egork;

import net.egork.collections.map.Counter;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        Counter<Integer> counter = new Counter<>();
        for (int i : a) {
            counter.add(i);
        }
        out.printLine(n - counter.values().stream().max(Long::compareTo).get());
    }
}
