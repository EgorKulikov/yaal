package on2015_01.on2015_01_12_Single_Round_Match_645.ArmyTeleportation;



import net.egork.collections.comparators.IntComparator;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

public class ArmyTeleportation {
    public String ifPossible(int[] x1, int[] y1, int[] x2, int[] y2, int[] xt, int[] yt) {
		if (check(x1, y1, x2, y2, xt, yt)) return "possible";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < x1.length; j++) {
				x1[j] = 2 * xt[i] - x1[j];
				y1[j] = 2 * yt[i] - y1[j];
			}
			if (check(x1, y1, x2, y2, xt, yt)) return "possible";
			for (int j = 0; j < x1.length; j++) {
				x1[j] = 2 * xt[i] - x1[j];
				y1[j] = 2 * yt[i] - y1[j];
			}
		}
		return "impossible";
    }

	private boolean check(int[] x1, int[] y1, int[] x2, int[] y2, int[] xt, int[] yt) {
		int count = x1.length;
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				if (x1[first] != x1[second]) return x1[first] - x1[second];
				return y1[first] - y1[second];
			}
		});
		ArrayUtils.orderBy(ArrayUtils.reversePermutation(order), x1, y1);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				if (x2[first] != x2[second]) return x2[first] - x2[second];
				return y2[first] - y2[second];
			}
		});
		ArrayUtils.orderBy(ArrayUtils.reversePermutation(order), x2, y2);
		long dx = x2[0] - x1[0];
		long dy = y2[0] - y1[0];
		for (int i = 0; i < count; i++) {
			if (x2[i] - x1[i] != dx || y2[i] - y1[i] != dy) return false;
		}
		long sx1 = 2 * xt[1] - 2 * xt[0];
		long sx2 = 2 * xt[2] - 2 * xt[0];
		long sy1 = 2 * yt[1] - 2 * yt[0];
		long sy2 = 2 * yt[2] - 2 * yt[0];
		long d = sx2 * sy1 - sy2 * sx1;
		long d1 = sx1 * dy - sy1 * dx;
		long d2 = sx2 * dy - sy2 * dx;
		if (dx == 0 && dy == 0) return true;
		if (d == 0) {
			if (d1 != 0 || d2 != 0) return false;
			if (sx1 == 0) {
				long gcd = IntegerUtils.gcd(sy1, sy2);
				return dy % gcd == 0;
			}
			return dx % IntegerUtils.gcd(sx1, sx2) == 0;
		}
		return d1 % d == 0 && d2 % d == 0;
	}
}
