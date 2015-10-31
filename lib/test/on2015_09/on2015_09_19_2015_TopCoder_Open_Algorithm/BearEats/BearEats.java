package on2015_09.on2015_09_19_2015_TopCoder_Open_Algorithm.BearEats;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.misc.ArrayUtils;

public class BearEats {
	static final long MOD = (long) (1e9 + 7);

	public long getDifference(int N, int R, int C, int D, int A_MAX, int B_MAX) {
		int[] a = new int[N];
		int[] b = new int[N];
		for (int i = 0; i < N; i++) {
			R = (int) (((long)C * R + D) % MOD);
			a[i] = R % A_MAX;
			R = (int) (((long)C * R + D) % MOD);
			b[i] = R % B_MAX;
		}
		int[] order = ArrayUtils.createOrder(N);
		ArrayUtils.sort(order, (i, j) -> b[i] != b[j] ? b[i] - b[j] : a[i] - a[j]);
		ArrayUtils.reverse(order);
		ArrayUtils.orderBy(ArrayUtils.reversePermutation(order), a, b);
		long[] delta = new long[N];
		for (int i = 0; i < N; i++) {
			delta[i] = a[i] + b[i];
		}
		order = ArrayUtils.order(delta);
		ArrayUtils.reverse(order);
		IntervalTree tree = new LongIntervalTree(N) {
			@Override
			protected long joinValue(long left, long right) {
				return Math.max(left, right);
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return was + delta;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				return value + delta;
			}

			@Override
			protected long neutralValue() {
				return Long.MIN_VALUE;
			}

			@Override
			protected long neutralDelta() {
				return 0;
			}

			@Override
			protected long initValue(int index) {
				return -index - 1;
			}
		};
		boolean[] our = new boolean[N];
		for (int i : order) {
			if (tree.query(i, N - 1) >= 0) {
				continue;
			}
//			int already = (int) tree.query(0, i);
//			if (already * 2 > i) {
//				continue;
//			}
			our[i] = true;
			tree.update(i, N - 1, 2);
		}
		long answer = 0;
		for (int i = 0; i < N; i++) {
			if (our[i]) {
				answer += a[i];
			} else {
				answer -= b[i];
			}
		}
		return answer;
	}
}
