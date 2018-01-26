package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class ChefAndBallons {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int r = in.readInt();
        int g = in.readInt();
        int b = in.readInt();
        int k = in.readInt();
        long answer = 1;
        answer += min(r, k - 1);
        answer += min(g, k - 1);
        answer += min(b, k - 1);
        out.printLine(answer);
    }
}
