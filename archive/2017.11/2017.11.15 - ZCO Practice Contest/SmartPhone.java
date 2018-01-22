package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.util.Arrays.sort;
import static net.egork.io.InputReader.readLongArray;

public class SmartPhone {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long[] price = in.readLongArray(n);
        sort(price);
        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, price[i] * (n - i));
        }
        out.printLine(answer);
    }
}
