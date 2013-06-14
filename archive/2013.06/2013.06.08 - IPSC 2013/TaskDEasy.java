package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskDEasy {
	short[] first = new short[1 << 26];
	int[][] previous;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		char[] sequence = in.readString().toCharArray();
//		Arrays.fill(first, (short) -2);
		previous = new int[sequence.length + 1][26];
		Arrays.fill(previous[0], -1);
		for (int i = 0; i < sequence.length; i++) {
			System.arraycopy(previous[i], 0, previous[i + 1], 0, 26);
			previous[i + 1][sequence[i] - 'a'] = i;
		}
		first[0] = (short) sequence.length;
		System.err.println("Init");
		for (int i = 1; i < first.length; i++) {
			if ((i & ((1 << 20) - 1)) == 0)
				System.err.println("x" + (i >> 20));
			int result = sequence.length;
			for (int j = 0; j < 26; j++) {
				if ((i >> j & 1) == 1) {
					int k = i - (1 << j);
					result = Math.min(result, previous[first[k]][j]);
				}
			}
			first[i] = (short) result;
			if (first[i] == -1) {
				out.printLine("NO");
				System.err.println("NO");
				return;
			}
		}
//		x = 0;
//		if (go(first.length - 1) == -1)
//			out.printLine("NO");
//		else
		out.printLine("YES");
		System.err.println("YES");
    }

	int x;

	private int go(int mask) {
		if (first[mask] != -2)
			return first[mask];
		x++;
		if ((x & ((1 << 20) - 1)) == 0)
			System.err.println("x" + (x >> 20));
		int result = previous.length - 1;
		for (int i = 0; i < 26; i++) {
			if ((mask >> i & 1) == 1) {
				int value = go(mask - (1 << i));
				if (value == -1)
					return first[mask] = -1;
				result = Math.min(result, previous[value][i]);
				if (result == -1)
					return first[mask] = -1;
			}
		}
		return first[mask] = (short) result;
	}
}
