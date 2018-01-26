package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long answer = 0;
        long[][] c = IntegerUtils.generateBinomialCoefficients(n + 1);
        for (int i = 5; i <= 7; i++) {
            answer += c[n][i];
        }
        out.printLine(answer);
    }
}
