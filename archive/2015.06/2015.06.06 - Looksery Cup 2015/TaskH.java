package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long[] a = IOUtils.readLongArray(in, 4);
        double answer = Double.POSITIVE_INFINITY;
        int[] s = new int[4];
        for (int i = 0; i < 16; i++) {
            double k0 = a[0] * a[3] - a[1] * a[2];
            double k1 = 0;
            for (int j = 0; j < 4; j++) {
                s[j] = (i >> j & 1) == 1 ? -1 : 1;
                k1 += s[j] * a[j];
            }
            double k2 = s[0] * s[3] - s[1] * s[2];
            if (k2 == 0) {
                if (k1 == 0) {
                    if (k0 == 0) {
                        answer = 0;
                    }
                } else {
                    answer = Math.min(answer, Math.abs(k0 / k1));
                }
            } else {
                double d = k1 * k1 - 4 * k0 * k2;
                if (d >= 0) {
                    double x1 = (-k1 - Math.sqrt(d)) / (2 * k2);
                    double x2 = (-k1 + Math.sqrt(d)) / (2 * k2);
                    answer = Math.min(answer, Math.abs(x1));
                    answer = Math.min(answer, Math.abs(x2));
                }
            }
        }
//        answer = Math.min(answer, Math.max(Math.min(Math.abs(a[0]), Math.abs(a[3])), Math.min(Math.abs(a[1]), Math.abs(a[2]))));
        out.printLine(answer);
//        if (a[0] < 0) {
//            a[0] = -a[0];
//            a[1] = -a[1];
//        }
//        if (a[2] < 0) {
//            a[2] = -a[2];
//            a[3] = -a[3];
//        }
//        if (a[2] < 0) {
//            a[2] = -a[2];
//            a[3] = -a[3];
//        }
//        if (a[3] >= 0) {
//            if (a[0] == 0 && a[1] == 0 && a[2] == 0 && a[3] == 0) {
//                out.printLine(0);
//            } else {
//                out.printLine((double) Math.abs(a[0] * a[3] - a[1] * a[2]) / ((a[0] + a[1] + a[2] + a[3])));
//            }
//        } else {
//            a[3] = -a[3];
//            if (Math.min(a[0], a[3]) > Math.min(a[1], a[2])) {
//
//            }
//        }
    }
}
