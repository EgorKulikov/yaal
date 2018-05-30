package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import net.egork.numbers.LongInteger;

import static java.lang.Math.log10;

public class DIdealnoeKodirovanie {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        LongInteger n = new LongInteger(s);
        if (s.equals("1")) {
            out.printLine(1);
            return;
        }
        double log3 = (log10(s.charAt(0) - '0') + (s.length() - 1)) / log10(3);
        int exponent = (int) Math.round(Math.floor(log3) - 2);
        exponent = Math.max(exponent, 0);
        LongInteger power = new LongInteger("3").power(exponent);
        while (power.multiply(9).compareTo(n) <= 0) {
            power.multiplyBy(3);
            exponent++;
        }
        if (power.multiply(2).compareTo(n) >= 0) {
            out.printLine(3 * exponent + 2);
            return;
        }
        if (power.multiply(3).compareTo(n) >= 0) {
            out.printLine(3 * exponent + 3);
            return;
        }
        if (power.multiply(4).compareTo(n) >= 0) {
            out.printLine(3 * exponent + 4);
            return;
        }
        if (power.multiply(6).compareTo(n) >= 0) {
            out.printLine(3 * exponent + 5);
            return;
        }
        out.printLine(3 * exponent + 6);
    }
}
