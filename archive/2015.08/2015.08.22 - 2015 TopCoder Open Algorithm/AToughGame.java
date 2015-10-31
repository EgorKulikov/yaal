package net.egork;

public class AToughGame {
    public double expectedGain(int[] prob, int[] value) {
        int n = prob.length;
        double[] p = new double[n];
        for (int i = 0; i < n; i++) {
            p[i] = prob[i] / 1000d;
        }
        double[] q = new double[n];
        double mult = 1;
        for (int i = 0; i < n; i++) {
            q[i] = p[i] / (1 - (1 - p[i]) * mult);
            mult *= p[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            q[i] *= q[i + 1];
        }
        double[] times = new double[n];
        times[n - 1] = 1 / p[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            times[i] = times[i + 1] / p[i];
        }
        double answer = value[n - 1];
        for (int i = 1; i < n; i++) {
            answer += times[i] * q[i] * value[i - 1];
        }
        return answer;
    }
}
