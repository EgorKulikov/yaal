package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Random;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] parent = new int[count];
		int[] salary = new int[count];
		for (int i = 0; i < count; i++) {
			parent[i] = in.readInt();
			salary[i] = in.readInt();
		}
		doit(count, parent, salary);
		for (int i : salary)
			out.printLine(i);
	}

	public static void main(String[] args) {
		Random random = new Random(53418957341L);
		while (true) {
			int n = 5;
			int[] a = new int[n];
			a[0] = 1;
			for (int i = 1; i < n; ++i) {
				a[i] = random.nextInt(i) + 1;
			}
			int[] b = new int[n];
			for (int i = 0; i < n; ++i) {
				if (random.nextBoolean() || (i > 0 && b[a[i] - 1] == 0)) b[i] = 0; else b[i] = n - i;
			}
			//a = new int[]{1, 1, 2, 1, 4};
			//b = new int[]{5, 2, 0, 4, 0};
			int[] a1 = a.clone();
			int[] b1 = b.clone();
			new TaskH().doit(n, a1, b1);
			int[] a2 = a.clone();
			int[] b2 = b.clone();
			new TaskH().doit2(n, a2, b2);
			for (int i = 0; i < n; ++i)
				if (b1[i] != b2[i]) {
					new TaskH().doit(n, a, b);
					throw new RuntimeException();
				}
		}
	}

	private void doit2(int count, int[] parent, int[] salary) {
		for (int i = 0; i < parent.length; ++i) --parent[i];
		boolean[] used = new boolean[count + 1];
		for (int x : salary) used[x] = true;
		int[] onlyKnown = salary.clone();
		rec(parent, salary, onlyKnown, 0, used);
		for (int i = 0; i < salary.length; ++i) {
			if (onlyKnown[i] == 0) throw new RuntimeException();
			if (onlyKnown[i] > 0) salary[i] = onlyKnown[i];
		}
	}

	private void rec(int[] parent, int[] salary, int[] onlyKnown, int at, boolean[] used) {
		boolean ok = true;
		for (int i = 0; i < salary.length; ++i)
			if ((salary[parent[i]] < salary[i] && salary[parent[i]] > 0)) {
				ok = false;
			}
		if (!ok) return;
		if (at >= salary.length) {
			for (int i = 0; i < salary.length; ++i)
				if (onlyKnown[i] == 0 || onlyKnown[i] == salary[i])
					onlyKnown[i] = salary[i];
				else
					onlyKnown[i] = -1;
			return;
		}
		if (salary[at] == 0) {
			for (int i = 1; i <= salary.length; ++i)
				if (!used[i]) {
					used[i] = true;
					salary[at] = i;
					rec(parent, salary, onlyKnown, at + 1, used);
					salary[at] = 0;
					used[i] = false;
				}
		} else {
			rec(parent, salary, onlyKnown, at + 1, used);
		}
	}

	private void doit(int count, int[] parent, int[] salary) {
		int ceo = -1;
		int[] degree = new int[count];
		for (int i = 0; i < count; i++) {
			parent[i]--;
			if (parent[i] == i) {
				parent[i] = -1;
				ceo = i;
			} else
				degree[parent[i]]++;
		}
		int[][] graph = new int[count][];
		for (int i = 0; i < count; i++)
			graph[i] = new int[degree[i]];
		for (int i = 0; i < count; i++) {
			if (parent[i] != -1)
				graph[parent[i]][--degree[parent[i]]] = i;
		}
		int[] order = new int[count];
		order[0] = ceo;
		int length = 1;
		for (int i = 0; i < length; i++) {
			for (int j : graph[order[i]])
				order[length++] = j;
		}
		int[] size = new int[count];
		int[] parentSalary = new int[count];
		int[] singleUnknownChild = new int[count];
		Arrays.fill(singleUnknownChild, -1);
		parentSalary[ceo] = count + 1;
		for (int ii = count - 1; ii > 0; ii--) {
			int i = order[ii];
			if (salary[i] == 0) {
				size[i]++;
				size[parent[i]] += size[i];
				if (singleUnknownChild[parent[i]] == -1)
					singleUnknownChild[parent[i]] = i;
				else
					singleUnknownChild[parent[i]] = -2;
			}
			parentSalary[i] = salary[parent[i]];
		}
		int[] index = new int[count];
		Arrays.fill(index, -1);
		for (int i = 0; i < count; i++) {
			if (salary[i] != 0)
				index[salary[i] - 1] = i;
		}
		int free = 0;
		int moot = 0;
		int qSize = 0;
		int[] queue = new int[count];
		for (int i = 0; i < count; i++) {
			if (index[i] == -1) {
				queue[qSize++] = i + 1;
				free++;
			} else {
				if (size[index[i]] == free) {
					int current = singleUnknownChild[index[i]];
					while (current >= 0 && qSize > moot) {
						salary[current] = queue[--qSize];
						current = singleUnknownChild[current];
					}
					free = 0;
					moot = 0;
					qSize = 0;
				} else {
					if (size[index[i]] > 0) {
						moot = qSize;
						free -= size[index[i]];
					}
				}
			}
		}
		if (salary[ceo] == 0) {
			int current = ceo;
			int c = count;
			while (current >= 0) {
				salary[current] = c--;
				current = singleUnknownChild[current];
			}
		}
	}
}
