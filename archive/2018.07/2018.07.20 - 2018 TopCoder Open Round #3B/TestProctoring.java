package net.egork;

public class TestProctoring {
    public double expectedTime(int[] p, int[] q) {
        int n = p.length;
        double[] pp = new double[n];
        for (int i = 0; i < n; i++) {
            pp[i] = (double)p[i] / q[i];
        }
        double[][] answer = new double[n + 1][1 << n];
        double[] coef = new double[n + 1];
        for (int i = 1; i < (1 << n); i++) {
            coef[n] = 1;
            for (int j = n - 1; j >= 0; j--) {
                if ((i >> j & 1) == 0) {
                    answer[j][i] = answer[j + 1][i];
                    coef[j] = coef[j + 1];
                } else {
                    answer[j][i] = answer[j + 1][i] * (1 - pp[j]) + answer[j + 1][i - (1 << j)] * pp[j];
                    coef[j] = coef[j + 1] * (1 - pp[j]);
                }
            }
            answer[0][i]++;
            answer[0][i] /= 1 - coef[0];
            for (int j = 1; j <= n; j++) {
                answer[j][i] += coef[j] * answer[0][i];
            }
        }
        return answer[0][(1 << n) - 1];
    }
}
