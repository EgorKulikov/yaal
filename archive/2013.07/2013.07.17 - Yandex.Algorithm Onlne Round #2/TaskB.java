package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long x = in.readInt();
		int p = in.readInt();
		int count = in.readInt();
		int m = in.readInt();
		long threshold = in.readLong();
		int[] shift = new int[(p - 1) * m];
		int[] powers = new int[p - 1];
		powers[0] = 1;
		long current = 1;
		for (int i = 1; i < p - 1; i++) {
			current *= x;
			current %= p;
			powers[i] = (int) (current % m);
		}
		int curHappiness = 1;
		int j = 0;
		for (int i = 1; i < (p - 1) * m; i++) {
			j++;
			if (j == p - 1)
				j = 0;
			curHappiness += powers[j];
			if (curHappiness >= m)
				curHappiness -= m;
			if (curHappiness == 0)
				shift[i] = shift[i - 1] + 1;
			else
				shift[i] = shift[i - 1];
		}
		int totalShift = shift[shift.length - 1];
		long left = 0;
		long right = 2000000000000000000L;
		while (left < right) {
			long middle = (left + right) >> 1;
			long cur = middle;
			for (int i = 0; i < count - 1; i++)
				cur = (cur / shift.length) * totalShift + shift[((int) (cur % shift.length))];
			if (middle - cur >= threshold)
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine(left);
    }
}
