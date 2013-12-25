package on2013_10.on2013_10_04_Codeforces_Round__204.D___Jeff_and_Removing_Periods;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.collections.sequence.Array;
import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int max = ArrayUtils.maxElement(array) + 1;
		int[] next = new int[count];
		int[] last = new int[count];
		int[] nextByTask = new int[max];
		Arrays.fill(nextByTask, count);
		for (int i = count - 1; i >= 0; i--) {
			next[i] = nextByTask[array[i]];
			nextByTask[array[i]] = i;
		}
		int[] lastByTask = new int[max];
		Arrays.fill(lastByTask, -1);
		for (int i = 0; i < count; i++) {
			last[i] = lastByTask[array[i]];
			lastByTask[array[i]] = i;
		}
		int maxScore = new EHashSet<Integer>(Array.wrap(array)).size();
		int step = (int)Math.round(Math.sqrt(count));
		int wholeParts = count / step;
		int[][] qty = new int[wholeParts][wholeParts];
		for (int i = 0; i < wholeParts; i++) {
			int current = 0;
			int start = i * step;
			for (int j = i; j < wholeParts; j++) {
				int end = (j + 1) * step;
				if (current != maxScore) {
					for (int k = j * step; k < end; k++) {
						if (last[k] < start)
							current++;
					}
				}
				qty[i][j] = current;
			}
		}
		IntervalTree until = new LongIntervalTree(count, true) {
			@Override
			protected long joinValue(long left, long right) {
				return Math.max(left, right);
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return Math.max(was, delta);
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				return Math.max(value, delta);
			}

			@Override
			protected long neutralValue() {
				return -1;
			}

			@Override
			protected long neutralDelta() {
				return -1;
			}
		};
		int[] periodMax = new int[count];
		for (int i = count - 1; i >= 0; i--) {
			if (next[i] == count || next[next[i]] == count) {
				periodMax[i] = count - 1;
			} else if (next[next[i]] - next[i] == next[i] - i) {
				periodMax[i] = periodMax[next[i]];
			} else {
				periodMax[i] = next[next[i]] - 1;
			}
		}
		int queryCount = in.readInt();
		int[] start = new int[queryCount];
		int[] to = new int[queryCount];
		IOUtils.readIntArrays(in, start, to);
		MiscUtils.decreaseByOne(start, to);
		int at = 0;
		int[] answer = new int[queryCount];
		for (int i : ArrayUtils.order(to)) {
			while (at <= to[i]) {
				until.update(last[at] + 1, at, periodMax[at]);
				at++;
			}
			int from = start[i];
			int end = to[i];
			int startPart = from / step;
			int endPart = end / step;
			int result = 0;
			if (endPart - startPart <= 1) {
				for (int j = from; j <= end; j++) {
					if (next[j] > end)
						result++;
				}
			} else {
				result = qty[startPart + 1][endPart - 1];
				int up = endPart * step;
				int down = (startPart + 1) * step;
				for (int j = from; j < down; j++) {
					if (next[j] >= up)
						result++;
				}
				for (int j = endPart * step; j <= end; j++) {
					if (last[j] < from)
						result++;
				}
			}
			if (end > until.query(from, from))
				result++;
			answer[i] = result;
		}
		for (int i : answer)
			out.printLine(i);
    }
}
