package net.egork;

import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.generateBinomialCoefficients;

public class Gangsters {
    long[][] answer;
    int alive;
    long[][] c;

    public int countOrderings(int people, int alive) {
        if (alive == 0) {
            return 0;
        }
        c = generateBinomialCoefficients(people, MOD7);
        this.alive = alive;
        answer = new long[people][];
        go(people - 1);
        long result = people * (people - 1) * answer[people - 1][alive];
        result %= MOD7;
        return (int)result;
    }

    private void go(int people) {
        if (answer[people] != null) {
            return;
        }
        answer[people] = new long[alive + 1];
        if (people == 1) {
            answer[people][1] = 1;
            return;
        }
        for (int i = 0; i < people - 2; i++) {
            int a = i + 1;
            go(a);
            int b = people - i - 2;
            go(b);
            for (int j = 0; j <= alive; j++) {
                for (int k = 0; k + j <= alive; k++) {
                    answer[people][j + k] += (people - 2) * c[people - 3][i] % MOD7 * answer[a][j] % MOD7 * answer[b][k] % MOD7;
                }
            }
        }
        go(people - 1);
        for (int i = 0; i <= alive; i++) {
            answer[people][i] = (answer[people][i] + answer[people - 1][i]) % MOD7;
        }
    }
}
