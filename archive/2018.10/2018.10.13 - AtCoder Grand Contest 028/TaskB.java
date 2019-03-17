package net.egork;

import net.egork.generated.collections.list.LongArray;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.factorial;
import static net.egork.numbers.IntegerUtils.generateReverse;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = in.readIntArray(n);
        long[] add = new long[n];
        add[0] = factorial(n, MOD7);
        long[] rev = generateReverse(n + 1, MOD7);
        for (int i = 1; i < n; i++) {
            add[i] = add[0] * rev[i + 1] % MOD7;
        }
        long current = new LongArray(add).sum() % MOD7;
        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer += current * a[i] % MOD7;
            if (i != n - 1) {
                current -= add[n - i - 1];
                current += add[i + 1];
                current %= MOD7;
                current += MOD7;
                current %= MOD7;
            }
        }
        answer %= MOD7;
        out.printLine(answer);
    }
}
