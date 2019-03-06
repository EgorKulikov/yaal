package net.egork;

import static java.util.Arrays.fill;

public class ReconstructNumber {
    public String smallest(String comparisons) {
        int n = comparisons.length();
        boolean[][] can = new boolean[n + 1][10];
        fill(can[n], true);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (can[i + 1][k] && correct(j, k, comparisons.charAt(i))) {
                        can[i][j] = true;
                        break;
                    }
                }
            }
        }
        StringBuilder builder = new StringBuilder(n + 1);
        for (int i = 0; i <= n; i++) {
            for (int j = (i == 0) ? 1 : 0; j < 10; j++) {
                if (can[i][j] && (i == 0 || correct(builder.charAt(i - 1) - '0', j, comparisons.charAt(i - 1)))) {
                    builder.append(j);
                    break;
                }
            }
            if (builder.length() == 0) {
                return "";
            }
        }
        return builder.toString();
    }

    private boolean correct(int a, int b, char c) {
        if (c == '>') {
            return a > b;
        }
        if (c == '<') {
            return a < b;
        }
        if (c == '=') {
            return a == b;
        }
        return a != b;
    }
}
