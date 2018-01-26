package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefAndTheHiringEvent {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long k = in.readLong() - 1;
        if (k < 5) {
            out.printLine(k * 2);
            return;
        }
        k -= 5;
        long current = 20;
        int length = 2;
        while (k >= current) {
            k -= current;
            current *= 5;
            length++;
        }
        current /= 4;
        out.print((k / current + 1) * 2);
        k %= current;
        for (int i = 0; i < length - 1; i++) {
            current /= 5;
            out.print(k / current * 2);
            k %= current;
        }
        out.printLine();
    }
}
