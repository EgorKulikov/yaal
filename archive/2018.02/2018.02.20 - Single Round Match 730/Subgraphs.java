package net.egork;

import static java.lang.Math.max;

public class Subgraphs {
    public String[] findGroups(int k) {
        boolean[][] g = new boolean[2 * k][2 * k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < i; j++) {
                g[i][j] = g[j][i] = true;
            }
            for (int j = 0; j <= i; j++) {
                g[i][j + k] = g[j + k][i] = true;
            }
        }
        long[] answer = new long[k * (k - 1) / 2 + 1];
        for (int i = 0; i < answer.length; i++) {
            int remaining = i;
            int left = 0;
            for (int j = 0; ; j++) {
                if (remaining < j) {
                    break;
                }
                left++;
                answer[i] += 1L << j;
                remaining -= j;
            }
            int right = 0;
            for (int j = 0; j < k && left + right < k; j++) {
                int current = max(0, left - j);
                if (current <= remaining) {
                    remaining -= current;
                    right++;
                    answer[i] += 1L << (j + k);
                }
            }
            if (left + right != k || remaining != 0) {
                throw new RuntimeException();
            }
        }
        String[] out = new String[2 * k + answer.length];
        for (int i = 0; i < 2 * k; i++) {
            out[i] = "";
            for (int j = 0; j < 2 * k; j++) {
                if (g[i][j]) {
                    out[i] += '1';
                } else {
                    out[i] += '0';
                }
            }
        }
        for (int i = 0; i < answer.length; i++) {
            out[i + 2 * k] = "";
            for (int j = 0; j < 2 * k; j++) {
                if ((answer[i] >> j & 1) == 1) {
                    out[i + 2 * k] += 'Y';
                } else {
                    out[i + 2 * k] += 'N';
                }
            }
        }
        for (int i = 0; i < answer.length; i++) {
            int qty = 0;
            for (int j = 0; j < 2 * k; j++) {
                if (out[i + 2 * k].charAt(j) == 'N') {
                    continue;
                }
                for (int l = 0; l < j; l++) {
                    if (out[i + 2 * k].charAt(l) == 'Y' && out[l].charAt(j) == '1') {
                        qty++;
                    }
                }
            }
            if (qty != i) {
                throw new RuntimeException();
            }
        }
        return out;
    }
}
