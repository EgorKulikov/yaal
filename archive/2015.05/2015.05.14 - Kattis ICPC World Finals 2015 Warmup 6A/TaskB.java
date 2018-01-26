package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (in.isExhausted()) {
            throw new UnknownError();
        }
        int degree = in.readInt();
        double[] a = IOUtils.readDoubleArray(in, degree + 1);
        double xLow = in.readDouble();
        double xHigh = in.readDouble();
        int increment = in.readInt();
        double[] b = new double[2 * degree + 2];
        for (int i = 0; i <= 2 * degree; i++) {
            for (int j = Math.max(i - degree, 0); j <= i && j <= degree; j++) {
                b[i + 1] += a[j] * a[i - j];
            }
            b[i + 1] *= Math.PI;
            b[i + 1] /= i + 1;
        }
        double base = value(b, xLow);
        double end = value(b, xHigh);
        out.print("Case " + testNumber + ": ");
        out.printFormat("%.2f\n", end - base);
        if (end - base < increment) {
            out.printLine("insufficient volume");
            return;
        }
        double x = xLow;
        for (int i = 1; i <= 8; i++) {
            if (end - base < increment) {
                break;
            }
            double left = x;
            double right = xHigh;
            for (int j = 0; j < 100; j++) {
                double middle = (left + right) / 2;
                if (value(b, middle) - base < increment) {
                    left = middle;
                } else {
                    right = middle;
                }
            }
            x = left;
            base += increment;
            if (i != 1) {
                out.print(' ');
            }
            out.printFormat("%.2f", x - xLow);
        }
        out.printLine();
    }

    private double value(double[] a, double x) {
        double value = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            value *= x;
            value += a[i];
        }
        return value;
    }
}
