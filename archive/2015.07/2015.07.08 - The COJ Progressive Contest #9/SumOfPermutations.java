package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class SumOfPermutations {
    static final int MOD = 1000003;
    long[] answer = new long[1000000 + 1];

    {
        for (int i = 1; i <= 1000000; i++) {
            answer[i] = (answer[i - 1] * i + 1) % MOD;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int number = in.readInt();
        out.printLine(answer[number]);
    }
}
