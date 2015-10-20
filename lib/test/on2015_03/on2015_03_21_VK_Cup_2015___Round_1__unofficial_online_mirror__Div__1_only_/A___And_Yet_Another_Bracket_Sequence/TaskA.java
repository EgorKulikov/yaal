package on2015_03.on2015_03_21_VK_Cup_2015___Round_1__unofficial_online_mirror__Div__1_only_.A___And_Yet_Another_Bracket_Sequence;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.ReadOnlyIntervalTree;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		int count = s.length();
		int[] balance = new int[count + 1];
		for (int i = 0; i < count; i++) {
			if (s.charAt(i) == '(') {
				balance[i + 1] = balance[i] + 1;
			} else {
				balance[i + 1] = balance[i] - 1;
			}
		}
		int freedom = Math.max(0, -balance[count]);
		long[] value = new long[count];
		for (int i = 0; i < count; i++) {
			value[i] = s.charAt(i) == '(' ? 0 : 1;
		}
		int[] order = ArrayUtils.order(value);
		int[] cl = new int[count];
		int current = -1;
		long last = -1;
		for (int j : order) {
			if (value[j] != last) {
				current++;
			}
			cl[j] = current;
			last = value[j];
		}
		current++;
		int[] nOrder = new int[count];
		int[] cnt = new int[count];
		int[] nCl = new int[count];
		for (int i = 1; i < count; i *= 2) {
			for (int j = 0; j < count; j++) {
				nOrder[j] = order[j] - i;
				if (nOrder[j] < 0) {
					nOrder[j] += count;
				}
			}
			Arrays.fill(cnt, 0, current, 0);
			for (int j = 0; j < count; j++) {
				cnt[cl[j]]++;
			}
			for (int j = 1; j < current; j++) {
				cnt[j] += cnt[j - 1];
			}
			for (int j = count - 1; j >= 0; j--) {
				order[--cnt[cl[nOrder[j]]]] = nOrder[j];
			}
			nCl[order[0]] = 0;
			current = 0;
			for (int j = 1; j < count; j++) {
				int s1 = order[j] + i;
				if (s1 >= count) {
					s1 -= count;
				}
				int s2 = order[j - 1] + i;
				if (s2 >= count) {
					s2 -= count;
				}
				if (cl[order[j]] != cl[order[j - 1]] || cl[s1] != cl[s2]) {
					current++;
				}
				nCl[order[j]] = current;
			}
			current++;
			System.arraycopy(nCl, 0, cl, 0, count);
		}
//		s += s;
//		StringHash hash = new SimpleStringHash(s);
//		final String finalS = s;
//		ArrayUtils.sort(order, new IntComparator() {
//			@Override
//			public int compare(int first, int second) {
//				int left = 0;
//				int right = count - 1;
//				while (left < right) {
//					int middle = (left + right + 1) >> 1;
//					if (hash.hash(first, first + middle) == hash.hash(second, second + middle)) {
//						left = middle;
//					} else {
//						right = middle - 1;
//					}
//				}
//				return Character.compare(finalS.charAt(first + left), finalS.charAt(second + left));
//			}
//		});
		IntervalTree tree = new ReadOnlyIntervalTree(ArrayUtils.asLong(balance)) {
			@Override
			protected long neutralValue() {
				return Integer.MAX_VALUE;
			}

			@Override
			protected long joinValue(long left, long right) {
				return Math.min(left, right);
			}
		};
		s += s;
		int min = ArrayUtils.minElement(balance);
		for (int i : order) {
			if (tree.query(i, count) >= balance[i] - freedom && tree.query(0, i - 1) >= balance[i] - freedom - balance[count]) {
				String answer = s.substring(i, i + count);
				for (int j = 0; j < freedom; j++) {
					out.print('(');
				}
				out.print(answer);
				for (int j = 0; j < balance[count]; j++) {
					out.print(')');
				}
				out.printLine();
				return;
			}
		}
	}
}
