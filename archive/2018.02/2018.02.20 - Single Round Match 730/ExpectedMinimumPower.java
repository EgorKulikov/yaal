package net.egork;

import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.*;

public class ExpectedMinimumPower {
    public int findExp(int n, int x) {
        long answer = power(2, n, MOD7);
        long[] rev = generateReverse(x + 1, MOD7);
        long c = 1;
        for (int i = 0; i < x; i++) {
            answer -= c;
            c *= n - i;
            c %= MOD7;
            c *= rev[i + 1];
            c %= MOD7;
        }
        answer %= MOD7;
        if (answer < 0) {
            answer += MOD7;
        }
        answer *= 2;
        answer %= MOD7;
        return (int) answer;
    }
}
