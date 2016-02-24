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
        int x1 = in.readInt();
        int y1 = in.readInt();
        int x2 = in.readInt();
        int y2 = in.readInt();
        long dx = (x2 - x1) / 2;
        long dy = y2 - y1;
        long answer;
        if (dy % 2 == 0) {
            dy /= 2;
            answer = (dx + 1) * (dy + 1) + dx * dy;
        } else {
            dy /= 2;
            answer = (2 * dx + 1) * (dy + 1);
        }
        out.printLine(answer);
    }
}
