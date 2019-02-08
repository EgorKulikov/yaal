package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

import static net.egork.misc.ArrayUtils.sort;

public class DMOPC18Contest1P0Sorting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] a = in.readIntArray(3);
        int[] b = a.clone();
        sort(b);
        out.printLine(Arrays.equals(a, b) ? "Good job!" : "Try again!");
    }
}
