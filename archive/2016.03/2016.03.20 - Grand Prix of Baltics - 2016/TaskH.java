package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        a++;
        if (a == 0) {
            out.printLine("infinity");
            return;
        }
        boolean sgn = a > 0;
        a = abs(a);
        int answer = 0;
        for (int i = 1; i * i <= a; i++) {
            if (a % i == 0) {
                answer++;
                if (i * i != a || sgn) {
                    answer++;
                }
            }
        }
        out.printLine(answer);
    }
}
