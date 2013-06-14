package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	private final int THRESHOLD = 120;
	private final int BIG_THRESHOLD = 800;

	int[] p = IntegerUtils.generatePrimes(2000);
	int[][][][] answer = new int[IntegerUtils.generatePrimes(THRESHOLD).length][THRESHOLD][][];
//	boolean[][][][] done = new boolean[answer.length][THRESHOLD][][];
	int[][][] forTwo = new int[IntegerUtils.generatePrimes(BIG_THRESHOLD).length][BIG_THRESHOLD][];
//	boolean[][][] doneForTwo = new boolean[forTwo.length][BIG_THRESHOLD][];
	int[][] forOne = new int[p.length + 1][2001];
	boolean[][] doneForOne = new boolean[p.length + 1][2001];
	int[] table = new int[p.length * 2001];
	int mask = (1 << 30) - 1;

	{
		for (int j = 0; j < answer.length; j++) {
			for (int i = 0; i < THRESHOLD; i++) {
				answer[j][i] = new int[i + 1][];
//				done[j][i] = new boolean[i + 1][];
				for (int k = 0; k <= i; k++) {
					answer[j][i][k] = new int[k + 1];
//					done[j][i][k] = new boolean[k + 1];
				}
			}
		}
		for (int i = 0; i < forTwo.length; i++) {
			for (int j = 0; j < BIG_THRESHOLD; j++) {
				forTwo[i][j] = new int[j + 1];
//				doneForTwo[i][j] = new boolean[j + 1];
			}
		}
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j <= 2000; j++)
				table[i * 2001 + j] = j / p[i];
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		int c = in.readInt();
		out.printLine(go(0, a, b, c) & ((1 << 30) - 1));
	}

	int go(int step, int a) {
		if (doneForOne[step][a])
			return forOne[step][a];
		doneForOne[step][a] = true;
		if (step == p.length)
			return forOne[step][a] = 1;
		int pp = p[step];
		if (a < pp)
			return forOne[step][a] = 1;
		int aCopy = a;
		int result = 0;
		int stp = step * 2001;
		for (int i = 0; aCopy >= 1; i++, aCopy = table[stp + aCopy])
			result += go(step + 1, aCopy) * (i + 1);
		return forOne[step][a] = result;
	}

	int go(int step, int a, int b) {
		if (step == p.length)
			return 1;
		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}
		int pp = p[step];
		if (a < pp)
			return go(step, b);
		if (b < BIG_THRESHOLD && forTwo[step][b][a] != 0)
			return forTwo[step][b][a] - 1;
		int stp = step * 2001;
		int result = 0;
		int aCopy = a;
		for (int i = 0; aCopy >= 1; i++, aCopy = table[stp + aCopy]) {
			int bCopy = b;
			for (int j = 0; bCopy >= 1; j++, bCopy = table[stp + bCopy])
				result += go(step + 1, aCopy, bCopy) * (1 + i + j);
		}
		result &= mask;
		if (b < BIG_THRESHOLD) {
//			doneForTwo[step][b][a] = true;
			forTwo[step][b][a] = result + 1;
		}
		return result;
	}

	private int go(int step, int a, int b, int c) {
		if (step == p.length)
			return 1;
		int temp;
		if (a > b) {
			temp = a;
			a = b;
			b = temp;
		}
		if (b > c) {
			temp = b;
			b = c;
			c = temp;
		}
		if (a > b) {
			temp = a;
			a = b;
			b = temp;
		}
		int pp = p[step];
		if (a < pp)
			return go(step, b, c);
		if (c < THRESHOLD) {
			if (answer[step][c][b][a] != 0)
				return answer[step][c][b][a] - 1;
		}
		int stp = step * 2001;
		int result = 0;
		int aCopy = a;
		for (int i = 0; aCopy >= 1; i++, aCopy = aCopy < pp ? 0 : table[stp + aCopy]) {
			int bCopy = b;
			for (int j = 0; bCopy >= 1; j++, bCopy = bCopy < pp ? 0 : table[stp + bCopy]) {
				int cCopy = c;
				for (int k = 0; cCopy >= 1; k++, cCopy = cCopy < pp ? 0 : table[stp + cCopy])
					result += go(step + 1, aCopy, bCopy, cCopy) * (1 + i + j + k);
			}
		}
		result &= mask;
		if (c < THRESHOLD) {
//			done[step][c][b][a] = true;
			answer[step][c][b][a] = result + 1;
		}
		return result;
	}
}
