package net.egork;

import net.egork.collections.intcollection.IntHashMap;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Integer.MAX_VALUE;
import static net.egork.io.IOUtils.readIntArray;

public class MinimumDistances {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        IntHashMap map = new IntHashMap();
        int answer = MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (map.contains(a[i])) {
                answer = Math.min(answer, i - map.get(a[i]));
            }
            map.put(a[i], i);
        }
        if (answer == MAX_VALUE) {
            answer = -1;
        }
        out.printLine(answer);
    }
}
