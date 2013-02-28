package net.egork;

import net.egork.misc.MiscUtils;

public class RobotHerb {
	int direction = 0;
	long x = 0;
	long y = 0;

    public long getdist(int T, int[] a) {
		execute(a);
		execute(a);
		execute(a);
		execute(a);
		long X = T / 4 * x;
		long Y = T / 4 * y;
		x = y = 0;
		for (int i = 0; i < T % 4; i++)
			execute(a);
		return Math.abs(X + x) + Math.abs(Y + y);
    }

	private void execute(int[] program) {
		for (int move : program) {
			x += MiscUtils.DX4[direction] * move;
			y += MiscUtils.DY4[direction] * move;
			direction = (direction + move) & 3;
		}
	}
}
