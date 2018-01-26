package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class RohithAndCircles {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int n0 = in.readInt();
        int p = in.readInt();
        int m = in.readInt();
        int b = in.readInt();
        double[] r = IOUtils.readDoubleArray(in, 4);
        double rr = (1 / (2 * r[1]) - 1 / (2 * r[0])) / 2;
        double y = (1 / (2 * r[1]) + 1 / (2 * r[0])) / 2;
        double left = 0;
        double right = 1;
        while (true) {
            double dCenter = Math.hypot(y, right);
            double rCand = (1 / (dCenter - rr) - 1 / (dCenter + rr)) / 2;
            if (rCand < r[2]) {
                break;
            }
            right *= 2;
        }
        for (int i = 0; i < 1000; i++) {
            double middle = (left + right) / 2;
            double dCenter = Math.hypot(y, middle);
            double rCand = (1 / (dCenter - rr) - 1 / (dCenter + rr)) / 2;
            if (rCand < r[2]) {
                right = middle;
            } else {
                left = middle;
            }
        }
        double x = (left + right) / 2;
        double dCenter = Math.hypot(y, x + 2 * rr);
        double rCand1 = (1 / (dCenter - rr) - 1 / (dCenter + rr)) / 2;
        dCenter = Math.hypot(y, x - 2 * rr);
        double rCand2 = (1 / (dCenter - rr) - 1 / (dCenter + rr)) / 2;
        double dx;
        if (Math.abs(rCand1 - r[3]) < Math.abs(rCand2 - r[3])) {
            dx = 2 * rr;
        } else {
            dx = -2 * rr;
        }
        double answer = 0;
        for (int i = 0; i < count; i++) {
            n0 = (int) (((long)p * n0) % m + b);
            if (n0 <= 4) {
                answer += r[n0 - 1];
            } else {
                dCenter = Math.hypot(y, x + dx * (n0 - 3));
                answer += (1 / (dCenter - rr) - 1 / (dCenter + rr)) / 2;
            }
        }
        out.printFormat("%.6f\n", answer);
    }
}
