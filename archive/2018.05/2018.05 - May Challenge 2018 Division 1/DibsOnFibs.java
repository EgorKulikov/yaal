package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static net.egork.misc.ArrayUtils.sumArray;
import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.generateFibonacci;

public class DibsOnFibs {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int m = in.readInt();
        int n = in.readInt();
        long[] f = generateFibonacci(n, MOD7);
        int[] a = in.readIntArray(m);
        int[] b = in.readIntArray(m);
        if (n == 1) {
            out.printLine(sumArray(a) % MOD7 * m % MOD7);
        } else if (n == 2) {
            out.printLine(sumArray(b) % MOD7 * m % MOD7);
        } else {
            out.printLine((sumArray(a) % MOD7 * f[n - 3] % MOD7 + sumArray(b) % MOD7 * f[n - 2] % MOD7) * m % MOD7);
        }
    }
}
