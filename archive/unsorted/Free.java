package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class Free {
	int[] longestUp;
	int[] longestUpTo;
	int[] firstParent;
	int[] nextSibling;
	boolean[] removed;
	int[] cycleSize;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		int[] a = new int[n];
		cycleSize = new int[n];
		Arrays.fill(cycleSize, 0);
		removed = new boolean[n];
		for (int i = 0; i < n; ++i) {
			a[i] = in.readInt() - 1;
		}
		firstParent = new int[n];
		nextSibling = new int[n];
		Arrays.fill(firstParent, -1);
		for (int i = 0; i < n; ++i) {
			nextSibling[i] = firstParent[a[i]];
			firstParent[a[i]] = i;
		}
		int[] mark = new int[n];
		int generation = 0;
		for (int i = 0; i < n; ++i) if (mark[i] == 0) {
			++generation;
			int j = i;
			while (mark[j] == 0) {
				mark[j] = generation;
				j = a[j];
			}
			if (mark[j] == generation) {
				++generation;
				int cycleRoot = j;
				cycleSize[cycleRoot] = 1;
				while (mark[j] != generation) {
					if (j != cycleRoot) {
						removed[j] = true;
						++cycleSize[cycleRoot];
						int tmp = firstParent[j];
						while (tmp >= 0) {
							a[tmp] = cycleRoot;
							tmp = nextSibling[tmp];
						}
					}
					mark[j] = generation;
					j = a[j];
				}
			}
		}
		Arrays.fill(firstParent, -1);
		for (int i = 0; i < n; ++i) {
			nextSibling[i] = firstParent[a[i]];
			firstParent[a[i]] = i;
		}
		longestUp = new int[n];
		longestUpTo = new int[n];
		for (int i = 0; i < n; ++i) if (!removed[i] && cycleSize[i] > 0) {
			dfs(i);
		}
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(n, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return longestUp[o2] - longestUp[o1];
			}
		});
		for (int i = 0; i < n; ++i)
			if (!removed[i] && cycleSize[i] > 0)
				queue.add(i);
		List<Integer> res = new ArrayList<Integer>();
		int resSum = 0;
		for (int i = 0; i < k; ++i) {
			if (queue.isEmpty()) break;
			int bottom = queue.poll();
			resSum += longestUp[bottom];
			int top = bottom;
			while (longestUpTo[top] != -1) {
				int tmp = firstParent[top];
				while (tmp >= 0) {
					if (tmp != longestUpTo[top] && !removed[tmp] && cycleSize[tmp] == 0) {
						cycleSize[tmp] = 1;
						queue.add(tmp);
					}
					tmp = nextSibling[tmp];
				}
				top = longestUpTo[top];
			}
			res.add(top);
		}
		if (res.size() < k) {
			boolean[] taken = new boolean[n];
			for (int x : res) {
				if (taken[x]) throw new RuntimeException();
				taken[x] = true;
			}
			for (int i = 0; i < n && res.size() < k; ++i)
				if (!taken[i]) {
					res.add(i);
					taken[i] = true;
				}
		}
		out.printLine(resSum);
		for (int i = 0; i < res.size(); ++i) {
			if (i > 0) out.print(" ");
			out.print(res.get(i) + 1);
		}
		out.printLine();
	}

	private void dfs(int at) {
		int curSize = Math.max(1, cycleSize[at]);
		int res = curSize;
		int tmp = firstParent[at];
		int by = -1;
		while (tmp >= 0) {
			if (!removed[tmp] && cycleSize[tmp] == 0) {
				dfs(tmp);
				if (curSize + longestUp[tmp] > res) {
					res = curSize + longestUp[tmp];
					by = tmp;
				}
			}
			tmp = nextSibling[tmp];
		}
		longestUp[at] = res;
		longestUpTo[at] = by;
	}
}
