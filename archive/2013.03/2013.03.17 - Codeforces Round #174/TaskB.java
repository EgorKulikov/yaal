package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	long[] up, down;
	int[] delta;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		delta = IOUtils.readIntArray(in, count - 1);
		up = new long[count - 1];
		down = new long[count - 1];
		for (int i = 0; i < count - 1; i++) {
			long result = goDown(i);
			if (result >= 0)
				out.printLine(result + i + 1);
			else
				out.printLine(-1);
		}
    }

	private long goDown(int position) {
		if (position < -1 || position >= delta.length)
			return 0;
		if (position == -1)
			return -1;
		if (down[position] != 0)
			return down[position];
		down[position] = -1;
		long result = goUp(position - delta[position]);
		if (result != -1)
			down[position] = result + delta[position];
		return down[position];
	}

	private long goUp(int position) {
		if (position < -1 || position >= delta.length)
			return 0;
		if (position == -1)
			return -1;
		if (up[position] != 0)
			return up[position];
		up[position] = -1;
		long result = goDown(position + delta[position]);
		if (result != -1)
			up[position] = result + delta[position];
		return up[position];
	}
}
