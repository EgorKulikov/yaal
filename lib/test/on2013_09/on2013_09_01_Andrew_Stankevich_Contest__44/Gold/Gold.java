package on2013_09.on2013_09_01_Andrew_Stankevich_Contest__44.Gold;



import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Gold {
	private static final int INFINITY = (int) (1e9 + 2);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int[] x = new int[count];
		int[] y = new int[count];
		int[] color = new int[count];
		IOUtils.readIntArrays(in, x, y, color);
		Answer answer = new Answer();
		answer.result = -1;
		for (int direction = 0; direction < 4; direction++) {
			for (int first = 0; first < 3; first++) {
				for (int second = 0; second < 3; second++) {
					if (first == second)
						continue;
					int third = 3 - first - second;
					Answer candidate = order(x, y, color, first, second, third);
					if (candidate.result > answer.result) {
						for (int i = direction; i < 4; i++)
							candidate.rotate();
						answer = candidate;
					}
					candidate = triangle(x, y, color, first, second, third);
					if (candidate.result > answer.result) {
						for (int i = direction; i < 4; i++)
							candidate.rotate();
						answer = candidate;
					}
				}
			}
			rotate(x, y);
		}
		out.printLine(answer.result);
		for (int i = 0; i < 3; i++)
			out.printLine(answer.x0[i], answer.y0[i], answer.x1[i], answer.y1[i]);
    }

	private Answer triangle(int[] x, int[] y, int[] color, int first, int second, int third) {
		x = x.clone();
		y = y.clone();
		int[] yy = ArrayUtils.compress(y);
		int[] xx = ArrayUtils.compress(x);
		int[] f = new int[xx.length];
		Arrays.fill(f, -1);
		int[] next = new int[y.length];
		for (int i = 0; i < y.length; i++) {
			next[i] = f[x[i]];
			f[x[i]] = i;
		}
		SpecialIntervalTree tree = new SpecialIntervalTree(yy.length);
//		tree.init();
		for (int i = 0; i < y.length; i++) {
			if (color[i] == first) {
				tree.update(y[i], yy.length - 1, 1);
			} else if (color[i] == second) {
				tree.update(0, y[i], 1);
			}
		}
		Answer answer = new Answer();
		long query = tree.query(0, yy.length - 1);
		answer.result = query >> 32;
		Arrays.fill(answer.x0, -INFINITY + 1);
		Arrays.fill(answer.x1, INFINITY);
		answer.x0[third] = -INFINITY;
		answer.x1[third] = -INFINITY + 1;
		answer.y0[first] = -INFINITY;
		answer.y1[first] = yy[(int) (query & (SpecialIntervalTree.STEP - 1))];
		answer.y0[second] = answer.y1[first];
		answer.y1[second] = INFINITY;
		answer.y0[third] = -INFINITY;
		answer.y1[third] = INFINITY;
		int countThird = 0;
		for (int i = 0; i < xx.length; i++) {
			for (int j = f[i]; j != -1; j = next[j]) {
				if (color[j] == third)
					countThird++;
			}
			query = tree.query(0, yy.length - 1);
			long current = (query >> 32) + countThird;
			if (current > answer.result) {
				answer.result = current;
				answer.x1[third] = xx[i];
				answer.x0[first] = xx[i];
				answer.x0[second] = xx[i];
				answer.y1[first] = yy[(int) (query & (SpecialIntervalTree.STEP - 1))];
				answer.y0[second] = answer.y1[first];
			}
			for (int j = f[i]; j != -1; j = next[j]) {
				if (color[j] == first) {
					tree.update(y[j], yy.length - 1, -1);
				} else if (color[j] == second) {
					tree.update(0, y[j], -1);
				}
			}
		}
		return answer;
	}

	private Answer order(int[] x, int[] y, int[] color, int first, int second, int third) {
		y = y.clone();
		int[] yy = ArrayUtils.compress(y);
		int[] f = new int[yy.length];
		Arrays.fill(f, -1);
		int[] next = new int[y.length];
		for (int i = 0; i < y.length; i++) {
			next[i] = f[y[i]];
			f[y[i]] = i;
		}
		SpecialIntervalTree tree = new SpecialIntervalTree(yy.length);
//		tree.init();
		for (int i = 0; i < y.length; i++) {
			if (color[i] == first) {
				tree.update(y[i], yy.length - 1, 1);
			} else if (color[i] == second) {
				tree.update(0, y[i], 1);
			}
		}
		Answer answer = new Answer();
		long query = tree.query(0, yy.length - 1);
		answer.result = query >> 32;
		Arrays.fill(answer.x0, -INFINITY);
		Arrays.fill(answer.x1, INFINITY);
		answer.y0[first] = -INFINITY;
		answer.y1[first] = yy[(int) (query & (SpecialIntervalTree.STEP - 1))];
		answer.y0[second] = answer.y1[first];
		answer.y1[second] = INFINITY - 1;
		answer.y0[third] = INFINITY - 1;
		answer.y1[third] = INFINITY;
		int countThird = 0;
		for (int i = yy.length - 1; i > 0; i--) {
			for (int j = f[i]; j != -1; j = next[j]) {
				if (color[j] == third)
					countThird++;
			}
			query = tree.query(0, i - 1);
			long current = (query >> 32) + countThird;
			if (current > answer.result) {
				answer.result = current;
				answer.y1[first] = yy[(int) (query & (SpecialIntervalTree.STEP - 1))];
				answer.y0[second] = answer.y1[first];
				answer.y1[second] = yy[i];
				answer.y0[third] = yy[i];
			}
			for (int j = f[i]; j != -1; j = next[j]) {
				if (color[j] == first) {
					tree.update(i, yy.length - 1, -1);
				} else if (color[j] == second) {
					tree.update(0, i, -1);
				}
			}
		}
		return answer;
	}

	static void rotate(int[] x, int[] y) {
		for (int i = 0; i < x.length; i++) {
			int xx = x[i];
			x[i] = y[i];
			y[i] = -xx;
		}
	}

	static class Answer {
		int[] x0 = new int[3];
		int[] y0 = new int[3];
		int[] x1 = new int[3];
		int[] y1 = new int[3];
		long result;

		void rotate() {
			Gold.rotate(x0, y0);
			Gold.rotate(x1, y1);
			for (int i = 0; i < 3; i++) {
				if (x0[i] > x1[i]) {
					int t = x0[i];
					x0[i] = x1[i];
					x1[i] = t;
				}
				if (y0[i] > y1[i]) {
					int t = y0[i];
					y0[i] = y1[i];
					y1[i] = t;
				}
			}
		}
	}

	static class SpecialIntervalTree extends LongIntervalTree {
		static long STEP = 1L << 32;

		protected SpecialIntervalTree(int size) {
			super(size);
		}

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
			return value + delta * STEP;
		}

		@Override
		protected long neutralValue() {
			return 0;
		}

		@Override
		protected long neutralDelta() {
			return 0;
		}

		@Override
		protected long initValue(int index) {
			return index;
		}
	}
}
