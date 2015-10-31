package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BeginEnd {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        char[] s = IOUtils.readCharArray(in, count);
        long[] qty = new long[256];
        for (char c : s) {
            qty[c]++;
        }
        long answer = 0;
        for (long i : qty) {
            answer += i * (i + 1) / 2;
        }
        out.printLine(answer);
    }
}
