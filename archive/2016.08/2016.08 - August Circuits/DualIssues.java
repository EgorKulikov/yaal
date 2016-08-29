package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.io.IOUtils.readIntArray;
import static net.egork.numbers.IntegerUtils.isPrime;

public class DualIssues {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] a = readIntArray(in, n);
        int answer = -1;
        for (int i : a) {
            if (isPrime(i)) {
                answer = Math.max(answer, i * i);
            }
        }
        out.printLine(answer);
    }
}
