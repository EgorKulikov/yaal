package net.egork;

import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String t = in.readString();
		StringHash hash = new SimpleStringHash(t);
		IntervalTree tree = new SumIntervalTree(t.length());
		IntSet candidates = new IntHashSet();
		for (int i = 0; i < t.length(); i++)
			candidates.add(i);
		long base = t.length();
		int shift = 2;
		long[][] answer = new long[t.length()][26];
		while (candidates.size() > 0) {
			int[] array = candidates.toArray();
			IntSet next = new IntHashSet();
			long baseScore = (long) (2 * shift - 1) * (2 * shift - 1);
			for (int i : array) {
				int used = 0;
				for (int j = i, add = 1; j < i + shift - 1; ) {
					used |= 1 << (t.charAt(j) - 'a');
					j += add;
					add *= 2;
				}
				if (candidates.contains(i + shift) && hash.hash(i, i + shift - 1) == hash.hash(i + shift, i + 2 * shift - 1)) {
					if ((used >> (t.charAt(i + shift - 1) - 'a') & 1) == 0) {
						next.add(i);
						base += baseScore;
						tree.update(i, i + shift - 2, -baseScore);
						tree.update(i + shift, i + 2 * shift - 2, -baseScore);
						for (int j = 0; j < 26; j++) {
							if ((used >> j & 1) == 1)
								answer[i + shift - 1][j] -= baseScore;
						}
					} else {
						for (int j = 0; j < 26; j++) {
							if ((used >> j & 1) == 0)
								answer[i + shift - 1][j] += baseScore;
						}
					}
				} else if (i + 2 * shift - 1 <= t.length() && ((used >> (t.charAt(i + shift - 1) - 'a') & 1) == 0)) {
					int left = 0;
					int right = shift - 1;
					int at = i + shift;
					while (left < right) {
						int middle = (left + right + 1) >> 1;
						if (hash.hash(i, i + middle) == hash.hash(at, at + middle))
							left = middle;
						else
							right = middle - 1;
					}
					if (left == shift - 2 || hash.hash(i + left + 1, i + shift - 1) == hash.hash(at + left + 1, at + shift - 1))
						answer[at + left][t.charAt(i + left) - 'a'] += baseScore;
				}
				if (i >= shift && ((used >> (t.charAt(i - 1) - 'a') & 1) == 0)) {
					int left = 0;
					int right = shift - 1;
					int at = i - shift;
					while (left < right) {
						int middle = (left + right + 1) >> 1;
						if (hash.hash(i, i + middle) == hash.hash(at, at + middle))
							left = middle;
						else
							right = middle - 1;
					}
					if (left != shift - 1 && (left == shift - 2 || hash.hash(i + left + 1, i + shift - 1) == hash.hash(at + left + 1, at + shift - 1)))
						answer[at + left][t.charAt(i + left) - 'a'] += baseScore;
				}
			}
			shift *= 2;
			candidates = next;
		}
		long result = base;
		for (int i = 0; i < t.length(); i++) {
			long delta = tree.query(i, i);
			for (int j = 0; j < 26; j++)
				result = Math.max(result, base + delta + answer[i][j]);
		}
		out.printLine(result);
    }
}
