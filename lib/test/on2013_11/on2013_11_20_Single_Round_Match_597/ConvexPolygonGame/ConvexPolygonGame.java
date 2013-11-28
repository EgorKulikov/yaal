package on2013_11.on2013_11_20_Single_Round_Match_597.ConvexPolygonGame;



import net.egork.collections.intcollection.IntPair;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

public class ConvexPolygonGame {
    public String winner(int[] X, int[] Y) {
		int minX = ArrayUtils.minElement(X);
		int maxX = ArrayUtils.maxElement(X);
		IntPair first = null;
		IntPair second = null;
		boolean hasSegment = false;
		for (int i = minX; i <= maxX; i++) {
			int firstMin = Integer.MIN_VALUE;
			int firstMax = Integer.MIN_VALUE;
			int secondMin = Integer.MIN_VALUE;
			int secondMax = Integer.MIN_VALUE;
			for (int j = 0; j < X.length; j++) {
				int last = j - 1;
				if (last == -1)
					last = X.length - 1;
				if (X[j] == i) {
					if (firstMin == Integer.MIN_VALUE) {
						firstMin = Y[j] - 1;
						firstMax = Y[j] + 1;
					} else {
						secondMin = Y[j] - 1;
						secondMax = Y[j] + 1;
					}
				} else if (X[j] > i && X[last] < i || X[j] < i && X[last] > i) {
					long numerator = (long)Y[last] * (i - X[j]) + (long)Y[j] * (X[last] - i);
					long denominator = X[last] - X[j];
					long g = IntegerUtils.gcd(numerator, denominator);
					numerator /= g;
					denominator /= g;
					if (denominator < 0) {
						numerator = -numerator;
						denominator = -denominator;
					}
					if (denominator == 1) {
						if (firstMin == Integer.MIN_VALUE) {
							firstMin = (int) numerator;
							firstMax = (int) numerator;
						} else {
							secondMin = (int) numerator;
							secondMax = (int) numerator;
						}
					} else {
						int at = (int) IntegerUtils.trueDivide(numerator, denominator);
						if (firstMin == Integer.MIN_VALUE) {
							firstMin = at;
							firstMax = at + 1;
						} else {
							secondMin = at;
							secondMax = at + 1;
						}
					}
				}
			}
			if (firstMin == Integer.MIN_VALUE || secondMin == Integer.MIN_VALUE)
				continue;
			if (firstMin > secondMin || firstMax > secondMax) {
				int temp = firstMin;
				firstMin = secondMin;
				secondMin = temp;
				temp = firstMax;
				firstMax = secondMax;
				secondMax = temp;
			}
			if (firstMax < secondMin) {
				if (hasSegment || first != null)
					return "Masha";
				hasSegment = true;
			} else if (firstMax == secondMin) {
				if (hasSegment)
					return "Masha";
				if (first == null)
					first = new IntPair(i, firstMax);
				else if (second == null) {
					second = new IntPair(i, firstMax);
				} else {
					if ((long)(second.first - first.first) * (firstMax - first.second) != (long)(i - first.first) * (second.second - first.second))
						return "Masha";
				}
			}
		}
		return "Petya";
    }
}
