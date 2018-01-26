package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        boolean first = getOddity(in);
        boolean second = getOddity(in);
        out.printLine(first == second ? "YES" : "NO");
    }

    private boolean getOddity(InputReader in) {
        char[][] puzzle = readTable(in, 2, 2);
        String s = "";
        s += puzzle[0][0];
        s += puzzle[0][1];
        s += puzzle[1][1];
        s += puzzle[1][0];
        while (s.charAt(0) != 'A') {
            s = s.substring(1) + s.substring(0, 1);
        }
        return s.charAt(1) == 'B' || s.charAt(1) == 'X' && s.charAt(2) == 'B';
    }
}
