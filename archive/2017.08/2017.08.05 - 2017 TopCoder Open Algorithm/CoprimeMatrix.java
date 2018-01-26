package net.egork;

import static net.egork.numbers.IntegerUtils.gcd;
import static net.egork.numbers.IntegerUtils.generatePrimes;

public class CoprimeMatrix {
    public String isPossible(String[] coprime) {
        int n = coprime.length;
        boolean[][] shouldBe = new boolean[n][n];
        boolean[][] canBe = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                shouldBe[i][j] = canBe[i][j] = coprime[i].charAt(j) == 'N';
            }
        }
        if (!shouldBe[0][0]) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (shouldBe[i][j] != (gcd(i + 1, j + 1) != 1)) {
                        return "Impossible";
                    }
                }
            }
            return "Possible";
        }
        for (int i = 0; i < n; i++) {
            if (!shouldBe[i][i]) {
                return "Impossible";
            }
            for (int j = 0; j < i; j++) {
                if (shouldBe[i][j] != shouldBe[j][i]) {
                    return "Impossible";
                }
            }
        }
        for (int i : generatePrimes(n)) {
            int posAt = -1;
            for (int j = 0; j < i; j++) {
                boolean can = true;
                for (int k = j; k < n; k += i) {
                    for (int l = k + i; l < n; l += i) {
                        if (!canBe[k][l]) {
                            can = false;
                        }
                    }
                }
                if (can) {
                    posAt = j;
                    break;
                }
            }
            if (posAt == -1) {
                return "Impossible";
            }
            for (int j = posAt; j < n; j += i) {
                for (int k = posAt; k < n; k += i) {
                    shouldBe[j][k] = false;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && shouldBe[i][j]) {
                    return "Impossible";
                }
            }
        }
        return "Possible";
    }
}
