package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.string.StringUtils;

public class EllysString {
	private int[][][] answer;
	private char[] a, b;

	public int theMin(String[] s, String[] t) {
		a = (StringUtils.unite(s) + " ").toCharArray();
		b = (StringUtils.unite(t) + " ").toCharArray();
		answer = new int[a.length - 1][26][26];
		ArrayUtils.fill(answer, -1);
		return go(0, a[0] - 'a', b[0] - 'a');
	}

	private int go(int step, int left, int right) {
		if (step == a.length - 1)
			return 0;
		if (answer[step][left][right] != -1)
			return answer[step][left][right];
		answer[step][left][right] = go(step + 1, a[step + 1] - 'a', b[step + 1] - 'a') + (left == right ? 0 : 1);
		if (step != a.length - 2) {
			answer[step][left][right] = Math.min(answer[step][left][right], go(step + 1, a[step + 1] - 'a', right) + (left == b[step + 1] - 'a' ? 0 : 1) + 1);
			answer[step][left][right] = Math.min(answer[step][left][right], go(step + 1, left, b[step + 1] - 'a') + (right == a[step + 1] - 'a' ? 0 : 1) + 1);
		}
		return answer[step][left][right];
	}

	public static void main(String[] args) {
		char[] permutation = new char[10];
		for (int i = 0; i < 10; i++)
			permutation[i] = (char) ('a' + i);
		dfs(permutation, 0, 0);
	}

	private static void dfs(char[] permutation, int step, int used) {
		if (step == 9) {
			if (new EllysString().theMin(new String[]{"abcdefghij"}, new String[]{new String(permutation)}) != 9)
				throw new RuntimeException();
			return;
		}
		for (int i = 0; i < 9; i++) {
			if ((used >> i & 1) == 0) {
				char temp = permutation[i];
				permutation[i] = permutation[i + 1];
				permutation[i + 1] = temp;
				dfs(permutation, step + 1, used + (1 << i));
				temp = permutation[i];
				permutation[i] = permutation[i + 1];
				permutation[i + 1] = temp;
			}
		}
	}
}

