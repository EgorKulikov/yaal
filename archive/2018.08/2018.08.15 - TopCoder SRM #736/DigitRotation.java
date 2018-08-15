package net.egork;

import static net.egork.misc.ArrayUtils.reverse;
import static net.egork.misc.MiscUtils.MODF;
import static net.egork.numbers.IntegerUtils.generatePowers;

public class DigitRotation {
    public int sumRotations(String X) {
        long answer = 0;
        long x = 0;
        for (int i = 0; i < X.length(); i++) {
            x *= 10;
            x += X.charAt(i) - '0';
            x %= MODF;
        }
        long totalWays = X.length() * (X.length() - 1) * (X.length() - 2) / 6;
        for (int i = 1; i < X.length(); i++) {
            if (X.charAt(i) == '0') {
                totalWays -= i - 1;
            }
        }
        answer = totalWays * x % MODF;
        long[] ten = generatePowers(10, X.length(), MODF);
        reverse(ten);
        // a -> b
        for (int i = 0; i < X.length(); i++) {
            int goodWays = 0;
            for (int j = X.length() - 1; j > i; j--) {
                answer += (ten[j] - ten[i]) * (X.charAt(i) - '0') * goodWays % MODF;
                if (X.charAt(j) != '0' || i != 0) {
                    goodWays++;
                }
            }
        }
        // c -> a
        for (int i = 0; i < X.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (X.charAt(i) != '0' || j != 0) {
                    answer += (ten[j] - ten[i]) * (X.charAt(i) - '0') * (i - j - 1) % MODF;
                }
            }
        }
        // b -> c
        for (int i = 0; i < X.length(); i++) {
            for (int j = i + 1; j < X.length(); j++) {
                int goodWays = i;
                if (X.charAt(j) == '0') {
                    goodWays--;
                }
                goodWays = Math.max(goodWays, 0);
                answer += (ten[j] - ten[i]) * (X.charAt(i) - '0') * goodWays % MODF;
            }
        }
        answer %= MODF;
        answer += MODF;
        answer %= MODF;
        return (int) answer;
    }
}
