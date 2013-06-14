package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	int[][] best;
	int[] x;
	int[] y;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		x = new int[12];
		y = new int[6];
		for (int i = 0; i < 12; ++i) {
			x[11 - i] = in.readInt() + 3;
		}
		for (int i = 0; i < 6; ++i) {
			y[i] = in.readInt();
		}
		for (int i = 0; i < 3; ++i) {
			if (y[i * 2 + 1] < y[i * 2]) {
				int tmp = y[i * 2];
				y[i * 2] = y[i * 2 + 1];
				y[i * 2 + 1] = tmp;
			}
		}
		best = new int[6][1 << (6 + 12)];
		rec((1 << (6 + 12)) - 1);
		for (int i = 0; i < 3; ++i) {
			if (i > 0) out.print(" ");
			out.print(best[i + 3][(1 << (6 + 12)) - 1] - 12);
		}
		out.printLine();
	}

	static final int INF = (int) 1e9;

	private void rec(int state) {
		int numCards = Integer.bitCount(state & ((1 << 12) - 1));
		if (numCards == 0) {
			return;
		}
		int who = (12 - numCards) % 3;
		for (int i = 0; i < 3; ++i)
			best[i][state] = -INF;
		if (who == 1) {
			for (int i = 3; i < 6; ++i)
				best[i][state] = INF;
		} else {
			for (int i = 3; i < 6; ++i)
				best[i][state] = -INF;
		}
		for (int useCard = -1; useCard < 2; ++useCard) {
			if (useCard >= 0 && (state & (1 << (12 + who * 2 + useCard))) == 0)
				continue;
			int take = (useCard < 0) ? 0 : (y[who * 2 + useCard] - 1);
			if (take >= numCards) {
				take = 0;
			}
			int posTake;
			for (posTake = 0; posTake < 12; ++posTake)
				if ((state & (1 << posTake)) != 0) {
					--take;
					if (take < 0) break;
				}
			int newState = state ^ (1 << posTake);
			if (useCard >= 0)
				newState ^= (1 << (12 + who * 2 + useCard));
			rec(newState);
			if (best[who][newState] + x[posTake] > best[who][state]) {
				for (int i = 0; i < 3; ++i)
					best[i][state] = best[i][newState];
				best[who][state] += x[posTake];
				if (who != 1) {
					for (int i = 3; i < 6; ++i)
						best[i][state] = best[i][newState];
					best[3 + who][state] += x[posTake];
				}
			}
			if (who == 1) {
				if (best[3][newState] < best[3][state]) {
					for (int i = 3; i < 6; ++i)
						best[i][state] = best[i][newState];
					best[3 + who][state] += x[posTake];
				}
			}
		}
	}

}
