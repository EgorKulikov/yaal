package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndPrimeDivisors {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.readLong();
        long b = in.readLong();
        while (IntegerUtils.gcd(a, b) != 1) {
            b /= IntegerUtils.gcd(a, b);
        }
        out.printLine(b == 1 ? "Yes" : "No");
    }
}
