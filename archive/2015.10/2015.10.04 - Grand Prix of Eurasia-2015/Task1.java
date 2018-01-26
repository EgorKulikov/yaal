package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Task1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        double[] x = new double[n];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.readDouble();
            y[i] = in.readDouble();
        }
        for (int j = 0; j < m; j++) {
            double x1 = in.readDouble();
            double y1 = in.readDouble();
            double x2 = in.readDouble();
            double y2 = in.readDouble();
            double a = y1 - y2;
            double b = x2 - x1;
            double d = Math.hypot(a, b);
            a /= d;
            b /= d;
            double c = -(a * x1 + b * y1);
            for (int i = 0; i < n; i++) {
                double dd = a * x[i] + b * y[i] + c;
                if (dd < 0) {
                    x[i] += a * (-2 * dd);
                    y[i] += b * (-2 * dd);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            out.printLine(x[i], y[i]);
        }

    }
}
