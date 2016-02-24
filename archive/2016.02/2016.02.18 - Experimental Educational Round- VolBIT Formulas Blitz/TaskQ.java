package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskQ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        double l3 = in.readInt();
        double l4 = in.readInt();
        double l5 = in.readInt();
        double answer = l3 * l3 * l3 * sqrt(2) / 12 + l4 * l4 * l4 * sqrt(2) / 6;
        double rc = sqrt(10) * sqrt(5 + sqrt(5)) / 10;
        double hc = sqrt(1 - rc * rc);
        answer += l5 * l5 * l5 * hc * sqrt(5) * sqrt(5 + 2 * sqrt(5)) / 12;
        out.printLine(answer);
    }
}
