package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int s = in.readInt() - 1;
		int[] a = IOUtils.readIntArray(in, n);
		ArrayUtils.compress(a);
		int[] first = ArrayUtils.createArray(ArrayUtils.maxElement(a) + 1, -1);
		int[] next = new int[n];
		int[] toLeft = ArrayUtils.createArray(n, -1);
		int[] toRight = ArrayUtils.createArray(n, -1);
		int[] last = new int[first.length];
		for (int i = 0; i < n; i++) {
			next[i] = first[a[i]];
			first[a[i]] = i;
			if (next[i] == -1) {
				last[a[i]] = i;
			} else {
				toLeft[i] = next[i];
				toRight[next[i]] = i;
			}
		}
		for (int i = 0; i < first.length; i++) {
			if (first[i] != last[i]) {
				toRight[first[i]] = last[i];
				toLeft[last[i]] = first[i];
			}
		}
		int[] answer = new int[n];
		IntList[] prev = new IntList[n];
		for (int i = first[0]; i != -1; i = next[i]) {
			if (toLeft[i] == -1) {
				prev[i] = new IntArrayList();
				prev[i].add(i);
				answer[i] = dst(s, i, n);
			} else {
				int distance = toLeft[i] - i;
				if (distance < 0) {
					distance += n;
				}
				distance +=  dst(s, toLeft[i], n);
				int other = i - toRight[i];
				if (other < 0) {
					other += n;
				}
				other += dst(s, toRight[i], n);
				prev[i] = new IntArrayList();
				if (distance < other) {
					int pos = toLeft[i];
					while (pos != i) {
						if (a[pos] == 0) {
							prev[i].add(pos);
						}
						pos--;
						if (pos < 0) {
							pos += n;
						}
					}
					prev[i].add(i);
				} else {
					int pos = toRight[i];
					while (pos != i) {
						if (a[pos] == 0) {
							prev[i].add(pos);
						}
						pos++;
						if (pos >= n) {
							pos -= n;
						}
					}
					prev[i].add(i);
				}
				answer[i] = Math.min(distance, other);
			}
		}
		for (int i = 1; i < first.length; i++) {
			for (int j = first[i]; j != -1; j = next[j]) {
				int bestAt = -1;
				int best = Integer.MAX_VALUE;
				boolean dir = false;
				for (int k = first[i - 1]; k != -1; k = next[k]) {
					if (toLeft[j] == -1) {
						int candidate = Math.abs(k - j);
						candidate = Math.min(candidate, n - candidate);
						candidate += answer[k];
						if (candidate < best) {
							best = candidate;
							bestAt = k;
						}
					} else {
						int left = Math.abs(toLeft[j] - k);
						left = Math.min(left, n - left);
						left += answer[k];
						left += toLeft[j] - j < 0 ? toLeft[j] - j + n : toLeft[j] - j;
						if (left < best) {
							best = left;
							bestAt = k;
							dir = true;
						}
						int right = Math.abs(toRight[j] - k);
						right = Math.min(right, n - right);
						right += answer[k];
						right += j - toRight[j] < 0 ? j - toRight[j] + n : j - toRight[j];
						if (right < best) {
							best = right;
							bestAt = k;
							dir = false;
						}
					}
				}
				answer[j] = best;
				prev[j] = new IntArrayList(prev[bestAt]);
				if (toLeft[j] != -1) {
					if (dir) {
						int pos = toLeft[j];
						while (pos != j) {
							if (a[pos] == i) {
								prev[j].add(pos);
							}
							pos--;
							if (pos < 0) {
								pos += n;
							}
						}
						prev[j].add(j);
					} else {
						int pos = toRight[j];
						while (pos != j) {
							if (a[pos] == i) {
								prev[j].add(pos);
							}
							pos++;
							if (pos >= n) {
								pos -= n;
							}
						}
						prev[j].add(j);
					}
				} else {
					prev[j].add(j);
				}
			}
		}
		int best = Integer.MAX_VALUE;
		int bestAt = -1;
		for (int i = first[first.length - 1]; i != -1; i = next[i]) {
			if (answer[i] < best) {
				best = answer[i];
				bestAt = i;
			}
		}
		out.printLine(best);
		int at = s;
		for (int i : prev[bestAt]) {
			int left = at - i;
			if (left < 0) {
				left += n;
			}
			int right = i - at;
			if (right < 0) {
				right += n;
			}
			if (right <= left) {
				out.print('+');
				out.printLine(right);
			} else {
				out.print('-');
				out.printLine(left);
			}
			at = i;
		}
	}

	private int dst(int f, int i, int n) {
		return Math.min(Math.abs(f - i), n - Math.abs(f - i));
	}
}
