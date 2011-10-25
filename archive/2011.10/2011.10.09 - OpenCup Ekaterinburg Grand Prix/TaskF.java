import net.egork.utils.Solver;

import java.awt.Point;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskF implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[][] distances = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				distances[i][j] = in.readInt();
		}
		int[] x = new int[count];
		int[] y = new int[count];
		boolean allSame = true;
		for (int i = 1; i < count && allSame; i++) {
			if (distances[0][i] != 0)
				allSame = false;
		}
		if (allSame) {
			for (int i = 1; i < count && allSame; i++) {
				for (int j = 0; j < count && allSame; j++) {
					if (distances[i][j] != 0)
						allSame = false;
				}
			}
			if (!allSame) {
				out.println("Impossible");
				return;
			}
			for (int i = 0; i < count; i++)
				out.println("0 0");
			return;
		}
		int swap = -1;
		for (int i = 1; i < count; i++) {
			if (distances[0][i] != 0) {
				swap = i;
				for (int j = 0; j < count; j++) {
					if (j == i || j == 1)
						continue;
					int temp = distances[j][i];
					distances[j][i] = distances[j][1];
					distances[j][1] = temp;
					temp = distances[i][j];
					distances[i][j] = distances[1][j];
					distances[1][j] = temp;
				}
				break;
			}
		}
		for (int i = 0; i * i < distances[0][1]; i++) {
			int j = (int) (Math.sqrt(distances[0][1] - i * i) + .5);
			if (i * i + j * j != distances[0][1])
				continue;
			x[1] = i;
			y[1] = j;
			if (canPlace(x, y, distances, 2)) {
				int temp = x[swap];
				x[swap] = x[1];
				x[1] = temp;
				temp = y[swap];
				y[swap] = y[1];
				y[1] = temp;
				for (int k = 0; k < count; k++)
					out.println(x[k] + " " + y[k]);
				return;
			}
		}
		out.println("Impossible");
	}

	private boolean canPlace(int[] x, int[] y, int[][] distances, int step) {
		if (step == distances.length)
			return true;
		List<Point> candidates = getCandidates(distances[0][step], distances[1][step], x[1], y[1]);
		for (Point candidate : candidates) {
			boolean good = true;
			x[step] = candidate.x;
			y[step] = candidate.y;
			for (int i = 2; i < step && good; i++) {
				if (distances[i][step] != distance(x, y, i, step))
					good = false;
			}
			if (good) {
				if (canPlace(x, y, distances, step + 1))
					return true;
			}
		}
		return false;
	}

	private List<Point> getCandidates(int a0, int a1, long x0, long y0) {
		Rational alpha = new Rational(BigInteger.valueOf(2 * x0), BigInteger.ONE);
		Rational beta = new Rational(BigInteger.valueOf(2 * y0), BigInteger.ONE);
		Rational gamma = new Rational(BigInteger.valueOf(a0 - a1 + x0 * x0 + y0 * y0), BigInteger.ONE);
		Rational p = gamma.divide(beta);
		Rational q = alpha.negate().divide(beta);
		Rational k = Rational.ONE.add(q.multiply(q));
		Rational l = Rational.valueOf(2).multiply(p).multiply(q);
		Rational m = p.multiply(p).subtract(Rational.valueOf(a0));
		Rational d = l.multiply(l).subtract(Rational.valueOf(4).multiply(k).multiply(m)).sqrt();
		if (d == null)
			return Collections.emptyList();
		Rational x1 = d.subtract(l).divide(Rational.valueOf(2).multiply(k));
		Rational x2 = d.negate().subtract(l).divide(Rational.valueOf(2).multiply(k));
		Rational[] x;
		if (x1.equals(x2))
			x = new Rational[]{x1};
		else
			x = new Rational[]{x1, x2};
		Rational[] y = new Rational[x.length];
		for (int i = 0; i < x.length; i++)
			y[i] = p.add(q.multiply(x[i]));
		List<Point> result = new ArrayList<Point>();
		for (int i = 0; i < x.length; i++) {
			if (x[i].q.equals(BigInteger.ONE) && y[i].q.equals(BigInteger.ONE))
				result.add(new Point(x[i].p.intValue(), y[i].p.intValue()));
		}
		return result;
	}

	private long distance(int[] x, int[] y, int index1, int index2) {
		long first = x[index1] - x[index2];
		long second = y[index1] - y[index2];
		return first * first + second * second;
	}
}

class Rational {
	public final BigInteger p;
	public final BigInteger q;
	private static final BigInteger TWO = BigInteger.valueOf(2);

	public static final Rational ONE = new Rational(BigInteger.ONE, BigInteger.ONE);

	public static Rational valueOf(long x) {
		return new Rational(BigInteger.valueOf(x), BigInteger.ONE);
	}

	Rational(BigInteger p, BigInteger q) {
		if (q.compareTo(BigInteger.ZERO) < 0) {
			q = q.negate();
			p = p.negate();
		}
		if (q.equals(BigInteger.ZERO))
			throw new ArithmeticException();
		BigInteger g = p.gcd(q);
		this.p = p.divide(g);
		this.q = q.divide(g);
	}

	public Rational add(Rational other) {
		return new Rational(p.multiply(other.q).add(other.p.multiply(q)), q.multiply(other.q));
	}

	public Rational subtract(Rational other) {
		return new Rational(p.multiply(other.q).subtract(other.p.multiply(q)), q.multiply(other.q));
	}

	public Rational multiply(Rational other) {
		return new Rational(p.multiply(other.p), q.multiply(other.q));
	}

	public Rational divide(Rational other) {
		return new Rational(p.multiply(other.q), q.multiply(other.p));
	}

	public Rational sqrt() {
		BigInteger p = sqrt(this.p);
		BigInteger q = sqrt(this.q);
		if (p == null || q == null)
			return null;
		return new Rational(p, q);
	}

	public Rational negate() {
		return new Rational(p.negate(), q);
	}

	private static BigInteger sqrt(BigInteger p) {
		if (p.equals(BigInteger.ZERO))
			return p;
		if (p.compareTo(BigInteger.ZERO) < 0)
			return null;
		BigInteger left = BigInteger.ONE;
		BigInteger right = p;
		while (left.compareTo(right) <= 0) {
			BigInteger middle = left.add(right).divide(TWO);
			int value = middle.multiply(middle).compareTo(p);
			if (value == 0)
				return middle;
			if (value > 0)
				right = middle.subtract(BigInteger.ONE);
			else
				left = middle.add(BigInteger.ONE);
		}
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Rational rational = (Rational) o;

		if (p != null ? !p.equals(rational.p) : rational.p != null)
			return false;
		if (q != null ? !q.equals(rational.q) : rational.q != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = p != null ? p.hashCode() : 0;
		result = 31 * result + (q != null ? q.hashCode() : 0);
		return result;
	}
}
