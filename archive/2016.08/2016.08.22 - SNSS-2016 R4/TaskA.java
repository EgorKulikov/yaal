package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.util.Arrays.fill;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long k = in.readLong();
        boolean[] input = new boolean[n];
        for (int i = 0; i < n; i++) {
            input[i] = in.readCharacter() == 'R';
        }
        boolean[] next = new boolean[n];
        for (int j = 49; j >= 0; j--) {
            if ((k >> j & 1) == 0) {
                continue;
            }
            fill(next, false);
            for (int i = 0; i < n; i++) {
                int at1 = (int) ((i + (1L << j)) % n);
                next[i] ^= input[at1];
                int at2 = (int) ((i - (1L << j)) % n);
                if (at2 < 0) {
                    at2 += n;
                }
                next[i] ^= input[at2];
            }
            boolean[] temp = input;
            input = next;
            next = temp;
        }
        for (int i = 0; i < n; i++) {
            out.print(input[i] ? 'R' : 'L');
        }
        out.printLine();
    }
}
