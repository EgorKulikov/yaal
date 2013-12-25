import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Integerpoints implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int p = in.readInt();
		int q = in.readInt();
		int n = in.readInt();
		long a = 0;
		long b = 1;
		long d = p * q;
		long c = q;
		for (int i = 2; i * i <= d; ++i)
			while (d % (i * i) == 0) {
				d /= i * i;
				b *= i;
			}
		sqd = Math.sqrt(d);
		if (d == 1)
			out.println(solveRational(n, a + b, c));
		else
			out.println(n + 1 + sumIntegerParts(n, a, b, c, d));
	}

	double sqd;

	private long solveRational(long n, long p, long q) {
		long g = gcd(p, q);
		p /= g;
		q /= g;
		long k = n / q;
		long n0 = k * q;
		long res = ((p + 1) * (q + 1) / 2) * k + 1;
		res += p * q * ((k * (k - 1)) / 2);
		for (long i = n0 + 1; i <= n; ++i) {
			res += (i * p / q) + 1;
		}
		return res;
	}

	long gcd(long a, long b) {
		while (b > 0) {
			long t = a % b;
			a = b;
			b = t;
		}
		return a;
	}

	private long sumIntegerParts(long n, long a, long b, long c, long d) {
		if (n == 0)
			return 0;
		if (b == 0) throw new RuntimeException();
		if (c < 0) {
			a = -a;
			b = -b;
			c = -c;
		}
		long g = gcd(gcd(Math.abs(a), Math.abs(b)), Math.abs(c));
		a /= g;
		b /= g;
		c /= g;
		long whole = integerPart(a, b, c, d);
		a -= c * whole;
		long res = whole * (n * (n + 1) / 2);
		long lastIntegerPart = integerPart(a * n, b * n, c, d);
		if (lastIntegerPart > 0) {
			res += lastIntegerPart * n - sumIntegerParts(lastIntegerPart, a * c, -b * c, a * a - b * b * d, d);
		}
		return res;
	}

	private long integerPart(long a, long b, long c, long d) {
		long whole = (long) ((a + b * sqd) / c - 1e-5);
		if (greaterThanZero(a - c * (whole + 1), b, c, d))
			++whole;
		return whole;
	}

	private boolean greaterThanZero(long a, long b, long c, long d) {
		long ma = -a;
		if (b > 0) {
			if (ma < 0)
				return true;
			return b * b * d - ma * ma >= 0;
		} else {
			b = -b;
			ma = -ma;
			if (ma < 0)
				return false;
			return b * b * d - ma * ma <= 0;
		}
	}
}

