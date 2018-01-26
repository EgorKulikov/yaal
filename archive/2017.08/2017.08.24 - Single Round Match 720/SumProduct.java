package net.egork;

import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.generateBinomialCoefficients;
import static net.egork.numbers.IntegerUtils.generatePowers;

public class SumProduct {
    public int findSum(int[] amount, int blank1, int blank2) {
        long answer = 0;
        long[] tens = generatePowers(10, blank1 + blank2 + 1, MOD7);
        long[][] c = generateBinomialCoefficients(blank1 + blank2 + 1, MOD7);
        for (int i = 1; i < 10; i++) {
            if (amount[i] == 0) {
                continue;
            }
            amount[i]--;
            for (int j = 1; j <= i; j++) {
                if (amount[j] == 0) {
                    continue;
                }
                amount[j]--;
                long[] current = new long[blank1 + blank2 - 1];
                current[0] = 1;
                for (int k = 0; k < 10; k++) {
                    for (int l = blank1 + blank2 - 2; l >= 0; l--) {
                            for (int n = 1; n <= l && n <= amount[k]; n++) {
                                current[l] += current[l - n] * c[l][n] % MOD7;
                            }
                            current[l] %= MOD7;
                    }
                }
                for (int k = 0; k < blank1; k++) {
                    for (int l = 0; l < blank2; l++) {
                        answer += current[blank1 + blank2 - 2] * tens[k] % MOD7 * tens[l] % MOD7 * (i == j ? 1 :
                                2) * i * j;
                    }
                }
                amount[j]++;
                answer %= MOD7;
            }
            amount[i]++;
        }
        return (int) (answer % MOD7);
    }
}
