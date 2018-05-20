package net.egork;

import net.egork.numbers.MultiplicativeFunction;

public class TheRoundCityDiv1 {
    public long find(int r) {
        if (r == 1) {
            return 4;
        }
        long[] mu = MultiplicativeFunction.MOBIUS.calculateUpTo(r + 1);
        long answer = 0;
        for (int i = 1; i <= r; i++) {
            answer += mu[i] * points((long)r * r / ((long)i * i));
        }
        return answer * 8 + 8;
    }

    private long points(long max) {
        long j = 0;
        long answer = 0;
        for (long i = 2; i * i <= max; i++) {
            j++;
            while (j * j + i * i > max) {
                j--;
            }
            answer += j;
        }
        return answer;
    }
}
