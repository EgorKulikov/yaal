package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class HelpWith {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int last = in.readInt();
		int a = in.readInt();
		int b = in.readInt();
		int c = in.readInt();
		int n = in.readInt();
		if (n == 0) {
			out.printLine(0);
			return;
		}
		if (a % 2 == 0 && b % 2 == 0) {
			out.printLine(-1);
			return;
		}
		int[] candidates = new int[1];
		for (int i = 0; i < n; i++) {
			int[] next = new int[candidates.length << 1];
			System.arraycopy(candidates, 0, next, 0, candidates.length);
			for (int j = 0; j < candidates.length; j++)
				next[j + candidates.length] = candidates[j] + (1 << i);
			int count = 0;
			for (int j = 0; j < next.length; j++) {
				int x = next[j];
				if (((a * x * x + b * x + c) & ((1 << (i + 1)) - 1)) == (last & ((1 << (i + 1)) - 1)))
					next[count++] = x;
			}
			candidates = Arrays.copyOf(next, count);
		}
		if (candidates.length == 1)
			out.printLine(candidates[0]);
		else
			out.printLine(-1);
	}
}
