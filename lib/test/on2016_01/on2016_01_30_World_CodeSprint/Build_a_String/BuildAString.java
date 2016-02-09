package on2016_01.on2016_01_30_World_CodeSprint.Build_a_String;



import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.misc.ArrayUtils;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class BuildAString {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int a = in.readInt();
		int b = in.readInt();
		String s = in.readString();
		int lastAnswer = 0;
		int start = 0;
		int length = 0;
		int[] position = StringUtils.suffixArray(s);
		int[] order = ArrayUtils.reversePermutation(position);
		StringHash hash = new SimpleStringHash(s);
		MinTree common = new MinTree(n);
		for (int i = 0; i < n - 1; i++) {
			int left = 0;
			int right = n - Math.max(order[i], order[i + 1]);
			while (left < right) {
				int middle = (left + right + 1) >> 1;
				if (hash.hash(order[i], order[i] + middle) == hash.hash(order[i + 1], order[i + 1] + middle)) {
					left = middle;
				} else {
					right = middle - 1;
				}
			}
			common.update(i, i, left);
		}
		MinTree answer = new MinTree(n + 1);
		answer.update(0, 0, 0);
		NavigableSet<Integer> added = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			int current = lastAnswer + a;
			length++;
			if (start - length + 1 >= 0) {
				added.remove(position[start - length + 1]);
			}
			while (start <= i) {
				Integer lower = added.lower(position[start]);
				if (lower != null && common.query(lower, position[start] - 1) >= length) {
					break;
				}
				Integer higher = added.higher(position[start]);
				if (higher != null && common.query(position[start], higher - 1) >= length) {
					break;
				}
				if (start >= length - 1) {
					added.add(position[start - length + 1]);
				}
				if (start >= length - 2 && start - length + 2 < n) {
					added.add(position[start - length + 2]);
				}
				start++;
				length--;
			}
			if (start <= i) {
				current = (int) Math.min(current, answer.query(start, i) + b);
			}
			answer.update(i + 1, i + 1, current);
			lastAnswer = current;
		}
		out.printLine(lastAnswer);
	}

	static class MinTree extends LongIntervalTree {
		protected MinTree(int size) {
			super(size);
		}

		@Override
		protected long joinValue(long left, long right) {
			return Math.min(left, right);
		}

		@Override
		protected long joinDelta(long was, long delta) {
			return Math.min(was, delta);
		}

		@Override
		protected long accumulate(long value, long delta, int length) {
			return Math.min(value, delta);
		}

		@Override
		protected long neutralValue() {
			return Long.MAX_VALUE;
		}

		@Override
		protected long neutralDelta() {
			return Long.MAX_VALUE;
		}
	}
}
