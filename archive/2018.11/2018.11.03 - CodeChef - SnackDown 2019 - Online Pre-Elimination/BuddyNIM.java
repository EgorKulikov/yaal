package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

import static java.util.Arrays.copyOfRange;
import static net.egork.misc.ArrayUtils.count;
import static net.egork.misc.ArrayUtils.sort;

public class BuddyNIM {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(m);
        sort(a);
        sort(b);
        int zA = count(a, 0);
        a = copyOfRange(a, zA, n);
        int zB = count(b, 0);
        b = copyOfRange(b, zB, m);
        if (Arrays.equals(a, b)) {
            out.printLine("Bob");
        } else {
            out.printLine("Alice");
        }
    }
}
