package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class RectanglesOfLove {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();
        long[] hearts = readLongArray(in, k);
        double answer = 0;
        long numRowWays = (long) n * (n + 1) / 2;
        long numColWays = (long) m * (m + 1) / 2;
        for (long i : hearts) {
            i--;
            long row = i / m;
            long column = i % m;
            answer += prob(row, n, numRowWays) * prob(column, m, numColWays);
        }
        out.printLine(answer);
    }

    private double prob(long row, int n, long numRowWays) {
        return (double)(row + 1) * (n - row) / numRowWays;
    }
}
