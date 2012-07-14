import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class SinesSumQueries implements Solver {
//	private static double[] cos = new double[1000000];
//	private static double[] sin = new double[1000000];
//
//	static {
//		for (int i = 0; i < 1000000; i++) {
//			sin[i] = Math.sin(i - 500000);
//			cos[i] = Math.cos(i);
//		}
//	}
//
//	public static double myCos(int n) {
//		if (n < 500000 && n >= -500000)
//			return cos[n + 500000];
//		return Math.cos(n);
//
//	}

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int length = in.readInt();
		int queryCount = in.readInt();
		int[] left = new int[queryCount];
		int[] right = new int[queryCount];
		int[] delta = new int[queryCount];
		int[] ends = new int[2 * queryCount];
		IOUtils.readIntArrays(in, left, right, delta);
		for (int i = 0; i < queryCount; i++)
			left[i]--;
		System.arraycopy(left, 0, ends, 0, queryCount);
		System.arraycopy(right, 0, ends, queryCount, queryCount);
		Arrays.sort(ends);
		double[] sinValue = new double[ends.length];
		double[] cosValue = new double[ends.length];
		for (int i = 0; i < ends.length; i++) {
			getValue(ends[i], sinValue, cosValue, i);
		}
		IntervalTree tree = new IntervalTree(sinValue, cosValue);
		for (int i = 0; i < queryCount; i++) {
			left[i] = Arrays.binarySearch(ends, left[i]);
			right[i] = Arrays.binarySearch(ends, right[i]);
			if (delta[i] == 0)
				out.printf("%.10f\n", tree.getSegment(left[i], right[i]));
			else
				tree.putSegment(left[i], right[i], delta[i]);
		}
	}

	private void getValue(int n, double[] sinValue, double[] cosValue, int index) {
		if (n < 0)
			return;
		if (n == 0) {
			cosValue[index] = 1;
			return;
		}
		double ax = 1 - Math.cos(n + 1);
		double ay = -Math.sin(n + 1);
		double bx = 1 - Math.cos(1);
		double by = -Math.sin(1);
		double d = bx * bx + by * by;
		double cx = (ax * bx + ay * by) / d;
		double cy = (ay * bx - ax * by) / d;
		sinValue[index] = cy;
		cosValue[index] = cx;
	}

}

class IntervalTree {
	private static final Pair<Double, Double> ZERO = Pair.makePair(0., 0.);
	private int[] left;
	private int[] right;
	private double[] sin;
	private double[] cos;
	private double[] lastValueSin;
	private double[] lastValueCos;
	private long[] delta;

	public IntervalTree(double[] sinValue, double[] cosValue) {
		int size = sinValue.length - 1;
		int arraysSize = Math.max(1, Integer.highestOneBit(size) << 2);
		left = new int[arraysSize];
		right = new int[arraysSize];
		sin = new double[arraysSize];
		cos = new double[arraysSize];
		lastValueSin = new double[arraysSize];
		lastValueCos = new double[arraysSize];
		delta = new long[arraysSize];
		initTree(0, size, 0, sinValue, cosValue);
	}

	private void initTree(int left, int right, int root, double[] sinValue, double[] cosValue) {
		this.left[root] = left;
		this.right[root] = right;
		if (right - left > 1) {
			initTree(left, (left + right + 1) / 2, 2 * root + 1, sinValue, cosValue);
			initTree((left + right + 1) / 2, right, 2 * root + 2, sinValue, cosValue);
			sin[root] = sin[2 * root + 1] + sin[2 * root + 2];
			cos[root] = cos[2 * root + 1] + cos[2 * root + 2];
		} else {
			sin[root] = sinValue[left + 1] - sinValue[left];
			cos[root] = cosValue[left + 1] - cosValue[left];
		}
	}

	public void putSegment(int left, int right, long value) {
		putSegment(left, right, value, 0);
	}

	private void putSegment(int left, int right, long value, int root) {
		if (left >= this.right[root] || right <= this.left[root])
			return;
		if (left <= this.left[root] && right >= this.right[root]) {
			this.delta[root] += value;
			apply(sin[root], cos[root], value, root);
			sin[root] = lastValueSin[root];
			cos[root] = lastValueCos[root];
			return;
		}
		putSegment(left, right, value, 2 * root + 1);
		putSegment(left, right, value, 2 * root + 2);
		apply(sin[2 * root + 1] + sin[2 * root + 2], cos[2 * root + 1] + cos[2 * root + 2], delta[root], root);
		sin[root] = lastValueSin[root];
		cos[root] = lastValueCos[root];
	}

	public double getSegment(int left, int right) {
		getSegment(left, right, 0);
		return lastValueSin[0];
	}

	private void getSegment(int left, int right, int root) {
		if (left >= this.right[root] || right <= this.left[root]) {
			lastValueSin[root] = 0;
			lastValueCos[root] = 0;
			return;
		}
		if (left <= this.left[root] && right >= this.right[root]) {
			lastValueSin[root] = sin[root];
			lastValueCos[root] = cos[root];
		} else {
			getSegment(left, right, 2 * root + 1);
			getSegment(left, right, 2 * root + 2);
			apply(lastValueSin[2 * root + 1] + lastValueSin[2 * root + 2], lastValueCos[2 * root + 1] + lastValueCos[2 * root + 2], delta[root], root);
		}
	}

	private void apply(double sina, double cosa, long delta, int root) {
		double sinb = Math.sin(delta);
		double cosb = Math.cos(delta);
		lastValueSin[root] = sina * cosb + sinb * cosa;
		lastValueCos[root] = cosa * cosb - sina * sinb;
	}
}
