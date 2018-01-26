package net.egork;

import net.egork.collections.intcollection.IntArray;
import net.egork.collections.intcollection.Range;
import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class WhoDaresToBeAMillionaire {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] correct = IOUtils.readCharArray(in, n);
        char[] chef = IOUtils.readCharArray(in, n);
        int[] points = IOUtils.readIntArray(in, n + 1);
        int max = (int) Range.range(n).map(i -> chef[i] == correct[i] ? 1 : 0).sum();
        int answer = new IntArray(points).subList(0, max + 1).max();
        if (max == n) {
            answer = points[n];
        }
        out.printLine(answer);
    }
}
