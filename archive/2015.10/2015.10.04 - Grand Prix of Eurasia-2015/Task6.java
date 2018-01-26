package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.math.BigInteger;

public class Task6 {
    private static final double EPS = 1e-9;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            Rational a = getRational(in);
            Rational b = getRational(in);
            Rational c = getRational(in);
            if (a.compareTo(Rational.ZERO) <= 0 || b.compareTo(Rational.ZERO) <= 0 || c.compareTo(Rational.ZERO) <= 0 || b.multiply(c).compareTo(Rational.ONE) <= 0) {
                out.printLine("NO");
            } else {
                Rational div = b.multiply(c).subtract(Rational.ONE);
                Rational y = b.add(Rational.ONE).divide(div);
                Rational x = c.add(Rational.ONE).divide(div);
                if (x.add(y).compareTo(a) < 0) {
                    out.printLine("YES");
                } else {
                    out.printLine("NO");
                }
            }
        }
    }

    private Rational getRational(InputReader in) {
        return new Rational(Math.round(in.readDouble() * 1e5), 100000).subtract(Rational.ONE);
    }

    static class Rational {
        public static final Rational ONE = new Rational(1, 1);
        public static final Rational ZERO = new Rational(0, 1);
        BigInteger x;
        BigInteger y;

        Rational(long x, long y) {
            this.x = BigInteger.valueOf(x);
            this.y = BigInteger.valueOf(y);
        }

        public Rational(BigInteger x, BigInteger y) {
            this.x = x;
            this.y = y;
        }

        public Rational add(Rational o) {
            return new Rational(x.multiply(o.y).add(y.multiply(o.x)), y.multiply(o.y));
        }

        public Rational multiply(Rational c) {
            return new Rational(x.multiply(c.x), y.multiply(c.y));
        }

        public Rational subtract(Rational o) {
            return new Rational(x.multiply(o.y).subtract(y.multiply(o.x)), y.multiply(o.y));
        }

        public Rational divide(Rational c) {
            return new Rational(x.multiply(c.y), y.multiply(c.x));
        }

        public int compareTo(Rational a) {
            return x.multiply(a.y).compareTo(a.x.multiply(y));
        }
    }
}
