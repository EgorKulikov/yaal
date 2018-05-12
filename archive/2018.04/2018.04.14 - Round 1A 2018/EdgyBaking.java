package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.hypot;

public class EdgyBaking {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int p = in.readInt();
        int[] w = new int[n];
        int[] h = new int[n];
        in.readIntArrays(w, h);
        double current = p;
        for (int i = 0; i < n; i++) {
            current -= 2 * w[i] + 2 * h[i];
        }
        double[] min = new double[n];
        double[] max = new double[n];
        for (int i = 0; i < n; i++) {
            min[i] = 2 * Math.min(w[i], h[i]);
            max[i] = 2 * hypot(w[i], h[i]);
        }
        boolean[] used = new boolean[n];
        double from = current;
        double to = current;
        double answer = current;
        while (true) {
            for (int i = 0; i < n; i++) {
                if (used[i]) {
                    continue;
                }
                if (max[i] >= from && to >= min[i]) {
                    answer = 0;
                    break;
                }
                if (from >= max[i]) {
                    answer = Math.min(answer, from - max[i]);
                }
                for (int j = 0; j < i; j++) {
                    if (used[j]) {
                        continue;
                    }
                    double cMin = min[i] + min[j];
                    double cMax = max[i] + max[j];
                    if (cMax >= from && to >= cMin) {
                        answer = 0;
                        break;
                    }
                    if (from >= cMax) {
                        answer = Math.min(answer, from - cMax);
                    }
                }
            }
            double maxDif = -1;
            int at = -1;
            for (int i = 0; i < n; i++) {
                if (used[i]) {
                    continue;
                }
                if (min[i] <= to) {
                    double dif = max[i] - min[i];
                    if (dif > maxDif) {
                        maxDif = dif;
                        at = i;
                    }
                }
            }
            if (at == -1) {
                break;
            }
            from -= max[at];
            to -= min[at];
            used[at] = true;
        }
        out.printLine("Case #" + testNumber + ":", p - answer);
    }
}
