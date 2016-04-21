package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        in = new InputReader(System.in);
        out = new OutputWriter(System.out);
        int n = in.readInt();
        int left = 0;
        int right = n / 2;
        while (left < right) {
            int middle = (left + right) >> 1;
            out.printLine('?', 2 * middle + 1);
            out.flush();
            int val1 = in.readInt();
            out.printLine('?', 2 * middle + 2);
            out.flush();
            int val2 = in.readInt();
            if (val1 != val2) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        out.printLine('?', 2 * left + 1);
        out.flush();
        out.printLine('!', in.readInt());
        out.flush();
    }
}
