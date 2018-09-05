package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int answer = 0;
        for (int i = 1; n != 0; i *= 2) {
            if (n < i) {
                answer++;
                break;
            }
            n -= i;
            answer++;
        }
        out.printLine(answer);
    }
}
