package net.egork;

import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class KillingAssistants {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        Rational unbiased = new Rational(m, n).multiply(new Rational(1, 4));
        Rational biased = new Rational(n - m, n).multiply(new Rational(2, 9));
        out.printLine(unbiased.divide(unbiased.add(biased)));
    }
}
