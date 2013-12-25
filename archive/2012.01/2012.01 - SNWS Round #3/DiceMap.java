package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DiceMap {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] x = {2, 3, 1, 0, 4, 5};
		int[] xRev = {3, 2, 0, 1, 4, 5};
		int[] y = {4, 5, 2, 3, 1, 0};
		int[] yRev = {5, 4, 2, 3, 0, 1};
		int[] start = {1, 6, 3, 4, 2, 5};
		int[] next = new int[6];
		boolean reverse = false;
		int count = 0;
		int curX = 0;
		int curY = 0;
		while (true) {
			char c = in.readCharacter();
			if (c == '-')
				reverse = true;
			else if (c == '+')
				reverse = false;
			else if (c == 'X') {
				if (count == 0)
					count = 1;
				if (reverse)
					curX -= count;
				else
					curX += count;
				count %= 4;
				if (!reverse) {
					for (int i = 0; i < count; i++) {
						for (int j = 0; j < 6; j++)
							next[j] = start[x[j]];
						int[] temp = start;
						start = next;
						next = temp;
					}
				} else {
					for (int i = 0; i < count; i++) {
						for (int j = 0; j < 6; j++)
							next[j] = start[xRev[j]];
						int[] temp = start;
						start = next;
						next = temp;
					}
				}
				count = 0;
			} else if (c == 'Y') {
				if (count == 0)
					count = 1;
				if (reverse)
					curY -= count;
				else
					curY += count;
				count %= 4;
				if (!reverse) {
					for (int i = 0; i < count; i++) {
						for (int j = 0; j < 6; j++)
							next[j] = start[y[j]];
						int[] temp = start;
						start = next;
						next = temp;
					}
				} else {
					for (int i = 0; i < count; i++) {
						for (int j = 0; j < 6; j++)
							next[j] = start[yRev[j]];
						int[] temp = start;
						start = next;
						next = temp;
					}
				}
				count = 0;
			} else if (c == '.') {
				break;
			} else if (Character.isDigit(c)) {
				count *= 10;
				count += c - '0';
			}
		}
		out.printLine(curX, curY, start[0]);
	}
}
