package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class BalancedSequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        char[] sequence = in.readCharArray(n);
        int balance = 0;
        int minBalance = 0;
        for (char c : sequence) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            minBalance = Math.min(minBalance, balance);
        }
        if (balance == 0 && minBalance == 0) {
            out.printLine(0);
            return;
        }
        if (balance == minBalance || minBalance == 0) {
            out.printLine(1);
            return;
        }
        out.printLine(2);
    }
}
