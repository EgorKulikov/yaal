package on2013_08.on2013_08_20_20_Hack_August.SignalTower;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class SignalTower {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		long[] x = IOUtils.readLongArray(in, count);
		long[] y = IOUtils.readLongArray(in, count);
		for (int i = 0; i < count; i++) {
			long xx = x[i] + y[i];
			long yy = x[i] - y[i];
			x[i] = xx;
			y[i] = yy;
		}
		MedianTree xTree = new MedianTree(x);
		MedianTree yTree = new MedianTree(y);
		for (int i = 0; i < queryCount; i++) {
			int left = in.readInt() - 1;
			int right = in.readInt();
			out.printLine((xTree.query(left, right) + yTree.query(left, right)) / 2d);
		}
    }

	class MedianTree {
		long[] value;
		int[] order;
		int[][] subArray;
		long[][] subSums;
		long curMedian;

		MedianTree(long[] value) {
			this.value = value;
			order = ArrayUtils.order(value);
			order = Arrays.copyOf(order, order.length);
			subArray = new int[value.length << 2][];
			subSums = new long[value.length << 2][];
			init(0, 0, value.length);
		}

		private void init(int root, int from, int to) {
			subArray[root] = new int[to - from];
			subSums[root] = new long[to - from + 1];
			for (int i = from; i < to; i++)
				subArray[root][i - from] = order[i];
			Arrays.sort(subArray[root]);
			for (int i = 0; i < subArray[root].length; i++)
				subSums[root][i + 1] = subSums[root][i] + value[subArray[root][i]];
			if (to - from > 1) {
				int middle = (from + to) >> 1;
				init(2 * root + 1, from, middle);
				init(2 * root + 2, middle, to);
			}
		}

		long query(int left, int right) {
			return query(0, 0, value.length, left, right, (right - left) >> 1, right - left);
		}

		private long query(int root, int from, int to, int left, int right, int index, int size) {
			if (to - from == 1) {
				curMedian = value[order[from]];
				return 0;
			}
			int leftSize = binarySearch(2 * root + 1, right) - binarySearch(2 * root + 1, left);
			if (leftSize > index) {
				long result = query(2 * root + 1, from, (from + to) >> 1, left, right, index, leftSize);
				result += subSums[2 * root + 2][binarySearch(2 * root + 2, right)] - subSums[2 * root + 2][binarySearch(2 * root + 2, left)] - (size - leftSize) * curMedian;
				return result;
			} else {
				long result = query(2 * root + 2, (from + to) >> 1, to, left, right, index - leftSize, size - leftSize);
				result += leftSize * curMedian - subSums[2 * root + 1][binarySearch(2 * root + 1, right)] + subSums[2 * root + 1][binarySearch(2 * root + 1, left)];
				return result;
			}
		}

		int binarySearch(int root, int index) {
			int result = Arrays.binarySearch(subArray[root], index);
			if (result < 0)
				result = -result - 1;
			return result;
		}
	}
}
