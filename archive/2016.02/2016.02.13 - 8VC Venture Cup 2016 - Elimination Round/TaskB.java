package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskB {
    boolean[][][] processed;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] cards = readCharArray(in, n);
        int[] qty = new int[3];
        for (char c : cards) {
            switch (c) {
            case 'R':
                qty[0]++;
                break;
            case 'G':
                qty[1]++;
                break;
            case 'B':
                qty[2]++;
                break;
            }
        }
        processed = new boolean[n + 1][n + 1][n + 1];
        go(qty[0], qty[1], qty[2]);
        if (processed[0][0][1]) {
            out.print('B');
        }
        if (processed[0][1][0]) {
            out.print('G');
        }
        if (processed[1][0][0]) {
            out.print('R');
        }
        out.printLine();
    }

    private void go(int r, int g, int b) {
        if (processed[r][g][b]) {
            return;
        }
        processed[r][g][b] = true;
        if (r >= 2) {
            go(r - 1, g, b);
        }
        if (g >= 2) {
            go(r, g - 1, b);
        }
        if (b >= 2) {
            go(r, g, b - 1);
        }
        if (r >= 1 && g >= 1) {
            go(r - 1, g - 1, b + 1);
        }
        if (r >= 1 && b >= 1) {
            go(r - 1, g + 1, b - 1);
        }
        if (b >= 1 && g >= 1) {
            go(r + 1, g - 1, b - 1);
        }
    }
}
