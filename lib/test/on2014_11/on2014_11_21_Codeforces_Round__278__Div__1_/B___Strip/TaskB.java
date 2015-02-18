package on2014_11.on2014_11_21_Codeforces_Round__278__Div__1_.B___Strip;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxDelta = in.readInt();
		int minLength = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		IntervalTree tree = new LongIntervalTree(count + 1) {
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
				return Integer.MAX_VALUE / 2;
			}

			@Override
			protected long neutralDelta() {
				return Integer.MAX_VALUE / 2;
			}
		};
		NavigableSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (array[o1] != array[o2]) {
					return array[o1] - array[o2];
				}
				return o1 - o2;
			}
		});
		tree.update(0, 0, 0);
		int start = 0;
		for (int i = 0; i < count; i++) {
			set.add(i);
			while (!set.isEmpty() && array[set.last()] - array[set.first()] > maxDelta) {
				set.remove(start++);
			}
			if (i - start >= minLength - 1) {
				tree.update(i + 1, i + 1, tree.query(start, i + 1 - minLength) + 1);
			}
		}
		if (tree.query(count, count) >= Integer.MAX_VALUE / 2) {
			out.printLine(-1);
		} else {
			out.printLine(tree.query(count, count));
		}
    }
}
