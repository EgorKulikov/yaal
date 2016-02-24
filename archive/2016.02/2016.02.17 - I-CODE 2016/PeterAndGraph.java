package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class PeterAndGraph {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        int[] value = new int[n + 1];
        int size = 1;
        for (int i : a) {
            int at = binarySearch(value, 0, size, i);
            if (at < 0) {
                at = -at - 1;
            }
            value[at] = i;
            if (at == size) {
                size++;
            }
        }
        out.printLine(size - 1);
    }
}
