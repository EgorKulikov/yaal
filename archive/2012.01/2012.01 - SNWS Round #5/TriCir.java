package net.egork;

import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TriCir {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		List<Long> circles = new ArrayList<Long>();
		List<Rational> triangles = new ArrayList<Rational>();
		Comparator<Rational> comparator = new Comparator<Rational>() {
			public int compare(Rational o1, Rational o2) {
				return BigInteger.valueOf(o1.numerator).multiply(BigInteger.valueOf(o2.denominator)).compareTo(
					BigInteger.valueOf(o2.numerator).multiply(BigInteger.valueOf(o1.denominator)));
			}
		};
		for (int i = 0; i < count; i++) {
			if (in.readInt() == 1) {
				long radius = in.readInt();
				circles.add(radius * radius);
			} else {
				long a = in.readInt();
				long b = in.readInt();
				long c = in.readInt();
				long maxSide = Math.max(a, Math.max(b, c));
				if (2 * maxSide * maxSide >= a * a + b * b + c * c)
					triangles.add(new Rational(maxSide * maxSide, 4));
				else
					triangles.add(new Rational(a * b * c * a * b * c, (a + b + c) * (a + b - c) * (a + c - b) * (b + c - a)));
			}
		}
		Collections.sort(circles);
		Collections.sort(triangles, comparator);
		int circleIndex = 0;
		int answer = 0;
		for (Rational triangle : triangles) {
			while (circleIndex < circles.size() && circles.get(circleIndex) * triangle.denominator < triangle.numerator)
					circleIndex++;
			if (circleIndex == circles.size())
				break;
			answer++;
			circleIndex++;
		}
		out.printLine(answer);
	}
}
