package net.egork;

import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        List<Long> array = new ArrayList<>();
        while (!in.isExhausted()) {
            array.add(in.readLong());
        }
        int count = array.size();
        Counter<Long> qty = new Counter<>();
        for (long l : array) {
            qty.add(l);
        }
        int min = qty.size() - 1;
        int max = count - 1;
        for (long q : qty.values()) {
            max = (int) Math.min(max, 2 * (count - q));
        }
        out.printLine(min, max);
    }
}
