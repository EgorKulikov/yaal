package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] answer = new int[8];
		for (int i = 0; i < 256; i++) {
			if (!keepZero(i) && !keepOne(i) && !reverse(i) && !monotone(i) && !linear(i)) {
				for (int j = 0; j < 8; j++)
					answer[j] = (i >> j & 1);
				out.printLine(answer);
			}
		}
    }

	private boolean linear(int f) {
		for (int i = 0; i < 3; i++) {
			boolean equal = false;
			boolean different = false;
			for (int j = 0; j < 8; j++) {
				if (value(f, j) == value(f, j ^ (1 << i)))
					equal = true;
				else
					different = true;
			}
			if (equal && different)
				return false;
		}
		return true;
	}

	private boolean monotone(int f) {
		for (int i = 0; i < 7; i++) {
			if (value(f, i)) {
				for (int j = 0; j < 3; j++) {
					if ((i >> j & 1) == 0 && !value(f, i + (1 << j)))
						return false;
				}
			}
		}
		return true;
	}

	private boolean reverse(int f) {
		for (int i = 0; i < 4; i++) {
			if (value(f, i) == value(f, 7 - i))
				return false;
		}
		return true;
	}

	private boolean keepOne(int f) {
		return value(f, 7);
	}

	private boolean value(int f, int x) {
		return (f >> x & 1) != 0;
	}

	private boolean keepZero(int f) {
		return !value(f, 0);
	}
}
