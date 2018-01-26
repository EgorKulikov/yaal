package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class GameWithABoomerang {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        long base = 2;
        while (true) {
            long current = base * 4 - 2;
            if (current > n) {
                break;
            }
            base = current;
        }
        long delta = n - base;
        if (delta * 2 < n) {
            out.printLine(delta + 1);
            return;
        }
        long add = base + 1;
        base *= 2;
        delta = n - base;
        add += delta / 2 * 3;
        delta -= delta / 2 * 2;
        add += delta;
        out.printLine(add + 1);
    }
}
