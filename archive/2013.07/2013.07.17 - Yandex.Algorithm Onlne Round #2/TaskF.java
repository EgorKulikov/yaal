package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	boolean[][][][][][] reachable = new boolean[3][3][3][50][50][50];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int p = in.readInt();
		int q = in.readInt();
		int r = in.readInt();
		String end = in.readString();
		if (p >= 50)
			p = 48 + p % 2;
		if (q >= 50)
			q = 48 + q % 2;
		if (r >= 50)
			r = 48 + r % 2;
		int first = value(end.charAt(0));
		int second = value(end.charAt(1));
		int third = value(end.charAt(2));
		if (go(first, second, third, p, q, r))
			out.printLine("Yes");
		else
			out.printLine("No");
    }

	private boolean go(int first, int second, int third, int p, int q, int r) {
		if (reachable[first][second][third][p][q][r])
			return false;
		reachable[first][second][third][p][q][r] = true;
		if (p == 0 && q == 0 && r == 0 && first == 0 && second == 1 && third == 2)
			return true;
		if (p != 0 && go(second, first, third, p - 1, q, r))
			return true;
		if (q != 0 && go(third, second, first, p, q - 1, r))
			return true;
		if (r != 0 && go(first, third, second, p, q, r - 1))
			return true;
		return false;
	}

	private int value(char c) {
		if (c == 'R')
			return 0;
		if (c == 'G')
			return 1;
		if (c == 'B')
			return 2;
		throw new IllegalArgumentException();
	}
}
