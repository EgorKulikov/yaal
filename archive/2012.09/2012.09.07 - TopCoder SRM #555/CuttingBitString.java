package net.egork;

import net.egork.collections.ArrayUtils;

public class CuttingBitString {
	private int[][] answer;

	public int getmin(String S) {
		answer = new int[S.length()][S.length()];
		ArrayUtils.fill(answer, -1);
		int answer = go(0, S.length() - 1, S);
		if (answer == Integer.MAX_VALUE / 2)
			answer = -1;
		return answer;
	}

	private int go(int from, int to, String s) {
		if (answer[from][to] != -1)
			return answer[from][to];
		if (good(s.substring(from, to + 1)))
			return answer[from][to] = 1;
		answer[from][to] = Integer.MAX_VALUE / 2;
		for (int i = from; i < to; i++)
			answer[from][to] = Math.min(answer[from][to], go(from, i, s) + go(i + 1, to, s));
		return answer[from][to];
	}

	private boolean good(String s) {
		if (s.charAt(0) != '1')
			return false;
		long number = Long.parseLong(s, 2);
		while (number % 5 == 0)
			number /= 5;
		return number == 1;
	}
}
