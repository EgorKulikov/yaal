package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskL {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readInt();
        long answer = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                answer += n;
                n /= 2;
            } else {
                answer += n + 1;
                n += n / 2 + 1;
            }
        }
        out.printLine(answer);
    }
}
