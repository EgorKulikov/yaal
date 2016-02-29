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
        int m = in.readInt();
        int l = 0;
        int r = (int) 1e9;
        while (l < r) {
            int mid = (l + r) >> 1;
            int q2 = mid / 2;
            int q3 = mid / 3;
            int q6 = mid / 6;
            if (q2 < n || q3 < m || q2 + q3 - q6 < n + m) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        out.printLine(l);
    }
}
