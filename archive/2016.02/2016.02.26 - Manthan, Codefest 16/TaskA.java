package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int c = in.readInt();
        for (int i = 0; i <= c; i += a) {
            if ((c - i) % b == 0) {
                out.printLine("Yes");
                return;
            }
        }
        out.printLine("No");
    }
}
