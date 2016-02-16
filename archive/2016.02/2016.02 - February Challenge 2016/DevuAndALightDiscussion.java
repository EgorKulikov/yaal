package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class DevuAndALightDiscussion {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int buben = 6;
        if (n <= buben) {
            out.printLine(-1);
            return;
        }
        out.printLine(n);
        for (int i = 0; i < buben - 1; i++) {
            out.printLine(i + 1, i + 2);
        }
        out.printLine(buben, 1);
        for (int i = buben + 1; i <= n; i++) {
            out.printLine(i, 2);
        }
        out.printLine(1);
    }
}
