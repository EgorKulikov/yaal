package net.egork;

import net.egork.generated.collections.list.LongArrayList;
import net.egork.generated.collections.list.LongList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.power;

public class Gymnasts {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        long answer = n;
        Map<Long, Long> result = new HashMap<>();
        LongList divisors = new LongArrayList();
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i != n / i) {
                    divisors.add(n / i);
                }
            }
        }
        divisors.sort();
        for (long i : divisors) {
            long x = n / i;
            long current = power(2, i, MOD7) - 2;
            for (long j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    current -= result.get(j);
                    if (i / j != j) {
                        current -= result.get(i / j);
                    }
                }
            }
            current %= MOD7;
            result.put(i, current);
            current *= ((x - 1) % MOD7) % MOD7;
            current %= MOD7;
            answer += current;
        }
        answer %= MOD7;
        answer += MOD7;
        answer %= MOD7;
        out.printLine(answer);
    }
}
