package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] countByMask = new int[1024];
		for (int i = 0; i < count; i++) {
			int c;
			//noinspection StatementWithEmptyBody
			while (InputReader.isSpaceChar(c = in.read()));
			int mask = 0;
			do {
				int digit = c - '0';
				mask |= 1 << digit;
			} while (!InputReader.isSpaceChar(c = in.read()));
			countByMask[mask]++;
		}
		for (int i = 0; i < 1024; i++) {
			for (int j = 0; j < i; j++) {
				if ((i & j) == j)
					countByMask[j] += countByMask[i];
			}
		}
		long result = 0;
		for (int i = 1; i < 1024; i++) {
			long current = (long)countByMask[i] * (countByMask[i] - 1) / 2;
			if (Integer.bitCount(i) % 2 == 0)
				result -= current;
			else
				result += current;
		}
		out.printLine(result);
	}
}
