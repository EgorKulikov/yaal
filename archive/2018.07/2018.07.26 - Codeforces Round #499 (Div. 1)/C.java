package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.numbers.IntegerUtils.gcd;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] a = in.readIntArray(n);
        int g = k;
        for (int i : a) {
            g = gcd(i, g);
        }
        out.printLine(k / g);
        int[] answer = new int[k / g];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = i * g;
        }
        out.printLine(answer);
    }
}
