package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	private static final double EPS = 1e-8;
	private int count;
	double[] probability;
	double[][] win;
	double[][] lose;
	int bytelandMask;
	int berlandMask;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		count = in.readInt();
		double[] byteland = IOUtils.readDoubleArray(in, count);
		double[] berland = IOUtils.readDoubleArray(in, count);
		probability = new double[2 * count];
		for (int i = 0; i < count; i++) {
			probability[2 * i] = byteland[i];
			probability[2 * i + 1] = berland[i];
		}
		win = new double[2 * count + 1][1 << (2 * count)];
		lose = new double[2 * count + 1][1 << (2 * count)];
		ArrayUtils.fill(win, -1);
		for (int i = 0; i < count; i++) {
			bytelandMask += 1 << (2 * i);
			berlandMask += 1 << (2 * i + 1);
		}
		calculate(0, (1 << 2 * count) - 1);
		out.printLine(win[0][(1 << 2 * count) - 1], 1 - lose[0][(1 << 2 * count) - 1] - win[0][(1 << 2 * count) - 1]);
    }

	private void calculate(int step, int mask) {
		if (win[step][mask] != -1)
			return;
		if (step == 2 * count) {
			int byteLand = Integer.bitCount(mask & bytelandMask);
			int berLand = Integer.bitCount(mask & berlandMask);
			win[step][mask] = 0;
			if (byteLand > berLand)
				win[step][mask] = 1;
			else if (berLand > byteLand)
				lose[step][mask] = 1;
			return;
		}
		if ((mask >> step & 1) == 0) {
			calculate(step + 1, mask);
			win[step][mask] = lose[step + 1][mask];
			lose[step][mask] = win[step + 1][mask];
			return;
		}
		win[step][mask] = 0;
		lose[step][mask] = 1;
		calculate(step + 1, mask);
		for (int i = 1 - (step & 1); i < 2 * count; i += 2) {
			if ((mask >> i & 1) == 0)
				continue;
			calculate(step + 1, mask - (1 << i));
			double curWin = lose[step + 1][mask] * (1 - probability[step]) + lose[step + 1][mask - (1 << i)] * probability[step];
			double curLose = win[step + 1][mask] * (1 - probability[step]) + win[step + 1][mask - (1 << i)] * probability[step];
			if (curWin > win[step][mask] + EPS || curWin > win[step][mask] - EPS && curLose < lose[step][mask]) {
				win[step][mask] = curWin;
				lose[step][mask] = curLose;
			}
		}
	}
}
