package net.egork;

import net.egork.generated.collections.pair.LongLongPair;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.reverse;

public class BrokenClock {
    long cosx;
    long sin2x;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int l = in.readInt();
        int d = in.readInt();
        long t = in.readLong();
        cosx = d * reverse(l, MOD7) % MOD7;
        sin2x = (1 - cosx * cosx) % MOD7;
        LongLongPair result = power(t);
        long answer = result.first;
        if (answer < 0) {
            answer += MOD7;
        }
        answer *= l;
        answer %= MOD7;
        out.printLine(answer);
    }

    private LongLongPair power(long exponent) {
        if (exponent == 0) {
            return new LongLongPair(1, 0);
        }
        if ((exponent & 1) == 1) {
            LongLongPair result = power(exponent - 1);
            long cos = (result.first * cosx - result.second * sin2x) % MOD7;
            long sin = (result.first + result.second * cosx) % MOD7;
            return new LongLongPair(cos, sin);
        } else {
            LongLongPair result = power(exponent >> 1);
            long cos = (result.first * result.first - result.second * result.second % MOD7 * sin2x) % MOD7;
            long sin = 2 * result.first * result.second % MOD7;
            return new LongLongPair(cos, sin);
        }
    }
}
