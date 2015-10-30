package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        BigInteger val = BigInteger.ONE;
        boolean[][] possible = new boolean[10][10];
        int a = in.readInt();
        int b = in.readInt();
        int target = 9 * 4 + 1;
        if (b == 0 || b % 2 == 1 && (a != 1 || b != 1)) {
            out.printLine(-1);
            return;
        }
        int n = 0;
        while (target != 0) {
            int last = val.mod(BigInteger.TEN).intValue();
            BigInteger copy = val;
            while (copy.compareTo(BigInteger.TEN) >= 0) {
                copy = copy.divide(BigInteger.TEN);
            }
            int first = copy.intValue();
            if (!possible[first][last]) {
                possible[first][last] = true;
//                System.err.println(val);
                target--;
            }
            if (first == a && last == b) {
                out.printLine(n);
                return;
            }
            n++;
            val = val.add(val);
        }
    }
}
