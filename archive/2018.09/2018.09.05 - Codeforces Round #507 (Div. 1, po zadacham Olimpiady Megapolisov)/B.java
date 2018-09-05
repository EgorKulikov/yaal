package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Random random = new Random();
        long n = in.readLong();
        int k = in.readInt();
        long l = 1;
        long r = n;
        while (true) {
            long a;
            long b;
            if (r - l < 5 * k) {
                a = l + random.nextInt((int) (r - l + 1));
                b = a;
            } else {
                a = l;
                b = (l + r) >> 1;
            }
            out.printLine(a, b);
            out.flush();
            String answer = in.readString();
            if ("Bad".equals(answer) || a == b && "Yes".equals(answer)) {
                return;
            }
            if ("Yes".equals(answer)) {
                l = max(a - k, 1);
                r = min(b + k, n);
            } else {
                if (l == a) {
                    l = max(b + 1 - k, 1);
                } else {
                    l = max(l - k, 1);
                }
                if (r == b) {
                    r = min(a - 1 + k, n);
                } else {
                    r = min(r + k, n);
                }
            }
        }
    }
}
