package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.Combinations;

public class TaskB {
    static final long MOD = 998244353;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int a = in.readInt();
        int b = in.readInt();
        long k = in.readLong();
        Combinations c = new Combinations(n + 1, MOD);
        long answer = 0;
        for (int i = 0; i <= n; i++) {
            long has = (long)a * i;
            if (has > k) {
                break;
            }
            long remaining = k - has;
            if (remaining % b != 0 || remaining / b > n) {
                continue;
            }
            int j = (int) (remaining / b);
            answer += c.c(n, i) * c.c(n, j) % MOD;
        }
        out.printLine(answer % MOD);
    }
}
