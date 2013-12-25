package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int firstRepeat = in.readInt();
		int secondRepeat = in.readInt();
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		for (char c : second) {
			boolean found = false;
			for (char d : first) {
				if (d == c) {
					found = true;
					break;
				}
			}
			if (!found) {
				out.printLine(0);
				return;
			}
		}
		int[] skipped = new int[first.length];
		int[] position = new int[first.length];
		for (int i = 0; i < first.length; i++) {
			int curSkipped = 0;
			int j = 0;
			int k = i;
			while (j != second.length) {
				if (first[k] == second[j])
					j++;
				k++;
				if (k == first.length) {
					k = 0;
					curSkipped++;
				}
			}
			skipped[i] = curSkipped;
			position[i] = k;
		}
		int[] qtySkipped = new int[first.length];
		int[] move = new int[first.length];
		int curSkipped = 0;
		int curPosition = 0;
		Arrays.fill(move, -1);
		move[0] = 0;
		long total = 0;
		boolean leap = false;
		int index = 0;
		while (true) {
			curSkipped += skipped[curPosition];
			curPosition = position[curPosition];
			if (curSkipped > firstRepeat || curSkipped == firstRepeat && curPosition > 0)
				break;
			total++;
			if (!leap) {
				index++;
				if (move[curPosition] != -1) {
					int delta = curSkipped - qtySkipped[curPosition];
					long steps = (firstRepeat - curSkipped - 1) / delta;
					curSkipped += steps * delta;
					total += steps * (index - move[curPosition]);
					leap = true;
				} else {
					move[curPosition] = index;
					qtySkipped[curPosition] = curSkipped;
				}
			}
		}
		out.printLine(total / secondRepeat);
    }
}
