package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int answer = n > 0 ? 0 : -1;
        for (int i = 1; i <= n - 1; i++) {
            long current = 1;
            long add = i;
            while (true) {
                current += add;
                if (current > n) {
                    break;
                }
                if (n % current == 0) {
                    answer++;
                }
                add *= i;
            }
        }
        out.printLine(answer);
    }
}
