package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.min;
import static net.egork.io.IOUtils.readCharArray;

public class XennyAndClassroom {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] sequence = readCharArray(in, 2 * n);
        int odd = 0;
        int even = 0;
        for (int i = 0; i < n; i++) {
            even += (sequence[2 * i] == 'G') ? 0 : 1;
            odd += (sequence[2 * i + 1] == 'G') ? 0 : 1;
        }
        out.printLine(min(even, odd));
    }
}
